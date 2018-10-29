package com.sr.facorder.server.staff.sql;

public class StaffSQL {
	public static String SQL_FIND_STAFF;//查找管理员
	public static String SQL_FIND_ROLEFUNCTION;//获取功能图标路径图片
	public static String SQL_UPDATE_STAFF;//修改管理员密码
	public static String SQL_GET_ORGNAME;//获取网点名称
	public static String SQL_GET_SUMPRICEANDSUMORDER;//获取网点区间内价格订单总数价格状态为1汇总
	public static String SQL_GET_SUMGOODS;//获取状态为1网点区间内的单品数
	public static String SQL_GET_SUMPRICEANDSUMORDERF;//获取状态为5网点区间内价格订单总数价格汇总
	public static String SQL_GET_SUMGOODSF;//获取状态为5网点区间内的单品数
	public static String SQL_GET_ORDERCOLLECT;//获得单个网点汇总信息
	public static String SQL_GET_ORDERCOLLECTF;//获得单个网点汇总信息
	public static String SQL_GET_ORDERDETAIL;//获得订单明细
	
	

	public void setSQL_GET_ORDERDETAIL(String sQL_GET_ORDERDETAIL) {
		SQL_GET_ORDERDETAIL = sQL_GET_ORDERDETAIL;
	}
	public void setSQL_GET_ORDERCOLLECTF(String sQL_GET_ORDERCOLLECTF) {
		SQL_GET_ORDERCOLLECTF = sQL_GET_ORDERCOLLECTF;
	}
	public void setSQL_GET_ORDERCOLLECT(String sQL_GET_ORDERCOLLECT) {
		SQL_GET_ORDERCOLLECT = sQL_GET_ORDERCOLLECT.trim();
	}
	public void setSQL_GET_SUMPRICEANDSUMORDERF(String sQL_GET_SUMPRICEANDSUMORDERF) {
		SQL_GET_SUMPRICEANDSUMORDERF = sQL_GET_SUMPRICEANDSUMORDERF.trim();
	}
	public void setSQL_GET_SUMGOODSF(String sQL_GET_SUMGOODSF) {
		SQL_GET_SUMGOODSF = sQL_GET_SUMGOODSF.trim();
	}
	public void setSQL_GET_SUMPRICEANDSUMORDER(String sQL_GET_SUMPRICEANDSUMORDER) {
		SQL_GET_SUMPRICEANDSUMORDER = sQL_GET_SUMPRICEANDSUMORDER.trim();
	}
	public void setSQL_GET_SUMGOODS(String sQL_GET_SUMGOODS) {
		SQL_GET_SUMGOODS = sQL_GET_SUMGOODS.trim();
	}
	public void setSQL_GET_ORGNAME(String sQL_GET_ORGNAME) {
		SQL_GET_ORGNAME = sQL_GET_ORGNAME.trim();
	}
	public void setSQL_UPDATE_STAFF(String sQL_UPDATE_STAFF) {
		SQL_UPDATE_STAFF = sQL_UPDATE_STAFF.trim();
	}

	public void setSQL_FIND_ROLEFUNCTION(String sQL_FIND_ROLEFUNCTION) {
		SQL_FIND_ROLEFUNCTION = sQL_FIND_ROLEFUNCTION.trim();
	}

	public void setSQL_FIND_STAFF(String sQL_FIND_STAFF) {
		SQL_FIND_STAFF = sQL_FIND_STAFF.trim();
	}
	
}
