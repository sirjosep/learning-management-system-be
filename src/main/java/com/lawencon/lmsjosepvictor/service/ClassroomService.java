package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.classroom.ClassroomReqDto;
import com.lawencon.lmsjosepvictor.dto.classroom.ClassroomResDto;

public interface ClassroomService {
	List<ClassroomResDto> getAll();
	List<ClassroomResDto> getUnenrolledClassByStudId();
	
	InsertResDto createClass(ClassroomReqDto data);
	ClassroomResDto getClassroomById(Long id);
}
