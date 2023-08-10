package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.QuestionFile;

@Repository
public interface QuestionFileRepo extends JpaRepository<QuestionFile, Long>{
	List<QuestionFile> getQuestionFileByQuestionId(Long questionId);
}
