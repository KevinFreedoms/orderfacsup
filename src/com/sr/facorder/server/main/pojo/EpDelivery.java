package com.sr.facorder.server.main.pojo;

import java.sql.Timestamp;


/**
 * EpDelivery entity. @author MyEclipse Persistence Tools
 */

public class EpDelivery  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String deliveryTaskId;
     private String deliveryTaskName;
     private Integer deliveryFrequent;
     private Integer week1;
     private Integer week2;
     private Integer week3;
     private Integer week4;
     private Integer week5;
     private Integer week6;
     private Integer week7;
     private Integer monthDay1;
     private Integer monthDay2;
     private Integer monthDay3;
     private String arrivalTime;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpDelivery() {
    }

    
    /** full constructor */
    public EpDelivery(Integer reckey, String deliveryTaskId, String deliveryTaskName, Integer deliveryFrequent, Integer week1, Integer week2, Integer week3, Integer week4, Integer week5, Integer week6, Integer week7, Integer monthDay1, Integer monthDay2, Integer monthDay3, String arrivalTime, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.reckey = reckey;
        this.deliveryTaskId = deliveryTaskId;
        this.deliveryTaskName = deliveryTaskName;
        this.deliveryFrequent = deliveryFrequent;
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.week4 = week4;
        this.week5 = week5;
        this.week6 = week6;
        this.week7 = week7;
        this.monthDay1 = monthDay1;
        this.monthDay2 = monthDay2;
        this.monthDay3 = monthDay3;
        this.arrivalTime = arrivalTime;
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

    public Integer getDeliveryFrequent() {
        return this.deliveryFrequent;
    }
    
    public void setDeliveryFrequent(Integer deliveryFrequent) {
        this.deliveryFrequent = deliveryFrequent;
    }

    public Integer getWeek1() {
        return this.week1;
    }
    
    public void setWeek1(Integer week1) {
        this.week1 = week1;
    }

    public Integer getWeek2() {
        return this.week2;
    }
    
    public void setWeek2(Integer week2) {
        this.week2 = week2;
    }

    public Integer getWeek3() {
        return this.week3;
    }
    
    public void setWeek3(Integer week3) {
        this.week3 = week3;
    }

    public Integer getWeek4() {
        return this.week4;
    }
    
    public void setWeek4(Integer week4) {
        this.week4 = week4;
    }

    public Integer getWeek5() {
        return this.week5;
    }
    
    public void setWeek5(Integer week5) {
        this.week5 = week5;
    }

    public Integer getWeek6() {
        return this.week6;
    }
    
    public void setWeek6(Integer week6) {
        this.week6 = week6;
    }

    public Integer getWeek7() {
        return this.week7;
    }
    
    public void setWeek7(Integer week7) {
        this.week7 = week7;
    }

    public Integer getMonthDay1() {
        return this.monthDay1;
    }
    
    public void setMonthDay1(Integer monthDay1) {
        this.monthDay1 = monthDay1;
    }

    public Integer getMonthDay2() {
        return this.monthDay2;
    }
    
    public void setMonthDay2(Integer monthDay2) {
        this.monthDay2 = monthDay2;
    }

    public Integer getMonthDay3() {
        return this.monthDay3;
    }
    
    public void setMonthDay3(Integer monthDay3) {
        this.monthDay3 = monthDay3;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
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