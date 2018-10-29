package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;


/**
 * EpProduct entity. @author MyEclipse Persistence Tools
 */

public class EpProduct  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String goodsId;
     private String date;
     private String subGoodsId;
     private String subGoodsName;
     private Double weight;
     private String singlePacking;
     private Double packingCount;
     private String standards;
     private String mark;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpProduct() {
    }

    
    /** full constructor */
    public EpProduct(Integer reckey, String goodsId, String date, String subGoodsId, String subGoodsName, Double weight, String singlePacking, Double packingCount, String standards, String mark, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.reckey = reckey;
        this.goodsId = goodsId;
        this.date = date;
        this.subGoodsId = subGoodsId;
        this.subGoodsName = subGoodsName;
        this.weight = weight;
        this.singlePacking = singlePacking;
        this.packingCount = packingCount;
        this.standards = standards;
        this.mark = mark;
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

    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String getSubGoodsId() {
        return this.subGoodsId;
    }
    
    public void setSubGoodsId(String subGoodsId) {
        this.subGoodsId = subGoodsId;
    }

    public String getSubGoodsName() {
        return this.subGoodsName;
    }
    
    public void setSubGoodsName(String subGoodsName) {
        this.subGoodsName = subGoodsName;
    }

    public Double getWeight() {
        return this.weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
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

    public String getMark() {
        return this.mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
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