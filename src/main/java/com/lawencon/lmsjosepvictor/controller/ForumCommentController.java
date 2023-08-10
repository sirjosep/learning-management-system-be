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
import com.lawencon.lmsjosepvictor.dto.forum.comment.ForumCommentReqDto;
import com.lawencon.lmsjosepvictor.dto.forum.comment.ForumCommentResDto;
import com.lawencon.lmsjosepvictor.service.ForumCommentService;

@RestController
@RequestMapping("comments")
public class ForumCommentController {

	private final ForumCommentService forumCommentService;

	ForumCommentController(ForumCommentService forumCommentService) {
		this.forumCommentService = forumCommentService;
	}
	
	@GetMapping
	private ResponseEntity<List<ForumCommentResDto>> getForumComment(Long forumId){
		final List<ForumCommentResDto> responses = forumCommentService.getByForumId(forumId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> addComment(@RequestBody ForumCommentReqDto data){
		final InsertResDto response = forumCommentService.addComment(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
