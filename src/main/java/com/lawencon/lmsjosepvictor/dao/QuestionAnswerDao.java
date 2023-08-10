package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.QuestionAnswer;

public interface QuestionAnswerDao {
	QuestionAnswer insertAnswer(QuestionAnswer questionAnswer);
	QuestionAnswer update(QuestionAnswer questionAnswer);
	
	List<QuestionAnswer> getAnswerByQuestionAndUser(Long questionId, Long userId);
	List<QuestionAnswer> getAnswerByQuestion(Long questionId);
}
