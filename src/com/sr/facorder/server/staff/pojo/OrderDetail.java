package com.sr.facorder.server.staff.pojo;

public class OrderDetail {
	private String goodsName;
	private String orderId;
	private Integer count;
	private Double deliverPrice;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getDeliverPrice() {
		return deliverPrice;
	}
	public void setDeliverPrice(Double deliverPrice) {
		this.deliverPrice = deliverPrice;
	}
	@Override
	public String toString() {
		return "OrderDetail [goodsName=" + goodsName + ", orderId=" + orderId + ", count=" + count + ", deliverPrice="
				+ deliverPrice + "]";
	}
}
