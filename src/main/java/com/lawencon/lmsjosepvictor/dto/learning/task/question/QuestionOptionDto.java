package com.lawencon.lmsjosepvictor.dto.learning.task.question;

public class QuestionOptionDto {
	private Long id;
	private String optionsLabel;
	private boolean isCorrect;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
