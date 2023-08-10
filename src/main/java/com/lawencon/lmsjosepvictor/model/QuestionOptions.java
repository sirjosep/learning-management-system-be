package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_question_options")
public class QuestionOptions extends Base {

	@Column(name = "options_label", nullable = false)
	private String optionsLabel;

	@Column(name = "is_correct", nullable = false)
	private boolean isCorrect;
	
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	public String getOptionsLabel() {
		return optionsLabel;
	}

	public void setOptionsLabel(String optionsLabel) {
		this.optionsLabel = optionsLabel;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
