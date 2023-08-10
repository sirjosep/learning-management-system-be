package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionDao;
import com.lawencon.lmsjosepvictor.model.Question;

@Repository
@Profile("native-query")
public class QuestionDaoImpl implements QuestionDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionByTaskId(Long taskId) {
		final String sql = "SELECT "
					+ "* " 
					+ "FROM " 
					+ "t_question " 
					+ "WHERE " 
					+ "learning_task_id = :taskId";

		final List<Question> questions = this.em.createNativeQuery(sql, Question.class).setParameter("taskId", taskId)
				.getResultList();

		return questions;
	}

	@Override
	public Question getById(Long id) {
		final Question question = this.em.find(Question.class, id);
		return question;
	}

	@Override
	public Question insert(Question question) {
		this.em.persist(question);
		return question;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionWithOptions(Long taskId) {
		final String sql = "SELECT " 
					+ "* " 
					+ "FROM " 
					+ "t_question tq " 
					+ "INNER JOIN "
					+ "t_question_options tqo ON tqo.question_id = tq.id " 
					+ "WHERE " 
					+ "tq.learning_task_id = :taskId ";

		final List<Question> questions = this.em.createNativeQuery(sql, Question.class).setParameter("taskId", taskId)
				.getResultList();

		return questions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionWithFile(Long taskId) {
		final String sql = "SELECT " 
					+ "tq.id, tq.* " 
					+ "FROM " 
					+ "t_question tq " 
					+ "INNER JOIN "
					+ "t_question_file tqf ON tqf.question_id = tq.id " 
					+ "WHERE " 
					+ "tq.learning_task_id = :taskId "
					+ "GROUP BY " 
					+ "tq.id ";

		final List<Question> questions = this.em.createNativeQuery(sql, Question.class).setParameter("taskId", taskId)
				.getResultList();

		return questions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getEssayQuestion(Long taskId) {
		final String sql = "SELECT " 
					+ "	* " 
					+ "FROM " 
					+ "	t_question tq " 
					+ "LEFT JOIN "
					+ "t_question_file tqf ON tqf.question_id = tq.id " 
					+ "LEFT JOIN "
					+ "t_question_options tqo ON tqo.question_id = tq.id " 
					+ "WHERE " 
					+ "	tq.learning_task_id = :taskId  "
					+ "AND " 
					+ "		tq.id NOT IN " 
					+ "			( " 
					+ "				SELECT "
					+ "					tqf2.question_id " 
					+ "				FROM "
					+ "					t_question_file tqf2" 
					+ "			) " 
					+ "	AND " 
					+ "		tq.id NOT IN "
					+ "			( " 
					+ "				SELECT " 
					+ "					tqo.question_id "
					+ "				FROM " 
					+ "					t_question_options tqo " 
					+ "			)";

		final List<Question> questions = this.em.createNativeQuery(sql, Question.class).setParameter("taskId", taskId)
				.getResultList();

		return questions;
	}

}
