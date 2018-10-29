package com.sr.facorder.server.delivery.pojo;

import java.sql.Timestamp;


/**
 * EpSynState entity. @author MyEclipse Persistence Tools
 */

public class EpSynState  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private String stateKey;
     private String stateValue;
     private String stateValueType;
     private String memo;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpSynState() {
    }

    
    /** full constructor */
    public EpSynState(String stateKey, String stateValue, String stateValueType, String memo, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.stateKey = stateKey;
        this.stateValue = stateValue;
        this.stateValueType = stateValueType;
        this.memo = memo;
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

    public String getStateKey() {
        return this.stateKey;
    }
    
    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public String getStateValue() {
        return this.stateValue;
    }
    
    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValueType() {
        return this.stateValueType;
    }
    
    public void setStateValueType(String stateValueType) {
        this.stateValueType = stateValueType;
    }

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
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


	@Override
	public String toString() {
		return "EpSynState [reckey=" + reckey + ", stateKey=" + stateKey
				+ ", stateValue=" + stateValue + ", stateValueType="
				+ stateValueType + ", memo=" + memo + ", createDate="
				+ createDate + ", createUserId=" + createUserId
				+ ", lastupDate=" + lastupDate + ", lastupdateUserId="
				+ lastupdateUserId + "]";
	}
   








}