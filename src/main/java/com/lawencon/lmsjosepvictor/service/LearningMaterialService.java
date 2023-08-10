package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.material.LearningMaterialReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.material.LearningMaterialResDto;

public interface LearningMaterialService {
	List<LearningMaterialResDto> getMaterialByLearnId(Long learnId);
	
	InsertResDto insert(LearningMaterialReqDto data);
}
