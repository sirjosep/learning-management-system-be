package com.lawencon.lmsjosepvictor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.review.ReviewResDto;
import com.lawencon.lmsjosepvictor.dto.review.UpdateReviewReqDto;
import com.lawencon.lmsjosepvictor.service.ReviewService;

@RestController
@RequestMapping("reviews")
public class ReviewController {

	private final ReviewService reviewService;

	ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PutMapping
	private ResponseEntity<UpdateResDto> updateReview(@RequestBody UpdateReviewReqDto data){
		final UpdateResDto response = reviewService.update(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping
	private ResponseEntity<List<ReviewResDto>> getReviewList(Long teacherId, Long taskId){
		final List<ReviewResDto> responses = reviewService.getReviewById(teacherId, taskId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
