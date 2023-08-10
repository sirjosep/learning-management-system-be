package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.dao.EnrollClassDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.enrollclass.EnrollClassReqDto;
import com.lawencon.lmsjosepvictor.dto.enrollclass.EnrollClassResDto;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.model.EnrollClass;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.EnrollClassroomService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;

@Service
public class EnrollClassroomServiceImpl implements EnrollClassroomService{

	private final EnrollClassDao enrollClassDao;
	private final UserDao userDao;
	private final ClassroomDao classroomDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;
	public EnrollClassroomServiceImpl(EnrollClassDao enrollClassDao, UserDao userDao, 
			ClassroomDao classroomDao, PrincipalService principalService) {
		this.enrollClassDao = enrollClassDao;
		this.userDao = userDao;
		this.classroomDao = classroomDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(EnrollClassReqDto data) {
		final InsertResDto response = new InsertResDto();
		final EnrollClass enrollClass = new EnrollClass();
		
		final User student = userDao.getById(principalService.getId());
		
		final Classroom classroom = classroomDao.getById(data.getClassId());
		
		enrollClass.setStudent(student);
		enrollClass.setClassroom(classroom);
		enrollClass.setCreatedBy(principalService.getId());
		
		enrollClassDao.insert(enrollClass);
		
		response.setId(enrollClass.getId());
		response.setMessage("Class Enrolled Successfully");
		
		return response;
	}

	@Override
	public List<EnrollClassResDto> getAllByClass(Long classId) {
		final List<EnrollClassResDto> responses = new ArrayList<>();
		
		enrollClassDao.getAllByClass(classId).forEach(ec -> {
			final EnrollClassResDto response = new EnrollClassResDto();
			response.setId(ec.getId());
			response.setStudId(ec.getStudent().getId());
			response.setClassId(ec.getClassroom().getId());
			
			responses.add(response);
		});
		
		return responses;
	}

	@Override
	public EnrollClassResDto getById(Long id) {
		final EnrollClass enrollClass = enrollClassDao.getById(id);
		final EnrollClassResDto response = new EnrollClassResDto();
		response.setId(enrollClass.getId());
		response.setStudId(enrollClass.getStudent().getId());
		response.setClassId(enrollClass.getClassroom().getId());
		
		return response;
	}

}
