package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionAnswerDao;
import com.lawencon.lmsjosepvictor.model.QuestionAnswer;
import com.lawencon.lmsjosepvictor.repo.QuestionAnswerRepo;
@Repository
@Profile("springdatajpa-query")
public class QuestionAnswerDaoSpringDataJPAImpl implements QuestionAnswerDao{
	
	private final QuestionAnswerRepo questionAnswerRepo;
	
	QuestionAnswerDaoSpringDataJPAImpl(QuestionAnswerRepo questionAnswerRepo) {
		this.questionAnswerRepo = questionAnswerRepo;
	}

	@Override
	public QuestionAnswer insertAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswerRepo.save(questionAnswer);
		return questionAnswer;
	}

	@Override
	public List<QuestionAnswer> getAnswerByQuestionAndUser(Long questionId, Long userId) {
		return questionAnswerRepo.getQuestionAnswerByQuestionIdAndUserId(questionId, userId);
	}

	@Override
	public QuestionAnswer update(QuestionAnswer questionAnswer) {
		final QuestionAnswer updatedAnswer = this.questionAnswerRepo.saveAndFlush(questionAnswer);
		return updatedAnswer;
	}

	@Override
	public List<QuestionAnswer> getAnswerByQuestion(Long questionId) {
		return questionAnswerRepo.getQuestionAnswerByQuestionId(questionId);
	}

}
