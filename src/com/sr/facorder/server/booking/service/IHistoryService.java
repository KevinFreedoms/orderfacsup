package com.sr.facorder.server.booking.service;
import java.util.Map;

public interface IHistoryService {

	//显示日期下的工厂已经完成的订单
	public Map<String,Object> getHisOrder(String startdate,String enddate,String orgid);
	//查询订单明细
	public Map<String,Object> getHisBookingInfo(String bookingid);
	//获取打印
	public Map<String,Object> getprint(String orderid);
}
