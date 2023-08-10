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
import com.lawencon.lmsjosepvictor.dto.learning.task.answer.QuestionAnswerReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.answer.QuestionAnswerResDto;
import com.lawencon.lmsjosepvictor.service.QuestionAnswerService;

@RestController
@RequestMapping("answers")
public class QuestionAnswerController {

	private final QuestionAnswerService questionAnswerService;

	QuestionAnswerController(QuestionAnswerService questionAnswerService) {
		this.questionAnswerService = questionAnswerService;
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> submitAnswer(@RequestBody QuestionAnswerReqDto data){
		final InsertResDto response = questionAnswerService.addAnswer(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
//	@GetMapping
//	private ResponseEntity<List<QuestionAnswerResDto>> getAnswer(Long questionId, Long userId){
//		final List<QuestionAnswerResDto> responses = questionAnswerService.getAnswerByQuestionAndUser(questionId, userId);
//		
//		return new ResponseEntity<>(responses, HttpStatus.OK);
//	}
	
	@GetMapping
	private ResponseEntity<List<QuestionAnswerResDto>> getAnswer(Long questionId){
		final List<QuestionAnswerResDto> responses = questionAnswerService.getAnswerByQuestion(questionId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
