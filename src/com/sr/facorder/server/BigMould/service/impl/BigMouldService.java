package com.sr.facorder.server.BigMould.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.sr.facorder.common.util.StringUtil;
import com.sr.facorder.server.BigMould.dao.BigMouldDao;
import com.sr.facorder.server.BigMould.pojo.EpOrderMouldGcateGory;
import com.sr.facorder.server.BigMould.pojo.Items;
import com.sr.facorder.server.BigMould.service.IBigMouldService;
import com.sr.facorder.server.booking.dao.BookingDao;
import com.sr.facorder.server.booking.pojo.EbOrderCollect;
import com.sr.facorder.server.booking.pojo.EbOrderDetail;
import com.sr.facorder.server.booking.pojo.EpMouldGoodsDelPrice;
import com.sr.facorder.server.booking.pojo.EpOrderLog;
import com.sr.facorder.server.booking.sql.BookingSQL;
import com.sr.facorder.server.main.dao.MainDao;
import com.sr.facorder.server.main.pojo.EpDelivery;
import com.sr.facorder.server.main.pojo.EpMould;
import com.sr.facorder.server.main.util.DeliveryUtil;


public class BigMouldService implements IBigMouldService{
	private BigMouldDao bigMouldDao;
	private BookingDao bookingDao;
	private MainDao mainDao;
	public void setBigMouldDao(BigMouldDao bigMouldDao) {
		this.bigMouldDao = bigMouldDao;
	}
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}
	
	public Map<String, Object> getAllGoods(String mouldId,String orgid,String deliverydate) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		List<EpOrderMouldGcateGory> list_category = new ArrayList<EpOrderMouldGcateGory>();
		try {
			String where = " WHERE mouldId='"+mouldId+"'";
			list_category = bigMouldDao.findCategoryName(where);
			if(list_category.size()==0){
				reMap.put("info", "该模板下没有任何类别的单品！");
				reMap.put("success", false);
				return reMap;
			}
			reMap.put("category", list_category);
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	public Map<String, Object> getCategoryGoods(String mincheng,String mouldId,String orgid,String deliverydate) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try {
			List<Items> list_items = new ArrayList<Items>();
			Map<String,Object> sql = new HashMap<String, Object>();
			sql.put("categoryid",mincheng);
			sql.put("orgid",orgid);
			sql.put("mouldid",mouldId);
			sql.put("date",deliverydate);
			list_items = bigMouldDao.selectRecords(BookingSQL.SQL_SHOW_BIGMOULD, sql,Items.class);
			reMap.put("goods", list_items);
			reMap.put("success", true);
			return reMap;   
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	public Map<String, Object> getYestodayOrder(String mouldId,String orgid,String deliverydate) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String where  ="";
		try {
			where  = " WHERE CONVERT(varchar(10),deliveryDate, 23) = CONVERT(varchar(10),DATEADD(DAY,-1,'"+deliverydate+"'), 23)";
			List<EbOrderCollect> list_order = bookingDao.findOrderCollect(where);
			if(list_order.size()==0){ 
				reMap.put("info", "昨日没有订单 无法进行参照");
				reMap.put("success", false);
				return reMap;
			}
			String orderid = list_order.get(0).getOrderId();
			where = " WHERE orderId='"+orderid+"' ORDER BY goodsName";
			List<EbOrderDetail> list_detail = bookingDao.findOrderGoods(where);
			reMap.put("list",list_detail);
			reMap.put("success", true);
			return reMap;
		} catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	public Map<String, Object> getCheckMoubld(String mouldid,String deliverydate) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			String where = " WHERE mouldId = '"+mouldid+"'";
			List<EpMould> moulds = mainDao.findDelMoulds(where);
			if(moulds.size()==0){
				reMap.put("info", "获取模板数据失败 请稍后重试！ ");
				reMap.put("success", false);
				return reMap;
			}
			
			if(!DeliveryUtil.checkDeliveryTime(moulds.get(0),deliverydate)){
				reMap.put("info", "当前选择日期订货已经截止！");
				reMap.put("success", false);
				return reMap;
			}
			
			//获取模板的配送作业
			String sql = " WHERE deliverytaskId = '"+moulds.get(0).getDeliverytaskId()+"'";
			List<EpDelivery> deliverys = mainDao.findDels(sql);
			if(deliverys.size()==0){
				reMap.put("info", "当前模板获取配送信息失败 请稍后重试！ ");
				reMap.put("success", false);
				return reMap;
			}
			moulds = null;
			deliverys = null;
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	public Map<String, Object> getOrder(String mouldid,
			String deliverydate, String orderid) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			String where = " WHERE mouldId = '"+mouldid+"'";
			List<EpMould> moulds = mainDao.findDelMoulds(where);
			//获取模板的配送作业
			where = " WHERE deliverytaskId = '"+moulds.get(0).getDeliverytaskId()+"'";
			List<EpDelivery> deliverys = mainDao.findDels(where);
			//获取明细
			List<EbOrderDetail> listC = new ArrayList<EbOrderDetail>();
			List<EpOrderLog> log = new ArrayList<EpOrderLog>();
			if(StringUtil.isNotEmpty(orderid)){
				where = " WHERE orderId='"+orderid+"' ORDER BY goodsName";
				listC = bookingDao.findOrderGoods(where);
			
				where = " WHERE pk='"+orderid+"'";
				log = bookingDao.findOrderLogs(where);
			}
			reMap.put("mouldname", moulds.get(0).getMouldName());
			reMap.put("log", log);
			reMap.put("collect", listC);
			reMap.put("delivery",DeliveryUtil.getDeliveryMessage(moulds.get(0), deliverys.get(0), deliverydate));
			reMap.put("success", true);
			listC = null;
			moulds = null;
			deliverys = null;
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	public Map<String, Object> getprint(String orderid) {
		String sql = "";
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			orderid = new String(decoder.decodeBuffer(orderid), "utf-8");
			//汇总
			sql = " WHERE orderId='"+orderid+"'";
			List<EbOrderCollect> collect = bookingDao.findOrderCollect(sql);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("bookingId", collect.get(0).getOrderId());
			map.put("orgName", collect.get(0).getOrgName());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			String deliverydate =sdf.format(collect.get(0).getDeliveryDate());
			
			sql = " WHERE mouldId = '"+collect.get(0).getMouldId()+"'";
			List<EpMould> moulds = mainDao.findDelMoulds(sql);
			sql = " WHERE deliverytaskId = '"+moulds.get(0).getDeliverytaskId()+"'";
			List<EpDelivery> deliverys = mainDao.findDels(sql);
			Map<String,Object> maps = DeliveryUtil.getDeliveryMessage(moulds.get(0), deliverys.get(0), deliverydate);
			map.put("arrival",maps.get("arrival"));
			map.put("orderend",maps.get("orderend"));
			reMap.put("collect", map);
			//明细
			sql = " WHERE orderId='"+orderid+"'";
			List<EbOrderDetail> detail = bookingDao.findOrderGoods(sql);
			reMap.put("detail", detail);
			reMap.put("type", "order");
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	
	public Map<String, Object> getSearchGoods(String mincheng,String mouldId,String orgid,String deliverydate){
		Map<String,Object> reMap = new HashMap<String, Object>();
		try {
			List<Items> list_items = new ArrayList<Items>();
			Map<String,Object> sql = new HashMap<String, Object>();
			sql.put("goodsname",mincheng);
			sql.put("orgid",orgid);
			sql.put("mouldid",mouldId);
			sql.put("date",deliverydate);
			list_items = bigMouldDao.selectRecords(BookingSQL.SQL_SEARCH_MOULD, sql,Items.class);
			reMap.put("goods", list_items);
			reMap.put("success", true);
			return reMap;   
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
	}
	@Override
	public Map<String, Object> getIsRetailPrice() {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try {
			Map<String,Object> isRetailPrice = new HashMap<String, Object>();
			isRetailPrice=bigMouldDao.selectRecord(BookingSQL.SQL_GET_ISRETAILPRICE);
			reMap.put("isRetailPrice", isRetailPrice);
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走了 请稍后!");
			reMap.put("success", false);
			return reMap;
		}
		
	}
	
}
