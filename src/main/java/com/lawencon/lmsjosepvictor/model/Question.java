package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_question")
public class Question extends Base {

	@Column(name = "question_code", length = 5, unique = true, nullable = false)
	private String questionCode;

	@Column(name = "question_body", nullable = false)
	private String questionBody;
	
	@ManyToOne
	@JoinColumn(name = "learning_task_id", nullable = false)
	private LearningTask learningTask;

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	public LearningTask getLearningTask() {
		return learningTask;
	}

	public void setLearningTask(LearningTask learningTask) {
		this.learningTask = learningTask;
	}

}
