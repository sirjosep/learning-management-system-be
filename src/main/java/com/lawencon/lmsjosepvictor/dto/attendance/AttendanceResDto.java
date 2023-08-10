package com.lawencon.lmsjosepvictor.dto.attendance;

public class AttendanceResDto {
	private Long id;
	private String profileName;
	private String attendanceTime;
	private boolean isApprove;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}
}
