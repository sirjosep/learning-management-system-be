package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ClassroomDao;
import com.lawencon.lmsjosepvictor.model.Classroom;
import com.lawencon.lmsjosepvictor.model.Profile;
import com.lawencon.lmsjosepvictor.model.User;
@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ClassroomDaoImpl implements ClassroomDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Classroom> getEnrolledClassByStudId(Long studId){
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_classroom tc "
				+ "INNER JOIN "
				+ "t_enroll_class tec ON tec.class_id = tc.id "
				+ "INNER JOIN "
				+ "t_user tu ON tc.teacher_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE "
				+ "tec.student_id = :studId ";

		final List<Classroom> classrooms = this.em.createNativeQuery(sql, Classroom.class)
				.setParameter("studId", studId)
				.getResultList();
		
		return classrooms;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Classroom> getUnenrolledClassByStudId(Long studId){
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_classroom tc "
				+ "INNER JOIN "
				+ "t_user tu ON tc.teacher_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE "
				+ "tc.id "
				+ "NOT IN "
				+ " ( "
				+ "		SELECT "
				+ "			tec.class_id "
				+ "		FROM "
				+ "			t_enroll_class tec "
				+ "		INNER JOIN "
				+ "			t_user tu ON tec.student_id = tu.id "
				+ "		WHERE "
				+ "			tu.id = :studId "
				+ " ) ";
		
		final List<Classroom> classrooms = this.em.createNativeQuery(sql, Classroom.class)
				.setParameter("studId", studId)
				.getResultList();
		
		return classrooms;
	}

	@Override
	public Classroom getClassroomByClassCode(String classCode){
		final String sql = "SELECT "
				+ "tc.id, tc.class_name, tc.teacher_id, tp.profile_name "
				+ "FROM "
				+ "t_classroom tc "
				+ "INNER JOIN "
				+ "t_user tu ON tc.teacher_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE "
				+ "tc.class_code = :classCode";
		
		try {
			final Object classroomObj = this.em.createNativeQuery(sql)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Classroom> getClassByTeacherId(Long teacherId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_classroom tc "
				+ "WHERE "
				+ "tc.teacher_id = :teacherId";
		
		final List<Classroom> classrooms = this.em.createNativeQuery(sql, Classroom.class)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Classroom> getAll() {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_classroom tc "
				+ "INNER JOIN "
				+ "t_user tu ON tc.teacher_id = tu.id ";
		
		final List<Classroom> classrooms = this.em.createNativeQuery(sql, Classroom.class).getResultList();
		return classrooms;
	}

}
