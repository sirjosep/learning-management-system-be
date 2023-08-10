package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_review")
public class Review extends Base {
	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private User student;

	@Column(name = "score", nullable = false)
	private Float score;

	@Column(name = "notes")
	private String notes;
	
	@ManyToOne
	@JoinColumn(name = "learning_task_id", nullable = false)
	private LearningTask learningTask;

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
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

	public LearningTask getLearningTask() {
		return learningTask;
	}

	public void setLearningTask(LearningTask learningTask) {
		this.learningTask = learningTask;
	}

}
