package com.sr.facorder.server.distribute.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IDistributeService {
	
	Map<String,Object> getDistributeByDate(String orgid,String date);
	Map<String,Object> getDistributeDetail(String bookingId);
	
	Map<String,Object> getPrintData(String bookingId,String orgId,String date,String status);
	Map<String,Object> changeArriveCount(HttpServletRequest request);

}
