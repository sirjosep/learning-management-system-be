package com.lawencon.lmsjosepvictor.controller;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {
	
	private final FileService fileService;

	FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("{id}")
    public ResponseEntity<?> getFileById(@PathVariable("id") Long id) {
        final File file = fileService.getById(id);
        final String fileName = "attachment";
        final byte[] fileBytes = Base64.getDecoder().decode(file.getFiles());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "." + file.getFileFormat())
                .body(fileBytes);
   }
}
