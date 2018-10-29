package com.sr.facorder.server.main.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.sr.facorder.server.booking.sql.BookingSQL;
import com.sr.facorder.server.main.dao.MainDao;
import com.sr.facorder.server.main.pojo.EpDelivery;
import com.sr.facorder.server.main.pojo.EpMould;
import com.sr.facorder.server.main.service.IMainService;
import com.sr.facorder.server.main.util.DeliveryUtil;

public class MainService implements IMainService {

	private MainDao mainDao;
	
	public MainDao getMainDao() {
		return mainDao;
	}

	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}

	@Override
	public Map<String,Object> getListDelMoulds(String orgid) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			Map<String,Object> sql = new HashMap<String, Object>();
			sql.put("orgid", orgid);
			List<EpMould> list = mainDao.selectRecords(BookingSQL.SQL_SHOW_ORGMOULD,sql,EpMould.class);
			if(list.size()==0){
				reMap.put("info", "没有匹配的模板 请谅解！");
				reMap.put("success", false);
				return reMap;
			}
			reMap.put("success", true);
			reMap.put("moulds", list);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
		
	}

	public Map<String, Object> getListDelDates(String deliveryId,String mouldId) {
		String where = "";
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			//获取配送作业
			BASE64Decoder decoder = new BASE64Decoder();
			String deliveryid = new String(decoder.decodeBuffer(deliveryId), "utf-8");
			List<EpDelivery> deliverys = new ArrayList<EpDelivery>();
			where = " WHERE deliveryTaskId = '"+deliveryid+"'";
			deliverys = mainDao.findDels(where);
			//获取模板信息
			String mouldid = new String(decoder.decodeBuffer(mouldId), "utf-8");
			List<EpMould> moulds = new ArrayList<EpMould>();
			where = " WHERE mouldId = '"+mouldid+"'";
			moulds = mainDao.findDelMoulds(where);
			//查询配送周期
			List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
			maps = DeliveryUtil.listDeliveryDates(deliverys.get(0),moulds.get(0));
			reMap.put("totalrecord", maps.size());
			reMap.put("dates", maps);
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
