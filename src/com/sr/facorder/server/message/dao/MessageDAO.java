package com.sr.facorder.server.message.dao;

import java.util.ArrayList;
import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.message.pojo.EpInfrom;

public class MessageDAO extends BaseDAO {

	@SuppressWarnings("unchecked")
	public List<EpInfrom> find(String where){
		List<EpInfrom> list = new ArrayList<EpInfrom>();
		list = hibernateTemplate.find("From EpInfrom" + where);
		return list;
	}
}
