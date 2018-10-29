package com.sr.facorder.server.delivery.pojo;

import java.sql.Timestamp;


/**
 * EpDeliveryGoods entity. @author MyEclipse Persistence Tools
 */

public class EpDeliveryGoods  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recKey;
     private String bookingId;
     private String orgId;
     private String orgName;
     private String supplierId;
     private String supplierName;
     private Timestamp deliveryDate;
     private Integer exigent;
     private String goodsId;
     private String goodsName;
     private String packingName;
     private String standards;
     private Double ordercount;
     private Double arrivecount;
     private Integer status;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;
     private String contactAddress;
     private String contactWay;
     private Double deliverPrice;
     private String remark;
     private String ref1;
     private String ref2;
     private String ref3;


    // Constructors

    /** default constructor */
    public EpDeliveryGoods() {
    	Timestamp now = new Timestamp(System.currentTimeMillis());
    	this.bookingId = "";
        this.orgId = "";
        this.orgName = "";
        this.supplierId = "";
        this.supplierName = "";
        this.deliveryDate = now;
        this.exigent = 0;
        this.goodsId = "";
        this.goodsName = "";
        this.packingName = "";
        this.standards = "";
        this.ordercount = 0.00;
        this.arrivecount = 0.00;
        this.status = 0;
        this.createDate = now;
        this.createUserId = "";
        this.lastupDate = now;
        this.lastupdateUserId = "";
        this.contactAddress = "";
        this.contactWay = "";
        this.deliverPrice = 0.00;
        this.remark = "";
        this.ref1 = "";
        this.ref2 = "";
        this.ref3 = "";
    }

    
    /** full constructor */
    public EpDeliveryGoods(String bookingId, String orgId, String orgName, String supplierId, String supplierName, Timestamp deliveryDate, Integer exigent, String goodsId, String goodsName, String packingName, String standards, Double ordercount, Double arrivecount, Integer status, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId, String contactAddress, String contactWay, Double deliverPrice, String remark, String ref1, String ref2, String ref3) {
        this.bookingId = bookingId;
        this.orgId = orgId;
        this.orgName = orgName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.deliveryDate = deliveryDate;
        this.exigent = exigent;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.packingName = packingName;
        this.standards = standards;
        this.ordercount = ordercount;
        this.arrivecount = arrivecount;
        this.status = status;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.lastupDate = lastupDate;
        this.lastupdateUserId = lastupdateUserId;
        this.contactAddress = contactAddress;
        this.contactWay = contactWay;
        this.deliverPrice = deliverPrice;
        this.remark = remark;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
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

    public String getSupplierId() {
        return this.supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }
    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Timestamp getDeliveryDate() {
        return this.deliveryDate;
    }
    
    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getExigent() {
        return this.exigent;
    }
    
    public void setExigent(Integer exigent) {
        this.exigent = exigent;
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

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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

    public String getContactAddress() {
        return this.contactAddress;
    }
    
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactWay() {
        return this.contactWay;
    }
    
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
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