package com.lawencon.lmsjosepvictor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.dto.forum.ForumResDto;
import com.lawencon.lmsjosepvictor.service.ForumService;

@RestController
@RequestMapping("forums")
public class ForumController {

	private final ForumService forumService;

	ForumController(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@GetMapping
	private ResponseEntity<ForumResDto> getForumByLearnId(Long learningId){
		final ForumResDto response = forumService.getForumByLearningId(learningId);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
