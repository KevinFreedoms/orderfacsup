package com.sr.facorder.server.delivery.pojo;

public class SupplerDelivery {
	private String bookingId;
	private String orgId;
	private String supplierId;
	private String supplierName;
	private int goodsCategoryNum;
	private double  ordercount;
	private double arrivecount;
	private Integer status;
	 public SupplerDelivery() {
		// TODO Auto-generated constructor stub
	}
	public SupplerDelivery(String bookingId,String orgId,String supplierId,
			String supplierName, int goodsCategoryNum, double ordercount,
			double arrivecount, Integer status) {
		super();
		this.bookingId = bookingId;
		this.orgId = orgId;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.goodsCategoryNum = goodsCategoryNum;
		this.ordercount = ordercount;
		this.arrivecount = arrivecount;
		this.status = status;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getGoodsCategoryNum() {
		return goodsCategoryNum;
	}
	public void setGoodsCategoryNum(int goodsCategoryNum) {
		this.goodsCategoryNum = goodsCategoryNum;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
