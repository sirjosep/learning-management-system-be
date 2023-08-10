package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{
	@Query(value = "SELECT "
				+ "	((100)/( "
				+ "		SELECT "
				+ "			COUNT(DISTINCT tq.id) "
				+ "		FROM "
				+ "			t_question tq "
				+ "		INNER JOIN "
				+ "			t_learning_task tlt ON tq.learning_task_id  = tlt.id "
				+ "		INNER JOIN "
				+ "			t_question_options tqo2 ON tqo2.question_id = tq.id "
				+ "		WHERE "
				+ "			tlt.id = :taskId "
				+ "	))*COUNT(tqa.question_options_id) "
				+ "FROM "
				+ "	t_question_answer tqa "
				+ "INNER JOIN "
				+ "	t_question tq ON tqa.question_id = tq.id "
				+ "INNER JOIN "
				+ "	t_question_options tqo ON tqa.question_options_id = tqo.id "
				+ "INNER JOIN "
				+ "	t_user tu ON tqa.user_id = tu.id "
				+ "WHERE "
				+ "	tqa.user_id = :studentId AND tqo.is_correct = true "
				+ "GROUP BY "
				+ "tqa.user_id ", nativeQuery = true)
	Review getScoringForMultipleChoice(@Param("taskId")Long taskId, @Param("studentId")Long studentId);
	
	List<Review> getReviewByTeacherIdAndLearningTaskId(Long teacherId, Long taskId);
}
