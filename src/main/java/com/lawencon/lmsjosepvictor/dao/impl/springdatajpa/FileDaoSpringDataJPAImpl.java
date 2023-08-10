package com.lawencon.lmsjosepvictor.dao.impl.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.repo.FileRepo;
@Repository
@Profile("springdatajpa-query")
public class FileDaoSpringDataJPAImpl implements FileDao{
	
	private final FileRepo fileRepo;
	
	FileDaoSpringDataJPAImpl(FileRepo fileRepo) {
		this.fileRepo = fileRepo;
	}

	@Override
	public File getFileById(Long id) {
		final File file = fileRepo.findById(id).get();
		return file;
	}

	@Override
	public File insertFile(File file) {
		fileRepo.save(file);
		return file;
	}

	@Override
	public boolean deleteFileById(Long id) {
		final int result = fileRepo.removeFile(id);
		
		return result > 0;
	}

}
