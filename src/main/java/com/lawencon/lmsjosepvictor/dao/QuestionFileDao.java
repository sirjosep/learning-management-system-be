package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.QuestionFile;

public interface QuestionFileDao {
	List<QuestionFile> getFileByQuestionId(Long questionId);
	
	QuestionFile insert(QuestionFile questionFile);
}
