package com.lawencon.lmsjosepvictor.service;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.forum.comment.ForumCommentReqDto;
import com.lawencon.lmsjosepvictor.dto.forum.comment.ForumCommentResDto;

public interface ForumCommentService {
	InsertResDto addComment(ForumCommentReqDto data);
	
	List<ForumCommentResDto> getByForumId(Long forumId);
}
