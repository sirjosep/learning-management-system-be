package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionAnswerFileDao;
import com.lawencon.lmsjosepvictor.model.QuestionAnswerFile;
import com.lawencon.lmsjosepvictor.repo.QuestionAnswerFileRepo;
@Repository
@Profile("springdatajpa-query")
public class QuestionAnswerFileDaoSpringDataJPAImpl implements QuestionAnswerFileDao{
	
	private final QuestionAnswerFileRepo questionAnswerFileRepo;
	
	QuestionAnswerFileDaoSpringDataJPAImpl(QuestionAnswerFileRepo questionAnswerFileRepo) {
		this.questionAnswerFileRepo = questionAnswerFileRepo;
	}

	@Override
	public QuestionAnswerFile insert(QuestionAnswerFile questionAnswerFile) {
		this.questionAnswerFileRepo.save(questionAnswerFile);
		return questionAnswerFile;
	}

	@Override
	public List<QuestionAnswerFile> getAnswerFile(Long questionAnswerId) {
		return questionAnswerFileRepo.getQuestionAnswerFileByQuestionAnswerId(questionAnswerId);
	}

}
