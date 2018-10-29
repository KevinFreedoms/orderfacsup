package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;


/**
 * EpMouldGoodsDelPrice entity. @author MyEclipse Persistence Tools
 */

public class EpMouldGoodsDelPrice  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recKey;
     private String mouldId;
     private String orgId;
     private String goodsId;
     private Double price;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpMouldGoodsDelPrice() {
    }

    
    /** full constructor */
    public EpMouldGoodsDelPrice(String mouldId, String orgId, String goodsId, Double price, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.mouldId = mouldId;
        this.orgId = orgId;
        this.goodsId = goodsId;
        this.price = price;
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

    public String getMouldId() {
        return this.mouldId;
    }
    
    public void setMouldId(String mouldId) {
        this.mouldId = mouldId;
    }

    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
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