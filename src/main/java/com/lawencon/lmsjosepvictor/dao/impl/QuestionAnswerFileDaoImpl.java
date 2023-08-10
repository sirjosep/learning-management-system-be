package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionAnswerFileDao;
import com.lawencon.lmsjosepvictor.model.QuestionAnswerFile;
@Repository
@Profile("native-query")
public class QuestionAnswerFileDaoImpl implements QuestionAnswerFileDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswerFile insert(QuestionAnswerFile questionAnswerFile) {
		this.em.persist(questionAnswerFile);
		return questionAnswerFile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionAnswerFile> getAnswerFile(Long questionAnswerId) {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	t_question_answer_file tqaf "
				+ "INNER JOIN "
				+ "	t_question_answer tqa ON tqaf.question_answer_id = tqa.id "
				+ "INNER JOIN "
				+ "	t_file tf ON tqaf.file_id = tf.id "
				+ "WHERE "
				+ "	tqaf.question_answer_id = :questionAnswerId";
		final List<QuestionAnswerFile> getAnswerFiles = this.em.createNativeQuery(sql, QuestionAnswerFile.class)
				.setParameter("questionAnswerId", questionAnswerId)
				.getResultList();
		
		return getAnswerFiles;
	}

}
