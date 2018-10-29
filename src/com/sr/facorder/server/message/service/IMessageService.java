package com.sr.facorder.server.message.service;

import java.util.Map;

public interface IMessageService {

	//查询消息列表
	public Map<String,Object> getMessageList(String type);
	
	//查询消息明细
	public Map<String,Object> getMessageContent(String where);
	
	//检索当前消息是否存在
	public boolean getMessageNowDayFlag(String where);
}
