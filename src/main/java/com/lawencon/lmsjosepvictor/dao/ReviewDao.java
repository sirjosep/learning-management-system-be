package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.Review;

public interface ReviewDao {
	Review insert(Review review);
	Review getScoringForMultipleChoice(Long taskId, Long studentId);
	Review getById(Long id);
	
	List<Review> getReviewByTeacherAndTask(Long teacherId, Long taskId);
}
