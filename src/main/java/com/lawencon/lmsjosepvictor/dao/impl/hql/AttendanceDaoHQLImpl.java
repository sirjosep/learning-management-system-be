package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.AttendanceDao;
import com.lawencon.lmsjosepvictor.model.Attendance;
import com.lawencon.lmsjosepvictor.model.Learning;
@Repository
@Profile("hql-query")
public class AttendanceDaoHQLImpl implements AttendanceDao{
	
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
				+ "a.isApproved, a.learning.learningTitle "
				+ "FROM "
				+ "Attendance a " 
				+ "WHERE "
				+ "a.student.id = :studId AND a.learning.id = :learnId";
		try {
			final Object attendObj = this.em.createQuery(sql)
					.setParameter("studId", studId)
					.setParameter("learnId", learnId)
					.getSingleResult();
			
			final Object[] attendObjArr = (Object[]) attendObj;
			
			Attendance attendance = null;
			if (attendObjArr.length > 0) {
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

	@Override
	public List<Attendance> getAllByLearning(Long learnId) {
		final String sql = "SELECT "
				+ "a "
				+ "FROM "
				+ "Attendance a "
				+ "WHERE "
				+ "a.learning.id = :learnId ";
		
		final List<Attendance> attendances = this.em.createQuery(sql, Attendance.class)
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
