package com.lawencon.lmsjosepvictor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	List<User> getUserByRoleRoleCode(String roleCode);
	
	User getUserByEmail(String email);
}
