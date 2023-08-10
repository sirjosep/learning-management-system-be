package com.lawencon.lmsjosepvictor.dto.learning.task.answer;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.file.FileDto;

public class QuestionAnswerResDto {
	private Long id;
	private String studentName;
	private String questionAnswer;
	private String questionOptions;
	private List<Long> fileIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(String questionOptions) {
		this.questionOptions = questionOptions;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<Long> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<Long> fileIds) {
		this.fileIds = fileIds;
	}
	
}
