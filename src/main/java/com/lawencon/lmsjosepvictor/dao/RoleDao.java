package com.lawencon.lmsjosepvictor.dao;

import java.util.List;

import com.lawencon.lmsjosepvictor.model.Role;

public interface RoleDao {
	List<Role> getAll();
	
	Role getById(Long id);
	Role getByRoleCode(String roleCode);
}
