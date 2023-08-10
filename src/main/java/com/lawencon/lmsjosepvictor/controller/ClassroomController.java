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
import com.lawencon.lmsjosepvictor.dto.classroom.ClassroomReqDto;
import com.lawencon.lmsjosepvictor.dto.classroom.ClassroomResDto;
import com.lawencon.lmsjosepvictor.service.ClassroomService;

@RestController
@RequestMapping("classes")
public class ClassroomController {

	private ClassroomService classroomService;

	ClassroomController(ClassroomService classroomService) {
		this.classroomService = classroomService;
	}
	
	@GetMapping
	private ResponseEntity<List<ClassroomResDto>> getAll() {
		final List<ClassroomResDto> responses = classroomService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/un-enrolled")
	private ResponseEntity<List<ClassroomResDto>> getUnenrolledClassroom() {
		final List<ClassroomResDto> responses = classroomService.getUnenrolledClassByStudId();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/details")
	private ResponseEntity<ClassroomResDto> getClassDetail(Long classId){
		final ClassroomResDto response = classroomService.getClassroomById(classId);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> createClassroom(@RequestBody ClassroomReqDto data){
		final InsertResDto response = classroomService.createClass(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
