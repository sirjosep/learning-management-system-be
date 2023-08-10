package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.LearningReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.LearningResDto;

public interface LearningService {
	List<LearningResDto> getLearningListsByClassId(Long classId);

	LearningResDto getLearningById(Long learnId);

	InsertResDto createLearning(LearningReqDto data);
}
