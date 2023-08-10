package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.EnrollClass;

@Repository
public interface EnrollClassRepo extends JpaRepository<EnrollClass, Long>{
	List<EnrollClass> getEnrollClassByClassroomId(Long classId);
}
