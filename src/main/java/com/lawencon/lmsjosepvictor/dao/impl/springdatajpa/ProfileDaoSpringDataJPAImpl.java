package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.ProfileDao;
import com.lawencon.lmsjosepvictor.model.Profile;
import com.lawencon.lmsjosepvictor.repo.ProfileRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ProfileDaoSpringDataJPAImpl implements ProfileDao {

	private final ProfileRepo profileRepo;
	
	ProfileDaoSpringDataJPAImpl(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}

	@Override
	public Profile getById(Long id) {
		final Profile profile = this.profileRepo.findById(id).get();
		return profile;
	}

	@Override
	public Profile insertProfile(Profile profile) {
		this.profileRepo.save(profile);
		return profile;
	}

	@Override
	public Profile update(Profile profile) {
		final Profile updatedProfile = this.profileRepo.saveAndFlush(profile);
		return updatedProfile;
	}
}
