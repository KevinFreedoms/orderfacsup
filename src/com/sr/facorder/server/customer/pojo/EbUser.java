package com.sr.facorder.server.customer.pojo;

import java.sql.Timestamp;

/**
 * EbUser entity. @author MyEclipse Persistence Tools
 */

public class EbUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
	private String userId;
	private String userName;
	private String passWord;
	private String phone;
	private String level;
	private String orgId;
	private Timestamp createDate;
	private String createUserId;
	private Timestamp lastupDate;
	private String lastupdateUserId;

	// Constructors

	/** default constructor */
	public EbUser() {
	}

	/** full constructor */
	public EbUser(String userId, String userName, String passWord,
			String phone, String level, String orgId, Timestamp createDate,
			String createUserId, Timestamp lastupDate, String lastupdateUserId) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.phone = phone;
		this.level = level;
		this.orgId = orgId;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.lastupDate = lastupDate;
		this.lastupdateUserId = lastupdateUserId;
	}

	// Property accessors

	public Integer getReckey() {
		return this.reckey;
	}

	public void setReckey(Integer reckey) {
		this.reckey = reckey;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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