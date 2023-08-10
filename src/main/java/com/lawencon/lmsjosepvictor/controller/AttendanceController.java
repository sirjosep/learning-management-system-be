package com.lawencon.lmsjosepvictor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceReqDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceResDto;
import com.lawencon.lmsjosepvictor.dto.attendance.AttendanceUpdateReqDto;
import com.lawencon.lmsjosepvictor.service.AttendanceService;

@RestController
@RequestMapping("attendances")
public class AttendanceController {

	private final AttendanceService attendanceService;

	AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	@GetMapping
	private ResponseEntity<List<AttendanceResDto>> getAllByLearning(Long learnId) {
		final List<AttendanceResDto> responses = attendanceService.getAllByLearning(learnId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/details")
	private ResponseEntity<AttendanceResDto> getAllByLearningAndUser(Long learnId) {
		final AttendanceResDto responses = attendanceService.getAttendanceByUserAndLearningId(learnId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PatchMapping
	private ResponseEntity<UpdateResDto> approvAttendance(@RequestBody AttendanceUpdateReqDto data) {
		final UpdateResDto response = attendanceService.approveAttendance(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> createAttendance(@RequestBody AttendanceReqDto data) {
		final InsertResDto response = attendanceService.createAttendance(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
