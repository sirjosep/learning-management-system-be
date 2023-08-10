package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.review.ReviewResDto;
import com.lawencon.lmsjosepvictor.dto.review.UpdateReviewReqDto;

public interface ReviewService {
	UpdateResDto update(UpdateReviewReqDto data);
	
	List<ReviewResDto> getReviewById(Long teacherId, Long taskId);
}
