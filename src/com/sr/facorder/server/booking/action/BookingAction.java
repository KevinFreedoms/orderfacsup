package com.sr.facorder.server.booking.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.BigMould.service.IBigMouldService;
import com.sr.facorder.server.booking.pojo.EpOrderLog;
import com.sr.facorder.server.booking.service.IBookingService;
import com.sr.facorder.server.booking.service.IHistoryService;

public class BookingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// paramter
	private EpOrderLog model;
	private Map<String,Object> result;
	//service
	private IBookingService bookingService;
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
	public IBookingService getBookingService() {
		return bookingService;
	}
	public void setBookingService(IBookingService bookingService) {
		this.bookingService = bookingService;
	}
	public void setHistoryService(IHistoryService historyService) {
		this.historyService = historyService;
	}
	//Action
	public String toListOrder(){
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = bookingService.getOrder(model);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String toShowGoodsInfo(){
		Map<String,Object> reMap = new HashMap<String, Object>();
		String goodsid = request.getParameter("goodsid");
		String flag = request.getParameter("flag");
		String deliverydate = request.getParameter("deliverydate");
		reMap = bookingService.getGoodsInfo(goodsid,flag,deliverydate);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String doCreateOrder() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = bookingService.getBookingMap(request);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String doModifyOrder() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = bookingService.getBookingMap(request);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String doDeleteOrder() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = bookingService.deleteOrder(request);
		this.result = reMap;
		return SUCCESS;
	}
	
	//展示订单详情
	public String toBookingInfo() throws Exception{
		String orderid = request.getParameter("orderid");
		String ishistory = request.getParameter("ishistory");
		result = historyService.getHisBookingInfo(orderid);
		String str = gson.toJson(result);
		byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
		request.setAttribute("bigdata", gson.toJson(new String(encodeBase64)));
		request.setAttribute("ishistory", gson.toJson(ishistory));
		return SUCCESS;
	}
	
}
