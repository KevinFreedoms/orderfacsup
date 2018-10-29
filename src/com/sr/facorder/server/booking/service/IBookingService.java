package com.sr.facorder.server.booking.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr.facorder.server.booking.pojo.EpOrderLog;

public interface IBookingService {
	//查询订单
	public Map<String,Object> getOrder(EpOrderLog model);
	//拼接订单数据
	public Map<String,Object> getBookingMap(HttpServletRequest request);
	//生成订单
	public Map<String,Object> insertOrder(Map<String,Object> map);
	//更新订单
	public Map<String,Object> updateOrder(Map<String,Object> map);
	//删除订单 
	public Map<String,Object> deleteOrder(HttpServletRequest request);
	//显示单品明细
	public Map<String,Object> getGoodsInfo(String goodid,String flag,String deliverydate);
	
}
