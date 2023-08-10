package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionAnswerDao;
import com.lawencon.lmsjosepvictor.model.QuestionAnswer;
@Repository
@Profile("native-query")
public class QuestionAnswerDaoImpl implements QuestionAnswerDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswer insertAnswer(QuestionAnswer questionAnswer) {
		this.em.persist(questionAnswer);
		return questionAnswer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswer> getAnswerByQuestionAndUser(Long questionId, Long userId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_answer tqa "
				+ "INNER JOIN "
				+ "t_question tq ON tqa.question_id = tq.id "
				+ "INNER JOIN "
				+ "t_user tu ON tqa.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "LEFT JOIN "
				+ "t_question_answer_file tqaf ON tqaf.question_answer_id = tqa.id "
				+ "LEFT JOIN "
				+ "t_file tf ON tqaf.file_id = tf.id "
				+ "WHERE "
				+ "tqa.question_id = :questionId AND tqa.user_id = :userId";
		
		final List<QuestionAnswer> answers = this.em.createNativeQuery(sql, QuestionAnswer.class)
				.setParameter("questionId", questionId)
				.setParameter("userId", userId)
				.getResultList();
		
		return answers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswer> getAnswerByQuestion(Long questionId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_answer tqa "
				+ "INNER JOIN "
				+ "t_question tq ON tqa.question_id = tq.id "
				+ "INNER JOIN "
				+ "t_user tu ON tqa.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "LEFT JOIN "
				+ "t_question_answer_file tqaf ON tqaf.question_answer_id = tqa.id "
				+ "LEFT JOIN "
				+ "t_file tf ON tqaf.file_id = tf.id "
				+ "WHERE "
				+ "tqa.question_id = :questionId";
		
		final List<QuestionAnswer> answers = this.em.createNativeQuery(sql, QuestionAnswer.class)
				.setParameter("questionId", questionId)
				.getResultList();
		
		return answers;
	}

	@Override
	public QuestionAnswer update(QuestionAnswer questionAnswer) {
		final QuestionAnswer updatedAnswer = this.em.merge(questionAnswer);
		this.em.flush();
		return updatedAnswer;
	}

}
