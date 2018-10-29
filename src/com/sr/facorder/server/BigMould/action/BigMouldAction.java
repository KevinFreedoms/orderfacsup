package com.sr.facorder.server.BigMould.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.BigMould.service.IBigMouldService;

public class BigMouldAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> result;
	private IBigMouldService bigMouldService ;
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	public void setBigMouldService(IBigMouldService bigMouldService) {
		this.bigMouldService = bigMouldService;
	}
	
	
	public String toBigPage(){
		String mouldId = request.getParameter("mouldId");
		String deliverydate = request.getParameter("deliverydate");
		deliverydate = deliverydate.trim().replace('\n',' ');
		result = bigMouldService.getCheckMoubld(mouldId,deliverydate);
		return SUCCESS;
	}
	
	public String toPcBigMould() throws Exception {
		String orgid = request.getParameter("orgid");
		String mouldType = request.getParameter("mouldType");
		String mouldId = request.getParameter("mouldId");
		String deliverydate = request.getParameter("deliverydate");
		deliverydate = deliverydate.trim().replace('\n',' ');
		String flag = request.getParameter("flag");
		String bookingtype = request.getParameter("type");
		String orderid ="";
		if(flag.equals("1")||flag=="1"){
			orderid = request.getParameter("orderid");
		}
		result = bigMouldService.getOrder(mouldId,deliverydate,orderid);
		result.put("mouldType", mouldType);
		result.put("mouldId", mouldId);
		result.put("orgid", orgid);
		result.put("flag", flag);
		result.put("bookingtype", bookingtype);
		result.put("deliverydate", deliverydate);
		String str = gson.toJson(result);
		byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
		request.setAttribute("bigdata", gson.toJson(new String(encodeBase64)));
		return SUCCESS;
	}
	
	public String toBigMould() throws Exception {
		String orgid = request.getParameter("orgid");
		String mouldType = request.getParameter("mouldType");
		String mouldId = request.getParameter("mouldId");
		String deliverydate = request.getParameter("deliverydate");
		deliverydate = deliverydate.trim().replace('\n',' ');
		String flag = request.getParameter("flag");
		String bookingtype = request.getParameter("type");
		String orderid ="";
		if(flag.equals("1")||flag=="1"){
			orderid = request.getParameter("orderid");
		}
		result = bigMouldService.getOrder(mouldId,deliverydate,orderid);
		result.put("mouldType", mouldType);
		result.put("mouldId", mouldId);
		result.put("orgid", orgid);
		result.put("flag", flag);
		result.put("bookingtype", bookingtype);
		result.put("deliverydate", deliverydate);
		String str = gson.toJson(result);
		byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
		request.setAttribute("bigdata", gson.toJson(new String(encodeBase64)));
		return SUCCESS;
	}
	
	public String toGetAllGoods(){
		Map<String,Object> reMap = new HashMap<String, Object>();
		String mouldId = request.getParameter("mouldId");
		String orgid = request.getParameter("orgid");
		String deliverydate = request.getParameter("deliverydate");
		reMap = bigMouldService.getAllGoods(mouldId,orgid,deliverydate);
		this.result = reMap;
		return SUCCESS;
	}
	//查找名称
	public String findminchengOrder(){
		String mincheng=request.getParameter("mingcheng");
		String mouldId=request.getParameter("mouldId");
		String orgid = request.getParameter("orgid");
		String deliverydate = request.getParameter("deliverydate");
		result=bigMouldService.getCategoryGoods(mincheng, mouldId,orgid,deliverydate);
		return SUCCESS;
	}

	//搜索模板下的单品
	public String toSearchGoods(){
		String mincheng=request.getParameter("mingcheng");
		String mouldId=request.getParameter("mouldId");
		String orgid = request.getParameter("orgid");
		String deliverydate = request.getParameter("deliverydate");
		result=bigMouldService.getSearchGoods(mincheng, mouldId,orgid,deliverydate);
		return SUCCESS;
	}
	
	public String toListYestodayOrder() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		String mouldId = request.getParameter("mouldId");
		String orgid = request.getParameter("orgid");
		String deliverydate = request.getParameter("deliverydate");
 		reMap = bigMouldService.getYestodayOrder(mouldId,orgid,deliverydate);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String print() throws Exception{
		String orderid = request.getParameter("id");
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = bigMouldService.getprint(orderid);
		if((Boolean)reMap.get("success")){
			request.setAttribute("collect", gson.toJson(reMap.get("collect")));
			request.setAttribute("detail", gson.toJson(reMap.get("detail")));
			request.setAttribute("type", reMap.get("type"));
		}
		this.result = reMap;
		return SUCCESS;
	}
	//获得是否启用建议零售价
	public String getIsRetailPrice() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap=bigMouldService.getIsRetailPrice();
		this.result = reMap;
		return SUCCESS;
	}
}
