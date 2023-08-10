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
import com.lawencon.lmsjosepvictor.dto.learning.LearningReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.LearningResDto;
import com.lawencon.lmsjosepvictor.service.LearningService;

@RestController
@RequestMapping("learnings")
public class LearningController {
	
	private LearningService learningService;

	LearningController(LearningService learningService) {
		this.learningService = learningService;
	}
	
	@GetMapping
	private ResponseEntity<List<LearningResDto>> getAll(Long classId) {
		final List<LearningResDto> responses = learningService.getLearningListsByClassId(classId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/details")
	private ResponseEntity<LearningResDto> getLearnDetail(Long learnId) {
		final LearningResDto responses = learningService.getLearningById(learnId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK); 
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> createLearning(@RequestBody LearningReqDto data) {
		final InsertResDto responses = learningService.createLearning(data);
		
		return new ResponseEntity<>(responses, HttpStatus.CREATED);
	}
}
