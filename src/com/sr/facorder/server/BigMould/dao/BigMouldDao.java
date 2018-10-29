package com.sr.facorder.server.BigMould.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.BigMould.pojo.EpOrderMouldGcateGory;
import com.sr.facorder.server.booking.pojo.EpOrderMould;

public class BigMouldDao extends BaseDAO{

	//获取模板下的类别
	@SuppressWarnings("unchecked")
	public List<EpOrderMouldGcateGory> findCategoryName(String where){
		List<EpOrderMouldGcateGory> list = new ArrayList<EpOrderMouldGcateGory>();
		list = hibernateTemplate.find(" FROM EpOrderMouldGcateGory"+where);
		return list;
	}
	
	//获取类别下的单品
	@SuppressWarnings("unchecked")
	public List<EpOrderMould> findAllGoods(String where){
		List<EpOrderMould> goodslist = new ArrayList<EpOrderMould>();
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Transaction ts = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(where);
		query.addEntity(EpOrderMould.class);   
		goodslist = query.list();
		ts.commit();
		return goodslist;
	}
}
