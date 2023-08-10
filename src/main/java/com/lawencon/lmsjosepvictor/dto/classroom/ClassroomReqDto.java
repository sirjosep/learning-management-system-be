package com.lawencon.lmsjosepvictor.dto.classroom;

public class ClassroomReqDto {
	private String classCode;
	private String className;
	private Long teacherId;
	private String files;
	private String fileFormat;

	public String getClassCode() {
		return classCode;
	}

	public String getClassName() {
		return className;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public String getFiles() {
		return files;
	}

	public String getFileFormat() {
		return fileFormat;
	}

}
