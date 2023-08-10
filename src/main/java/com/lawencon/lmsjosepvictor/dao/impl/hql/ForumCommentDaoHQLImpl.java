package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ForumCommentDao;
import com.lawencon.lmsjosepvictor.model.ForumComment;
@Repository
@Profile("hql-query")
public class ForumCommentDaoHQLImpl implements ForumCommentDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ForumComment insert(ForumComment forumComment) {
		em.persist(forumComment);
		return forumComment;
	}

	@Override
	public List<ForumComment> getByForumId(Long forumId) {
		final String sql = "SELECT "
				+ "fc "
				+ "FROM "
				+ "ForumComment fc "
				+ "WHERE "
				+ "fc.forum.id = :forumId "
				+ "ORDER BY "
				+ "fc.createdAt DESC";
		
		final List<ForumComment> comments = this.em.createQuery(sql, ForumComment.class)
				.setParameter("forumId", forumId)
				.getResultList();
		
		return comments;
	}

}
