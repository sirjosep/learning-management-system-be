package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.LearningTaskReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.LearningTaskResDto;

public interface LearningTaskService {
	List<LearningTaskResDto> getTaskByLearningId(Long learnId);
	
	InsertResDto createTask(LearningTaskReqDto data);
	LearningTaskResDto getById(Long id);
}
