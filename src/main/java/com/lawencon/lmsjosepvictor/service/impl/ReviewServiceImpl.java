package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.QuestionDao;
import com.lawencon.lmsjosepvictor.dao.ReviewDao;
import com.lawencon.lmsjosepvictor.dto.UpdateResDto;
import com.lawencon.lmsjosepvictor.dto.review.ReviewResDto;
import com.lawencon.lmsjosepvictor.dto.review.UpdateReviewReqDto;
import com.lawencon.lmsjosepvictor.model.Review;
import com.lawencon.lmsjosepvictor.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewDao reviewDao;
	private final QuestionDao questionDao;
	
	@PersistenceContext
	private EntityManager em;

	public ReviewServiceImpl(ReviewDao reviewDao, QuestionDao questionDao) {
		this.reviewDao = reviewDao;
		this.questionDao = questionDao;
	}

	@Override
	public List<ReviewResDto> getReviewById(Long teacherId, Long taskId) {
		final List<ReviewResDto> responses = new ArrayList<>();
		
		reviewDao.getReviewByTeacherAndTask(teacherId, taskId).forEach(r -> {
			final ReviewResDto response = new ReviewResDto();
			response.setId(r.getId());
			response.setStudentId(r.getStudent().getId());
			response.setStudentName(r.getStudent().getProfile().getProfileName());
			response.setLearningTaskId(r.getLearningTask().getId());
			response.setLearningTaskName(r.getLearningTask().getLearningTaskTitle());
			response.setScore(r.getScore());
			response.setNotes(r.getNotes());
			
			responses.add(response);
		});
		
		return responses;
	}

	@Transactional
	@Override
	public UpdateResDto update(UpdateReviewReqDto data) {
		final UpdateResDto response = new UpdateResDto();
		final Review updatedReview = reviewDao.getById(data.getReviewId());
		
		boolean isMultipleChoice = false;
		if (questionDao.getQuestionWithOptions(data.getTaskId()) != null) {
			isMultipleChoice = true;
		}
		
		float score = 0;
		float multipleChoiceScore = 0f;
		if(isMultipleChoice) {
			if (reviewDao.getScoringForMultipleChoice(data.getTaskId(), data.getStudentId()) != null) {
				multipleChoiceScore = reviewDao.getScoringForMultipleChoice(data.getTaskId(), data.getStudentId()).getScore();	
			}
		}
		
		if (data.getEssayScore() != null && isMultipleChoice && data.getFileScore() == null) {
			score = (data.getEssayScore() + multipleChoiceScore) / 2;
		} else if (data.getEssayScore() == null && isMultipleChoice && data.getFileScore() != null) {
			score = (multipleChoiceScore + data.getFileScore()) / 2;
		} else if (data.getEssayScore() != null && !isMultipleChoice && data.getFileScore() != null) {
			score = (data.getEssayScore() + data.getFileScore()) / 2;
		} else if (data.getEssayScore() != null && !isMultipleChoice && data.getFileScore() == null) {
			score = data.getEssayScore();
		} else if (data.getEssayScore() == null && isMultipleChoice && data.getFileScore() == null) {
			score = multipleChoiceScore;
		} else if (data.getEssayScore() == null && !isMultipleChoice && data.getFileScore() != null) {
			score = data.getFileScore();
		} else if (data.getEssayScore() != null && isMultipleChoice && data.getFileScore() != null) {
			score = (data.getEssayScore() + multipleChoiceScore + data.getFileScore()) / 3;
		}
		
		updatedReview.setScore(score);
		updatedReview.setNotes(data.getNotes());
		updatedReview.setUpdatedBy(1L);
		
		response.setMessage("Review updated successfully");
		
		return response;
	}

}
