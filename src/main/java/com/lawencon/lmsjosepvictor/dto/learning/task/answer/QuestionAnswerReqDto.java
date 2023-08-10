package com.lawencon.lmsjosepvictor.dto.learning.task.answer;

import java.util.List;

public class QuestionAnswerReqDto {
	private Long taskId;
	private List<QuestionAnswerDto> answers;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<QuestionAnswerDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionAnswerDto> answers) {
		this.answers = answers;
	}

}
