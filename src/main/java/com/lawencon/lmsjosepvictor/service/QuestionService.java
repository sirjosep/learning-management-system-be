package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionResDto;

public interface QuestionService {
	List<QuestionResDto> getQuestionByTaskId(Long taskId, boolean isMultipleChoice, boolean isFile, boolean isEssay);
	
	InsertResDto createQuestion(QuestionReqDto data);
}
