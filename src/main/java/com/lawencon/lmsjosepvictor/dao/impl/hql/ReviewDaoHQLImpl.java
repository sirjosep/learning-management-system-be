package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ReviewDao;
import com.lawencon.lmsjosepvictor.model.Review;
@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class ReviewDaoHQLImpl implements ReviewDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Review insert(Review review) {
		this.em.persist(review);
		return review;
	}

	@Override
	public List<Review> getReviewByTeacherAndTask(Long teacherId, Long taskId) {
		final String sql = "SELECT "
				+ "r "
				+ "FROM "
				+ "Review r "
				+ "WHERE "
				+ "r.teacher.id = :teacherId AND r.learningTask.id = :taskId";
		final List<Review> reviews = this.em.createQuery(sql, Review.class)
				.setParameter("teacherId", teacherId)
				.setParameter("taskId", taskId)
				.getResultList();
		
		return reviews;
	}
	
	@Override
	public Review getScoringForMultipleChoice(Long taskId, Long studentId) {
		final String sql = "SELECT "
				+ "	((100)/( "
				+ "		SELECT "
				+ "			COUNT(DISTINCT q.id) "
				+ "		FROM "
				+ "			Question q "
				+ "		INNER JOIN "
				+ "			LearningTask lt ON q.learningTask.id  = lt.id "
				+ "		INNER JOIN "
				+ "			QuestionOptions qo2 ON qo2.question.id = q.id "
				+ "		WHERE "
				+ "			lt.id = :taskId "
				+ "	))*COUNT(qa.questionOptions.id) "
				+ "FROM "
				+ "	QuestionAnswer qa "
				+ "INNER JOIN "
				+ "	Question q ON qa.question.id = q.id "
				+ "INNER JOIN "
				+ "	QuestionOptions qo ON qa.questionOptions.id = qo.id "
				+ "INNER JOIN "
				+ " User u ON qa.user.id = u.id "
				+ "WHERE "
				+ "	qa.user.id = :studentId AND qo.isCorrect = true "
				+ "GROUP BY "
				+ "	qa.user.id ";
		
		try {
			final Object scoreObj = this.em.createQuery(sql)
					.setParameter("taskId", taskId)
					.setParameter("studentId", studentId)
					.getSingleResult();
			
			final Review review = new Review();
			review.setScore(Float.valueOf(scoreObj.toString()));
			
			return review;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Review getById(Long id) {
		final Review review = this.em.find(Review.class, id);
		return review;
	}

}
