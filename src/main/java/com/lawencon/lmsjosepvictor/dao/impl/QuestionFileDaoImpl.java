package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionFileDao;
import com.lawencon.lmsjosepvictor.model.QuestionFile;
@Repository
@Profile("native-query")
public class QuestionFileDaoImpl implements QuestionFileDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionFile> getFileByQuestionId(Long questionId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_file tqf "
				+ "INNER JOIN "
				+ "t_file tf ON tqf.file_id = tf.id "
				+ "WHERE "
				+ "tqf.question_id = :questionId";
		final List<QuestionFile> questionFiles = this.em.createNativeQuery(sql, QuestionFile.class)
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
