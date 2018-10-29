package com.sr.facorder.server.booking.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Decoder;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.booking.pojo.EpOrderLog;
import com.sr.facorder.server.booking.service.IHistoryService;

public class HistoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	// paramter
	private EpOrderLog model;
	private Map<String,Object> result;
	//service
	private IHistoryService historyService;
	public EpOrderLog getModel() {
		return model;
	}
	public void setModel(EpOrderLog model) {
		this.model = model;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	public IHistoryService getHistoryService() {
		return historyService;
	}
	
	
	public void setHistoryService(IHistoryService historyService) {
		this.historyService = historyService;
	}
	
	//method
	public String toListOrderHis() throws UnsupportedEncodingException, IOException{
		Map<String,Object> reMap = new HashMap<String, Object>();
		BASE64Decoder decoder = new BASE64Decoder();
		String orgid = new String(decoder.decodeBuffer(request.getParameter("orgid")), "utf-8");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		reMap = historyService.getHisOrder(startdate,enddate,orgid);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String toShowOrderHis()throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		String bookingid = request.getParameter("orderid");
		reMap = historyService.getHisBookingInfo(bookingid);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String print() throws Exception{
		String orderid = request.getParameter("id");
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = historyService.getprint(orderid);
		if((Boolean)reMap.get("success")){
			request.setAttribute("collect", gson.toJson(reMap.get("collect")));
			request.setAttribute("detail", gson.toJson(reMap.get("detail")));
			request.setAttribute("type", reMap.get("type"));
		}
		this.result = reMap;
		return SUCCESS;
	}
}
