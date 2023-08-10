package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.EnrollClassDao;
import com.lawencon.lmsjosepvictor.model.EnrollClass;
import com.lawencon.lmsjosepvictor.repo.EnrollClassRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class EnrollClassDaoSpringDataJPAImpl implements EnrollClassDao {

	private final EnrollClassRepo enrollClassRepo;

	EnrollClassDaoSpringDataJPAImpl(EnrollClassRepo enrollClassRepo) {
		this.enrollClassRepo = enrollClassRepo;
	}

	@Override
	public EnrollClass insert(EnrollClass enrollClass) {
		enrollClassRepo.save(enrollClass);
		return enrollClass;
	}

	@Override
	public EnrollClass getById(Long id) {
		final EnrollClass enrollClass = enrollClassRepo.findById(id).get();
		return enrollClass;
	}

	@Override
	public List<EnrollClass> getAllByClass(Long classId) {
		return enrollClassRepo.getEnrollClassByClassroomId(classId);
	}

}
