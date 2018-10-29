package com.sr.facorder.server.season.pojo;

import java.sql.Timestamp;

/**
 * EpGoodsSeason entity. @author MyEclipse Persistence Tools
 */

public class EpGoodsSeason  implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer recKey;
	private String goodsId;
	private String goodsName;
	private Integer goodsType;
	private String startDate;
	private String endDate;
	private Timestamp createDate;
	private String createUserId;
	private Timestamp lastupDate;
	private String lastupdateUserId;

	// Constructors

	/** default constructor */
	public EpGoodsSeason() {
	}

	/** full constructor */
	public EpGoodsSeason(String goodsId, String goodsName, Integer goodsType,
			String startDate, String endDate, Timestamp createDate,
			String createUserId, Timestamp lastupDate, String lastupdateUserId) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsType() {
		return this.goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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