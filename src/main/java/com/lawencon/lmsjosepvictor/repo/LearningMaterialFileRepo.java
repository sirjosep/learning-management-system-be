package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.LearningMaterialFile;

@Repository
public interface LearningMaterialFileRepo extends JpaRepository<LearningMaterialFile, Long>{
	List<LearningMaterialFile> getLearningMaterialFileByLearningMaterialId(Long materialId);
}
