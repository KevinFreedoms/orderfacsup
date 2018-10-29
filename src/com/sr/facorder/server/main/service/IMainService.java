package com.sr.facorder.server.main.service;
import java.util.Map;

public interface IMainService {

	//查询所有模板
	public Map<String,Object> getListDelMoulds(String orgid);
	//查询模板一个星期内的配送日期
	public Map<String,Object> getListDelDates(String deliveryId,String mouldId);
}
