package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.model.Learning;
import com.lawencon.lmsjosepvictor.repo.LearningRepo;
@Repository
@Profile("springdatajpa-query")
public class LearningDaoSpringDataJPAImpl implements LearningDao{
	
	private final LearningRepo learningRepo;
	
	LearningDaoSpringDataJPAImpl(LearningRepo learningRepo) {
		this.learningRepo = learningRepo;
	}

	@Override
	public List<Learning> getLearningListsByClassId(Long classId) {
		return learningRepo.getLearningByClassroomId(classId);
	}

	@Override
	public Learning getById(Long learnId) {
		final Learning learning = learningRepo.findById(learnId).get();
		return learning;
	}

	@Override
	public Learning insert(Learning learning) {
		learningRepo.save(learning);
		return learning;
	}
	
	@Override
	public Learning update(Learning learning) {
		final Learning updatedLearning = learningRepo.saveAndFlush(learning);
		return updatedLearning;
	}

	@Override
	public Learning getLearningByClassAndTeacher(Long classId, Long teacherId, LocalDateTime dateStart, LocalDateTime dateEnd) {
		return learningRepo.getLearningByClassAndTeacher(classId, teacherId, dateStart, dateEnd);
	}

}
