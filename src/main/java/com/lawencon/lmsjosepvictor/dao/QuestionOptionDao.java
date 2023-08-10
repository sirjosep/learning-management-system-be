package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.QuestionOptions;

public interface QuestionOptionDao {
	List<QuestionOptions> getOptionsByQuestionId(Long questionId);
	
	QuestionOptions insert(QuestionOptions questionOptions);
	
	QuestionOptions getById(Long id);
	QuestionOptions getByIdRef(Long id);
}
