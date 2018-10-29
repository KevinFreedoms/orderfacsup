package com.sr.facorder.server.staff.pojo;

public class EBStaff {
	private String staffId;
	private String staffName;
	private String roleId;
	private String ref1;
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	@Override
	public String toString() {
		return "EBStaff [staffId=" + staffId + ", staffName=" + staffName + ", roleId=" + roleId + ", ref1=" + ref1
				+ "]";
	}
	

}
