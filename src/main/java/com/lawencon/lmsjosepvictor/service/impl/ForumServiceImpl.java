package com.lawencon.lmsjosepvictor.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.ForumDao;
import com.lawencon.lmsjosepvictor.dto.forum.ForumResDto;
import com.lawencon.lmsjosepvictor.model.Forum;
import com.lawencon.lmsjosepvictor.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{
	
	private final ForumDao forumDao;

	public ForumServiceImpl(ForumDao forumDao) {
		this.forumDao = forumDao;
	}

	@Override
	public ForumResDto getForumByLearningId(Long learningId) {
		final ForumResDto response = new ForumResDto();
		final Forum forum = forumDao.getForumByLearningId(learningId);
		
		response.setId(forum.getId());
		response.setForumTitle(forum.getForumTitle());
		response.setForumBody(forum.getForumBody());
		
		return response;
	}

}
