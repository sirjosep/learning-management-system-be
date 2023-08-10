package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.Question;

public interface QuestionDao {
	List<Question> getQuestionByTaskId(Long taskId);
	List<Question> getQuestionWithOptions(Long taskId);
	List<Question> getQuestionWithFile(Long taskId);
	List<Question> getEssayQuestion(Long taskId);
	
	Question getById(Long id);
	Question insert(Question question);
}
