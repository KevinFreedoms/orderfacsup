package com.sr.facorder.server.distribute.pojo;

import java.sql.Timestamp;

/**
 * EpDeliveryDetail entity. @author MyEclipse Persistence Tools
 */

public class EpDeliveryDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recKey;
	private String bookingId;
	private String goodsId;
	private String goodsName;
	private String packingName;
	private String standards;
	private Double ordercount;
	private Double arrivecount;
	private Double price;
	private Double taxprice;
	private Double rate;
	private Double rateprice;
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
	public EpDeliveryDetail() {
	}

	/** full constructor */
	public EpDeliveryDetail(String bookingId, String goodsId, String goodsName,
			String packingName, String standards, Double ordercount,
			Double arrivecount, Double price, Double taxprice, Double rate,
			Double rateprice, String ref1, String ref2, String ref3,
			String remark, Timestamp createDate, String createUserId,
			Timestamp lastupDate, String lastupdateUserId) {
		this.bookingId = bookingId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.packingName = packingName;
		this.standards = standards;
		this.ordercount = ordercount;
		this.arrivecount = arrivecount;
		this.price = price;
		this.taxprice = taxprice;
		this.rate = rate;
		this.rateprice = rateprice;
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

	public String getPackingName() {
		return this.packingName;
	}

	public void setPackingName(String packingName) {
		this.packingName = packingName;
	}

	public String getStandards() {
		return this.standards;
	}

	public void setStandards(String standards) {
		this.standards = standards;
	}

	public Double getOrdercount() {
		return this.ordercount;
	}

	public void setOrdercount(Double ordercount) {
		this.ordercount = ordercount;
	}

	public Double getArrivecount() {
		return this.arrivecount;
	}

	public void setArrivecount(Double arrivecount) {
		this.arrivecount = arrivecount;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTaxprice() {
		return this.taxprice;
	}

	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getRateprice() {
		return this.rateprice;
	}

	public void setRateprice(Double rateprice) {
		this.rateprice = rateprice;
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