package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;


/**
 * EbOrderDetail entity. @author MyEclipse Persistence Tools
 */

public class EbOrderDetail  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String orderId;
     private String goodsId;
     private String goodsName;
     private String packingName;
     private String singlePacking;
     private Double packingCount;
     private String standards;
     private Double count;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;
     private Integer packingType;
     private Double deliverPrice;
     private String remark;
     private String ref1;
     private String ref2;
     private String ref3;


    // Constructors

    /** default constructor */
    public EbOrderDetail() {
    	Timestamp now = new Timestamp(System.currentTimeMillis());
    	this.orderId = "";
        this.goodsId = "";
        this.goodsName = "";
        this.packingName = "";
        this.singlePacking = "";
        this.packingCount = 0.00;
        this.standards = "";
        this.count = 0.00;
        this.createDate = now;
        this.createUserId = "";
        this.lastupDate = now;
        this.lastupdateUserId = "";
        this.packingType = 0;
        this.deliverPrice = 0.00;
        this.remark = "";
        this.ref1 = "";
        this.ref2 = "";
        this.ref3 = "";
    }

    
    /** full constructor */
    public EbOrderDetail(String orderId, String goodsId, String goodsName, String packingName, String singlePacking, Double packingCount, String standards, Double count, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId, Integer packingType, Double deliverPrice, String remark, String ref1, String ref2, String ref3) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.packingName = packingName;
        this.singlePacking = singlePacking;
        this.packingCount = packingCount;
        this.standards = standards;
        this.count = count;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.lastupDate = lastupDate;
        this.lastupdateUserId = lastupdateUserId;
        this.packingType = packingType;
        this.deliverPrice = deliverPrice;
        this.remark = remark;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
    }

   
    // Property accessors

    public Integer getReckey() {
        return this.reckey;
    }
    
    public void setReckey(Integer reckey) {
        this.reckey = reckey;
    }

    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Double getCount() {
        return this.count;
    }
    
    public void setCount(Double count) {
        this.count = count;
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

    public Double getDeliverPrice() {
        return this.deliverPrice;
    }
    
    public void setDeliverPrice(Double deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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
   








}