package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long>{
	Attendance getAttendanceByStudentIdAndLearningId(Long studId, Long learnId);
	
	List<Attendance> getAttendanceByLearningId(Long learnId);
}
