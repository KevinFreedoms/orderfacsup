package com.sr.facorder.server.customer.service;

import java.util.Map;
import com.sr.facorder.server.customer.pojo.EbUser;

public interface ICustomerService {

	//用户信息的检索
	public Map<String,Object> getCustomer(EbUser user);
	//修改用户密码
	public Map<String,Object> updatePassword(String use,String password,String newpassword);
}
