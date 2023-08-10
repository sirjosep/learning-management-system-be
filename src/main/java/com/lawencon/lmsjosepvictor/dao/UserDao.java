package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.User;

public interface UserDao {
	List<User> getAllUsers();
	
	List<User> getUserByRoleCode(String roleCode);
	
	User insert(User user);
	
	User getByEmail(String email);
	
	User getById(Long id);

	User update(User user);
}
