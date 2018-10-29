package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;


/**
 * EpOrderLog entity. @author MyEclipse Persistence Tools
 */

public class EpOrderLog  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long reckey;
     private String type;
     private String tableName;
     private String pk;
     private String memo;
     private Integer status;
     private String location;
     private String orgId;
     private String orgName;
     private String deliveryTaskId;
     private String deliveryTaskName;
     private String mouldId;
     private String mouldName;
     private Timestamp deliveryDate;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpOrderLog() {
    	Timestamp now = new Timestamp(System.currentTimeMillis());
    	this.type = "";
        this.tableName = "";
        this.pk = "";
        this.memo = "";
        this.status = 1;
        this.location = "";
        this.orgId = "";
        this.orgName = "";
        this.deliveryTaskId = "";
        this.deliveryTaskName = "";
        this.mouldId = "";
        this.mouldName = "";
        this.deliveryDate = now;
        this.createDate = now;
        this.createUserId = "";
        this.lastupDate = now;
        this.lastupdateUserId = "";
    }

    
    /** full constructor */
    public EpOrderLog(String type, String tableName, String pk, String memo, Integer status, String location, String orgId, String orgName, String deliveryTaskId, String deliveryTaskName, String mouldId, String mouldName, Timestamp deliveryDate, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.type = type;
        this.tableName = tableName;
        this.pk = pk;
        this.memo = memo;
        this.status = status;
        this.location = location;
        this.orgId = orgId;
        this.orgName = orgName;
        this.deliveryTaskId = deliveryTaskId;
        this.deliveryTaskName = deliveryTaskName;
        this.mouldId = mouldId;
        this.mouldName = mouldName;
        this.deliveryDate = deliveryDate;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.lastupDate = lastupDate;
        this.lastupdateUserId = lastupdateUserId;
    }

   
    // Property accessors

    public Long getReckey() {
        return this.reckey;
    }
    
    public void setReckey(Long reckey) {
        this.reckey = reckey;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPk() {
        return this.pk;
    }
    
    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
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

    public String getMouldId() {
        return this.mouldId;
    }
    
    public void setMouldId(String mouldId) {
        this.mouldId = mouldId;
    }

    public String getMouldName() {
        return this.mouldName;
    }
    
    public void setMouldName(String mouldName) {
        this.mouldName = mouldName;
    }

    public Timestamp getDeliveryDate() {
        return this.deliveryDate;
    }
    
    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
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