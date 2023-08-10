package com.lawencon.lmsjosepvictor.dto.review;

public class UpdateReviewReqDto {
	private Long reviewId;
	private Long taskId;
	private Long studentId;
	private Float essayScore;
	private Float fileScore;
	private String notes;
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Float getEssayScore() {
		return essayScore;
	}
	public void setEssayScore(Float essayScore) {
		this.essayScore = essayScore;
	}
	public Float getFileScore() {
		return fileScore;
	}
	public void setFileScore(Float fileScore) {
		this.fileScore = fileScore;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
