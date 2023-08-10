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
import com.lawencon.lmsjosepvictor.dto.learning.material.LearningMaterialReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.material.LearningMaterialResDto;
import com.lawencon.lmsjosepvictor.service.LearningMaterialService;

@RestController
@RequestMapping("materials")
public class LearningMaterialController {
	
	private final LearningMaterialService learningMaterialService;

	LearningMaterialController(LearningMaterialService learningMaterialService) {
		this.learningMaterialService = learningMaterialService;
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> createMaterial(@RequestBody LearningMaterialReqDto data){
		final InsertResDto response = learningMaterialService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<LearningMaterialResDto>> getMaterialByLearnId(Long learnId){
		final List<LearningMaterialResDto> responses = learningMaterialService.getMaterialByLearnId(learnId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
