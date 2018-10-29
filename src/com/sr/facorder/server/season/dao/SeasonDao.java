package com.sr.facorder.server.season.dao;

import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.season.pojo.EpGoodsSeason;

public class SeasonDao extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<EpGoodsSeason> findSeasonGoods(String where){
		
		String hql = " FROM EpGoodsSeason"+where;
		List<EpGoodsSeason> list=hibernateTemplate.find(hql);
		return list;
	}

	
}
