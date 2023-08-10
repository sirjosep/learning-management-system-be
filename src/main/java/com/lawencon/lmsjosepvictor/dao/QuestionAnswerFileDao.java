package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.QuestionAnswerFile;

public interface QuestionAnswerFileDao {
	QuestionAnswerFile insert(QuestionAnswerFile questionAnswerFile);
	
	List<QuestionAnswerFile> getAnswerFile(Long questionAnswerId);
}
