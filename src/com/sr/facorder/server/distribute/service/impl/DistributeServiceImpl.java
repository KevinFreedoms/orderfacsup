package com.sr.facorder.server.distribute.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;

import com.sr.facorder.common.util.StringUtil;
import com.sr.facorder.server.booking.sql.BookingSQL;
import com.sr.facorder.server.distribute.dao.DistributeDao;
import com.sr.facorder.server.distribute.pojo.DistributeCollect;
import com.sr.facorder.server.distribute.pojo.EpDeliveryCollect;
import com.sr.facorder.server.distribute.pojo.EpDeliveryDetail;
import com.sr.facorder.server.distribute.service.IDistributeService;

public class DistributeServiceImpl implements IDistributeService{
	
	
	private DistributeDao distributeDao;
	
	public void setDistributeDao(DistributeDao distributeDao) {
		this.distributeDao = distributeDao;
	}
	
	//汇总
	@Override
	public Map<String, Object> getDistributeByDate(String orgid,String date) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		BASE64Decoder decode = new BASE64Decoder();
		try {
			Map<String,Object> sql = new HashMap<String, Object>();
			String orgId = new String(decode.decodeBuffer(orgid),"utf-8");
			String disdate = new String(decode.decodeBuffer(date),"utf-8");
			sql.put("date", disdate);
			sql.put("orgid", orgId);
			List<DistributeCollect> listCollect = distributeDao.selectRecords(BookingSQL.SQL_SHOW_DISTRIBUTE,sql,DistributeCollect.class);
			if(listCollect.size() == 0){
				map.put("info", "所选择日期没有数据");
			}
			map.put("success", true);
			map.put("listCollect", listCollect);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info", "网络出走了 请稍后!");
			map.put("success", false);
			return map;
		}
	}
	//明细
	@Override
	public Map<String, Object> getDistributeDetail(String bookingid) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<EpDeliveryDetail> listDetail = new ArrayList<EpDeliveryDetail>();
		try {
			String where = " WHERE bookingId = '" +bookingid + "'";
			listDetail =  distributeDao.findDistributeDetail(where);
			if(listDetail.size()!=0){
				map.put("listDetail", listDetail);
				map.put("success",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info", "网络出走了 请稍后!");
			map.put("success", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> getPrintData(String bookingId, String orgId,String date,String status) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		List<DistributeCollect> listCollect = new ArrayList<DistributeCollect>();
		List<EpDeliveryDetail> listDetail = new ArrayList<EpDeliveryDetail>();
		BASE64Decoder decode = new BASE64Decoder();
		try {
			Map<String,Object> sql = new HashMap<String, Object>();
			String bookingid =new String(decode.decodeBuffer(bookingId),"utf-8");
			String orgid =new String (decode.decodeBuffer(orgId),"utf-8");
			String disdate =new String (decode.decodeBuffer(date),"utf-8");
			String statustemp =new String (decode.decodeBuffer(status),"utf-8");
			int status1 =Integer.parseInt(statustemp);
			sql.put("date", disdate);
			sql.put("orgid", orgid);
			listCollect = distributeDao.selectRecords(BookingSQL.SQL_SHOW_DISTRIBUTE,sql,DistributeCollect.class);
			if(listCollect.size()!=0){
				map.put("collect", listCollect);
				map.put("success", true);
			}
			//明细
			String where = " WHERE bookingId = '" +bookingid + "'";
			listDetail = distributeDao.findDistributeDetail(where);
			if(listDetail.size()!=0){
				map.put("detail", listDetail);
				map.put("success", true);
			}
			map.put("type", "distribute");
			map.put("status", status1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info", "网络出走了 请稍后!");
			map.put("success", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> changeArriveCount(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EpDeliveryDetail> list = new ArrayList<EpDeliveryDetail>();
		try {
			String orgId = request.getParameter("org");
			String bookingId = request.getParameter("bookingId");
			String result = request.getParameter("info");
			List<Map<String,Object>> goods = toGetGoods(result);
			if(goods.size()==0){
				goods = null;
				map.put("info", "获取待收货单品失败 请稍后重试 ");
				map.put("success", false);
				return map;
			}
			String where = " WHERE bookingId='"+bookingId+"' AND orgId='"+orgId+"'";
			List<EpDeliveryCollect> listcoList = distributeDao.findDistributeCollect(where);
			if(listcoList.size()==0){
				map.put("success", false);
				map.put("info", "获取订单失败，请重试！");
				return map;
			}
			List<EpDeliveryDetail> listAll =new ArrayList<EpDeliveryDetail>();
			for(int i = 0,size = goods.size(); i < size; i++)
			{
				Map<String,Object> map1 = new HashMap<String, Object>();
				map1 = goods.get(i);
				String goodsid = map1.get("goodsid").toString();
				double arriveNum = Double.valueOf(map1.get("count").toString());
				String remark = map1.get("remark").toString();
				where = " WHERE goodsId='"+goodsid+"' AND bookingId = '"+bookingId+"'";
				list = distributeDao.findDistributeDetail(where);
				if(list.size()>0){
					list.get(0).setArrivecount(arriveNum);
					list.get(0).setRef3(remark);//备注存储  
					listAll.add(list.get(0));
				}
			}
			listcoList.get(0).setStatus(1);
			distributeDao.updateDelivery(listAll,listcoList.get(0));
		    map.put("success", true);
		    map.put("info", "收货成功！");
		    return map;
		} catch (Exception e) {
			map.put("success", false);
			map.put("info","网络出走了 请稍后!");
			e.printStackTrace();
			return map;
		}
	}

	//拼接数据
	public  List<Map<String,Object>> toGetGoods(String str) throws UnsupportedEncodingException, IOException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtil.isNotEmpty(str)){
			BASE64Decoder decoder = new BASE64Decoder();
			str = new String(decoder.decodeBuffer(str), "utf-8");
			String[] arryGoods = str.split(",");
			String[] arryGoodsinfo = null;
			Map<String,Object> map = null;
			for (int i = 0, length = arryGoods.length; i < length; i++) {
				map = new HashMap<String, Object>();
				arryGoodsinfo = arryGoods[i].split("_");
				map.put("goodsid", arryGoodsinfo[0]);
				map.put("count", arryGoodsinfo[1]);
				map.put("remark", arryGoodsinfo[2]);
				list.add(map);
			}
		}
		return list;
	}
}
