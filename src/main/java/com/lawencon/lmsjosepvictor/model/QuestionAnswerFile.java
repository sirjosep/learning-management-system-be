package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_question_answer_file")
public class QuestionAnswerFile extends Base {
	@ManyToOne
	@JoinColumn(name = "question_answer_id", nullable = false)
	private QuestionAnswer questionAnswer;
	
	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
