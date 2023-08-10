package com.lawencon.lmsjosepvictor.dao;

import com.lawencon.lmsjosepvictor.model.File;

public interface FileDao {
	
	File getFileById(Long id);
	
	File insertFile(File file);
	
	boolean deleteFileById(Long id);
}
