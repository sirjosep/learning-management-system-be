package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.answer.QuestionAnswerReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.answer.QuestionAnswerResDto;

public interface QuestionAnswerService {
	InsertResDto addAnswer(QuestionAnswerReqDto data);
	
	List<QuestionAnswerResDto> getAnswerByQuestionAndUser(Long questionId, Long userId);
	List<QuestionAnswerResDto> getAnswerByQuestion(Long questionId);
}