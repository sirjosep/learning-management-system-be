package com.lawencon.lmsjosepvictor.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_attendance")
public class Attendance extends Base{
	@ManyToOne
	@JoinColumn(name = "learning_id", nullable = false)
	private Learning learning;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private User student;
	
	@Column(name = "attendance_time", nullable = false)
	private LocalDateTime attendanceTime;
	
	@Column(name = "is_approved", nullable = false)
	private Boolean isApproved;

	public Learning getLearning() {
		return learning;
	}

	public void setLearning(Learning learning) {
		this.learning = learning;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public LocalDateTime getAttendanceTime() {
		return attendanceTime;
	}

	public void setAttendanceTime(LocalDateTime attendanceTime) {
		this.attendanceTime = attendanceTime;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
}
