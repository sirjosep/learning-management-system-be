package com.lawencon.lmsjosepvictor.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.lmsjosepvictor.model.Learning;

public interface LearningDao {
	List<Learning> getLearningListsByClassId(Long classId);

	Learning getLearningByClassAndTeacher(Long classId, Long teacherId, LocalDateTime dateStart, LocalDateTime dateEnd);

	Learning getById(Long learnId);

	Learning insert(Learning learning);

	Learning update(Learning learning);
}
