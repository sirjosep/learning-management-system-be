package com.lawencon.lmsjosepvictor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.enrollclass.EnrollClassReqDto;
import com.lawencon.lmsjosepvictor.dto.enrollclass.EnrollClassResDto;
import com.lawencon.lmsjosepvictor.service.EnrollClassroomService;

@RestController
@RequestMapping("enrolls")
public class EnrollClassController {

	private EnrollClassroomService enrollClassroomService;

	EnrollClassController(EnrollClassroomService enrollClassroomService) {
		this.enrollClassroomService = enrollClassroomService;
	}
	
	@GetMapping
	private ResponseEntity<List<EnrollClassResDto>> getAllByClass(Long classId) {
		final List<EnrollClassResDto> responses = enrollClassroomService.getAllByClass(classId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/details")
	private ResponseEntity<EnrollClassResDto> getEnrollDetail(Long id) {
		final EnrollClassResDto response = enrollClassroomService.getById(id);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> enrollClass(@RequestBody EnrollClassReqDto data) {
		final InsertResDto response = enrollClassroomService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
