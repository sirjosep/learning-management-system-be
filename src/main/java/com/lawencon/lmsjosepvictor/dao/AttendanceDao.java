package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.Attendance;

public interface AttendanceDao {
	Attendance insert(Attendance attendance) ;
	Attendance getByUserAndLearning(Long studId, Long learnId) ;
	Attendance getById(Long id) ;
	
	List<Attendance> getAllByLearning(Long learnId) ;
}
