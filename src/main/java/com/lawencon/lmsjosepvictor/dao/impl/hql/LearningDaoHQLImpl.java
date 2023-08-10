package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.model.Learning;
@Repository
@Profile("hql-query")
public class LearningDaoHQLImpl implements LearningDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Learning> getLearningListsByClassId(Long classId) {
		final String sql = "SELECT "
				+ "l "
				+ "FROM "
				+ "Learning l "
				+ "WHERE "
				+ "l.classroom.id = :classId ";
		
		final List<Learning> learnings = this.em.createQuery(sql, Learning.class)
				.setParameter("classId", classId)
				.getResultList();
		
		return learnings;
	}

	@Override
	public Learning getById(Long learnId) {
		final Learning learning = this.em.find(Learning.class, learnId);
		return learning;
	}

	@Override
	public Learning insert(Learning learning) {
		this.em.persist(learning);
		return learning;
	}
	
	@Override
	public Learning update(Learning learning) {
		final Learning updatedLearning = this.em.merge(learning);
		this.em.flush();
		return updatedLearning;
	}

	@Override
	public Learning getLearningByClassAndTeacher(Long classId, Long teacherId, LocalDateTime dateStart, LocalDateTime dateEnd) {
		final String sql = "SELECT "
				+ "l.id, l.classroom.className "
				+ "FROM "
				+ "Learning l "
				+ "WHERE "
				+ "l.classroom.id = :classId AND l.classroom.teacher.id = :teacherId "
				+ "AND l.learningDateStart BETWEEN :dateStart and :dateEnd "
				+ "AND l.learningDateEnd BETWEEN :dateStart and :dateEnd ";
		
		try {
			final Object learnObj = this.em.createQuery(sql)
					.setParameter("classId", classId)
					.setParameter("teacherId", teacherId)
					.setParameter("dateStart", dateStart)
					.setParameter("dateEnd", dateEnd)
					.getSingleResult();
			
			final Object[] learnObjArr = (Object[]) learnObj;
			Learning learning = null;
			if(learnObjArr.length > 0) {
				learning = new Learning();
				learning.setId(Long.valueOf(learnObjArr[0].toString()));
				
				final Classroom classroom = new Classroom();
				classroom.setClassName(learnObjArr[1].toString());
				
				learning.setClassroom(classroom);
			}
			
			return learning;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
