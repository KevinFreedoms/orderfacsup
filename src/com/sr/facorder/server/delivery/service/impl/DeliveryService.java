package com.sr.facorder.server.delivery.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.sr.facorder.common.util.StringUtil;
import com.sr.facorder.server.booking.sql.BookingSQL;
import com.sr.facorder.server.delivery.dao.DeliveryDao;
import com.sr.facorder.server.delivery.pojo.EpDeliveryGoods;
import com.sr.facorder.server.delivery.pojo.EpSynState;
import com.sr.facorder.server.delivery.pojo.SupplerDelivery;
import com.sr.facorder.server.delivery.service.IDeliveryService;

public class DeliveryService implements IDeliveryService{
	private DeliveryDao deliveryDao;
	
	
	@Override
	public Map<String, Object> getAllDeliveryTask() {
		Map<String,Object> map = new HashMap<String, Object>();
		List<EpDeliveryGoods> list = new ArrayList<EpDeliveryGoods>();
		List<String> list_supplierId = new ArrayList<String>();
		List<List<EpDeliveryGoods>> list_All = new ArrayList<List<EpDeliveryGoods>>();
		
		try {
			String where = " ORDER BY goodsName";
			list = deliveryDao.getAllEpDeliveryGoods(where);
			
			for (int i = 0; i < list.size(); i++) {
				if(list_supplierId.size()==0){
					list_supplierId.add(list.get(i).getSupplierId());
				}else{
					for (int j2 = 0; j2 < list_supplierId.size(); j2++) {
						if(!list_supplierId.contains(list.get(i).getSupplierId())){
							list_supplierId.add(list.get(i).getSupplierId());
						}
					}
					
				}
			}
			
			for (int i = 0; i < list_supplierId.size(); i++) {
				List<EpDeliveryGoods> listi = new ArrayList<EpDeliveryGoods>();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getSupplierId().equals(list_supplierId.get(i))){
						listi.add(list.get(j));
					}
				}
				list_All.add(listi);
			}
			map.put("success", true);
			if(list.size()>0){
				map.put("list", list_All);
			}else{
				map.put("info","当天无直送作业！");
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("info","网络出走了 请稍后!");
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> ChangeArrivecount(String result,String orgid,String bookingId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EpDeliveryGoods> list = new ArrayList<EpDeliveryGoods>();
		try {
			String where = " WHERE bookingId='"+bookingId+"' AND orgId='"+orgid+"'";
			list = deliveryDao.getAllEpDeliveryGoods(where);
			if(list.size()==0){
				map.put("success", false);
				map.put("info", "获取订单失败，请重试！");
				return map;
			}
			if(list.get(0).getStatus()==2){
				map.put("success", false);
				map.put("info", "当前订单工厂已经确认，无法更改，请重试！");
				return map;
			}
			List<Map<String,Object>> goods = toGetGoods(result);
			if(goods.size()==0){
				goods = null;
				map.put("info", "获取待收货单品失败 请稍后重试 ");
				map.put("success", false);
				return map;
			}
			List<EpDeliveryGoods> listAll = new ArrayList<EpDeliveryGoods>();
			for(int i = 0,size = goods.size(); i < size; i++)
			{
				Map<String,Object> map1 = new HashMap<String, Object>();
				map1 = goods.get(i);
				String goodsid = map1.get("goodsid").toString();
				double arriveNum = Double.valueOf(map1.get("count").toString());
				String remark = map1.get("remark").toString();
				where = " WHERE goodsId='"+goodsid+"' AND bookingId='"+bookingId+"' AND orgId='"+orgid+"'";
				list = deliveryDao.getAllEpDeliveryGoods(where);
				if(list.size()>0){
					list.get(0).setArrivecount(arriveNum);
					list.get(0).setStatus(1);
					list.get(0).setRef3(remark);//备注存储  
					listAll.add(list.get(0));
				}
			}
			deliveryDao.updateDeliveryGoodsAll(listAll);
			list = null;
			listAll = null;
			goods = null;
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
	
	@Override
	public Map<String, Object> getSupplierDetail(String bookingid,String orgid,String date) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<EpDeliveryGoods> list = new ArrayList<EpDeliveryGoods>();
		try {
			String where = " WHERE CONVERT(varchar(10),deliveryDate,23)='"+date+"' AND bookingId='"+bookingid+"' AND orgid='"+orgid+"' ORDER BY goodsName";
			list = deliveryDao.getAllEpDeliveryGoods(where);
			map.put("success", true);
			map.put("list", list);
		} catch (Exception e) {
			map.put("success", false);
			map.put("info", "网络出走了 请稍后!");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> getDeliveryTask(String username,String date) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<SupplerDelivery> list = new ArrayList<SupplerDelivery>();
		try {
			Map<String,Object> sql = new HashMap<String, Object>();
			sql.put("date", date);
			sql.put("orgid", username);
			/**jiaself 2017-03-20**/
			List<EpSynState> statusList = new ArrayList<EpSynState>();
			String where = " WHERE stateKey='purchasestate'";
			statusList = deliveryDao.getEnableStatus(where);
			Integer status = Integer.parseInt(statusList.get(0).getStateValue());
			if(status == 0){
				list = deliveryDao.selectRecords(BookingSQL.SQL_SHOW_DELIVERYREF,sql,SupplerDelivery.class);
			}else{
				list = deliveryDao.selectRecords(BookingSQL.SQL_SHOW_DELIVERY,sql,SupplerDelivery.class);
			}
			/**********/
			if(list.size()==0){
				map.put("info","当天没有直送订单！");
			}
			map.put("listSuppler",list);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("info","网络出走了 请稍后!");
			e.printStackTrace();
		}
		return map;
	}
	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	@Override
	public Map<String, Object> getPrint(String orgid, String bookingId) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		List<EpDeliveryGoods> list = new ArrayList<EpDeliveryGoods>();
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			bookingId = new String(decoder.decodeBuffer(bookingId), "utf-8");
			orgid = new String(decoder.decodeBuffer(orgid), "utf-8");
			//明细
			String whereref = " WHERE bookingId='"+bookingId+"' AND orgId='"+orgid+"' ORDER BY goodsName";
			list = deliveryDao.getAllEpDeliveryGoods(whereref);
			reMap.put("detail", list);
			reMap.put("type", "delivery");
			//汇总
			Map<String,Object> collect = new HashMap<String, Object>();
			collect.put("bookingId", list.get(0).getBookingId());
			collect.put("supplierName", list.get(0).getSupplierName());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			String date =sdf.format(list.get(0).getDeliveryDate());  
			collect.put("deliveryDate",date);
			reMap.put("collect",collect);
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