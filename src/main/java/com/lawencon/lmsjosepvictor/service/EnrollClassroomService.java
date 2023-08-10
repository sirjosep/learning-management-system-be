package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.enrollclass.EnrollClassReqDto;
import com.lawencon.lmsjosepvictor.dto.enrollclass.EnrollClassResDto;

public interface EnrollClassroomService {
	InsertResDto insert(EnrollClassReqDto data);

	List<EnrollClassResDto> getAllByClass(Long classId);

	EnrollClassResDto getById(Long id);
}
