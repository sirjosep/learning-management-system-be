package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.lmsjosepvictor.model.ForumComment;

public interface ForumCommentRepo extends JpaRepository<ForumComment, Long>{
	List<ForumComment> getForumCommentByForumId(Long forumId);
}
