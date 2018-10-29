package com.sr.facorder.server.delivery.action;

import java.util.HashMap;
import java.util.Map;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.delivery.service.IDeliveryService;

public class DeliveryAction extends BaseAction{
	private Map<String,Object> result;
	private IDeliveryService deliveryService;

	private static final long serialVersionUID = 1L;
	public String toChangeArrivecount(){
		Map<String,Object> map = new HashMap<String, Object>();
		String info = request.getParameter("info");
		String org = request.getParameter("org");
		String bookingId = request.getParameter("bookingId");
		map = deliveryService.ChangeArrivecount(info,org,bookingId);
		this.result = map;
		return SUCCESS;
	}
	
	public String toListDelivery(){
		String username = request.getParameter("username");
		String date = request.getParameter("date");
		Map<String,Object> map = new HashMap<String, Object>();
		map = deliveryService.getDeliveryTask(username,date);
		this.result = map;
		return SUCCESS;
	}
	
	public String toSupplierDetail(){
		String bookingid = request.getParameter("bookingid");
		String org = request.getParameter("org");
		String date = request.getParameter("date");
		Map<String,Object> map = new HashMap<String, Object>();
		map = deliveryService.getSupplierDetail(bookingid,org,date);
		this.result = map;
		return SUCCESS;
	}
	
	public String print() throws Exception{
		String org = request.getParameter("org");
		String bookingId = request.getParameter("id");
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = deliveryService.getPrint(org,bookingId);
		if((Boolean)reMap.get("success")){
			request.setAttribute("collect", gson.toJson(reMap.get("collect")));
			request.setAttribute("detail", gson.toJson(reMap.get("detail")));
			request.setAttribute("type", reMap.get("type"));
		}
		this.result = reMap;
		return SUCCESS;
	}
	
	public String toMdetail() throws Exception{
		return SUCCESS;
	}
	
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public void setDeliveryService(IDeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
}