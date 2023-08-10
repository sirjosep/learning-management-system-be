package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.RoleDao;
import com.lawencon.lmsjosepvictor.model.Role;
import com.lawencon.lmsjosepvictor.repo.RoleRepo;

@Repository
@Profile("springdatajpa-query")
public class RoleDaoSpringDataJPAImpl implements RoleDao {

	private final RoleRepo roleRepo;

	RoleDaoSpringDataJPAImpl(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public List<Role> getAll() {
		return roleRepo.findAll();
	}

	@Override
	public Role getById(Long id) {
		final Role role = this.roleRepo.findById(id).get();
		return role;
	}

	@Override
	public Role getByRoleCode(String roleCode) {
		return roleRepo.getRoleByRoleCode(roleCode);
	}
}
