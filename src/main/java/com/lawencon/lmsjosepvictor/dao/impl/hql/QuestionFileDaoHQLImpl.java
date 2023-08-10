package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionFileDao;
import com.lawencon.lmsjosepvictor.model.QuestionFile;
@Repository
@Profile("hql-query")
public class QuestionFileDaoHQLImpl implements QuestionFileDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<QuestionFile> getFileByQuestionId(Long questionId) {
		final String sql = "SELECT "
				+ "qf "
				+ "FROM "
				+ "QuestionFile qf "
				+ "WHERE "
				+ "qf.question.id = :questionId";
		final List<QuestionFile> questionFiles = this.em.createQuery(sql, QuestionFile.class)
				.setParameter("questionId", questionId)
				.getResultList();
		
		return questionFiles;
	}

	@Override
	public QuestionFile insert(QuestionFile questionFile) {
		this.em.persist(questionFile);
		return questionFile;
	}

}
