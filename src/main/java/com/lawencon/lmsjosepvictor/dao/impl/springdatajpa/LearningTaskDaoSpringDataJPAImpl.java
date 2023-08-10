package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningTaskDao;
import com.lawencon.lmsjosepvictor.model.LearningTask;
import com.lawencon.lmsjosepvictor.repo.LearningTaskRepo;
@Repository
@Profile("springdatajpa-query")
public class LearningTaskDaoSpringDataJPAImpl implements LearningTaskDao{
	
	private final LearningTaskRepo learningTaskRepo;
	
	LearningTaskDaoSpringDataJPAImpl(LearningTaskRepo learningTaskRepo) {
		this.learningTaskRepo = learningTaskRepo;
	}

	@Override
	public List<LearningTask> getTaskByLearningId(Long learnId) {
		return learningTaskRepo.getLearningTaskByLearningId(learnId);
	}

	@Override
	public LearningTask insert(LearningTask learningTask) {
		learningTaskRepo.save(learningTask);
		return learningTask;
	}

	@Override
	public LearningTask getById(Long id) {
		final LearningTask learningTask = learningTaskRepo.findById(id).get();
		return learningTask;
	}

}
