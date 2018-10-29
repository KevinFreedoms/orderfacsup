package com.sr.facorder.server.distribute.pojo;

import java.sql.Timestamp;

/**
 * EpDeliveryCollect entity. @author MyEclipse Persistence Tools
 */

public class EpDeliveryCollect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recKey;
	private String bookingId;
	private String deliveryDate;
	private String deliveryTaskId;
	private String deliveryTaskName;
	private String orgId;
	private String orgName;
	private Double totalMoney;
	private Integer status;
	private String ref1;
	private String ref2;
	private String ref3;
	private String remark;
	private Timestamp createDate;
	private String createUserId;
	private Timestamp lastupDate;
	private String lastupdateUserId;

	// Constructors

	/** default constructor */
	public EpDeliveryCollect() {
	}

	/** full constructor */
	public EpDeliveryCollect(String bookingId, String deliveryDate,
			String deliveryTaskId, String deliveryTaskName, String orgId,
			String orgName, Double totalMoney, Integer status, String ref1,
			String ref2, String ref3, String remark, Timestamp createDate,
			String createUserId, Timestamp lastupDate, String lastupdateUserId) {
		this.bookingId = bookingId;
		this.deliveryDate = deliveryDate;
		this.deliveryTaskId = deliveryTaskId;
		this.deliveryTaskName = deliveryTaskName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.totalMoney = totalMoney;
		this.status = status;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.ref3 = ref3;
		this.remark = remark;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.lastupDate = lastupDate;
		this.lastupdateUserId = lastupdateUserId;
	}

	// Property accessors

	public Integer getRecKey() {
		return this.recKey;
	}

	public void setRecKey(Integer recKey) {
		this.recKey = recKey;
	}

	public String getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTaskId() {
		return this.deliveryTaskId;
	}

	public void setDeliveryTaskId(String deliveryTaskId) {
		this.deliveryTaskId = deliveryTaskId;
	}

	public String getDeliveryTaskName() {
		return this.deliveryTaskName;
	}

	public void setDeliveryTaskName(String deliveryTaskName) {
		this.deliveryTaskName = deliveryTaskName;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getRef3() {
		return this.ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getLastupDate() {
		return this.lastupDate;
	}

	public void setLastupDate(Timestamp lastupDate) {
		this.lastupDate = lastupDate;
	}

	public String getLastupdateUserId() {
		return this.lastupdateUserId;
	}

	public void setLastupdateUserId(String lastupdateUserId) {
		this.lastupdateUserId = lastupdateUserId;
	}

}