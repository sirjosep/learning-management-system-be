package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.UserDao;
import com.lawencon.lmsjosepvictor.model.User;
import com.lawencon.lmsjosepvictor.repo.UserRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class UserDaoSpringDataJPAImpl implements UserDao {

	private final UserRepo userRepo;

	UserDaoSpringDataJPAImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		return userRepo.getUserByRoleRoleCode(roleCode);
	}

	@Override
	public User insert(User user) {
		this.userRepo.save(user);
		return user;
	}

	@Override
	public User getByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}

	@Override
	public User getById(Long id) {
		final User user = this.userRepo.findById(id).get();
		return user;
	}

	@Override
	public User update(User user) {
		final User newUser = userRepo.saveAndFlush(user);
		return newUser;
	}
}
