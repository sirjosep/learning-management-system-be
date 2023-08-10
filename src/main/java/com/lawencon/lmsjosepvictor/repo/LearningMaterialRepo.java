package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.LearningMaterial;

@Repository
public interface LearningMaterialRepo extends JpaRepository<LearningMaterial, Long>{
	List<LearningMaterial> getLearningMaterialByLearningId(Long learnId);
}
