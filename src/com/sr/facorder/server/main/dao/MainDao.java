package com.sr.facorder.server.main.dao;

import java.util.ArrayList;
import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.main.pojo.EpDelivery;
import com.sr.facorder.server.main.pojo.EpMould;

public class MainDao extends BaseDAO {

	//查询模板
	@SuppressWarnings("unchecked")
	public List<EpMould> findDelMoulds (String where){
		List<EpMould> list = new ArrayList<EpMould>();
		list = hibernateTemplate.find(" FROM EpMould" + where);
		return list;
	}
	
	//查询配送作业
	@SuppressWarnings("unchecked")
	public List<EpDelivery> findDels (String where){
		List<EpDelivery> list = new ArrayList<EpDelivery>();
		list = hibernateTemplate.find(" FROM EpDelivery" + where);
		return list;
	}
}
