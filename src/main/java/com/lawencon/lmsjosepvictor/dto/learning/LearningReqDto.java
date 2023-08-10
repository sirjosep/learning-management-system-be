package com.lawencon.lmsjosepvictor.dto.learning;

public class LearningReqDto {

	private String learningTitle;
	private String learningDateStart;
	private String learningDateEnd;
	private Long classId;

	public String getLearningTitle() {
		return learningTitle;
	}

	public void setLearningTitle(String learningTitle) {
		this.learningTitle = learningTitle;
	}

	public String getLearningDateStart() {
		return learningDateStart;
	}

	public void setLearningDateStart(String learningDateStart) {
		this.learningDateStart = learningDateStart;
	}

	public String getLearningDateEnd() {
		return learningDateEnd;
	}

	public void setLearningDateEnd(String learningDateEnd) {
		this.learningDateEnd = learningDateEnd;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

}
