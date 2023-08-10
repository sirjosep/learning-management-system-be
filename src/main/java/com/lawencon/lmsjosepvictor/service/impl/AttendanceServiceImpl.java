package com.lawencon.lmsjosepvictor.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.AttendanceDao;
import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceReqDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceResDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceUpdateReqDto;
import com.lawencon.lmsjosepvictor.exception.CustomException;
import com.lawencon.lmsjosepvictor.model.Attendance;
import com.lawencon.lmsjosepvictor.model.Learning;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.AttendanceService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.util.DateUtil;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	private final AttendanceDao attendanceDao;
	private final UserDao userDao;
	private final LearningDao learningDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	public AttendanceServiceImpl(AttendanceDao attendanceDao, UserDao userDao, 
			LearningDao learningDao, PrincipalService principalService) {
		this.attendanceDao = attendanceDao;
		this.userDao = userDao;
		this.learningDao = learningDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto createAttendance(AttendanceReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		if (attendanceDao.getByUserAndLearning(principalService.getId(), data.getLearnId()) == null) {
			final Attendance attendance = new Attendance();
			
			final User student = userDao.getById(principalService.getId());
			
			final Learning learning = learningDao.getById(data.getLearnId());
			
			attendance.setStudent(student);
			attendance.setLearning(learning);
			attendance.setAttendanceTime(LocalDateTime.now());
			attendance.setIsApproved(false);
			attendance.setCreatedBy(principalService.getId());
			
			attendanceDao.insert(attendance);
			
			response.setId(attendance.getId());
			response.setMessage("Attendance success!");
		} else {
			throw new CustomException("Erorr! You already attend this learning");
		}
		
		return response;
	}

	@Transactional
	@Override
	public UpdateResDto approveAttendance(AttendanceUpdateReqDto datas) {
		final UpdateResDto response = new UpdateResDto();
		
		datas.getId().forEach(d -> {
			final Attendance approvedAttend = attendanceDao.getById(d);
			approvedAttend.setIsApproved(true);
			approvedAttend.setUpdatedBy(principalService.getId());
			
		});
		
		response.setMessage("Approved Successfully!");
		
		return response;
	}


	@Override
	public List<AttendanceResDto> getAllByLearning(Long learnId) {
		final List<AttendanceResDto> responses = new ArrayList<>();
		
		attendanceDao.getAllByLearning(learnId).forEach(a -> {
			final AttendanceResDto response = new AttendanceResDto();
			response.setId(a.getId());
			response.setProfileName(a.getStudent().getProfile().getProfileName());
			response.setAttendanceTime(DateUtil.dateFormat(a.getAttendanceTime()));
			response.setIsApprove(a.getIsApproved());
			
			responses.add(response);
		});
		
		return responses;
	}

	@Override
	public AttendanceResDto getAttendanceByUserAndLearningId(Long learnId) {
		final AttendanceResDto response = new AttendanceResDto();
		
		final Attendance resAttend = attendanceDao.getByUserAndLearning(principalService.getId(), learnId);
		
		if(resAttend != null) {
			response.setIsApprove(resAttend.getIsApproved());
		}
		return response;
	}

}
