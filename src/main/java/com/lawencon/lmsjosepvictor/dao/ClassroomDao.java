package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.Classroom;

public interface ClassroomDao {
	List<Classroom> getEnrolledClassByStudId(Long studId);
	List<Classroom> getUnenrolledClassByStudId(Long studId);
	List<Classroom> getClassByTeacherId(Long teacherId);
	List<Classroom> getAll();
	
	Classroom insert(Classroom classroom);
	Classroom getClassroomByClassCode(String classCode);
	Classroom getById(Long id);
}
