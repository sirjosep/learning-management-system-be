package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.model.Profile;
import com.lawencon.lmsjosepvictor.model.User;
@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class ClassroomDaoHQLImpl implements ClassroomDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Classroom> getEnrolledClassByStudId(Long studId){
		final String sql = "SELECT "
				+ "c "
				+ "FROM "
				+ "Classroom c "
				+ "INNER JOIN "
				+ "EnrollClass ec ON ec.classroom.id = c.id "
				+ "WHERE "
				+ "ec.student.id = :studId ";

		final List<Classroom> classrooms = this.em.createQuery(sql, Classroom.class)
				.setParameter("studId", studId)
				.getResultList();
		
		return classrooms;
	}

	@Override
	public List<Classroom> getUnenrolledClassByStudId(Long studId){
		final String sql = "SELECT "
				+ "c "
				+ "FROM "
				+ "Classroom c "
				+ "WHERE "
				+ "c.id "
				+ "NOT IN "
				+ " ( "
				+ "		SELECT "
				+ "			ec.classroom.id "
				+ "		FROM "
				+ "			EnrollClass ec "
				+ "		WHERE "
				+ "			ec.student.id = :studId "
				+ " ) ";
		
		final List<Classroom> classrooms = this.em.createQuery(sql, Classroom.class)
				.setParameter("studId", studId)
				.getResultList();
		
		return classrooms;
	}

	@Override
	public Classroom getClassroomByClassCode(String classCode){
		final String sql = "SELECT "
				+ "c.id, c.className, c.teacher.id, c.teacher.profile.profileName "
				+ "FROM "
				+ "Classroom c "
				+ "WHERE "
				+ "c.classCode = :classCode";
		
		try {
			final Object classroomObj = this.em.createQuery(sql)
					.setParameter("classCode", classCode)
					.getSingleResult();
			
			final Object[] classroomObjArr = (Object[]) classroomObj;
			Classroom classroom = null;
			if (classroomObjArr.length > 0) {
				classroom = new Classroom();
				classroom.setId(Long.valueOf(classroomObjArr[0].toString()));
				classroom.setClassName(classroomObjArr[1].toString());
				
				final User teacher = new User();
				teacher.setId(Long.valueOf(classroomObjArr[2].toString()));
				
				final Profile profile = new Profile();
				profile.setProfileName(classroomObjArr[3].toString());
				teacher.setProfile(profile);
				
				classroom.setTeacher(teacher);
			}
			
			return classroom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Classroom> getClassByTeacherId(Long teacherId) {
		final String sql = "SELECT "
				+ "c "
				+ "FROM "
				+ "Classroom c "
				+ "WHERE "
				+ "c.teacher.id = :teacherId";
		
		final List<Classroom> classrooms = this.em.createQuery(sql, Classroom.class)
				.setParameter("teacherId", teacherId)
				.getResultList();
		
		return classrooms;
	}

	@Override
	public Classroom getById(Long id) {
		final Classroom classroom = this.em.find(Classroom.class, id);
		return classroom;
	}

	@Override
	public Classroom insert(Classroom classroom) {
		this.em.persist(classroom);
		return classroom;
	}

	@Override
	public List<Classroom> getAll() {
		final String sql = "SELECT "
				+ "c "
				+ "FROM "
				+ "Classroom c ";
		
		final List<Classroom> classrooms = this.em.createQuery(sql, Classroom.class).getResultList();
		return classrooms;
	}

}
