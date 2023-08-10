package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ForumDao;
import com.lawencon.lmsjosepvictor.model.Forum;
import com.lawencon.lmsjosepvictor.repo.ForumRepo;
@Repository
@Profile("springdatajpa-query")
public class ForumDaoSpringDataJPAImpl implements ForumDao{
	
	private final ForumRepo forumRepo;
	
	ForumDaoSpringDataJPAImpl(ForumRepo forumRepo) {
		this.forumRepo = forumRepo;
	}
	
	@Override
	public Forum getForumByLearningId(Long learningId){
		return forumRepo.getForumByLearningId(learningId);
	}

	@Override
	public Forum getForumById(Long id) {
		final Forum forum = forumRepo.findById(id).get();
		return forum;
	}

	@Override
	public Forum insert(Forum forum) {
		forumRepo.save(forum);
		return forum;
	}

}
