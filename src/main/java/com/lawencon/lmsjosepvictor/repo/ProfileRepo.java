package com.lawencon.lmsjosepvictor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.model.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long>{
}
