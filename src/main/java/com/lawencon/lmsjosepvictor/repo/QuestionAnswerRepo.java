package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.QuestionAnswer;

@Repository
public interface QuestionAnswerRepo extends JpaRepository<QuestionAnswer, Long>{
	List<QuestionAnswer> getQuestionAnswerByQuestionIdAndUserId(Long questionId, Long userId);
	List<QuestionAnswer> getQuestionAnswerByQuestionId(Long questionId);
}
