package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.dao.LearningTaskDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.LearningTaskReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.task.LearningTaskResDto;
import com.lawencon.lmsjosepvictor.model.Learning;
import com.lawencon.lmsjosepvictor.model.LearningTask;
import com.lawencon.lmsjosepvictor.service.LearningTaskService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.util.DateUtil;

@Service
public class LearningTaskServiceImpl implements LearningTaskService{
	
	private final LearningTaskDao learningTaskDao;
	private final LearningDao learningDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	public LearningTaskServiceImpl(LearningTaskDao learningTaskDao, LearningDao learningDao, PrincipalService principalService){
		this.learningTaskDao = learningTaskDao;
		this.learningDao = learningDao;
		this.principalService = principalService;
	}

	@Override
	public List<LearningTaskResDto> getTaskByLearningId(Long learnId) {
		final List<LearningTaskResDto> responses = new ArrayList<>();
		
		learningTaskDao.getTaskByLearningId(learnId).forEach(t -> {
			final LearningTaskResDto response = new LearningTaskResDto();
			response.setId(t.getId());
			response.setLearningTasktitle(t.getLearningTaskTitle());
			response.setLearningTaskDesc(t.getLearningTaskDesc());
			response.setLearningTaskStart(DateUtil.dateFormat(t.getLearningTaskStart()));
			response.setLearningTaskEnd(DateUtil.dateFormat(t.getLearningTaskEnd()));
			
			responses.add(response);
		});
		
		return responses;
	}
	
	@Transactional
	@Override
	public InsertResDto createTask(LearningTaskReqDto data) {
		final InsertResDto response = new InsertResDto();
		final LearningTask learningTask = new LearningTask();
		learningTask.setLearningTaskTitle(data.getLearningTaskTitle());
		learningTask.setLearningTaskDesc(data.getLearningTaskDesc());
		learningTask.setLearningTaskStart(DateUtil.dateParse(data.getLearningTaskStart()));
		learningTask.setLearningTaskEnd(DateUtil.dateParse(data.getLearningTaskEnd()));
		
		final Learning learning = learningDao.getById(data.getLearnId());
		learningTask.setLearning(learning);
		
		learningTask.setCreatedBy(principalService.getId());
		
		final LearningTask newTask =  learningTaskDao.insert(learningTask);
			
		if(newTask != null) {
			response.setId(newTask.getId());
			response.setMessage("Task for learning " + learning.getLearningTitle() + " successfully created");
		}
		return response;
	}

	@Override
	public LearningTaskResDto getById(Long id) {
		final LearningTask task = learningTaskDao.getById(id);
		final LearningTaskResDto response = new LearningTaskResDto();
		response.setId(task.getId());
		response.setLearningTasktitle(task.getLearningTaskTitle());
		response.setLearningTaskDesc(task.getLearningTaskDesc());
		response.setLearningTaskStart(DateUtil.dateFormat(task.getLearningTaskStart()));
		response.setLearningTaskEnd(DateUtil.dateFormat(task.getLearningTaskEnd()));
		
		return response;
	}

}
