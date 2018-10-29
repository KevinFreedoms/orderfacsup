package com.sr.facorder.server.main.pojo;

import java.sql.Timestamp;


/**
 * EpMould entity. @author MyEclipse Persistence Tools
 */

public class EpMould  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String mouldId;
     private String mouldName;
     private String deliverytaskId;
     private Integer type;
     private String startDate;
     private String endDate;
     private Integer intervalDays;
     private String orderTime;
     private String mealTypeId;
     private String mealtypeName;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpMould() {
    }

    
    /** full constructor */
    public EpMould(Integer reckey, String mouldId, String mouldName, String deliverytaskId, Integer type, String startDate, String endDate, Integer intervalDays, String orderTime, String mealTypeId, String mealtypeName, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.reckey = reckey;
        this.mouldId = mouldId;
        this.mouldName = mouldName;
        this.deliverytaskId = deliverytaskId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.intervalDays = intervalDays;
        this.orderTime = orderTime;
        this.mealTypeId = mealTypeId;
        this.mealtypeName = mealtypeName;
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

    public String getDeliverytaskId() {
        return this.deliverytaskId;
    }
    
    public void setDeliverytaskId(String deliverytaskId) {
        this.deliverytaskId = deliverytaskId;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
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

    public Integer getIntervalDays() {
        return this.intervalDays;
    }
    
    public void setIntervalDays(Integer intervalDays) {
        this.intervalDays = intervalDays;
    }

    public String getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getMealTypeId() {
        return this.mealTypeId;
    }
    
    public void setMealTypeId(String mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public String getMealtypeName() {
        return this.mealtypeName;
    }
    
    public void setMealtypeName(String mealtypeName) {
        this.mealtypeName = mealtypeName;
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