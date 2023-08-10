package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionOptionDao;
import com.lawencon.lmsjosepvictor.model.QuestionOptions;
@Repository
@Profile("hql-query")
public class QuestionOptionDaoHQLImpl implements QuestionOptionDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<QuestionOptions> getOptionsByQuestionId(Long questionId) {
		final String sql = "SELECT "
				+ "qo "
				+ "FROM "
				+ "QuestionOptions qo "
				+ "WHERE "
				+ "qo.question.id = :questionId";
		final List<QuestionOptions> questionOptions = this.em.createQuery(sql, QuestionOptions.class)
				.setParameter("questionId", questionId)
				.getResultList();
		
		return questionOptions;
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
