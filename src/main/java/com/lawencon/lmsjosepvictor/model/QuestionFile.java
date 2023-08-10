package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_question_file")
public class QuestionFile extends Base {
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;
	
	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
