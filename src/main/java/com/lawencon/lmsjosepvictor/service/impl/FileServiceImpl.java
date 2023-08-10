package com.lawencon.lmsjosepvictor.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	private final FileDao fileDao;
	
	FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public File getById(Long id) {
		return fileDao.getFileById(id);
	}

}
