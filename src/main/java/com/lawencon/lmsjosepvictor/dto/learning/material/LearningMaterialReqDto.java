package com.lawencon.lmsjosepvictor.dto.learning.material;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.file.FileDto;

public class LearningMaterialReqDto {

	private String learningMaterialTitle;
	private String learningMaterialBody;
	private Long learnId;
	private List<FileDto> files;

	public String getLearningMaterialTitle() {
		return learningMaterialTitle;
	}

	public void setLearningMaterialTitle(String learningMaterialTitle) {
		this.learningMaterialTitle = learningMaterialTitle;
	}

	public String getLearningMaterialBody() {
		return learningMaterialBody;
	}

	public void setLearningMaterialBody(String learningMaterialBody) {
		this.learningMaterialBody = learningMaterialBody;
	}

	public Long getLearnId() {
		return learnId;
	}

	public void setLearnId(Long learnId) {
		this.learnId = learnId;
	}

	public List<FileDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileDto> files) {
		this.files = files;
	}

}
