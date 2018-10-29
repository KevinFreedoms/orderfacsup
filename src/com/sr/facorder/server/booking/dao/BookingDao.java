package com.sr.facorder.server.booking.dao;

import java.util.ArrayList;
import java.util.List;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.booking.pojo.EbOrderCollect;
import com.sr.facorder.server.booking.pojo.EbOrderDetail;
import com.sr.facorder.server.booking.pojo.EpMouldGoodsDelPrice;
import com.sr.facorder.server.booking.pojo.EpOrderLog;
import com.sr.facorder.server.booking.pojo.EpOrderMould;
import com.sr.facorder.server.booking.pojo.EpOrg;
import com.sr.facorder.server.booking.pojo.EpProduct;
import com.sr.facorder.server.main.pojo.EpDelivery;

public class BookingDao extends BaseDAO {
	
	@SuppressWarnings("unchecked")
	public List<EpDelivery> findDels (String where){
		List<EpDelivery> list = new ArrayList<EpDelivery>();
		list = hibernateTemplate.find(" FROM EpDelivery" + where);
		return list;
	}
	//查询订单
	@SuppressWarnings("unchecked")
	public List<EpOrderLog> findOrderLogs(String where){
		List<EpOrderLog> list = new ArrayList<EpOrderLog>();
		list = hibernateTemplate.find(" FROM EpOrderLog" + where);
		return list;
	}
	
	//获取模板明细
	@SuppressWarnings("unchecked")
	public List<EpOrderMould> findMouldGoods(String where){
		List<EpOrderMould> list = new ArrayList<EpOrderMould>();
		list = hibernateTemplate.find(" FROM EpOrderMould" + where);
		return list;
	}
	
	//插入汇总
	public void insertCollect(EbOrderCollect collect){
		hibernateTemplate.save(collect);
		hibernateTemplate.flush();
	}
	
	//删除汇总
	public void deleteCollect(List<EbOrderCollect> list){
		hibernateTemplate.deleteAll(list);
		hibernateTemplate.flush();
	}
	
	//插入明细
	public void insertDetail(List<EbOrderDetail> list){
		hibernateTemplate.saveOrUpdateAll(list);
		hibernateTemplate.flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<EbOrderCollect> findOrderCollect(String where){
		List<EbOrderCollect> list = new ArrayList<EbOrderCollect>();
		list = hibernateTemplate.find(" FROM EbOrderCollect" + where);
		return list;
	}
	
	//获取订单明细
	@SuppressWarnings("unchecked")
	public List<EbOrderDetail> findOrderGoods(String where){
		List<EbOrderDetail> list = new ArrayList<EbOrderDetail>();
		list = hibernateTemplate.find(" FROM EbOrderDetail" + where);
		return list;
	}
	
	//获取模板对应网点配送价格
	@SuppressWarnings("unchecked")
	public List<EpMouldGoodsDelPrice> findOrderGoodsPrice(String where){
		List<EpMouldGoodsDelPrice> list = new ArrayList<EpMouldGoodsDelPrice>();
		list = hibernateTemplate.find(" FROM EpMouldGoodsDelPrice" + where);
		return list;
	}
	
	//删除订单明细
	public void deleteDetail(List<EbOrderDetail> list){
		hibernateTemplate.deleteAll(list);
		hibernateTemplate.flush();
	}
	
	//插入日志
	public void insertLog(EpOrderLog log){
		hibernateTemplate.save(log);
		hibernateTemplate.flush();
	}
	
	//删除日志文件
	public void deleteLog(List<EpOrderLog> list){
		hibernateTemplate.deleteAll(list);
		hibernateTemplate.flush();
	}
	//查询套餐组成Ep
	@SuppressWarnings("unchecked")
	public List<EpProduct> findGoodsInfo(String where){
		List<EpProduct> list = new ArrayList<EpProduct>();
		list = hibernateTemplate.find(" FROM EpProduct" + where);
		return list;
	}
	
	//查询网点信息
	@SuppressWarnings("unchecked")
	public List<EpOrg> findOrgs(String where){
		List<EpOrg> list = new ArrayList<EpOrg>();
		list = hibernateTemplate.find(" FROM EpOrg" + where);
		return list;
	}
	//2018-01-14 更改 新增修改删除
	public void saveBooking(EpOrderLog log,EbOrderCollect collect,List<EbOrderDetail> list){
		hibernateTemplate.clear();
		hibernateTemplate.persist(log);
		hibernateTemplate.persist(collect);
		hibernateTemplate.saveOrUpdateAll(list);
		hibernateTemplate.flush();
	}
	
	public void updateBooking(EbOrderCollect collect,EpOrderLog log,List<EbOrderDetail> deletes,List<EbOrderDetail> list){
		hibernateTemplate.clear();
		//更新明细
		hibernateTemplate.deleteAll(deletes);
		hibernateTemplate.saveOrUpdateAll(list);
		//更新订单日志
		hibernateTemplate.update(collect);
		hibernateTemplate.update(log);
		hibernateTemplate.flush();
	}
	
	public void deleteBooking(EpOrderLog log,EbOrderCollect collect,List<EbOrderDetail> list){
		hibernateTemplate.clear();
		hibernateTemplate.deleteAll(list);
		hibernateTemplate.delete(collect);
		hibernateTemplate.delete(log);
		hibernateTemplate.flush();
	}
}
