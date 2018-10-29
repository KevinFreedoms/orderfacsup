package com.sr.facorder.server.cservice.pojo;

import java.sql.Timestamp;


public class EbProblemCollect implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer reckey;
	private String voucherId;
	private String ptitle;
	private String orgId;
	private String orgName;
	private Integer orgStatus;
	private String replyUserId;
	private String replyUser;
	private Timestamp replyDate;
	private Integer replyStatus;
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
	public EbProblemCollect() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		this.voucherId="";
		this.ptitle="";
		this.orgId="";
		this.orgName="";
		this.orgStatus = 0;
		this.replyUserId="";
		this.replyUser="";
		this.replyDate = now;
		this.replyStatus = 0;
		this.remark = "";
		this.ref1 = "";
		this.ref2 = "";
		this.ref3 = "";
		this.createDate = now;
		this.createUserId = "";
		this.updateDate = now;
		this.updateUserId = "";
	}

	/** minimal constructor */
	public EbProblemCollect(Integer reckey,String voucherId,
			String orgId, String orgName,Integer orgStatus,Integer replyStatus,Timestamp createDate,
			String createUserId, Timestamp updateDate,String updateUserId) {
		this.reckey = reckey;
		this.voucherId = voucherId;
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgStatus = orgStatus;
		this.replyStatus= replyStatus;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.updateDate = updateDate;
		this.updateUserId = updateUserId;
	}

	
	public EbProblemCollect(Integer reckey, String voucherId,String ptitle, String orgId,
			String orgName, Integer orgStatus, String replyUserId,String replyUser,
			Timestamp replyDate, Integer replyStatus, String remark,
			String ref1, String ref2, String ref3, Timestamp createDate,
			String createUserId, Timestamp updateDate, String updateUserId) {
		super();
		this.reckey = reckey;
		this.voucherId = voucherId;
		this.ptitle=ptitle;
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgStatus = orgStatus;
		this.replyUserId = replyUserId;
		this.replyUser = replyUser;
		this.replyDate = replyDate;
		this.replyStatus = replyStatus;
		this.remark = remark;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.ref3 = ref3;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.updateDate = updateDate;
		this.updateUserId = updateUserId;
	}

	//getter && setter
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}

	public Integer getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(Integer replyStatus) {
		this.replyStatus = replyStatus;
	}

	public String getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(String replyUser) {
		this.replyUser = replyUser;
	}

	public Timestamp getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	
	
	
	




	


	
	
	
	

}