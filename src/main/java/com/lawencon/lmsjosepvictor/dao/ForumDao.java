package com.lawencon.lmsjosepvictor.dao;

import com.lawencon.lmsjosepvictor.model.Forum;

public interface ForumDao {
	Forum insert(Forum forum);
	Forum getForumByLearningId(Long learningId);
	Forum getForumById(Long id);
}
