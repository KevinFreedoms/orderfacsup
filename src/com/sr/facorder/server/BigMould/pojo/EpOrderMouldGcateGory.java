package com.sr.facorder.server.BigMould.pojo;

import java.sql.Timestamp;


/**
 * EpOrderMouldGcateGory entity. @author MyEclipse Persistence Tools
 */

public class EpOrderMouldGcateGory  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recKey;
     private String mouldId;
     private String mouldName;
     private String gcategoryId;
     private String gcategoryName;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpOrderMouldGcateGory() {
    }

    
    /** full constructor */
    public EpOrderMouldGcateGory(String mouldId, String mouldName, String gcategoryId, String gcategoryName, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.mouldId = mouldId;
        this.mouldName = mouldName;
        this.gcategoryId = gcategoryId;
        this.gcategoryName = gcategoryName;
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

    public String getMouldName() {
        return this.mouldName;
    }
    
    public void setMouldName(String mouldName) {
        this.mouldName = mouldName;
    }

    public String getGcategoryId() {
        return this.gcategoryId;
    }
    
    public void setGcategoryId(String gcategoryId) {
        this.gcategoryId = gcategoryId;
    }

    public String getGcategoryName() {
        return this.gcategoryName;
    }
    
    public void setGcategoryName(String gcategoryName) {
        this.gcategoryName = gcategoryName;
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