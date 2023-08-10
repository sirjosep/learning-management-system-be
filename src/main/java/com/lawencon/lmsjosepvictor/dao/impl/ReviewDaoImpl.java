package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ReviewDao;
import com.lawencon.lmsjosepvictor.model.Review;
@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ReviewDaoImpl implements ReviewDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Review insert(Review review) {
		this.em.persist(review);
		return review;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getReviewByTeacherAndTask(Long teacherId, Long taskId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_review tr "
				+ "INNER JOIN "
				+ "t_user stud ON tr.student_id = stud.id "
				+ "INNER JOIN "
				+ "t_profile studprof ON stud.profile_id = studprof.id "
				+ "INNER JOIN "
				+ "t_user teacher ON tr.teacher_id = teacher.id "
				+ "INNER JOIN "
				+ "t_profile teachprof ON teacher.profile_id = teachprof.id "
				+ "WHERE "
				+ "tr.teacher_id = :teacherId AND tr.learning_task_id = :taskId";
		final List<Review> reviews = this.em.createNativeQuery(sql, Review.class)
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
				+ "			COUNT(DISTINCT tq.id) "
				+ "		FROM "
				+ "			t_question tq "
				+ "		INNER JOIN "
				+ "			t_learning_task tlt ON tq.learning_task_id  = tlt.id "
				+ "		INNER JOIN "
				+ "			t_question_options tqo2 ON tqo2.question_id = tq.id "
				+ "		WHERE "
				+ "			tlt.id = :taskId "
				+ "	))*COUNT(tqa.question_options_id) "
				+ "FROM "
				+ "	t_question_answer tqa "
				+ "INNER JOIN "
				+ "	t_question tq ON tqa.question_id = tq.id "
				+ "INNER JOIN "
				+ "	t_question_options tqo ON tqa.question_options_id = tqo.id "
				+ "INNER JOIN "
				+ "	t_user tu ON tqa.user_id = tu.id "
				+ "WHERE "
				+ "	tqa.user_id = :studentId AND tqo.is_correct = true "
				+ "GROUP BY "
				+ "	tqa.user_id ";
		
		try {
			final Object scoreObj = this.em.createNativeQuery(sql)
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
