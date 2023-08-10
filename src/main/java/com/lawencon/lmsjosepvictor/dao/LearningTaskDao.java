package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.LearningTask;

public interface LearningTaskDao {
	List<LearningTask> getTaskByLearningId(Long learnId);
	
	LearningTask insert(LearningTask learningTask);
	LearningTask getById(Long id);
}
