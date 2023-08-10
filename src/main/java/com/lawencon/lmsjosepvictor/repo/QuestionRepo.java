package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long>{
	List<Question> getQuestionByLearningTaskId(Long taskId);
	
	@Query(value = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_question tq "
				+ "INNER JOIN "
				+ "t_question_options tqo ON tqo.question_id = tq.id "
				+ "WHERE "
				+ "tq.learning_task_id = ?1 ", nativeQuery = true)
	List<Question> getQuestionWithOptions(Long taskId);
	
	@Query(value = "SELECT "
				+ "tq.id, tq.* "
				+ "FROM "
				+ "t_question tq "
				+ "INNER JOIN "
				+ "t_question_file tqf ON tqf.question_id = tq.id "
				+ "WHERE "
				+ "tq.learning_task_id = ?1 "
				+ "GROUP BY "
				+ "tq.id ", nativeQuery = true)
	List<Question> getQuestionWithFile(Long taskId);
	
	@Query(value = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	t_question tq "
				+ "LEFT JOIN "
				+ "t_question_file tqf ON tqf.question_id = tq.id "
				+ "LEFT JOIN "
				+ "t_question_options tqo ON tqo.question_id = tq.id "
				+ "WHERE "
				+ "	tq.learning_task_id = ?1  "
				+ "AND "
				+ "		tq.id NOT IN "
				+ "			( "
				+ "				SELECT "
				+ "					tqf2.question_id "
				+ "				FROM "
				+ "					t_question_file tqf2"
				+ "			) "
				+ "	AND "
				+ "		tq.id NOT IN "
				+ "			( "
				+ "				SELECT "
				+ "					tqo.question_id "
				+ "				FROM "
				+ "					t_question_options tqo "
				+ "			)", nativeQuery = true)
	List<Question> getEssayQuestion(Long taskId);
}
