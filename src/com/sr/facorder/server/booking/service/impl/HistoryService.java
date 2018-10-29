package com.sr.facorder.server.booking.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.sr.facorder.server.booking.dao.BookingDao;
import com.sr.facorder.server.booking.pojo.EbOrderCollect;
import com.sr.facorder.server.booking.pojo.EbOrderDetail;
import com.sr.facorder.server.booking.pojo.EpOrderLog;
import com.sr.facorder.server.booking.service.IHistoryService;
import com.sr.facorder.server.booking.sql.BookingSQL;
import com.sr.facorder.server.main.pojo.EpDelivery;

public class HistoryService implements IHistoryService {
	//dao
	private BookingDao bookingDao;
	//getter&setter
	public BookingDao getBookingDao() {
		return bookingDao;
	}

	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}

	public Map<String,Object> getHisOrder(String startdate,String enddate,String orgid) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			Map<String,Object> sql = new HashMap<String, Object>();
			sql.put("startdate", startdate);
			sql.put("enddate", enddate);
			sql.put("orgid", orgid);
			sql.put("status", 5);
			sql.put("mould", "");
			sql.put("type", "");
			List<Map<String,Object>> list = bookingDao.selectRecords(BookingSQL.SQL_SHOW_BOOKINGLIST,sql);
			reMap.put("list", list);
			reMap.put("success", true);
			reMap.put("totalrecord", list.size());
			list = null;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
		}
		return reMap;
	}

	public Map<String, Object> getHisBookingInfo(String bookingid) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			//日志
			String where = " WHERE pk='"+bookingid+"'";
			List<EpOrderLog> log = new ArrayList<EpOrderLog>();
			log = bookingDao.findOrderLogs(where);
			reMap.put("delivery", log.get(0));
			
			//汇总
			where=" WHERE orderId='"+bookingid+"'";
			List<EbOrderCollect> collects = new ArrayList<EbOrderCollect>();
			collects = bookingDao.findOrderCollect(where);
			reMap.put("collect", collects.get(0));
			
			//明细
			where=" WHERE orderId='"+bookingid+"' ORDER BY goodsName";
			List<EbOrderDetail> detail = new ArrayList<EbOrderDetail>();
			detail = bookingDao.findOrderGoods(where);
			reMap.put("goods", detail);
			
			String arrivalTime = "";
			where = " WHERE deliveryTaskId='"+collects.get(0).getDeliveryTaskId()+"'";
			List<EpDelivery> deliveries = bookingDao.findDels(where);
			if(deliveries.size()>0){
				arrivalTime = deliveries.get(0).getArrivalTime();
			}
			reMap.put("arrivalTime", arrivalTime);
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	@Override
	public Map<String, Object> getprint(String orderid) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			orderid = new String(decoder.decodeBuffer(orderid), "utf-8");
			//汇总
			String where = " WHERE pk='"+orderid+"'";
			List<EpOrderLog> log = new ArrayList<EpOrderLog>();
			log = bookingDao.findOrderLogs(where);
			EpOrderLog collect  = log.get(0);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String deliverydate =sdf.format(collect.getDeliveryDate());
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("pk", collect.getPk());
			map.put("mouldName", collect.getMouldName());
			map.put("deliveryDate", deliverydate);
			reMap.put("collect", map);
			//明细
			where = " WHERE orderId='"+orderid+"' ORDER BY goodsName";
			List<EbOrderDetail> detail = new ArrayList<EbOrderDetail>();
			detail = bookingDao.findOrderGoods(where);
			reMap.put("detail", detail);
			reMap.put("type", "history");
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	
}
