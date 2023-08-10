package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningTaskDao;
import com.lawencon.lmsjosepvictor.model.LearningTask;
@Repository
@Profile("hql-query")
public class LearningTaskDaoHQLImpl implements LearningTaskDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<LearningTask> getTaskByLearningId(Long learnId) {
		final String sql = "SELECT "
				+ "lt "
				+ "FROM "
				+ "LearningTask lt "
				+ "WHERE "
				+ "lt.learning.id = :learnId";
		
		final List<LearningTask> tasks = this.em.createQuery(sql, LearningTask.class)
				.setParameter("learnId", learnId)
				.getResultList();
		
		return tasks;
	}

	@Override
	public LearningTask insert(LearningTask learningTask) {
		this.em.persist(learningTask);
		return learningTask;
	}

	@Override
	public LearningTask getById(Long id) {
		final LearningTask learningTask = this.em.find(LearningTask.class, id);
		return learningTask;
	}

}
