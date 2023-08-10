package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionAnswerDao;
import com.lawencon.lmsjosepvictor.model.QuestionAnswer;
@Repository
@Profile("hql-query")
public class QuestionAnswerDaoHQLImpl implements QuestionAnswerDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswer insertAnswer(QuestionAnswer questionAnswer) {
		this.em.persist(questionAnswer);
		return questionAnswer;
	}
	
	@Override
	public List<QuestionAnswer> getAnswerByQuestionAndUser(Long questionId, Long userId) {
		final String sql = "SELECT "
				+ "qa "
				+ "FROM "
				+ "QuestionAnswer qa "
				+ "INNER JOIN "
				+ "Question q ON qa.question.id = q.id "
				+ "INNER JOIN "
				+ "User u ON qa.user.id = u.id "
				+ "LEFT JOIN "
				+ "QuestionAnswerFile qaf ON qaf.questionAnswer.id = qa.id "
				+ "LEFT JOIN "
				+ "File f ON qaf.file.id = f.id "
				+ "WHERE "
				+ "qa.question.id = :questionId AND qa.user.id = :userId";
		final List<QuestionAnswer> answers = this.em.createQuery(sql, QuestionAnswer.class)
				.setParameter("questionId", questionId)
				.setParameter("userId", userId)
				.getResultList();
		
		return answers;
	}

	@Override
	public QuestionAnswer update(QuestionAnswer questionAnswer) {
		final QuestionAnswer updatedAnswer = this.em.merge(questionAnswer);
		this.em.flush();
		return updatedAnswer;
	}

	@Override
	public List<QuestionAnswer> getAnswerByQuestion(Long questionId) {
		final String sql = "SELECT "
				+ "qa "
				+ "FROM "
				+ "QuestionAnswer qa "
				+ "INNER JOIN "
				+ "Question q ON qa.question.id = q.id "
				+ "INNER JOIN "
				+ "User u ON qa.user.id = u.id "
				+ "LEFT JOIN "
				+ "QuestionAnswerFile qaf ON qaf.questionAnswer.id = qa.id "
				+ "LEFT JOIN "
				+ "File f ON qaf.file.id = f.id "
				+ "WHERE "
				+ "qa.question.id = :questionId";
		final List<QuestionAnswer> answers = this.em.createQuery(sql, QuestionAnswer.class)
				.setParameter("questionId", questionId)
				.getResultList();
		
		return answers;
	}

}
