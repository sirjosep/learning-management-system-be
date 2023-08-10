package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.dao.LearningTaskDao;
import com.lawencon.lmsjosepvictor.dao.QuestionAnswerDao;
import com.lawencon.lmsjosepvictor.dao.QuestionAnswerFileDao;
import com.lawencon.lmsjosepvictor.dao.QuestionDao;
import com.lawencon.lmsjosepvictor.dao.QuestionOptionDao;
import com.lawencon.lmsjosepvictor.dao.ReviewDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.file.FileDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.answer.QuestionAnswerReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.answer.QuestionAnswerResDto;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.model.LearningTask;
import com.lawencon.lmsjosepvictor.model.Question;
import com.lawencon.lmsjosepvictor.model.QuestionAnswer;
import com.lawencon.lmsjosepvictor.model.QuestionAnswerFile;
import com.lawencon.lmsjosepvictor.model.QuestionOptions;
import com.lawencon.lmsjosepvictor.model.Review;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.service.QuestionAnswerService;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService{
	
	private final QuestionDao questionDao;
	private final QuestionAnswerDao questionAnswerDao;
	private final QuestionAnswerFileDao questionAnswerFileDao;
	private final QuestionOptionDao questionOptionDao;
	private final LearningTaskDao learningTaskDao;
	private final ReviewDao reviewDao;
	private final UserDao userDao;
	private final FileDao fileDao;
	private final LearningDao learningDao;
	private final ClassroomDao classroomDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	public QuestionAnswerServiceImpl(QuestionAnswerDao questionAnswerDao, QuestionAnswerFileDao questionAnswerFileDao,
			ReviewDao reviewDao, FileDao fileDao, UserDao userDao, QuestionDao questionDao, 
			QuestionOptionDao questionOptionDao, LearningTaskDao learningTaskDao,
			LearningDao learningDao, ClassroomDao classroomDao, PrincipalService principalService) {
		this.questionAnswerDao = questionAnswerDao;
		this.questionAnswerFileDao = questionAnswerFileDao;
		this.reviewDao = reviewDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.questionDao = questionDao;
		this.questionOptionDao = questionOptionDao;
		this.learningTaskDao = learningTaskDao;
		this.principalService = principalService;
		this.learningDao = learningDao;
		this.classroomDao = classroomDao;
	}

	@Transactional
	@Override
	public InsertResDto addAnswer(QuestionAnswerReqDto data){
		final InsertResDto response = new InsertResDto();
		final User user = userDao.getById(principalService.getId());
		final LearningTask task = learningTaskDao.getById(data.getTaskId());
		final Long learningId = task.getLearning().getId();
		final Long classId = learningDao.getById(learningId).getClassroom().getId();
		final Long teacherId = classroomDao.getById(classId).getTeacher().getId();
		final User teacher = userDao.getById(teacherId);
		
		if (data.getAnswers().size() > 0) {
			Question question = null;
			for (int i = 0; i < data.getAnswers().size(); i++) {
				final QuestionAnswer answer = new QuestionAnswer();
				answer.setUser(user);

				question = questionDao.getById(data.getAnswers().get(i).getQuestionId());
				answer.setQuestion(question);

				answer.setCreatedBy(principalService.getId());
				answer.setQuestionAnswer(data.getAnswers().get(i).getQuestionAnswer());
				QuestionOptions options = null;
				if (data.getAnswers().get(i).getQuestionOptions() != null) {
					options = questionOptionDao.getByIdRef(data.getAnswers().get(i).getQuestionOptions());
				}
				answer.setQuestionOptions(options);

				questionAnswerDao.insertAnswer(answer);

				if (data.getAnswers().get(i).getAnswerFiles().size() > 0) {
					questionAnswerDao.update(answer);
					for (int j = 0; j < data.getAnswers().get(i).getAnswerFiles().size(); j++) {
						final File file = new File();
						file.setFiles(data.getAnswers().get(i).getAnswerFiles().get(j).getFiles());
						file.setFileFormat(data.getAnswers().get(i).getAnswerFiles().get(j).getFileFormat());
						file.setCreatedBy(principalService.getId());

						fileDao.insertFile(file);

						final QuestionAnswerFile questionAnswerFile = new QuestionAnswerFile();
						questionAnswerFile.setQuestionAnswer(answer);
						questionAnswerFile.setFile(file);
						questionAnswerFile.setCreatedBy(principalService.getId());

						questionAnswerFileDao.insert(questionAnswerFile);
					}
				}

			}

			final Review review = new Review();
			review.setTeacher(teacher);
			review.setStudent(user);
			review.setLearningTask(task);
			review.setCreatedBy(principalService.getId());
			
			if (reviewDao.getScoringForMultipleChoice(data.getTaskId(), principalService.getId()) != null) {
				review.setScore(reviewDao.getScoringForMultipleChoice(review.getLearningTask().getId(), principalService.getId())
						.getScore());
			} else {
				review.setScore(0f);
			}
			reviewDao.insert(review);

		}
			
		response.setMessage("Answer submitted successfully");
		return response;
	}

	@Override
	public List<QuestionAnswerResDto> getAnswerByQuestionAndUser(Long questionId, Long userId) {
		final List<QuestionAnswerResDto> responses = new ArrayList<>();
		
		final List<QuestionAnswer> filteredAnswers = questionAnswerDao.getAnswerByQuestionAndUser(questionId, userId)
				.stream()
				.distinct()
				.collect(Collectors.toList());
		
		filteredAnswers.forEach(qa -> {
			final QuestionAnswerResDto response = new QuestionAnswerResDto();
			response.setId(qa.getId());
			response.setQuestionAnswer(qa.getQuestionAnswer());
			if(qa.getQuestionOptions() != null) {
				response.setQuestionOptions(qa.getQuestionOptions().getOptionsLabel());
			}
			
			if (questionAnswerFileDao.getAnswerFile(qa.getId()).size() > 0) {
				final List<Long> fileResponses = new ArrayList<>();
				questionAnswerFileDao.getAnswerFile(qa.getId()).forEach(f -> {
					fileResponses.add(f.getFile().getId());
					response.setFileIds(fileResponses);
				});
			}
			
			responses.add(response);
		});
		
		return responses;
	}

	@Override
	public List<QuestionAnswerResDto> getAnswerByQuestion(Long questionId) {
		final List<QuestionAnswerResDto> responses = new ArrayList<>();
		
		final List<QuestionAnswer> filteredAnswers = questionAnswerDao.getAnswerByQuestion(questionId)
				.stream()
				.distinct()
				.collect(Collectors.toList());
		
		filteredAnswers.forEach(qa -> {
			final User student = userDao.getById(qa.getUser().getId());
			final QuestionAnswerResDto response = new QuestionAnswerResDto();
			response.setId(qa.getId());
			response.setStudentName(student.getProfile().getProfileName());
			response.setQuestionAnswer(qa.getQuestionAnswer());
			if(qa.getQuestionOptions() != null) {
				response.setQuestionOptions(qa.getQuestionOptions().getOptionsLabel());
			}
			
			if (questionAnswerFileDao.getAnswerFile(qa.getId()).size() > 0) {
				final List<Long> fileResponses = new ArrayList<>();
				questionAnswerFileDao.getAnswerFile(qa.getId()).forEach(f -> {
					fileResponses.add(f.getFile().getId());
					response.setFileIds(fileResponses);
				});
			}
			
			responses.add(response);
		});
		
		return responses;
	}

}
