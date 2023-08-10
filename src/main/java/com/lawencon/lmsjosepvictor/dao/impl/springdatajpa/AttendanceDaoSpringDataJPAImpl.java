package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.AttendanceDao;
import com.lawencon.lmsjosepvictor.model.Attendance;
import com.lawencon.lmsjosepvictor.repo.AttendanceRepo;

@Repository
@Profile("springdatajpa-query")
public class AttendanceDaoSpringDataJPAImpl implements AttendanceDao{
	
	private final AttendanceRepo attendanceRepo;

	AttendanceDaoSpringDataJPAImpl(AttendanceRepo attendanceRepo) {
		this.attendanceRepo = attendanceRepo;
	}

	@Override
	public Attendance insert(Attendance attendance) {
		attendanceRepo.save(attendance);
		return attendance;
	}

	@Override
	public Attendance getByUserAndLearning(Long studId, Long learnId) {
		return attendanceRepo.getAttendanceByStudentIdAndLearningId(studId, learnId);
	}

	@Override
	public List<Attendance> getAllByLearning(Long learnId) {
		return attendanceRepo.getAttendanceByLearningId(learnId);
	}

	@Override
	public Attendance getById(Long id) {
		return attendanceRepo.findById(id).get();
	}

}
