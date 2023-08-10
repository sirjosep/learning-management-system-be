package com.lawencon.lmsjosepvictor.dto.learning.task.question;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.file.FileDto;

public class QuestionReqDto {
	private String questionCode;
	private String questionBody;
	private Long taskId;
	private List<QuestionOptionDto> options;
	private List<FileDto> files;

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

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<QuestionOptionDto> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOptionDto> options) {
		this.options = options;
	}

	public List<FileDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileDto> files) {
		this.files = files;
	}

}
