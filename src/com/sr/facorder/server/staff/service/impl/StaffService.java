package com.sr.facorder.server.staff.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqlArcher.server.dao.ISQLDao;
import com.sr.facorder.common.util.MD5Util;
import com.sr.facorder.server.staff.pojo.EBRoleFunction;
import com.sr.facorder.server.staff.pojo.EBStaff;
import com.sr.facorder.server.staff.pojo.OrderCollect;
import com.sr.facorder.server.staff.pojo.OrderSelect;
import com.sr.facorder.server.staff.service.IStaffService;
import com.sr.facorder.server.staff.sql.StaffSQL;

public class StaffService implements IStaffService {
	private ISQLDao sqlDao;

	public ISQLDao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(ISQLDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	@Override
	public Map<String, Object> findStaff(String staffId, String password) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			String passWord = MD5Util.getMD5(password);
			List<EBStaff> list = new ArrayList<EBStaff>();
			List<EBRoleFunction> roleFunction = new ArrayList<EBRoleFunction>();
			Map<String, Object> paMap = new HashMap<String, Object>();
			paMap.put("staffId", staffId);
			paMap.put("password", passWord);
			list = sqlDao.selectRecords(StaffSQL.SQL_FIND_STAFF, paMap, EBStaff.class);
			if (list.size() == 0) {
				reMap.put("info", "用户名编码或口令不正确！");
				reMap.put("success", false);
				return reMap;
			}
			String roleId = list.get(0).getRoleId();
			if(!"0002".equals(roleId)){
				reMap.put("info", "您没有权限登录");
				reMap.put("success", false);
				return reMap;
			}
			paMap.put("roleId", roleId);
			roleFunction = sqlDao.selectRecords(StaffSQL.SQL_FIND_ROLEFUNCTION, paMap, EBRoleFunction.class);
			if (roleFunction.size() == 0) {
				reMap.put("info", "未找到信息");
				reMap.put("success", false);
				return reMap;
			}
			reMap.put("success", true);
			reMap.put("staff", roleFunction);
			return reMap;
		} catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	@Override
	public Map<String, Object> updatePassword(String staffId, String password, String newpassword) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			// 判断账号密码是否正确
			String pass = MD5Util.getMD5(password);
			List<EBStaff> list = new ArrayList<EBStaff>();
			Map<String, Object> paMap = new HashMap<String, Object>();
			paMap.put("staffId", staffId);
			paMap.put("password", pass);
			list = sqlDao.selectRecords(StaffSQL.SQL_FIND_STAFF, paMap, EBStaff.class);
			if (list.size() == 0) {
				reMap.put("info", "户名编码或口令不正确！");
				reMap.put("success", false);
				return reMap;
			}
			// 更新密码
			String newPass = MD5Util.getMD5(newpassword);
			paMap.put("newPassword", newPass);
			sqlDao.updateRecord(StaffSQL.SQL_UPDATE_STAFF, paMap);
			reMap.put("success", true);
			return reMap;
		} catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	@Override
	public Map<String, Object> getOrderSelect(String orgId, String lastupdate, String status, String deliveryDate,
			String addDay) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			Map<String, Object> paMap = new HashMap<String, Object>();
			List<OrderSelect> orderSelect = new ArrayList<OrderSelect>();
			List<Map<String, Object>> sumGoods = new ArrayList<Map<String, Object>>();
			if (lastupdate == null || "".equals(lastupdate)) {
				reMap.put("info", "参数错误！");
				reMap.put("success", false);
				return reMap;
			}
			paMap.put("orgId", orgId);
			paMap.put("status", status);
			paMap.put("addDay", new Integer(addDay));
			paMap.put("deliveryDate", deliveryDate);
			if ("5".equals(status)) {
				orderSelect = sqlDao.selectRecords(StaffSQL.SQL_GET_SUMPRICEANDSUMORDERF, paMap, OrderSelect.class);
				sumGoods = sqlDao.selectRecords(StaffSQL.SQL_GET_SUMGOODSF, paMap);
			}
			if ("1".equals(status)) {
				String lastupdateS = lastupdate.split(" ")[0] + " 00:00:00.000";
				String lastupdateE = lastupdate + ".999";
				paMap.put("lastupdateS", lastupdateS);
				paMap.put("lastupdate", lastupdateS);
				paMap.put("lastupdateE", lastupdateE);
				orderSelect = sqlDao.selectRecords(StaffSQL.SQL_GET_SUMPRICEANDSUMORDER, paMap, OrderSelect.class);
				sumGoods = sqlDao.selectRecords(StaffSQL.SQL_GET_SUMGOODS, paMap);
				
			}
			if (orderSelect.size() > 0) {
				for (OrderSelect o : orderSelect) {
					for (Map<String, Object> sumGood : sumGoods) {
						if (sumGood.get("orgId").equals(o.getOrgId())) {
							o.setGoodsType((Integer) sumGood.get("goodsType"));
							break;
						}
					}
				}
			}
			reMap.put("success", true);
			reMap.put("orderlist", orderSelect);
			return reMap;
		} catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

	@Override
	public Map<String, Object> getOrderCollect(String orgId, String lastupdate, String status, String deliveryDate,
			String addDay) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			Map<String, Object> paMap = new HashMap<String, Object>();
			List<OrderCollect> orderCollect = new ArrayList<OrderCollect>();
			if (lastupdate == null || "".equals(lastupdate)) {
				reMap.put("info", "参数错误！");
				reMap.put("success", false);
				return reMap;
			}
			paMap.put("orgId", orgId);
			paMap.put("status", status);
			paMap.put("addDay", new Integer(addDay));
			paMap.put("deliveryDate", deliveryDate);
			if ("5".equals(status)) {
				orderCollect = sqlDao.selectRecords(StaffSQL.SQL_GET_ORDERCOLLECTF, paMap, OrderCollect.class);
			}
			if ("1".equals(status)) {
				String lastupdateS = lastupdate.split(" ")[0] + " 00:00:00.000";
				String lastupdateE = lastupdate + ".999";
				paMap.put("lastupdateS", lastupdateS);
				paMap.put("lastupdate", lastupdateS);
				paMap.put("lastupdateE", lastupdateE);
				orderCollect = sqlDao.selectRecords(StaffSQL.SQL_GET_ORDERCOLLECT, paMap, OrderCollect.class);
			}
			reMap.put("success", true);
			reMap.put("orderCollect", orderCollect);
			return reMap;
		} catch (Exception e) {
			e.printStackTrace();
			reMap.put("info", "数据库访问异常请重试！");
			reMap.put("success", false);
			return reMap;
		}
	}

}
