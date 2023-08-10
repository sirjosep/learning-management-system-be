package com.lawencon.lmsjosepvictor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmsjosepvictor.dao.FileDao;
import com.lawencon.lmsjosepvictor.dao.LearningDao;
import com.lawencon.lmsjosepvictor.dao.LearningMaterialDao;
import com.lawencon.lmsjosepvictor.dao.LearningMaterialFileDao;
import com.lawencon.lmsjosepvictor.dto.InsertResDto;
import com.lawencon.lmsjosepvictor.dto.learning.material.LearningMaterialReqDto;
import com.lawencon.lmsjosepvictor.dto.learning.material.LearningMaterialResDto;
import com.lawencon.lmsjosepvictor.model.File;
import com.lawencon.lmsjosepvictor.model.Learning;
import com.lawencon.lmsjosepvictor.model.LearningMaterial;
import com.lawencon.lmsjosepvictor.model.LearningMaterialFile;
import com.lawencon.lmsjosepvictor.service.LearningMaterialService;
import com.lawencon.lmsjosepvictor.service.PrincipalService;

@Service
public class LearningMaterialServiceImpl implements LearningMaterialService {

	private final LearningDao learningDao;
	private final LearningMaterialDao learningMaterialDao;
	private final LearningMaterialFileDao learningMaterialFileDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public LearningMaterialServiceImpl(LearningMaterialDao learningMaterialDao, FileDao fileDao,
			LearningMaterialFileDao learningMaterialFileDao, LearningDao learningDao,
			PrincipalService principalService) {
		this.learningDao = learningDao;
		this.learningMaterialDao = learningMaterialDao;
		this.learningMaterialFileDao = learningMaterialFileDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Override
	public List<LearningMaterialResDto> getMaterialByLearnId(Long learnId) {
		final List<LearningMaterialResDto> responses = new ArrayList<>();

		learningMaterialDao.getMaterialByLearnId(learnId).forEach(m -> {
			final LearningMaterialResDto response = new LearningMaterialResDto();
			response.setId(m.getId());
			response.setLearningMaterialTitle(m.getLearningMaterialTitle());
			response.setLearningMaterialBody(m.getLearningMaterialBody());

			final List<LearningMaterialFile> files = learningMaterialFileDao.getMaterialFileByMaterialId(m.getId());
			if (files.size() > 0) {
				final List<Long> fileResponses = new ArrayList<>();
				files.forEach(f -> {
					fileResponses.add(f.getFileMaterial().getId());
				});

				response.setFileIds(fileResponses);
			}

			responses.add(response);
		});

		return responses;
	}

	@Transactional
	@Override
	public InsertResDto insert(LearningMaterialReqDto data) {
		final InsertResDto response = new InsertResDto();
		final LearningMaterial learningMaterial = new LearningMaterial();
		learningMaterial.setLearningMaterialTitle(data.getLearningMaterialTitle());
		learningMaterial.setLearningMaterialBody(data.getLearningMaterialBody());

		final Learning learning = learningDao.getById(data.getLearnId());
		learningMaterial.setLearning(learning);

		learningMaterial.setCreatedBy(principalService.getId());

		final LearningMaterial newMaterial = learningMaterialDao.insert(learningMaterial);

		final List<LearningMaterialFile> files = new ArrayList<>();
		if (data.getFiles() != null) {
			if (data.getFiles().get(0) != null) {
				for (int i = 0; i < data.getFiles().size(); i++) {
					final File file = new File();
					file.setFiles(data.getFiles().get(i).getFiles());
					file.setFileFormat(data.getFiles().get(i).getFileFormat());
					file.setCreatedBy(1L);

					fileDao.insertFile(file);

					final LearningMaterialFile learningMaterialFile = new LearningMaterialFile();
					learningMaterialFile.setLearningMaterial(newMaterial);
					learningMaterialFile.setFileMaterial(file);
					learningMaterialFile.setCreatedBy(principalService.getId());

					final LearningMaterialFile newMaterialFile = learningMaterialFileDao.insert(learningMaterialFile);
					files.add(newMaterialFile);
				}
			}
		}

		response.setId(newMaterial.getId());
		response.setMessage("Material for learning : " + learning.getLearningTitle() + " is successfully created");

		return response;
	}

}
