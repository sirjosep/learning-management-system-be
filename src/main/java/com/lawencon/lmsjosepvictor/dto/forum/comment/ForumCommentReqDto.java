package com.lawencon.lmsjosepvictor.dto.forum.comment;

public class ForumCommentReqDto {
	private String commentBody;
	private Long forumId;

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
