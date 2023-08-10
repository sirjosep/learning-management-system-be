package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.QuestionAnswerFile;

@Repository
public interface QuestionAnswerFileRepo extends JpaRepository<QuestionAnswerFile, Long>{
	List<QuestionAnswerFile> getQuestionAnswerFileByQuestionAnswerId(Long questionAnswerId);
}
