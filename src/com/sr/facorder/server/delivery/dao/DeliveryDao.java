package com.sr.facorder.server.delivery.dao;

import java.util.ArrayList;
import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.delivery.pojo.EpDeliveryGoods;
import com.sr.facorder.server.delivery.pojo.EpSynState;


public class DeliveryDao extends BaseDAO{
	@SuppressWarnings("unchecked")
	public List<EpDeliveryGoods> getAllEpDeliveryGoods(String where){
		List<EpDeliveryGoods> list = new ArrayList<EpDeliveryGoods>();
		list = hibernateTemplate.find(" FROM EpDeliveryGoods" + where);
		return list;						 
	}
	
	public void updateDeliveryGoods(EpDeliveryGoods edg){
		hibernateTemplate.update(edg);
	}
	
	public void updateDeliveryGoodsAll( List<EpDeliveryGoods> list){
		hibernateTemplate.saveOrUpdateAll(list);
		hibernateTemplate.flush();
	}
	
	//jiaself 2017-03-20  获取采购平台启用状态 0 1 2
	@SuppressWarnings("unchecked")
	public List<EpSynState> getEnableStatus(String where){
		List<EpSynState> list = new ArrayList<EpSynState>();
		list = hibernateTemplate.find(" FROM EpSynState" + where);
		return list;
	}
	
}
