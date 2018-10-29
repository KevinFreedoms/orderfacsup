package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;


/**
 * EpOrderMould entity. @author MyEclipse Persistence Tools
 */

public class EpOrderMould  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String mouldId;
     private String date;
     private String goodsId;
     private String goodsName;
     private String packingName;
     private String singlePacking;
     private Double packingCount;
     private String standards;
     private Double deliverPrice;
     private Integer orderNum;
     private Integer goodsFlag;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;
     private Integer packingType;
     private Double retailPrice;


    // Constructors

    /** default constructor */
    public EpOrderMould() {
    }

    
    /** full constructor */
    public EpOrderMould(String mouldId, String date, String goodsId, String goodsName, String packingName, String singlePacking, Double packingCount, String standards, Double deliverPrice, Integer orderNum, Integer goodsFlag, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId, Integer packingType, Double retailPrice) {
        this.mouldId = mouldId;
        this.date = date;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.packingName = packingName;
        this.singlePacking = singlePacking;
        this.packingCount = packingCount;
        this.standards = standards;
        this.deliverPrice = deliverPrice;
        this.orderNum = orderNum;
        this.goodsFlag = goodsFlag;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.lastupDate = lastupDate;
        this.lastupdateUserId = lastupdateUserId;
        this.packingType = packingType;
        this.retailPrice=retailPrice;
    }

   
    // Property accessors

    public Integer getReckey() {
        return this.reckey;
    }
    
    public void setReckey(Integer reckey) {
        this.reckey = reckey;
    }

    public String getMouldId() {
        return this.mouldId;
    }
    
    public void setMouldId(String mouldId) {
        this.mouldId = mouldId;
    }

    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
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

    public String getSinglePacking() {
        return this.singlePacking;
    }
    
    public void setSinglePacking(String singlePacking) {
        this.singlePacking = singlePacking;
    }

    public Double getPackingCount() {
        return this.packingCount;
    }
    
    public void setPackingCount(Double packingCount) {
        this.packingCount = packingCount;
    }

    public String getStandards() {
        return this.standards;
    }
    
    public void setStandards(String standards) {
        this.standards = standards;
    }

    public Double getDeliverPrice() {
        return this.deliverPrice;
    }
    
    public void setDeliverPrice(Double deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getGoodsFlag() {
        return this.goodsFlag;
    }
    
    public void setGoodsFlag(Integer goodsFlag) {
        this.goodsFlag = goodsFlag;
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

    public Integer getPackingType() {
        return this.packingType;
    }
    
    public void setPackingType(Integer packingType) {
        this.packingType = packingType;
    }
    

	public Double getRetailPrice() {
		return retailPrice;
	}


	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}


	@Override
	public String toString() {
		return "EpOrderMould [reckey=" + reckey + ", mouldId=" + mouldId + ", date=" + date + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", packingName=" + packingName + ", singlePacking=" + singlePacking
				+ ", packingCount=" + packingCount + ", standards=" + standards + ", deliverPrice=" + deliverPrice
				+ ", orderNum=" + orderNum + ", goodsFlag=" + goodsFlag + ", createDate=" + createDate
				+ ", createUserId=" + createUserId + ", lastupDate=" + lastupDate + ", lastupdateUserId="
				+ lastupdateUserId + ", packingType=" + packingType + ", retailPrice=" + retailPrice + "]";
	}


	
    







}