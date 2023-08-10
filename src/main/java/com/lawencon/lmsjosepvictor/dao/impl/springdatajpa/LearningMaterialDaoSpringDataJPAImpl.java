package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningMaterialDao;
import com.lawencon.lmsjosepvictor.model.LearningMaterial;
import com.lawencon.lmsjosepvictor.repo.LearningMaterialRepo;
@Repository
@Profile("springdatajpa-query")
public class LearningMaterialDaoSpringDataJPAImpl implements LearningMaterialDao{
	
	private final LearningMaterialRepo learningMaterialRepo;
	
	LearningMaterialDaoSpringDataJPAImpl(LearningMaterialRepo learningMaterialRepo) {
		this.learningMaterialRepo = learningMaterialRepo;
	}

	@Override
	public List<LearningMaterial> getMaterialByLearnId(Long learnId) {
		return learningMaterialRepo.getLearningMaterialByLearningId(learnId);
	}

	@Override
	public LearningMaterial insert(LearningMaterial learningMaterial) {
		learningMaterialRepo.save(learningMaterial);
		return learningMaterial;
	}

}
