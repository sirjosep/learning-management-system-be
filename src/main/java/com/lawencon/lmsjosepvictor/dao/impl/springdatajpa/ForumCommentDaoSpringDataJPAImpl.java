package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ForumCommentDao;
import com.lawencon.lmsjosepvictor.model.ForumComment;
import com.lawencon.lmsjosepvictor.repo.ForumCommentRepo;
@Repository
@Profile("springdatajpa-query")
public class ForumCommentDaoSpringDataJPAImpl implements ForumCommentDao{
	
	private final ForumCommentRepo forumCommentRepo;
	
	ForumCommentDaoSpringDataJPAImpl(ForumCommentRepo forumCommentRepo) {
		this.forumCommentRepo = forumCommentRepo;
	}

	@Override
	public ForumComment insert(ForumComment forumComment) {
		forumCommentRepo.save(forumComment);
		return forumComment;
	}

	@Override
	public List<ForumComment> getByForumId(Long forumId) {
		return forumCommentRepo.getForumCommentByForumId(forumId);
	}

}
