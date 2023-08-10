package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceReqDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceResDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceUpdateReqDto;

public interface AttendanceService {
	InsertResDto createAttendance(AttendanceReqDto data);
	UpdateResDto approveAttendance(AttendanceUpdateReqDto data);
	
	List<AttendanceResDto> getAllByLearning(Long learnId);
	AttendanceResDto getAttendanceByUserAndLearningId(Long learnId);
}
