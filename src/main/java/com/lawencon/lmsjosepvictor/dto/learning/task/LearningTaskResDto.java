package com.lawencon.lmsjosepvictor.dto.learning.task;

public class LearningTaskResDto {
	private Long id;
	private String learningTaskTitle;
	private String learningTaskDesc;
	private String learningTaskStart;
	private String learningTaskEnd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLearningTasktitle() {
		return learningTaskTitle;
	}

	public void setLearningTasktitle(String learningTasktitle) {
		this.learningTaskTitle = learningTasktitle;
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

}
