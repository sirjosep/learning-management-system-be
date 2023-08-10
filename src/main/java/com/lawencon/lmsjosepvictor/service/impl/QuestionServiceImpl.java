package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.dao.LearningTaskDao;
import com.lawencon.lmsjosepvictor.dao.QuestionDao;
import com.lawencon.lmsjosepvictor.dao.QuestionFileDao;
import com.lawencon.lmsjosepvictor.dao.QuestionOptionDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionOptionDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.question.QuestionResDto;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.model.LearningTask;
import com.lawencon.lmsjosepvictor.model.Question;
import com.lawencon.lmsjosepvictor.model.QuestionFile;
import com.lawencon.lmsjosepvictor.model.QuestionOptions;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	private final QuestionDao questionDao;
	private final QuestionOptionDao questionOptionDao;
	private final QuestionFileDao questionFileDao;
	private final LearningTaskDao learningTaskDao;
	private final FileDao fileDao; 
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	public QuestionServiceImpl(QuestionDao questionDao, LearningTaskDao learningTaskDao, 
			QuestionOptionDao questionOptionDao, QuestionFileDao questionFileDao, 
			FileDao fileDao, PrincipalService principalService){
		this.questionDao = questionDao;
		this.questionOptionDao = questionOptionDao;
		this.questionFileDao = questionFileDao;
		this.learningTaskDao = learningTaskDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Override
	public List<QuestionResDto> getQuestionByTaskId(Long taskId, boolean isMultipleChoice, boolean isFile, boolean isEssay) {
		final List<QuestionResDto> responses = new ArrayList<>();

		List<Question> questions = new ArrayList<>();
		if(isMultipleChoice == true) {
			questions = questionDao.getQuestionWithOptions(taskId);
		} else if(isFile == true) {
			questions = questionDao.getQuestionWithFile(taskId);
		} else if(isEssay == true) {
			questions = questionDao.getEssayQuestion(taskId);
		} else {
			questions = questionDao.getQuestionByTaskId(taskId);
		}
		
		final List<Question> filteredQuestions = questions
				.stream()
				.distinct()
				.collect(Collectors.toList());
		
		filteredQuestions.forEach(q -> {
			final QuestionResDto response = new QuestionResDto();
			response.setId(q.getId());
			response.setQuestionCode(q.getQuestionCode());
			response.setQuestionBody(q.getQuestionBody());
			
			if(questionOptionDao.getOptionsByQuestionId(q.getId()).size() > 0) {
				final List<QuestionOptionDto> optionResponses = new ArrayList<>();
				questionOptionDao.getOptionsByQuestionId(q.getId()).forEach(qo -> {
					final QuestionOptionDto optionResponse = new QuestionOptionDto();
					optionResponse.setId(qo.getId());
					optionResponse.setOptionsLabel(qo.getOptionsLabel());
					optionResponse.setIsCorrect(qo.getIsCorrect());
					
					optionResponses.add(optionResponse);
				});
				response.setOptions(optionResponses);
			} else if(questionFileDao.getFileByQuestionId(q.getId()).size() > 0) {
				final List<Long> fileResponses = new ArrayList<>();
				questionFileDao.getFileByQuestionId(q.getId()).forEach(qf -> {
					fileResponses.add(qf.getFile().getId());
				});
				response.setFileIds(fileResponses);
			}
			responses.add(response);
		});
		
		return responses;
	}

	@Transactional
	@Override
	public InsertResDto createQuestion(QuestionReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		final Question newQuestion = new Question();
		newQuestion.setQuestionCode(data.getQuestionCode());
		newQuestion.setQuestionBody(data.getQuestionBody());

		final LearningTask task = learningTaskDao.getById(data.getTaskId());
		newQuestion.setLearningTask(task);

		newQuestion.setCreatedBy(principalService.getId());

		questionDao.insert(newQuestion);

		if (data.getOptions().size() > 0) {
			for (int i = 0; i < data.getOptions().size(); i++) {
				final QuestionOptions questionOptions = new QuestionOptions();
				questionOptions.setOptionsLabel(data.getOptions().get(i).getOptionsLabel());
				questionOptions.setIsCorrect(data.getOptions().get(i).getIsCorrect());
				questionOptions.setQuestion(newQuestion);
				questionOptions.setCreatedBy(1L);

				questionOptionDao.insert(questionOptions);
			}
		}

		if (data.getFiles().size() > 0) {
			for (int i = 0; i < data.getFiles().size(); i++) {
				final File file = new File();
				file.setFiles(data.getFiles().get(i).getFiles());
				file.setFileFormat(data.getFiles().get(i).getFileFormat());
				file.setCreatedBy(principalService.getId());
				
				fileDao.insertFile(file);
				
				final QuestionFile questionFile = new QuestionFile();
				questionFile.setQuestion(newQuestion);
				questionFile.setFile(file);
				questionFile.setCreatedBy(principalService.getId());

				questionFileDao.insert(questionFile);
			}
		}
		
		response.setId(newQuestion.getId());
		response.setMessage("Question are successfully created for task" + task.getLearningTaskTitle());
		
		return response;
	}

}
