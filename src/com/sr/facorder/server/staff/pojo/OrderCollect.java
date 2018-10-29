package com.sr.facorder.server.staff.pojo;

public class OrderCollect {
	private String orgId;
	private String mouldName;
	private String orderId;
	private Integer status;
	private String ref2;
	private String deliveryDate;
	private String orgName;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getMouldName() {
		return mouldName;
	}
	public void setMouldName(String mouldName) {
		this.mouldName = mouldName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Override
	public String toString() {
		return "OrderCollect [orgId=" + orgId + ", mouldName=" + mouldName + ", orderId=" + orderId + ", status="
				+ status + ", ref2=" + ref2 + ", deliveryDate=" + deliveryDate + ", orgName=" + orgName + "]";
	}
	
	

}
