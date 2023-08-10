package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.LearningMaterial;

public interface LearningMaterialDao {
	List<LearningMaterial> getMaterialByLearnId(Long learnId);
	
	LearningMaterial insert(LearningMaterial learningMaterial);
}
