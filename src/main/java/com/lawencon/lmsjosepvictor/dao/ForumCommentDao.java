package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.ForumComment;

public interface ForumCommentDao {
	ForumComment insert(ForumComment forumComment);
	
	List<ForumComment> getByForumId(Long forumId);
}
