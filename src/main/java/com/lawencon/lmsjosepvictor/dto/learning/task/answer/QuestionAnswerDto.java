package com.lawencon.lmsjosepvictor.dto.learning.task.answer;

import java.util.List;

import com.lawencon.lmsjosepvictor.dto.file.FileDto;

public class QuestionAnswerDto {

	private Long questionId;
	private Long questionOptions;
	private String questionAnswer;
	private List<FileDto> answerFiles;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(Long questionOptions) {
		this.questionOptions = questionOptions;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public List<FileDto> getAnswerFiles() {
		return answerFiles;
	}

	public void setAnswerFiles(List<FileDto> answerFiles) {
		this.answerFiles = answerFiles;
	}


}
