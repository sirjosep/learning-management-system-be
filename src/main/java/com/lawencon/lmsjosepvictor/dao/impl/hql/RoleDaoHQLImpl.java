package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.RoleDao;
import com.lawencon.lmsjosepvictor.model.Role;
@Repository
@Profile("hql-query")
public class RoleDaoHQLImpl implements RoleDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Role> getAll()  {
		final String sql = "SELECT "
				+ "r "
				+ "FROM "
				+ "Role r ";
		
		final List<Role> roles = this.em.createQuery(sql, Role.class).getResultList();
		
		return roles;
	}

	@Override
	public Role getById(Long id)  {
		final Role role = this.em.find(Role.class, id);
		return role;
	}

	@Override
	public Role getByRoleCode(String roleCode)  {
		final String sql = "SELECT "
				+ "r.id "
				+ "FROM "
				+ "Role r "
				+ "WHERE "
				+ "r.roleCode = :roleCode";
		
		final Object roleObj = this.em.createQuery(sql)
				.setParameter("roleCode", roleCode)
				.getSingleResult();
		
		final Role role = new Role();
		role.setId(Long.valueOf(roleObj.toString()));
		
		return role;
	}
}
