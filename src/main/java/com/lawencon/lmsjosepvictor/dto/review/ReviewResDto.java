package com.lawencon.lmsjosepvictor.dto.review;

public class ReviewResDto {
	private Long id;
	private Long learningTaskId;
	private String learningTaskName;
	private Long studentId;
	private String studentName;
	private Float score;
	private String notes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLearningTaskName() {
		return learningTaskName;
	}
	public void setLearningTaskName(String learningTaskName) {
		this.learningTaskName = learningTaskName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Long getLearningTaskId() {
		return learningTaskId;
	}
	public void setLearningTaskId(Long learningTaskId) {
		this.learningTaskId = learningTaskId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	
}
