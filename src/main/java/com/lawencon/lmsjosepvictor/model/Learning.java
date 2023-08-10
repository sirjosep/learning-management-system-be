package com.lawencon.lmsjosepvictor.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_learning")
public class Learning extends Base {

	@Column(name = "learning_title", length = 50, nullable = false)
	private String learningTitle;

	@Column(name = "learning_date_start", nullable = false)
	private LocalDateTime learningDateStart;

	@Column(name = "learning_date_end", nullable = false)
	private LocalDateTime learningDateEnd;
	
	@ManyToOne
	@JoinColumn(name = "class_id", nullable = false)
	private Classroom classroom;

	public String getLearningTitle() {
		return learningTitle;
	}

	public void setLearningTitle(String learningTitle) {
		this.learningTitle = learningTitle;
	}

	public LocalDateTime getLearningDateStart() {
		return learningDateStart;
	}

	public void setLearningDateStart(LocalDateTime learningDateStart) {
		this.learningDateStart = learningDateStart;
	}

	public LocalDateTime getLearningDateEnd() {
		return learningDateEnd;
	}

	public void setLearningDateEnd(LocalDateTime learningDateEnd) {
		this.learningDateEnd = learningDateEnd;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

}
