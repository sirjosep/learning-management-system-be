package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionDao;
import com.lawencon.lmsjosepvictor.model.Question;
@Repository
@Profile("hql-query")
public class QuestionDaoHQLImpl implements QuestionDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Question> getQuestionByTaskId(Long taskId) {
		final String sql = "SELECT "
				+ "q "
				+ "FROM "
				+ "Question q "
				+ "WHERE "
				+ "q.learningTask.id = :taskId";
		
		final List<Question> questions = this.em.createQuery(sql, Question.class)
				.setParameter("taskId", taskId)
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

	@Override
	public List<Question> getQuestionWithOptions(Long taskId) {
		final String sql = "SELECT "
				+ "q "
				+ "FROM "
				+ "Question q "
				+ "INNER JOIN "
				+ "QuestionOptions qo ON qo.question.id = q.id "
				+ "WHERE "
				+ "q.learningTask.id = :taskId "
				+ "GROUP BY "
				+ "q.id ";
		
		final List<Question> questions = this.em.createQuery(sql, Question.class)
				.setParameter("taskId", taskId)
				.getResultList();
		
		return questions;
	}

	@Override
	public List<Question> getQuestionWithFile(Long taskId) {
		final String sql = "SELECT "
				+ "q "
				+ "FROM "
				+ "Question q "
				+ "INNER JOIN "
				+ "QuestionFile qf ON qf.question.id = q.id "
				+ "WHERE "
				+ "q.learningTask.id = :taskId ";
		
		
		final List<Question> questions = this.em.createQuery(sql, Question.class)
				.setParameter("taskId", taskId)
				.getResultList();
		
		return questions;
	}

	@Override
	public List<Question> getEssayQuestion(Long taskId) {
		final String sql = "SELECT "
				+ "q "
				+ "FROM "
				+ "Question q "
				+ "LEFT JOIN "
				+ "QuestionFile qf ON qf.question.id = q.id "
				+ "LEFT JOIN "
				+ "QuestionOptions qo ON qo.question.id = q.id "
				+ "WHERE "
				+ "q.learningTask.id = :taskId "
				+ "AND "
				+ "		q.id NOT IN "
				+ "			( "
				+ "				SELECT "
				+ "					qf2.question.id "
				+ "				FROM "
				+ "					QuestionFile qf2 "
				+ "			) "
				+ "	AND "
				+ "		q.id NOT IN "
				+ "			( "
				+ "				SELECT "
				+ "					qo2.question.id "
				+ "				FROM "
				+ "					QuestionOptions qo2 "
				+ "			)";
		
		
		final List<Question> questions = this.em.createQuery(sql, Question.class)
				.setParameter("taskId", taskId)
				.getResultList();
		
		return questions;
	}

}
