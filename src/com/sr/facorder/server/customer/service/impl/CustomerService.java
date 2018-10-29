package com.sr.facorder.server.customer.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sqlArcher.server.dao.ISQLDao;
import com.sr.facorder.common.util.MD5Util;
import com.sr.facorder.server.customer.dao.CustomerDao;
import com.sr.facorder.server.customer.pojo.EbOrgState;
import com.sr.facorder.server.customer.pojo.EbUser;
import com.sr.facorder.server.customer.pojo.EpMouldOrg;
import com.sr.facorder.server.customer.service.ICustomerService;

public class CustomerService implements ICustomerService {
	private CustomerDao customerDao;
	private ISQLDao sqlDao;
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public ISQLDao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(ISQLDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	public Map<String, Object> getCustomer(EbUser user) {
		String where = "";
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			//判断程序是否已经过了限时
			/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse("2017-12-12"); 
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(daystart);
			Calendar calendar1 = Calendar.getInstance();
			if(calendar1.getTime().after(calendar.getTime())){
				reMap.put("info", "当前程序已经到期~请联系相关人员~");
				reMap.put("success", false);
				return reMap;
			}*/
			//判断账号密码是否正确
			String passWord = MD5Util.getMD5(user.getPassWord());
			where = " WHERE (userId='"+ user.getUserId() +"' OR phone='"+ user.getPhone() +"') AND passWord='"+passWord+"'";
			List<EbUser> list= customerDao.findUser(where);
			if(list.size()==0){
				reMap.put("info", "用户名编码或口令不正确！");
				reMap.put("success", false);
				return reMap;
			}
			//判断网点是否已经启用
			where = " WHERE orgId = '"+list.get(0).getOrgId()+"' AND usestate= '1'";
			List<EbOrgState> orgs = customerDao.findOrgState(where);
			if(orgs.size()==0){
				reMap.put("info", "网点未启用或者已经停用！");
				reMap.put("success", false);
				return reMap;
			}
			//判断网点是否分配配送作业
			where = " WHERE orgId = '"+list.get(0).getOrgId()+"'";
			List<EpMouldOrg> moulds = customerDao.findMoulds(where);
			if(moulds.size()==0){
				reMap.put("info","没有匹配的模板~请谅解~");
				reMap.put("success", false);
				return reMap;
			}
			reMap.put("moulds", moulds);
			//判断即将过期的天数
			/*long between_days=(calendar.getTimeInMillis()-calendar1.getTimeInMillis())/(1000*3600*24);
			int day = Integer.parseInt(String.valueOf(between_days));
			if(day<=15){
				reMap.put("limit", true);
				reMap.put("info", "当前程序还有"+day+"到期，请及时联系相关人员~");
			}*/
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	public Map<String, Object> updatePassword(String use,String password,String newpassword) {
		Map<String,Object> reMap = new HashMap<String, Object>();
		try{
			//判断账号密码是否正确
			String pass = MD5Util.getMD5(password);
			String where = " WHERE (userId='"+ use +"' OR phone='"+ use +"') AND passWord='"+pass+"'";
			List<EbUser> list = customerDao.findUser(where);
			if(list.size()==0){
				reMap.put("info", "户名编码或口令不正确！");
				reMap.put("success", false);
				return reMap;
			}
			//判断网点是否已经启用
			where = " WHERE orgId = '"+list.get(0).getOrgId()+"' AND usestate= '1'";
			List<EbOrgState> orgs = customerDao.findOrgState(where);
			if(orgs.size()==0){
				reMap.put("info", "网点未启用或者已经停用！");
				reMap.put("success", false);
				return reMap;
			}
			//判断网点是否分配配送作业
			where = " WHERE orgId = '"+list.get(0).getOrgId()+"'";
			List<EpMouldOrg> moulds = customerDao.findMoulds(where);
			if(moulds.size()==0){
				reMap.put("info", "当前网点尚未分配配送作业，无法进行请货操作！");
				reMap.put("success", false);
				return reMap;
			}
			//更新密码
			String newPass = MD5Util.getMD5(newpassword);
			where = "UPDATE EB_User SET passWord='"+newPass+"' WHERE (userId='"+ use +"' OR phone='"+ use +"')";
			sqlDao.updateRecord(where);
			reMap.put("success", true);
			return reMap;
		}catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

}
