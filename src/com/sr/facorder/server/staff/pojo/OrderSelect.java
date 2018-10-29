package com.sr.facorder.server.staff.pojo;

public class OrderSelect {
	private String orgId;
	private String orgName;
	private Integer goodsNum;
	private Integer goodsType;
	private Double sumMoney;
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
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}
	@Override
	public String toString() {
		return "OrderSelect [orgId=" + orgId + ", orgName=" + orgName + ", goodsNum=" + goodsNum + ", goodsType="
				+ goodsType + ", sumMoney=" + sumMoney + "]";
	}
	
	
}
