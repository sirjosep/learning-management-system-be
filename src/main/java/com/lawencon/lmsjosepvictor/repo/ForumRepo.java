package com.lawencon.lmsjosepvictor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.lmsjosepvictor.model.Forum;

public interface ForumRepo extends JpaRepository<Forum, Long>{
	Forum getForumByLearningId(Long learningId);
}
