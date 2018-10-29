package com.sr.facorder.server.booking.service.impl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import sun.misc.BASE64Decoder;

import com.sr.facorder.common.util.StringUtil;
import com.sr.facorder.server.booking.dao.BookingDao;
import com.sr.facorder.server.booking.pojo.EbOrderCollect;
import com.sr.facorder.server.booking.pojo.EbOrderDetail;
import com.sr.facorder.server.booking.pojo.EpOrderLog;
import com.sr.facorder.server.booking.pojo.EpOrderMould;
import com.sr.facorder.server.booking.pojo.EpOrg;
import com.sr.facorder.server.booking.pojo.EpProduct;
import com.sr.facorder.server.booking.service.IBookingService;
import com.sr.facorder.server.booking.sql.BookingSQL;
import com.sr.facorder.server.main.dao.MainDao;
import com.sr.facorder.server.main.pojo.EpDelivery;
import com.sr.facorder.server.main.pojo.EpMould;
import com.sr.facorder.server.main.util.DeliveryUtil;

public class BookingService implements IBookingService {
	//dao
	private BookingDao bookingDao;
	private MainDao mainDao;
	//getter&setter
	public BookingDao getBookingDao() {
		return bookingDao;
	}

	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	public MainDao getMainDao() {
		return mainDao;
	}

	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}

	public Map<String, Object> getOrder(EpOrderLog model) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			String orgid = model.getOrgId();
			String mouldid = model.getMouldId();
			String where = " WHERE CONVERT(varchar(10),deliveryDate, 23)=CONVERT(varchar(10),'"+model.getDeliveryDate()+"', 23) AND orgId=" +
					"'"+orgid+"' AND mouldId='"+mouldid+"'";
			List<EpOrderLog> list = new ArrayList<EpOrderLog>();
			list = bookingDao.findOrderLogs(where);
			if(list.size()>0&&list.get(0).getStatus()==5){
				reMap.put("info", "工厂已经接收了您的订单 请前往历史订单中查询");
				reMap.put("success", false);
				return reMap;
			}
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startdate = sdf.format(model.getDeliveryDate()); 
			Map<String,Object> sql = new HashMap<String, Object>();
			sql.put("startdate", startdate);
			sql.put("enddate", startdate);
			sql.put("orgid", model.getOrgId());
			sql.put("status",1);
			sql.put("mould", model.getMouldId());
			List<Map<String,Object>> orderlist = bookingDao.selectRecords(BookingSQL.SQL_SHOW_BOOKINGLIST,sql);
			reMap.put("list", orderlist);
			reMap.put("success", true);
			reMap.put("totalrecord", orderlist.size());
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走 请稍后重试");
			reMap.put("success", false);
			return reMap;
		}
	}

	public Map<String,Object> insertOrder(Map<String,Object> map){
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			
			@SuppressWarnings("unchecked")
			List<EbOrderDetail> details =(List<EbOrderDetail>) map.get("details");
			if(details.size()==0){
				reMap.put("info", "获取待订货单品失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			EbOrderCollect collect = (EbOrderCollect)map.get("collect");
			EpOrderLog log = (EpOrderLog)map.get("log");
			bookingDao.saveBooking(log, collect, details);
			reMap.put("bookingid", log.getPk());
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走 请稍后重试！");
			reMap.put("success", false);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
			return reMap;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> updateOrder(Map<String,Object> map){
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			List<EbOrderDetail> details =(List<EbOrderDetail>) map.get("details");
			if(details.size()==0){
				reMap.put("info", "获取待订货单品失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			String bookingid = (String)map.get("bookingid");
			
			String where = " WHERE pk = '"+bookingid+"'";
			List<EpOrderLog> logs = bookingDao.findOrderLogs(where);
			if(logs.size()==0){
				reMap.put("info", "获取历史订单数据失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			EpOrderLog log = logs.get(0);
			log.setLastupDate(new Timestamp(System.currentTimeMillis()));
			
			
			where = " WHERE orderId = '" + bookingid + "'";
			List<EbOrderCollect> collects = new ArrayList<EbOrderCollect>();
			collects = bookingDao.findOrderCollect(where);
			if(collects.size()==0){
				reMap.put("info", "获取历史订单数据失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			
			EbOrderCollect collect = collects.get(0);
			collect.setLastupDate(new Timestamp(System.currentTimeMillis()));
			collect.setRef1(map.get("sumcount").toString());
			collect.setRef2(map.get("sumprice").toString());
			
			List<EbOrderDetail> orderinfo = new ArrayList<EbOrderDetail>();
			orderinfo = bookingDao.findOrderGoods(where);
			if(orderinfo.size()==0){
				reMap.put("info", "获取历史订单数据失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			bookingDao.updateBooking(collect,log,orderinfo,details);
			logs = null;
			orderinfo=null;
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走 请稍后重试！");
			reMap.put("success", false);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
			return reMap;
		}
	}
	
	//拼接数据
	public  List<Map<String,Object>> toGetGoods(String str){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(!str.equals("")||str!=""){
			String[] arryGoods = str.split(",");
			String[] arryGoodsinfo = null;
			Map<String,Object> map = null;
			for (int i = 0, length = arryGoods.length; i < length; i++) {
				map = new HashMap<String, Object>();
				arryGoodsinfo = arryGoods[i].split("_");
				map.put("goodsid", arryGoodsinfo[0]);
				map.put("count", arryGoodsinfo[1]);
				list.add(map);
			}
		}
		return list;
	}
	
	public Map<String, Object> deleteOrder(HttpServletRequest request) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			String deliverydate = request.getParameter("deliverydate");
			//查询配送作业是否已经超时
			String mouldid = new String(decoder.decodeBuffer(request.getParameter("mouldid")), "utf-8");
			String where = " WHERE mouldId = '"+mouldid+"'";
			List<EpMould> moulds = mainDao.findDelMoulds(where);
			if(moulds.size()==0){
				reMap.put("info", "模板不存在 下次再来吧！ ");
				reMap.put("success", false);
				return reMap;
			}
			if(!DeliveryUtil.checkDeliveryTime(moulds.get(0),deliverydate.substring(0,10))){
				reMap.put("info", "当天已经截止了 下次再来吧 ！");
				reMap.put("success", false);
				return reMap;
			}
			String orderid = request.getParameter("bookingid");
			where = " WHERE orderId = '"+orderid+"'";
			List<EbOrderCollect>  collect= bookingDao.findOrderCollect(where);
			List<EbOrderDetail> orderinfo = bookingDao.findOrderGoods(where);
			
			where = " WHERE pk = '"+orderid+"'";
			List<EpOrderLog> log = bookingDao.findOrderLogs(where);
			
			//先删除订单汇总
			bookingDao.deleteLog(log);
			bookingDao.deleteCollect(collect);
			
			//删除明细
			bookingDao.deleteDetail(orderinfo);
			collect = null;
			orderinfo = null;
			log = null;
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走 请稍后重试！");
			reMap.put("success", false);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			return reMap;
		}
	}

	public Map<String, Object> getGoodsInfo(String goodid, String flag,String deliverydate) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			String where = " WHERE goodsId = '"+goodid+"'";
			if(flag =="1"||!flag.equals("1")){
				String date = deliverydate.replace("-", "");
				where = " WHERE goodsId = '"+goodid+"' AND date='"+date+"'";
			}
			List<EpProduct> product = new ArrayList<EpProduct>();
			product = bookingDao.findGoodsInfo(where);
			if(product.size()>0){
				reMap.put("products", product);
				reMap.put("success", true);
			}else{
				reMap.put("info", "获取单品组成失败 请稍后重试！");
				reMap.put("success", false);
			}
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走 请稍后重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	@Override
	public Map<String, Object> getBookingMap(HttpServletRequest request) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			Integer orderflag = Integer.valueOf(request.getParameter("orderflag").toString());
			String deliverydate = request.getParameter("deliverydate");
			
			//查询配送作业是否已经超时
			String mouldid = new String(decoder.decodeBuffer(request.getParameter("mouldid")), "utf-8");
			String where = " WHERE mouldId = '"+mouldid+"'";
			List<EpMould> moulds = mainDao.findDelMoulds(where);
			if(moulds.size()==0){
				reMap.put("info", "获取模板信息失败 稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			if(!DeliveryUtil.checkDeliveryTime(moulds.get(0),deliverydate.substring(0,10))){
				reMap.put("info", "当天已经截止了 下次再来吧 ");
				reMap.put("success", false);
				return reMap;
			}
			//获取模板的配送作业
			where = " WHERE deliverytaskId = '"+moulds.get(0).getDeliverytaskId()+"'";
			List<EpDelivery> deliverys = mainDao.findDels(where);
			if(deliverys.size()==0){
				deliverys = null;
				reMap.put("info", "当前模板获取配送信息失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			//获取用户信息
			String orgid = new String(decoder.decodeBuffer(request.getParameter("orgid")), "utf-8");
			where = " WHERE orgId = '"+orgid+"'";
			List<EpOrg> orgs = bookingDao.findOrgs(where);
			if(orgs.size()==0){
				orgs = null;
				reMap.put("info", "获取网点信息失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			String goodsStr= request.getParameter("info");
			List<Map<String,Object>> goods = toGetGoods(goodsStr);
			if(goods.size()==0){
				goods = null;
				reMap.put("info", "获取待订货单品失败 请稍后重试 ");
				reMap.put("success", false);
				return reMap;
			}
			String bookingid = "";
			List<EpOrderLog> listlog = new ArrayList<EpOrderLog>();
			if(orderflag==0){
				where = " WHERE CONVERT(varchar(10),deliveryDate, 23)='"+deliverydate+"'"+
						" AND mouldId='"+mouldid+"' AND orgId='"+orgid+"'";
				listlog = bookingDao.findOrderLogs(where);
				if(listlog.size()>0){
					reMap.put("info", "当前模板下已经生成订单");
					reMap.put("success", false);
					return reMap;
				}
				//生成订单号
				Date now = new Date();
				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
				String tempdate = outFormat.format(now);
				bookingid = orgid+tempdate+StringUtil.buildRandom(4);
				where = " WHERE pk='"+bookingid+"'";
				listlog = bookingDao.findOrderLogs(where);
				if(listlog.size()>0){
					reMap.put("info", "网络出走 请稍后重试");
					reMap.put("success", false);
					return reMap;
				}
			}else{
				bookingid= request.getParameter("bookingid");
				where = " WHERE mouldId='"+mouldid+"' AND pk='"+bookingid+"' AND orgId='"+orgid+"'";
				listlog = bookingDao.findOrderLogs(where);
				if(listlog.size()==0){
					reMap.put("info", "网络出走 请稍后重试");
					reMap.put("success", false);
					return reMap;
				}
			}
			//获取单品明细
			List<EbOrderDetail> details = new ArrayList<EbOrderDetail>();
			double sumcount = 0,sumprice=0;
			for(int i = 0,size = goods.size(); i < size; i++)
			{
				Map<String,Object> map = new HashMap<String, Object>();
				map = goods.get(i);
				String goodsid = map.get("goodsid").toString();
				Map<String,Object> sql = new HashMap<String, Object>();
				sql.put("moulddate", "");
				sql.put("goodid", goodsid);
				sql.put("mouldid", mouldid);
				//jiaself
				sql.put("date", deliverydate);
				sql.put("orgid",orgid);
				List<EpOrderMould>  mouldgoods = bookingDao.selectRecords(BookingSQL.SQL_SHOW_MOULD,sql,EpOrderMould.class);
				if(mouldgoods.size()==0){
					mouldgoods = null;
					reMap.put("info", "模板中存在失效单品 请稍后重试 ");
					reMap.put("success", false);
					return reMap;
				}
				EpOrderMould mould = mouldgoods.get(0);
				Double count = Double.valueOf(map.get("count").toString());
				if(count>0){
					sumcount += count;
					sumprice += count*mould.getDeliverPrice();
					EbOrderDetail detail = new EbOrderDetail();
					detail.setGoodsId(mould.getGoodsId());
					detail.setGoodsName(mould.getGoodsName());
					detail.setOrderId(bookingid);
					detail.setPackingCount(mould.getPackingCount());
					detail.setPackingName(mould.getPackingName());
					detail.setSinglePacking(mould.getSinglePacking());
					detail.setStandards(mould.getStandards());
					detail.setCreateUserId(orgid);
					detail.setLastupdateUserId(orgid);
					detail.setCount(count);
					detail.setPackingType(mould.getPackingType());
					detail.setDeliverPrice(mould.getDeliverPrice());
					details.add(detail);
				}
				mouldgoods = null;
			}
			DecimalFormat df = new DecimalFormat("#.00");
			
			if(orderflag==0){
				//日志
				String tsStr = "";
				String tempdeliverydate=deliverydate.trim();
				tsStr = tempdeliverydate.replace('\n',' ')+" 00:00:00";
				EpOrderLog log = new EpOrderLog();
				log.setStatus(1);
				log.setPk(bookingid);
				log.setTableName("OrderCollect");
				log.setMemo("order");
				log.setLocation("move");
				log.setDeliveryDate(Timestamp.valueOf(tsStr));
				log.setOrgId(orgs.get(0).getOrgId());
				log.setOrgName(orgs.get(0).getOrgName());
				log.setMouldId(moulds.get(0).getMouldId());
				log.setMouldName(moulds.get(0).getMouldName());
				log.setDeliveryTaskId(deliverys.get(0).getDeliveryTaskId());
				log.setDeliveryTaskName(deliverys.get(0).getDeliveryTaskName());
				log.setCreateUserId(orgs.get(0).getOrgId());
				log.setLastupdateUserId(orgs.get(0).getOrgId());
				
				//订单汇总
				EbOrderCollect collect = new EbOrderCollect();
				collect.setOrderId(bookingid);
				collect.setOrgId(orgid);
				collect.setOrgName(orgs.get(0).getOrgName());
				collect.setMouldId(moulds.get(0).getMouldId());
				collect.setDeliveryTaskId(deliverys.get(0).getDeliveryTaskId());
				collect.setDeliveryDate(Timestamp.valueOf(tsStr));
				collect.setCreateUserId(orgid);
				collect.setLastupdateUserId(orgid);
				collect.setRef1(df.format(sumcount));
				collect.setRef2(df.format(sumprice));
				
				reMap.put("log", log);
				reMap.put("collect", collect);
				reMap.put("details", details);
				reMap = insertOrder(reMap);
			}else{
				reMap.put("sumcount", df.format(sumcount));
				reMap.put("sumprice", df.format(sumprice));
				reMap.put("bookingid", bookingid);
				reMap.put("details", details);
				reMap = updateOrder(reMap);
			}
			details = null;
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "网络出走 请稍后重试！");
			reMap.put("success", false);
			return reMap;
		}
	}
}
