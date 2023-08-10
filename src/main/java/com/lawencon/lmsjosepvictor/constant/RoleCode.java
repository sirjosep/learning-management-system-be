package com.lawencon.lmsjosepvictor.constant;

public enum RoleCode {
	ADMIN("ADM"), TEACHER("TCH"), STUDENT("STD");
	
	public final String roleCode;
	
	RoleCode(String roleCode){
		this.roleCode = roleCode;
	}
}
