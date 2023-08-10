package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.RoleDao;
import com.lawencon.lmsjosepvictor.model.Role;
@Repository
@Profile("native-query")
public class RoleDaoImpl implements RoleDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_role tr ";
		
		final List<Role> roles = this.em.createNativeQuery(sql, Role.class).getResultList();
		
		return roles;
	}

	@Override
	public Role getById(Long id) {
		final Role role = this.em.find(Role.class, id);
		return role;
	}

	@Override
	public Role getByRoleCode(String roleCode) {
		final String sql = "SELECT "
				+ "tr.id "
				+ "FROM "
				+ "t_role tr "
				+ "WHERE "
				+ "tr.role_code = :roleCode";
		
		final Object roleObj = this.em.createNativeQuery(sql)
				.setParameter("roleCode", roleCode)
				.getSingleResult();
		
		final Role role = new Role();
		role.setId(Long.valueOf(roleObj.toString()));
		
		return role;
	}
}
