package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ReviewDao;
import com.lawencon.lmsjosepvictor.model.Review;
import com.lawencon.lmsjosepvictor.repo.ReviewRepo;
@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ReviewDaoSpringDataJPAImpl implements ReviewDao {
	
	private final ReviewRepo reviewRepo;
	
	ReviewDaoSpringDataJPAImpl(ReviewRepo reviewRepo) {
		this.reviewRepo = reviewRepo;
	}

	@Override
	public Review insert(Review review) {
		this.reviewRepo.save(review);
		return review;
	}

	@Override
	public List<Review> getReviewByTeacherAndTask(Long teacherId, Long taskId) {
		return reviewRepo.getReviewByTeacherIdAndLearningTaskId(teacherId, taskId);
	}
	
	@Override
	public Review getScoringForMultipleChoice(Long taskId, Long studentId) {
		return reviewRepo.getScoringForMultipleChoice(taskId, studentId);
	}

	@Override
	public Review getById(Long id) {
		final Review review = reviewRepo.findById(id).get();
		return review;
	}

}
