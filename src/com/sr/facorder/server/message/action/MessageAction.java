package com.sr.facorder.server.message.action;

import java.util.HashMap;
import java.util.Map;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.message.service.IMessageService;

public class MessageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// paramter
	private Map<String,Object> result;
	private String url;
	
	//service
	private IMessageService messageService;

	// Getters & Setters //
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	public String toMessageList() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String type = request.getParameter("type");
		reMap = messageService.getMessageList(type);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String toMessageInfoById() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String where = " WHERE reckey=" + id;
		reMap = messageService.getMessageContent(where);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String toMessageInfo() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		String where = " WHERE CONVERT(VARCHAR(10),noticeDate,120) = CONVERT(VARCHAR(10),getdate(),120)";
		reMap = messageService.getMessageContent(where);
		this.result = reMap;
		return SUCCESS;
		
	}
	
	public String toCheckMessage() throws Exception {
		boolean flag = false;
		String where = " WHERE CONVERT(VARCHAR(10),noticeDate,120) = CONVERT(VARCHAR(10),getdate(),120)";
		flag = messageService.getMessageNowDayFlag(where);
		if(flag){
			this.url ="message.jsp";
		}else{
			this.url ="main.jsp";
		}
		return SUCCESS;
	}
	
	public String toMessage(){
		return SUCCESS;
	}
}
