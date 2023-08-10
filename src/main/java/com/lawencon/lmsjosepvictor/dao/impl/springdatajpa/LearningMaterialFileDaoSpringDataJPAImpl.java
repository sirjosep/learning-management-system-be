package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningMaterialFileDao;
import com.lawencon.lmsjosepvictor.model.LearningMaterialFile;
import com.lawencon.lmsjosepvictor.repo.LearningMaterialFileRepo;
@Repository
@Profile("springdatajpa-query")
public class LearningMaterialFileDaoSpringDataJPAImpl implements LearningMaterialFileDao{
	
	private final LearningMaterialFileRepo learningMaterialFileRepo;
	
	LearningMaterialFileDaoSpringDataJPAImpl(LearningMaterialFileRepo learningMaterialFileRepo) {
		this.learningMaterialFileRepo = learningMaterialFileRepo;
	}

	@Override
	public List<LearningMaterialFile> getMaterialFileByMaterialId(Long materialId) {
		return learningMaterialFileRepo.getLearningMaterialFileByLearningMaterialId(materialId);
	}

	@Override
	public LearningMaterialFile insert(LearningMaterialFile learningFileMaterial) {
		learningMaterialFileRepo.save(learningFileMaterial);
		return learningFileMaterial;
	}

}
