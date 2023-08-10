package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.dao.ForumDao;
import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.LearningReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.LearningResDto;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.model.Forum;
import com.lawencon.lmsjosepvictor.model.Learning;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.LearningService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.util.DateUtil;

@Service
public class LearningServiceImpl implements LearningService{
	
	private final LearningDao learningDao;
	private final ClassroomDao classroomDao;
	private final ForumDao forumDao;
	private final UserDao userDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	public LearningServiceImpl(LearningDao learningDao, ClassroomDao classroomDao, 
			ForumDao forumDao, UserDao userDao, PrincipalService principalService) {
		this.learningDao = learningDao;
		this.classroomDao = classroomDao;
		this.forumDao = forumDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	@Override
	public List<LearningResDto> getLearningListsByClassId(Long classId) {
		final List<LearningResDto> responses = new ArrayList<>();
		
		learningDao.getLearningListsByClassId(classId).forEach(l -> {
			final LearningResDto response = new LearningResDto();
		
			response.setId(l.getId());
			response.setLearningTitle(l.getLearningTitle());
			response.setLearningDateStart(DateUtil.dateFormat(l.getLearningDateStart()));
			response.setLearningDateEnd(DateUtil.dateFormat(l.getLearningDateEnd()));
			response.setClassId(l.getClassroom().getId());
			
			responses.add(response);
		});
		
		return responses;
	}

	@Override
	public LearningResDto getLearningById(Long learnId) {
		final LearningResDto response = new LearningResDto();
		
		final Learning learning = learningDao.getById(learnId);
		response.setId(learning.getId());
		response.setLearningTitle(learning.getLearningTitle());
		response.setLearningDateStart(DateUtil.dateFormat(learning.getLearningDateStart()));
		response.setLearningDateEnd(DateUtil.dateFormat(learning.getLearningDateEnd()));
		response.setClassId(learning.getClassroom().getId());
		
		return response;
	}

	@Transactional
	@Override
	public InsertResDto createLearning(LearningReqDto data) {
		final InsertResDto response = new InsertResDto();
		final Classroom classroom = classroomDao.getById(data.getClassId());
		final Learning learning = learningDao.getLearningByClassAndTeacher(data.getClassId(), classroom.getTeacher().getId(),
				DateUtil.dateParse(data.getLearningDateStart()),
				DateUtil.dateParse(data.getLearningDateStart()));
		
		if (learning == null) {
			final Learning newLearning = new Learning();
			newLearning.setLearningTitle(data.getLearningTitle());
			newLearning.setLearningDateStart(DateUtil.dateParse(data.getLearningDateStart()));
			newLearning.setLearningDateEnd(DateUtil.dateParse(data.getLearningDateEnd()));

			newLearning.setClassroom(classroom);

			newLearning.setCreatedBy(principalService.getId());

			final Learning inputtedLearning = learningDao.insert(newLearning);

			final Forum forum = new Forum();
			forum.setForumTitle(data.getLearningTitle() + "'s Discussion");
			forum.setForumBody("This is a forum that generated automatically. Please don't talk about something bad or harassment");
			forum.setLearning(learningDao.getById(inputtedLearning.getId()));

			final User user = userDao.getById(classroom.getTeacher().getId());
			forum.setUser(user);
			forum.setCreatedBy(principalService.getId());

			final Forum newForum = forumDao.insert(forum);
			
			if (inputtedLearning != null && newForum != null) {
				response.setId(inputtedLearning.getId());
				response.setMessage("Learning for class " + classroom.getClassName() + " is successfully created");
			}
		} else {
			response.setMessage("Error! you have learning at class " + learning.getClassroom().getClassName()
					+ " from " + learning.getLearningDateStart() + " to " + learning.getLearningDateEnd());
		}
		
			
		return response;
	}
}
