package com.sr.facorder.server.customer.action;

import java.util.HashMap;
import java.util.Map;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.customer.pojo.EbUser;
import com.sr.facorder.server.customer.service.ICustomerService;

public class CustomerAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// paramter
	private EbUser model;
	private Map<String,Object> result;
	//service
	private ICustomerService customerService;
	//setter&getter
	public EbUser getModel() {
		return model;
	}
	public void setModel(EbUser model) {
		this.model = model;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	public ICustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	
	// Action Method //
	public String toIndex() throws Exception {
		return SUCCESS;
	}
	
	public String dologout() throws Exception {
		return SUCCESS;
	}
	
	public String dologin() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = customerService.getCustomer(model);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String doPassword() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String use = request.getParameter("username");
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		reMap = customerService.updatePassword(use,password,newpassword);
		this.result = reMap;
		
		return SUCCESS;
	}
	
}
