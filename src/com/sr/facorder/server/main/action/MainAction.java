package com.sr.facorder.server.main.action;

import java.util.HashMap;
import java.util.Map;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.main.service.IMainService;

public class MainAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// paramter
	private Map<String,Object> result;
	private IMainService mainService;
	//getter&setter
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	public IMainService getMainService() {
		return mainService;
	}
	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}
	// Action Method //
	public String toOrder() throws Exception {
		return SUCCESS;
	}
	
	public String toPcMain() throws Exception {
		return SUCCESS;
	}
	
	public String toPcDelivery() throws Exception {
		return SUCCESS;
	}
	
	public String toPcHistory() throws Exception {
		return SUCCESS;
	}
	
	public String toPcBigMould() throws Exception {
		return SUCCESS;
	}
	
	public String toBigMould() throws Exception {
		return SUCCESS;
	}
	
	public String toPcMessage() throws Exception {
		return SUCCESS;
	}
	
	public String toDelivery() throws Exception {
		return SUCCESS;
	}
	public String toHistory() throws Exception {
		return SUCCESS;
	}
	
	public String toMessage() throws Exception {
		return SUCCESS;
	}
	
	public String toListDelMoulds() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String orgid = request.getParameter("orgid");
		reMap = mainService.getListDelMoulds(orgid);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String toListDelTime() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String deliveryid = request.getParameter("deliveryid");
		String mouldid = request.getParameter("mouldid");
		reMap = mainService.getListDelDates(deliveryid,mouldid);
		this.result = reMap;
		return SUCCESS;
	}
}
