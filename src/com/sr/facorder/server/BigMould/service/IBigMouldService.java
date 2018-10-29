package com.sr.facorder.server.BigMould.service;

import java.util.Map;

public interface IBigMouldService {
	//参考昨日
	public Map<String,Object> getYestodayOrder(String mouldId,String orgid,String deliverydate);
	//获取数据源
	public Map<String,Object> getAllGoods(String mouldId,String orgid,String deliverydate);
	//判断模板
	public Map<String,Object> getCheckMoubld(String mouldid,String deliverydate);
	//获取已定单品
	public Map<String,Object> getOrder(String mouldid,String deliverydate,String orderid);
	//获取打印
	public Map<String,Object> getprint(String orderid);
	//查找类别
	public Map<String,Object> getCategoryGoods(String mincheng,String mouldId,String orgid,String deliverydate);
	//查找模板下单品
	public Map<String,Object> getSearchGoods(String mincheng,String mouldId,String orgid,String deliverydate);
	//查看是否启用建议零售价
	public Map<String,Object> getIsRetailPrice();
}
