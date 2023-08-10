package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ForumCommentDao;
import com.lawencon.lmsjosepvictor.model.ForumComment;
@Repository
@Profile("native-query")
public class ForumCommentDaoImpl implements ForumCommentDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ForumComment insert(ForumComment forumComment) {
		em.persist(forumComment);
		return forumComment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumComment> getByForumId(Long forumId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_forum_comment tfc "
				+ "INNER JOIN "
				+ "t_user tu ON tfc.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id "
				+ "WHERE "
				+ "tfc.forum_id = :forumId "
				+ "ORDER BY "
				+ "tfc.created_at DESC";
		
		final List<ForumComment> comments = this.em.createNativeQuery(sql, ForumComment.class)
				.setParameter("forumId", forumId)
				.getResultList();
		
		return comments;
	}

}
