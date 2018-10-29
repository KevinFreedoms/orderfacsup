package com.sr.facorder.server.message.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sr.facorder.server.message.dao.MessageDAO;
import com.sr.facorder.server.message.pojo.EpInfrom;
import com.sr.facorder.server.message.service.IMessageService;

public class MessageService implements IMessageService {

	private MessageDAO messageDao;
	
	public MessageDAO getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}

	public Map<String, Object> getMessageList(String type) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			String where = "";
			List<EpInfrom> list = new ArrayList<EpInfrom>();
			if(type.equals("day"))
			{
				where = " WHERE CONVERT(VARCHAR(10),noticeDate,120) = CONVERT(VARCHAR(10),getdate(),120) ORDER BY noticeDate";
			}else if(type.equals("week")){
				where = " WHERE datediff(week,noticeDate,getdate())=0 ORDER BY noticeDate";
			}else{
				where = " WHERE datediff(month,noticeDate,getdate())=0 ORDER BY noticeDate";
			}
			list = messageDao.find(where);
			reMap.put("list", list);
			reMap.put("success", true);
			reMap.put("totalrecord", list.size());
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	@Override
	public Map<String, Object> getMessageContent(String where) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			List<EpInfrom> list = new ArrayList<EpInfrom>();
			list = messageDao.find(where);
			reMap.put("content", list.get(0));
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}
	@Override
	public boolean getMessageNowDayFlag(String where) {
		boolean flag = false;
		try{
			List<EpInfrom> list = new ArrayList<EpInfrom>();
			list = messageDao.find(where);
			if(list.size()>0)
				flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
