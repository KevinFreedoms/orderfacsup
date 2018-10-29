package com.sr.facorder.server.booking.pojo;

import java.sql.Timestamp;


/**
 * EpOrg entity. @author MyEclipse Persistence Tools
 */

public class EpOrg  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String orgId;
     private String orgName;
     private String npemployeeName;
     private String contactWay;
     private String contactAddress;
     private String parentOrgId;
     private String parentOrgName;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpOrg() {
    }

    
    /** full constructor */
    public EpOrg(Integer reckey, String orgId, String orgName, String npemployeeName, String contactWay, String contactAddress, String parentOrgId, String parentOrgName, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.reckey = reckey;
        this.orgId = orgId;
        this.orgName = orgName;
        this.npemployeeName = npemployeeName;
        this.contactWay = contactWay;
        this.contactAddress = contactAddress;
        this.parentOrgId = parentOrgId;
        this.parentOrgName = parentOrgName;
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

    public String getNpemployeeName() {
        return this.npemployeeName;
    }
    
    public void setNpemployeeName(String npemployeeName) {
        this.npemployeeName = npemployeeName;
    }

    public String getContactWay() {
        return this.contactWay;
    }
    
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getContactAddress() {
        return this.contactAddress;
    }
    
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getParentOrgId() {
        return this.parentOrgId;
    }
    
    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getParentOrgName() {
        return this.parentOrgName;
    }
    
    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
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