package com.lawencon.lmsjosepvictor.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ForumDao;
import com.lawencon.lmsjosepvictor.model.Forum;
@Repository
@Profile("native-query")
public class ForumDaoImpl implements ForumDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Forum getForumByLearningId(Long learningId){
		final String sql = "SELECT "
				+ "tf.id, tf.forum_title, tf.forum_body "
				+ "FROM "
				+ "t_forum tf "
				+ "WHERE "
				+ "tf.learning_id = :learningId";
		try {
			final Object forumObj = this.em.createNativeQuery(sql)
					.setParameter("learningId", learningId)
					.getSingleResult();
			
			final Object[] forumObjArr = (Object[]) forumObj;
			
			Forum forum = null;
			if(forumObjArr.length > 0) {
				forum = new Forum();
				forum.setId(Long.valueOf(forumObjArr[0].toString()));
				forum.setForumTitle(forumObjArr[1].toString());
				if (forumObjArr[2] != null) {
					forum.setForumBody(forumObjArr[2].toString());	
				} else {
					forum.setForumBody("-");
				}
			}
			
			return forum;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Forum getForumById(Long id) {
		final Forum forum = this.em.find(Forum.class, id);
		return forum;
	}

	@Override
	public Forum insert(Forum forum) {
		this.em.persist(forum);
		return forum;
	}

}
