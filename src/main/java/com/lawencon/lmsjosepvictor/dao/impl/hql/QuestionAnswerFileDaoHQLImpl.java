package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionAnswerFileDao;
import com.lawencon.lmsjosepvictor.model.QuestionAnswerFile;
@Repository
@Profile("hql-query")
public class QuestionAnswerFileDaoHQLImpl implements QuestionAnswerFileDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswerFile insert(QuestionAnswerFile questionAnswerFile) {
		this.em.persist(questionAnswerFile);
		return questionAnswerFile;
	}

	@Override
	public List<QuestionAnswerFile> getAnswerFile(Long questionAnswerId) {
		final String sql = "SELECT "
				+ "qaf "
				+ "FROM "
				+ "QuestionAnswerFile qaf "
				+ "WHERE "
				+ "qaf.questionAnswer.id = :questionAnswerId";
		final List<QuestionAnswerFile> getAnswerFiles = this.em.createQuery(sql, QuestionAnswerFile.class)
				.setParameter("questionAnswerId", questionAnswerId)
				.getResultList();
		
		return getAnswerFiles;
	}

}
