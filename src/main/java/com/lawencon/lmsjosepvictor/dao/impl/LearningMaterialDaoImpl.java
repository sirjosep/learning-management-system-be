package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningMaterialDao;
import com.lawencon.lmsjosepvictor.model.LearningMaterial;
@Repository
@Profile("native-query")
public class LearningMaterialDaoImpl implements LearningMaterialDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningMaterial> getMaterialByLearnId(Long learnId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_learning_material "
				+ "WHERE "
				+ "learning_id = :learnId";
		
		final List<LearningMaterial> materials= this.em.createNativeQuery(sql, LearningMaterial.class)
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
