package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningMaterialFileDao;
import com.lawencon.lmsjosepvictor.model.LearningMaterialFile;
@Repository
@Profile("hql-query")
public class LearningMaterialFileDaoHQLImpl implements LearningMaterialFileDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<LearningMaterialFile> getMaterialFileByMaterialId(Long materialId) {
		final String sql = "SELECT "
				+ "lmf "
				+ "FROM "
				+ "LearningMaterialFile lmf "
				+ "WHERE "
				+ "lmf.learningMaterial.id = :materialId";
		final List<LearningMaterialFile> materialFiles = this.em.createQuery(sql, LearningMaterialFile.class)
				.setParameter("materialId", materialId)
				.getResultList();
		
		return materialFiles;
	}

	@Override
	public LearningMaterialFile insert(LearningMaterialFile learningFileMaterial) {
		this.em.persist(learningFileMaterial);
		return learningFileMaterial;
	}

}
