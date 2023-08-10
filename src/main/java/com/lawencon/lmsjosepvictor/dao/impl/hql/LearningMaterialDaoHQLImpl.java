package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningMaterialDao;
import com.lawencon.lmsjosepvictor.model.LearningMaterial;
@Repository
@Profile("hql-query")
public class LearningMaterialDaoHQLImpl implements LearningMaterialDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<LearningMaterial> getMaterialByLearnId(Long learnId) {
		final String sql = "SELECT "
				+ "lm "
				+ "FROM "
				+ "LearningMaterial lm "
				+ "WHERE "
				+ "lm.learning.id = :learnId";
		
		final List<LearningMaterial> materials= this.em.createQuery(sql, LearningMaterial.class)
				.setParameter("learnId", learnId)
				.getResultList();
		
		return materials;
	}

	@Override
	public LearningMaterial insert(LearningMaterial learningMaterial) {
		this.em.persist(learningMaterial);
		return learningMaterial;
	}

}
