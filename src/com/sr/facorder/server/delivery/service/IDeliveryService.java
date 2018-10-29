package com.sr.facorder.server.delivery.service;

import java.util.Map;

public interface IDeliveryService {
	//查询指定日期的网点
	public Map<String, Object> getDeliveryTask(String username,String date);
	//查询所有直送作业
	public Map<String, Object> getAllDeliveryTask();
	//查询指定网点下的所有直送作业
	public Map<String, Object> getSupplierDetail(String bookingid,String orgid,String date);
	//修改实收数量
	public Map<String,Object> ChangeArrivecount(String val_id,String orgid,String bookingId);
	//获取打印
	public Map<String,Object> getPrint(String orgid,String bookingId);
}
