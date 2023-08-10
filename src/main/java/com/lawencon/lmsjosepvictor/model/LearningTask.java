package com.lawencon.lmsjosepvictor.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "t_learning_task")
public class LearningTask extends Base {

	@Column(name = "learning_task_title", length = 50, nullable = false)
	private String learningTaskTitle;

	@Column(name = "learning_task_desc", nullable = false)
	private String learningTaskDesc;

	@Column(name = "learning_task_start", nullable = false)
	private LocalDateTime learningTaskStart;

	@Column(name = "learning_task_end", nullable = false)
	private LocalDateTime learningTaskEnd;
	
	@ManyToOne
	@JoinColumn(name = "learning_id", nullable = false)
	private Learning learning;

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

	public LocalDateTime getLearningTaskStart() {
		return learningTaskStart;
	}

	public void setLearningTaskStart(LocalDateTime learningTaskStart) {
		this.learningTaskStart = learningTaskStart;
	}

	public LocalDateTime getLearningTaskEnd() {
		return learningTaskEnd;
	}

	public void setLearningTaskEnd(LocalDateTime learningTaskEnd) {
		this.learningTaskEnd = learningTaskEnd;
	}

	public Learning getLearning() {
		return learning;
	}

	public void setLearning(Learning learning) {
		this.learning = learning;
	}

}
