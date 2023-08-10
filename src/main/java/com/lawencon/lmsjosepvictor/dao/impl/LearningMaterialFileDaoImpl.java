package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningMaterialFileDao;
import com.lawencon.lmsjosepvictor.model.LearningMaterialFile;
@Repository
@Profile("native-query")
public class LearningMaterialFileDaoImpl implements LearningMaterialFileDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningMaterialFile> getMaterialFileByMaterialId(Long materialId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_learning_material_file tlmf "
				+ "INNER JOIN "
				+ "t_file tf ON tlmf.file_material_id = tf.id "
				+ "WHERE "
				+ "tlmf.learning_material_id = :materialId";
		final List<LearningMaterialFile> materialFiles = this.em.createNativeQuery(sql, LearningMaterialFile.class)
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
