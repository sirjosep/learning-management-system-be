package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionDao;
import com.lawencon.lmsjosepvictor.model.Question;
import com.lawencon.lmsjosepvictor.repo.QuestionRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionDaoSpringDataJPAImpl implements QuestionDao {

	private final QuestionRepo questionRepo;

	QuestionDaoSpringDataJPAImpl(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	@Override
	public List<Question> getQuestionByTaskId(Long taskId) {
		return questionRepo.getQuestionByLearningTaskId(taskId);
	}

	@Override
	public Question getById(Long id) {
		final Question question = questionRepo.findById(id).get();
		return question;
	}

	@Override
	public Question insert(Question question) {
		this.questionRepo.save(question);
		return question;
	}

	@Override
	public List<Question> getQuestionWithOptions(Long taskId) {
		return questionRepo.getQuestionWithOptions(taskId);
	}

	@Override
	public List<Question> getQuestionWithFile(Long taskId) {
		return questionRepo.getQuestionWithFile(taskId);
	}

	@Override
	public List<Question> getEssayQuestion(Long taskId) {
		return questionRepo.getEssayQuestion(taskId);
	}

}
