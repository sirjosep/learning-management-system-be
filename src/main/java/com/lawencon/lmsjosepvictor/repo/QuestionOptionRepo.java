package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.QuestionOptions;

@Repository
public interface QuestionOptionRepo extends JpaRepository<QuestionOptions, Long>{
	List<QuestionOptions> getQuestionOptionsByQuestionId(Long questionId);
}
