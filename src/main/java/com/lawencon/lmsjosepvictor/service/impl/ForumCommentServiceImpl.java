package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.ForumCommentDao;
import com.lawencon.lmsjosepvictor.dao.ForumDao;
import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.forum.comment.ForumCommentReqDto;
import com.lawencon.lmsjosepvictor.dto.forum.comment.ForumCommentResDto;
import com.lawencon.lmsjosepvictor.model.Forum;
import com.lawencon.lmsjosepvictor.model.ForumComment;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.service.ForumCommentService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;
import com.lawencon.lmsjosepvictor.util.DateUtil;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {

	private final ForumCommentDao forumCommentDao;
	private final ForumDao forumDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public ForumCommentServiceImpl(ForumCommentDao forumCommentDao, UserDao userDao, ForumDao forumDao, PrincipalService principalService) {
		this.forumCommentDao = forumCommentDao;
		this.forumDao = forumDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto addComment(ForumCommentReqDto data) {
		final InsertResDto response = new InsertResDto();
		final ForumComment comment = new ForumComment();

		final User user = userDao.getById(principalService.getId());

		final Forum forum = forumDao.getForumById(data.getForumId());

		comment.setForum(forum);
		comment.setUser(user);
		comment.setForumCommentBody(data.getCommentBody());
		comment.setCreatedBy(1L);

		forumCommentDao.insert(comment);

		response.setId(comment.getId());
		response.setMessage("Comment added successfully!");
		
		return response;
	}

	@Override
	public List<ForumCommentResDto> getByForumId(Long forumId) {
		final List<ForumCommentResDto> responses = new ArrayList<>();
		
		forumCommentDao.getByForumId(forumId).forEach(c -> {
			final ForumCommentResDto response = new ForumCommentResDto();
			response.setId(c.getId());
			response.setUserId(c.getUser().getId());
			response.setProfileName(c.getUser().getProfile().getProfileName());
			response.setCommentBody(c.getForumCommentBody());
			response.setCreatedAt(DateUtil.dateFormat(c.getCreatedAt()));
			if(c.getUser().getProfile().getFile() != null) {
				response.setFileId(c.getUser().getProfile().getFile().getId());
			}
			
			responses.add(response);
		});
		
		return responses;
	}

}
