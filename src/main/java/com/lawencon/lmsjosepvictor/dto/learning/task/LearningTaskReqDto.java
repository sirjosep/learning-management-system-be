package com.lawencon.lmsjosepvictor.dto.learning.task;

public class LearningTaskReqDto {
	private String learningTaskTitle;
	private String learningTaskDesc;
	private String learningTaskStart;
	private String learningTaskEnd;
	private Long learnId;

	public String getLearningTaskTitle() {
		return learningTaskTitle;
	}

	public void setLearningTaskTitle(String learningTaskTitle) {
		this.learningTaskTitle = learningTaskTitle;
	}

	public String getLearningTaskDesc() {
		return learningTaskDesc;
	}

	public void setLearningTaskDesc(String learningTaskDesc) {
		this.learningTaskDesc = learningTaskDesc;
	}

	public String getLearningTaskStart() {
		return learningTaskStart;
	}

	public void setLearningTaskStart(String learningTaskStart) {
		this.learningTaskStart = learningTaskStart;
	}

	public String getLearningTaskEnd() {
		return learningTaskEnd;
	}

	public void setLearningTaskEnd(String learningTaskEnd) {
		this.learningTaskEnd = learningTaskEnd;
	}

	public Long getLearnId() {
		return learnId;
	}

	public void setLearnId(Long learnId) {
		this.learnId = learnId;
	}

}
