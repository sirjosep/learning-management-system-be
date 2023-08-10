package com.lawencon.lmsjosepvictor.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.model.Profile;
import com.lawencon.lmsjosepvictor.model.Role;
import com.lawencon.lmsjosepvictor.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		final String sql = "SELECT "
				+ "* "
				+ "FROM t_user tu "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id";

		final List<User> users = this.em.createNativeQuery(sql, User.class).getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_user tu " 
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id " 
				+ "WHERE "
				+ "tr.role_code = :roleCode";

		final List<User> users = this.em.createNativeQuery(sql, User.class)
				.setParameter("roleCode", roleCode)
				.getResultList();

		return users;
	}

	@Override
	public User insert(User user) {
		this.em.persist(user);
		return user;
	}

	@Override
	public User getByEmail(String email) {
		final String sql = "SELECT " 
				+ "tu.id, tu.password, tp.profile_name, tf.id as fileId, tr.role_code " 
				+ "FROM " 
				+ "t_user tu " 
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id " 
				+ "INNER JOIN " 
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "LEFT JOIN "
				+ "t_file tf ON tp.file_id = tf.id "
				+ "WHERE "
				+ "tu.email = :email";
		try {
			final Object userObj = this.em.createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();

			final Object[] userArr = (Object[]) userObj;

			User user = null;
			if (userArr.length > 0) {
				user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
				user.setPassword(userArr[1].toString());

				final Profile profile = new Profile();
				profile.setProfileName(userArr[2].toString());
				
				if(userArr[3] != null) {
					final File file = new File();
					file.setId(Long.valueOf(userArr[3].toString()));
					
					profile.setFile(file);
				}
				user.setProfile(profile);

				final Role role = new Role();
				role.setRoleCode(userArr[4].toString());
				user.setRole(role);
			}

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getById(Long id) {
		final User user = this.em.find(User.class, id);
		return user;
	}

	@Override
	public User update(User user) {
		final User newUser = this.em.merge(user);
		this.em.flush();
		return newUser;
	}
}
