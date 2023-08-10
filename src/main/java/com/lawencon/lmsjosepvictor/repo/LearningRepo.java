package com.lawencon.lmsjosepvictor.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.Learning;

@Repository
public interface LearningRepo extends JpaRepository<Learning, Long>{
	List<Learning> getLearningByClassroomId(Long classId);

	@Query(value = "SELECT "
				+ "tl.id, tc.class_name "
				+ "FROM "
				+ "t_learning tl "
				+ "INNER JOIN "
				+ "t_classroom tc ON tl.class_id = tc.id "
				+ "WHERE "
				+ "tl.class_id = ?1 AND tc.teacher_id = ?2 "
				+ "AND tl.learning_date_start BETWEEN ?3 and ?4 "
				+ "AND tl.learning_date_end BETWEEN ?3 and ?4 ", nativeQuery = true)
	Learning getLearningByClassAndTeacher(Long classId, Long teacherId, LocalDateTime dateStart, LocalDateTime dateEnd);

}
