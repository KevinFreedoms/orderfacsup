package com.sr.facorder.server.BigMould.pojo;

public class Items {
	private String goodsId;
	private String goodsName;
	private String singlePacking;
	private String standards;
	private double count;
	private double price;
	private double retailPrice;
	private int goodsType;
	public Items() {
		// TODO Auto-generated constructor stub
	}
	public Items(String goodsId, String goodsName, String singlePacking,
			String standards, double count, double price,int goodsType,double retailPrice) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.singlePacking = singlePacking;
		this.standards = standards;
		this.count = count;
		this.price = price;
		this.goodsType=goodsType;
		this.retailPrice=retailPrice;
	}
	public Items(String goodsId, String goodsName, String singlePacking,
			String standards, double count, double price,double retailPrice) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.singlePacking = singlePacking;
		this.standards = standards;
		this.count = count;
		this.price = price;
		this.retailPrice=retailPrice;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSinglePacking() {
		return singlePacking;
	}
	public void setSinglePacking(String singlePacking) {
		this.singlePacking = singlePacking;
	}
	public String getStandards() {
		return standards;
	}
	public void setStandards(String standards) {
		this.standards = standards;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	@Override
	public String toString() {
		return "Items [goodsId=" + goodsId + ", goodsName=" + goodsName + ", singlePacking=" + singlePacking
				+ ", standards=" + standards + ", count=" + count + ", price=" + price + ", retailPrice=" + retailPrice
				+ ", goodsType=" + goodsType + "]";
	}
	
	
	
}