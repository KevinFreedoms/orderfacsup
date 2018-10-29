package com.sr.facorder.server.customer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.customer.pojo.EbOrgState;
import com.sr.facorder.server.customer.pojo.EbUser;
import com.sr.facorder.server.customer.pojo.EpMouldOrg;

public class CustomerDao extends BaseDAO {

	//密码验证
	@SuppressWarnings("unchecked")
	public List<EbUser> findUser (String where){
		List<EbUser> list = new ArrayList<EbUser>();
		list = hibernateTemplate.find(" FROM EbUser " + where);
		return list;
	}
	
	//查询网点下的配送作业
	@SuppressWarnings("unchecked")
	public List<EpMouldOrg> findMoulds(String where){
		List<EpMouldOrg> list = new ArrayList<EpMouldOrg>();
		list = hibernateTemplate.find(" FROM EpMouldOrg" + where);
		return list;
	}
	
	//查询网点状态
	@SuppressWarnings("unchecked")
	public List<EbOrgState> findOrgState(String where){
		List<EbOrgState> list = new ArrayList<EbOrgState>();
		list = hibernateTemplate.find(" FROM EbOrgState" + where);
		return list;
	}
}
