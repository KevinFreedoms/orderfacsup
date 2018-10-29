package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;


/**
 * EbOrderCollect entity. @author MyEclipse Persistence Tools
 */

public class EbOrderCollect  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String orderId;
     private String deliveryTaskId;
     private String mouldId;
     private String orgId;
     private String orgName;
     private Timestamp deliveryDate;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;
     private String remark;
     private String ref1;
     private String ref2;
     private String ref3;


    // Constructors

    /** default constructor */
    public EbOrderCollect() {
    	Timestamp now = new Timestamp(System.currentTimeMillis());
    	this.orderId = "";
        this.deliveryTaskId = "";
        this.mouldId = "";
        this.orgId = "";
        this.orgName = "";
        this.deliveryDate = now;
        this.createDate = now;
        this.createUserId = "";
        this.lastupDate = now;
        this.lastupdateUserId = "";
        this.remark = "";
        this.ref1 = "";
        this.ref2 = "";
        this.ref3 = "";
    }

    
    /** full constructor */
    public EbOrderCollect(String orderId, String deliveryTaskId, String mouldId, String orgId, String orgName, Timestamp deliveryDate, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId, String remark, String ref1, String ref2, String ref3) {
        this.orderId = orderId;
        this.deliveryTaskId = deliveryTaskId;
        this.mouldId = mouldId;
        this.orgId = orgId;
        this.orgName = orgName;
        this.deliveryDate = deliveryDate;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.lastupDate = lastupDate;
        this.lastupdateUserId = lastupdateUserId;
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

    public String getDeliveryTaskId() {
        return this.deliveryTaskId;
    }
    
    public void setDeliveryTaskId(String deliveryTaskId) {
        this.deliveryTaskId = deliveryTaskId;
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

    public String getOrgName() {
        return this.orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Timestamp getDeliveryDate() {
        return this.deliveryDate;
    }
    
    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
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