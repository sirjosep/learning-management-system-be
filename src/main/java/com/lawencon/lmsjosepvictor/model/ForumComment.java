package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_forum_comment")
public class ForumComment extends Base {
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "forum_comment_body", nullable = false)
	private String forumCommentBody;
	
	@ManyToOne
	@JoinColumn(name = "forum_id", nullable = false)
	private Forum forum;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getForumCommentBody() {
		return forumCommentBody;
	}

	public void setForumCommentBody(String forumCommentBody) {
		this.forumCommentBody = forumCommentBody;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

}
