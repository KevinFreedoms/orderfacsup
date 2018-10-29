package com.sr.facorder.server.cservice.pojo;

import java.sql.Timestamp;



public class EbProblemDetail implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer reckey;
	private String voucherId;
	private String pictureUrl;
	private String pDescription;
	private String rDescription;
	private String remark;
	private String ref1;
	private String ref2;
	private String ref3;
	private Timestamp createDate;
	private String createUserId;
	private Timestamp updateDate;
	private String updateUserId;

	// Constructors

	/** default constructor */
	public EbProblemDetail() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		this.voucherId="";
		this.pictureUrl="";
		this.pDescription="";
		this.rDescription = "";
		this.remark = "";
		this.ref1 = "";
		this.ref2 = "";
		this.ref3 = "";
		this.createDate = now;
		this.createUserId = "";
		this.updateDate = now;
		this.updateUserId = "";
	}

	public EbProblemDetail(Integer reckey, String voucherId, String pictureUrl,
			String pDescription, 
			String rDescription, String remark, String ref1, String ref2,
			String ref3, Timestamp createDate, String createUserId,
			Timestamp updateDate, String updateUserId) {
		super();
		this.reckey = reckey;
		this.voucherId = voucherId;
		this.pictureUrl = pictureUrl;
		this.pDescription = pDescription;
		this.rDescription = rDescription;
		this.remark = remark;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.ref3 = ref3;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.updateDate = updateDate;
		this.updateUserId = updateUserId;
	}

	public Integer getReckey() {
		return reckey;
	}

	public void setReckey(Integer reckey) {
		this.reckey = reckey;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public String getrDescription() {
		return rDescription;
	}

	public void setrDescription(String rDescription) {
		this.rDescription = rDescription;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getRef3() {
		return ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	
	
	
	
	
	
	
	
	

}