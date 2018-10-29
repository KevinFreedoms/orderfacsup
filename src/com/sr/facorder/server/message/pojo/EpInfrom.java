package com.sr.facorder.server.message.pojo;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;


/**
 * EpInfrom entity. @author MyEclipse Persistence Tools
 */

public class EpInfrom  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reckey;
     private Timestamp entryDate;
     private Timestamp noticeDate;
     private String content;
     private Timestamp createDate;
     private String createUserId;
     private Timestamp lastupDate;
     private String lastupdateUserId;


    // Constructors

    /** default constructor */
    public EpInfrom() {
    }

    
    /** full constructor */
    public EpInfrom(Integer reckey, Timestamp entryDate, Timestamp noticeDate, String content, Timestamp createDate, String createUserId, Timestamp lastupDate, String lastupdateUserId) {
        this.reckey = reckey;
        this.entryDate = entryDate;
        this.noticeDate = noticeDate;
        this.content = content;
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

    public Timestamp getEntryDate() {
        return this.entryDate;
    }
    
    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Timestamp getNoticeDate() {
        return this.noticeDate;
    }
    
    public void setNoticeDate(Timestamp noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
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