package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_classroom")
public class Classroom extends Base{
	@Column(name = "class_code", length = 5, unique = true, nullable = false)
	private String classCode;

	@Column(name = "class_name", length = 5, nullable = false)
	private String className;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name = "class_thumb_file_id", nullable = false)
	private File classThumbFile;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public File getClassThumbFile() {
		return classThumbFile;
	}

	public void setClassThumbFile(File classThumbFile) {
		this.classThumbFile = classThumbFile;
	}

}
