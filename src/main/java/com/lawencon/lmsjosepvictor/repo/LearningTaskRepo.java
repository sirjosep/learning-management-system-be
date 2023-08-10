package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.LearningTask;

@Repository
public interface LearningTaskRepo extends JpaRepository<LearningTask, Long>{
	List<LearningTask> getLearningTaskByLearningId(Long learnId);
}
