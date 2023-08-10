package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.constant.RoleCode;
import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.classroom.ClassroomReqDto;
import com.lawencon.lmsjosepvictor.dto.classroom.ClassroomResDto;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.ClassroomService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;

@Service
public class ClassroomServiceImpl implements ClassroomService{
	
	private final ClassroomDao classroomDao;
	private final UserDao userDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;
	public ClassroomServiceImpl(ClassroomDao classroomDao, UserDao userDao, FileDao fileDao, PrincipalService principalService) {
		this.classroomDao = classroomDao;
		this.userDao = userDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Override
	public List<ClassroomResDto> getAll() {
		final List<ClassroomResDto> responses = new ArrayList<>();
		
		List<Classroom> classrooms = null;
		if(RoleCode.STUDENT.roleCode.equals(principalService.getRoleCode())) {
			classrooms = classroomDao.getEnrolledClassByStudId(principalService.getId());
		} else if (RoleCode.TEACHER.roleCode.equals(principalService.getRoleCode())) {
			classrooms = classroomDao.getClassByTeacherId(principalService.getId());
		} else {
			classrooms = classroomDao.getAll();
		}
		
		classrooms.forEach(c -> {
			final ClassroomResDto response = new ClassroomResDto();
			response.setId(c.getId());
			response.setClassCode(c.getClassCode());
			response.setClassName(c.getClassName());
			response.setTeacherName(c.getTeacher().getProfile().getProfileName());
			response.setFileId(c.getClassThumbFile().getId());
			
			responses.add(response);
		});
		return responses;
	}
	
	@Override
	public List<ClassroomResDto> getUnenrolledClassByStudId() {
		final List<ClassroomResDto> responses = new ArrayList<>();
		
		classroomDao.getUnenrolledClassByStudId(principalService.getId()).forEach(c -> {
			final ClassroomResDto response = new ClassroomResDto();
			response.setId(c.getId());
			response.setClassCode(c.getClassCode());
			response.setClassName(c.getClassName());
			response.setTeacherName(c.getTeacher().getProfile().getProfileName());
			response.setFileId(c.getClassThumbFile().getId());
			
			responses.add(response);
		});
		return responses;
	}
	
	@Override
	public ClassroomResDto getClassroomById(Long id) {
		final Classroom c = classroomDao.getById(id);
		final ClassroomResDto response = new ClassroomResDto();
		response.setId(c.getId());
		response.setClassCode(c.getClassCode());
		response.setClassName(c.getClassName());
		response.setTeacherName(c.getTeacher().getProfile().getProfileName());
		response.setFileId(c.getClassThumbFile().getId());
		
		return response;
	}

	@Transactional
	@Override
	public InsertResDto createClass(ClassroomReqDto data){
		final User teacher = userDao.getById(data.getTeacherId());
		final File thumbFile = new File();
		thumbFile.setFiles(data.getFiles());
		thumbFile.setFileFormat(data.getFileFormat());
		thumbFile.setCreatedBy(principalService.getId());
		
		fileDao.insertFile(thumbFile);

		final Classroom classroom = new Classroom();
		classroom.setClassCode(data.getClassCode());
		classroom.setClassName(data.getClassName());
		classroom.setTeacher(teacher);
		classroom.setClassThumbFile(thumbFile);
		classroom.setCreatedBy(principalService.getId());

		final Classroom newClassroom = classroomDao.insert(classroom);
		
		final InsertResDto response = new InsertResDto();
		if(newClassroom != null) {
			response.setId(newClassroom.getId());
			response.setMessage("Classroom with code " + newClassroom.getClassCode() 
			+ " is successfully created and assigned to teacher : " 
			+ teacher.getProfile().getProfileName());
		} 
		return response;
	}

}
