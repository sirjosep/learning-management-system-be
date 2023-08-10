package com.lawencon.lmsjosepvictor.dto.learning.task.question;

import java.util.List;

public class QuestionResDto {
	private Long id;
	private String questionCode;
	private String questionBody;
	private List<QuestionOptionDto> options;
	private List<Long> fileIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<QuestionOptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOptionDto> options) {
		this.options = options;
	}

	public List<Long> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<Long> fileIds) {
		this.fileIds = fileIds;
	}
	
}
