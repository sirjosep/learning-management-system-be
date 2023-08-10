package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.QuestionOptionDao;
import com.lawencon.lmsjosepvictor.model.QuestionOptions;
import com.lawencon.lmsjosepvictor.repo.QuestionOptionRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionOptionDaoSpringDataJPAImpl implements QuestionOptionDao {

	private final QuestionOptionRepo questionOptionRepo;

	QuestionOptionDaoSpringDataJPAImpl(QuestionOptionRepo questionOptionRepo) {
		this.questionOptionRepo = questionOptionRepo;
	}

	@Override
	public List<QuestionOptions> getOptionsByQuestionId(Long questionId) {
		return questionOptionRepo.getQuestionOptionsByQuestionId(questionId);
	}

	@Override
	public QuestionOptions insert(QuestionOptions questionOptions) {
		this.questionOptionRepo.save(questionOptions);
		return questionOptions;
	}
	
	@Override
	public QuestionOptions getById(Long id) {
		final QuestionOptions options = questionOptionRepo.findById(id).get();
		
		return options;
	}

	@Override
	public QuestionOptions getByIdRef(Long id) {
		final QuestionOptions options = questionOptionRepo.getReferenceById(id);
		
		return options;
	}
}
