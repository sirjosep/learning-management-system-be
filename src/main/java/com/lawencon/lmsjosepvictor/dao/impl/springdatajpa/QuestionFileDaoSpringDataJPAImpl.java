package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionFileDao;
import com.lawencon.lmsjosepvictor.model.QuestionFile;
import com.lawencon.lmsjosepvictor.repo.QuestionFileRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionFileDaoSpringDataJPAImpl implements QuestionFileDao {

	private final QuestionFileRepo questionFileRepo;

	QuestionFileDaoSpringDataJPAImpl(QuestionFileRepo questionFileRepo) {
		this.questionFileRepo = questionFileRepo;
	}

	@Override
	public List<QuestionFile> getFileByQuestionId(Long questionId) {
		return questionFileRepo.getQuestionFileByQuestionId(questionId);
	}

	@Override
	public QuestionFile insert(QuestionFile questionFile) {
		this.questionFileRepo.save(questionFile);
		return questionFile;
	}

}
