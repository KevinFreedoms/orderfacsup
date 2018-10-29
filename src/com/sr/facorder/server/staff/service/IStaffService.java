package com.sr.facorder.server.staff.service;

import java.util.Map;



public interface IStaffService {
	public Map<String,Object> findStaff(String staffId,String password);
	public Map<String,Object> updatePassword(String staffId,String password,String newpassword);
	public Map<String,Object> getOrderSelect(String orgId,String lastupdate,String status,String deliveryDate,String addDay);
	public Map<String,Object> getOrderCollect(String orgId,String lastupdate,String status,String deliveryDate,String addDay);
	
}
