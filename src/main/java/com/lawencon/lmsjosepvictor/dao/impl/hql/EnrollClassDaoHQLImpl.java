package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.EnrollClassDao;
import com.lawencon.lmsjosepvictor.model.EnrollClass;
@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class EnrollClassDaoHQLImpl implements EnrollClassDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public EnrollClass insert(EnrollClass enrollClass) {
		this.em.persist(enrollClass);
		return enrollClass;
	}
	
	@Override
	public EnrollClass getById(Long id) {
		final EnrollClass enrollClass = this.em.find(EnrollClass.class, id);
		return enrollClass;
	}

	@Override
	public List<EnrollClass> getAllByClass(Long classId) {
		final String sql = "SELECT "
				+ "ec "
				+ "FROM "
				+ "EnrollClass ec "
				+ "WHERE "
				+ "ec.classroom.id = :classId";
		
		final List<EnrollClass> enrollClasses = this.em.createQuery(sql, EnrollClass.class)
				.setParameter("classId", classId)
				.getResultList();
		return enrollClasses;
	}
	
	

}
