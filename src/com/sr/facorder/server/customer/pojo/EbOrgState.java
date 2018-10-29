package com.sr.facorder.server.customer.pojo;

import java.sql.Timestamp;


/**
 * EbOrgState entity. @author MyEclipse Persistence Tools
 */

public class EbOrgState  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String orgId;
     private String orgName;
     private Timestamp startDate;
     private Timestamp endDate;
     private Integer useState;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EbOrgState() {
    }

    
    /** full constructor */
    public EbOrgState(Integer reckey, String orgId, String orgName, Timestamp startDate, Timestamp endDate, Integer useState, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.reckey = reckey;
        this.orgId = orgId;
        this.orgName = orgName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.useState = useState;
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

    public Timestamp getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getUseState() {
        return this.useState;
    }
    
    public void setUseState(Integer useState) {
        this.useState = useState;
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