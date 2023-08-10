package com.lawencon.lmsjosepvictor.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.model.File;
@Repository
@Profile("hql-query")
public class FileDaoHQLImpl implements FileDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public File getFileById(Long id) {
		final File file = this.em.find(File.class, id);
		return file;
	}

	@Override
	public File insertFile(File file) {
		this.em.persist(file);
		return file;
	}

	@Override
	public boolean deleteFileById(Long id) {
		final String sql = "DELETE "
				+ "FROM "
				+ "File f "
				+ "WHERE f.id = :id";
		final int result = this.em.createQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result > 0;
	}

}
