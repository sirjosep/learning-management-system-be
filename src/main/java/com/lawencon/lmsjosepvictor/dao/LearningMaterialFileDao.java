package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.LearningMaterialFile;

public interface LearningMaterialFileDao {
	List<LearningMaterialFile> getMaterialFileByMaterialId(Long materialId);
	
	LearningMaterialFile insert(LearningMaterialFile learningFileMaterial);
}
