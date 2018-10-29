package com.sr.facorder.server.distribute.pojo;


public class DistributeCollect {
	
	private String bookingId;
	private String deliveryDate;
	private String deliveryTaskId;
	private String deliveryTaskName;
	private String orgId;
	private String orgName;
	private double disCount;
	private int status;
	private double ordercount;
	private double arrivecount;
	
	
	public DistributeCollect(){
		
	}
	public DistributeCollect(String bookingId, String deliveryDate,
			String deliveryTaskId, String deliveryTaskName, String orgId,
			String orgName, double disCount,int status,double ordercount,double arrivecount) {
		super();
		this.bookingId = bookingId;
		this.deliveryDate = deliveryDate;
		this.deliveryTaskId = deliveryTaskId;
		this.deliveryTaskName = deliveryTaskName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.disCount = disCount;
		this.status = status;
		this.ordercount= ordercount;
		this.arrivecount = arrivecount;
	}
	
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryTaskId() {
		return deliveryTaskId;
	}
	public void setDeliveryTaskId(String deliveryTaskId) {
		this.deliveryTaskId = deliveryTaskId;
	}
	public String getDeliveryTaskName() {
		return deliveryTaskName;
	}
	public void setDeliveryTaskName(String deliveryTaskName) {
		this.deliveryTaskName = deliveryTaskName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public double getDisCount() {
		return disCount;
	}
	public void setDisCount(double disCount) {
		this.disCount = disCount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getOrdercount() {
		return ordercount;
	}
	public void setOrdercount(double ordercount) {
		this.ordercount = ordercount;
	}
	public double getArrivecount() {
		return arrivecount;
	}
	public void setArrivecount(double arrivecount) {
		this.arrivecount = arrivecount;
	}

	
	
	
	
	
	

}
