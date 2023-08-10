package com.lawencon.lmsjosepvictor.dto.learning.material;

import java.util.List;

public class LearningMaterialResDto {

	private Long id;
	private String learningMaterialTitle;
	private String learningMaterialBody;
	private List<Long> fileIds;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public List<Long> getFileIds() {
		return fileIds;
	}
	public void setFileIds(List<Long> fileIds) {
		this.fileIds = fileIds;
	}
	
	
}
