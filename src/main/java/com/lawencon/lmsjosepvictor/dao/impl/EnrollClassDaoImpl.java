package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.EnrollClassDao;
import com.lawencon.lmsjosepvictor.model.EnrollClass;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class EnrollClassDaoImpl implements EnrollClassDao {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<EnrollClass> getAllByClass(Long classId) {
		final String sql = "SELECT " 
					+ "* " 
					+ "FROM " 
					+ "t_enroll_class tec " 
					+ "INNER JOIN "
					+ "t_user tu ON tec.student_id = tu.id " 
					+ "INNER JOIN " 
					+ "t_profile tp ON tu.profile_id = tp.id "
					+ "WHERE " 
					+ "tec.class_id = :classId";

		final List<EnrollClass> enrollClasses = this.em.createNativeQuery(sql, EnrollClass.class)
				.setParameter("classId", classId)
				.getResultList();
		return enrollClasses;
	}

}
