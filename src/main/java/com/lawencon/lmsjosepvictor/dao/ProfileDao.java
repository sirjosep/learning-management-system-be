package com.lawencon.lmsjosepvictor.dao;

import com.lawencon.lmsjosepvictor.model.Profile;

public interface ProfileDao {
	Profile getById(Long id);
	Profile insertProfile(Profile profile);
	Profile update(Profile profile);
}
