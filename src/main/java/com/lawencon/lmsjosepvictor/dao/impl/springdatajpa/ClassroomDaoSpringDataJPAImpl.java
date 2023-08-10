package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.repo.ClassroomRepo;
@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ClassroomDaoSpringDataJPAImpl implements ClassroomDao{
	
	private final ClassroomRepo classroomRepo;

	ClassroomDaoSpringDataJPAImpl(ClassroomRepo classroomRepo) {
		this.classroomRepo = classroomRepo;
	}

	@Override
	public List<Classroom> getEnrolledClassByStudId(Long studId){
		return classroomRepo.getEnrolledClassByStudId(studId);
	}
	
	@Override
	public List<Classroom> getUnenrolledClassByStudId(Long studId){
		return classroomRepo.getUnenrolledClassByStudId(studId);
	}

	@Override
	public Classroom getClassroomByClassCode(String classCode){
		return classroomRepo.getClassroomByClassCode(classCode);
	}

	@Override
	public List<Classroom> getClassByTeacherId(Long teacherId) {
		return classroomRepo.getClassroomByTeacherId(teacherId);
	}

	@Override
	public Classroom getById(Long id) {
		final Classroom classroom = classroomRepo.findById(id).get();
		return classroom;
	}

	@Override
	public Classroom insert(Classroom classroom) {
		classroomRepo.save(classroom);
		return classroom;
	}

	@Override
	public List<Classroom> getAll() {
		return classroomRepo.findAll();
	}

}
