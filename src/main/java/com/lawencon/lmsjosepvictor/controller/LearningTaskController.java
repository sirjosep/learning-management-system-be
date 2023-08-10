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
import com.lawencon.lmsjosepvictor.dto.learning.task.LearningTaskReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.LearningTaskResDto;
import com.lawencon.lmsjosepvictor.service.LearningTaskService;

@RestController
@RequestMapping("tasks")
public class LearningTaskController {

	private final LearningTaskService learningTaskService;

	LearningTaskController(LearningTaskService learningTaskService) {
		this.learningTaskService = learningTaskService;
	}
	
	@GetMapping
	private ResponseEntity<List<LearningTaskResDto>> getTaskByLearnId(Long learnId) {
		final List<LearningTaskResDto> responses = learningTaskService.getTaskByLearningId(learnId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/details")
	private ResponseEntity<LearningTaskResDto> getTaskByid(Long id) {
		final LearningTaskResDto response = learningTaskService.getById(id);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> createLearning(@RequestBody LearningTaskReqDto data) {
		final InsertResDto response = learningTaskService.createTask(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
