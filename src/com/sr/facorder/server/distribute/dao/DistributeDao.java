package com.sr.facorder.server.distribute.dao;

import java.util.ArrayList;
import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.distribute.pojo.EpDeliveryCollect;
import com.sr.facorder.server.distribute.pojo.EpDeliveryDetail;


public class DistributeDao extends BaseDAO{
	

	
	@SuppressWarnings("unchecked")
	public List<EpDeliveryDetail> findDistributeDetail(String where){
		List<EpDeliveryDetail> listDetail =new ArrayList<EpDeliveryDetail>();
		listDetail = hibernateTemplate.find(" FROM EpDeliveryDetail" + where);
		return listDetail;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EpDeliveryCollect> findDistributeCollect(String where){
		List<EpDeliveryCollect> listCollect =new ArrayList<EpDeliveryCollect>();
		listCollect = hibernateTemplate.find(" FROM EpDeliveryCollect" + where);
		return listCollect;
	}
	
	public void updateDisDetail(EpDeliveryDetail edd){
		hibernateTemplate.update(edd);
		hibernateTemplate.flush();
	}
	

	public void updateDelivery(List<EpDeliveryDetail> list,EpDeliveryCollect collect){
		hibernateTemplate.saveOrUpdateAll(list);
		hibernateTemplate.update(collect);
		hibernateTemplate.flush();
		
	}
}
