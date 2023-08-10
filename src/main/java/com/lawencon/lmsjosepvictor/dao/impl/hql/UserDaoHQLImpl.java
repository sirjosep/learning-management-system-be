package com.lawencon.lmsjosepvictor.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.model.Profile;
import com.lawencon.lmsjosepvictor.model.Role;
import com.lawencon.lmsjosepvictor.model.User;

@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class UserDaoHQLImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> getAllUsers()  {
		final String sql = "SELECT u FROM User u";
		
		final List<User> users = this.em.createQuery(sql, User.class).getResultList();
		return users;
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode)  {
		final String sql = "SELECT "
				+ "u "
				+ "FROM "
				+ "User u "
				+ "WHERE u.role.roleCode = :roleCode";
		
		final List<User> users = this.em.createQuery(sql, User.class)
				.setParameter("roleCode", roleCode)
				.getResultList();
		
		return users;
	}

	@Override
	public User insert(User user)  {
		this.em.persist(user);
		return user;
	}

	
	@Override
	public User getByEmail(String email)  {
		final String sql = "SELECT "
				+ "u.id, u.password, u.profile.profileName, u.role.roleCode "
				+ "FROM "
				+ "User u "
				+ "WHERE "
				+ "u.email = :email";
		try {
			final Object userObj = this.em.createQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] userArr = (Object[])userObj;
			
			User user = null;
			if(userArr.length > 0) {
				user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
				user.setPassword(userArr[1].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[2].toString());
				user.setProfile(profile);
				
				final Role role = new Role();
				role.setRoleCode(userArr[3].toString());
				user.setRole(role);
			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getById(Long id)  {
		final User user = this.em.find(User.class, id);
		return user;
	}
	
	@Override
	public User update(User user)  {
		final User newUser = this.em.merge(user);
		this.em.flush();
		return newUser;
	}
}
