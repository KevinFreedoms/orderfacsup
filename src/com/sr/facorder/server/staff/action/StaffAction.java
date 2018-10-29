package com.sr.facorder.server.staff.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqlArcher.server.dao.ISQLDao;
import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.staff.pojo.OrderDetail;
import com.sr.facorder.server.staff.service.IStaffService;
import com.sr.facorder.server.staff.sql.StaffSQL;

public class StaffAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// paramter
	private Map<String,Object> result;
	//service
	private IStaffService staffService;
	private String staffId;
	private String password;
	private String newpassword;
	private String orgId;
	private String lastupdate;
	private String status;
	private String addDay;
	private String deliveryDate;
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddDay() {
		return addDay;
	}

	public void setAddDay(String addDay) {
		this.addDay = addDay;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	private ISQLDao sqlDao;
	public ISQLDao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(ISQLDao sqlDao) {
		this.sqlDao = sqlDao;
	}
	
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//setter&getter
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	public IStaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(IStaffService staffService) {
		this.staffService = staffService;
	}
	// Action Method //
	public String toMainsys() throws Exception {
		return SUCCESS;
	}
	
	public String dologsys() throws Exception {
		return SUCCESS;
	}
	public String searchOrder() throws Exception {
		return SUCCESS;
	}
	public String toOrderCollect() throws Exception {
		return SUCCESS;
	}
	public String toOrderDetail() throws Exception {
		return SUCCESS;
	}
	
	public String dologinsys() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap = staffService.findStaff(staffId, password);
		this.result = reMap;
		return SUCCESS;
	}
	
	public String doPassword() throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap =staffService.updatePassword(staffId,password,newpassword);
		this.result = reMap;
		
		return SUCCESS;
	}
	public String getOrg() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		List<Map<String,Object>> list = sqlDao.selectRecords(StaffSQL.SQL_GET_ORGNAME);
		reMap.put("success", true);
		reMap.put("org",list);
		this.result=reMap;
		return SUCCESS;
	}
	public String getOrderSelect() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap=staffService.getOrderSelect(orgId, lastupdate, status, deliveryDate, addDay);
		this.result=reMap;
		return SUCCESS;
	}
	public String getOrderCollect() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		reMap=staffService.getOrderCollect(orgId, lastupdate, status, deliveryDate, addDay);
		this.result=reMap;
		return SUCCESS;
	}
	public String getOrderDetail() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		Map<String,Object> paMap = new HashMap<String, Object>();
		paMap.put("orderId", orderId);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		list=sqlDao.selectRecords(StaffSQL.SQL_GET_ORDERDETAIL,paMap,OrderDetail.class);
		reMap.put("success", true);
		reMap.put("orderDetail",list);
		this.result=reMap;
		return SUCCESS;
	}
	
}
