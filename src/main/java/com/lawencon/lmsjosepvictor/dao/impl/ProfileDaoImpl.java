package com.lawencon.lmsjosepvictor.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ProfileDao;
import com.lawencon.lmsjosepvictor.model.Profile;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ProfileDaoImpl implements ProfileDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Profile getById(Long id) {
		final Profile profile = this.em.find(Profile.class, id);
		return profile;
	}

	@Override
	public Profile insertProfile(Profile profile) {
		this.em.persist(profile);
		return profile;
	}

	@Override
	public Profile update(Profile profile) {
		final Profile updatedProfile = this.em.merge(profile);
		this.em.flush();
		return updatedProfile;
	}
}
