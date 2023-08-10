package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionOptionDao;
import com.lawencon.lmsjosepvictor.model.QuestionOptions;
@Repository
@Profile("native-query")
public class QuestionOptionDaoImpl implements QuestionOptionDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionOptions> getOptionsByQuestionId(Long questionId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_options "
				+ "WHERE "
				+ "question_id = :questionId";
		final List<QuestionOptions> questionAnswers = this.em.createNativeQuery(sql, QuestionOptions.class)
				.setParameter("questionId", questionId)
				.getResultList();
		
		return questionAnswers;
	}

	@Override
	public QuestionOptions insert(QuestionOptions questionOptions) {
		this.em.persist(questionOptions);
		return questionOptions;
	}

	@Override
	public QuestionOptions getById(Long id) {
		final QuestionOptions options = this.em.find(QuestionOptions.class, id);
		
		return options;
	}

	@Override
	public QuestionOptions getByIdRef(Long id) {
		final QuestionOptions options = this.em.getReference(QuestionOptions.class, id);
		
		return options;
	}
}
