package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.AttendanceDao;
import com.lawencon.lmsjosepvictor.model.Attendance;
import com.lawencon.lmsjosepvictor.model.Learning;

@Repository
@Profile("native-query")
public class AttendanceDaoImpl implements AttendanceDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Attendance insert(Attendance attendance) {
		this.em.persist(attendance);
		return attendance;
	}

	@Override
	public Attendance getByUserAndLearning(Long studId, Long learnId) {
		final String sql = "SELECT "
				+ "ta.is_approved, tl.learning_title "
				+ "FROM "
				+ "t_attendance ta "
				+ "INNER JOIN "
				+ "t_learning tl ON ta.learning_id = tl.id "
				+ "WHERE "
				+ "ta.student_id = :studId AND ta.learning_id = :learnId";
		try {
			final Object attendObj = this.em.createNativeQuery(sql)
					.setParameter("studId", studId)
					.setParameter("learnId", learnId)
					.getSingleResult();
			
			final Object[] attendObjArr = (Object[]) attendObj;
			Attendance attendance = null;
			
			if(attendObjArr.length > 0) {
				attendance = new Attendance();
				attendance.setIsApproved(Boolean.valueOf(attendObjArr[0].toString()));
				
				final Learning learning = new Learning();
				learning.setLearningTitle(attendObjArr[1].toString());
				
				attendance.setLearning(learning);
			}
			
			return attendance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attendance> getAllByLearning(Long learnId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_attendance ta "
				+ "INNER JOIN "
				+ "t_learning tl ON ta.learning_id = tl.id "
				+ "INNER JOIN "
				+ "t_user tu ON ta.student_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE "
				+ "ta.learning_id = :learnId ";
		
		final List<Attendance> attendances = this.em.createNativeQuery(sql, Attendance.class)
				.setParameter("learnId", learnId)
				.getResultList();
		
		return attendances;
	}

	@Override
	public Attendance getById(Long id) {
		final Attendance attendance =  this.em.find(Attendance.class, id);
		return attendance;
	}

}
