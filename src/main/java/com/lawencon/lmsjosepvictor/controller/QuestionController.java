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
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionResDto;
import com.lawencon.lmsjosepvictor.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {

	private final QuestionService questionService;

	QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@GetMapping
	private ResponseEntity<List<QuestionResDto>> getQuestion(Long taskId, boolean isMultipleChoice, boolean isFile,
			boolean isEssay) {
		final List<QuestionResDto> responses = questionService.getQuestionByTaskId(taskId, isMultipleChoice, isFile, isEssay);

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> createLearning(@RequestBody QuestionReqDto data) {
		final InsertResDto responses = questionService.createQuestion(data);
		
		return new ResponseEntity<>(responses, HttpStatus.CREATED);
	}
}
