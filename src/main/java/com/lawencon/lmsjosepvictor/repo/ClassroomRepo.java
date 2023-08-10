package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.Classroom;

@Repository
public interface ClassroomRepo extends JpaRepository<Classroom, Long>{
	@Query(value = "SELECT "
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
			+ "tec.student_id = ?1 ", nativeQuery = true)
	List<Classroom> getEnrolledClassByStudId(Long studId);
	
	@Query(value = "SELECT "
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
				+ "			tu.id = ?1 "
				+ " ) ", nativeQuery = true)
	List<Classroom> getUnenrolledClassByStudId(Long studId);
	
	List<Classroom> getClassroomByTeacherId(Long teacherId);
	
	Classroom getClassroomByClassCode(String classCode);
}
