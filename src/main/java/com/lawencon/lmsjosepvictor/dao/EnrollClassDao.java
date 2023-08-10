package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.EnrollClass;

public interface EnrollClassDao {
	EnrollClass insert(EnrollClass enrollClass);
	
	List<EnrollClass> getAllByClass(Long classId);

	EnrollClass getById(Long id);
}
