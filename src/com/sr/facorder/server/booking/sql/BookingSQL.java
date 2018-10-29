package com.sr.facorder.server.booking.sql;

public class BookingSQL {
	public static String SQL_SEARCH_MOULD;			//搜索单品
	public static String SQL_SHOW_BIGMOULD;			//显示超大模板信息
	public static String SQL_SHOW_MOULD;			//显示订单模板信息
	public static String SQL_SHOW_ORGMOULD;			//查询网点下有效的模板	2018-01-16
	public static String SQL_SHOW_BOOKINGLIST;	    //查询订单列表  2018-01-19
	
	public static String SQL_SHOW_DELIVERY;			//显示直送
	public static String SQL_SHOW_DISTRIBUTE;		//配送收货 jiaself
	public static String SQL_SHOW_DELIVERYREF;		//不启用采购平台 jiaself  2017-03-20
	public static String SQL_GET_ISRETAILPRICE;		//获得是否启用建议零售价
	
	public void setSQL_GET_ISRETAILPRICE(String sQL_GET_ISRETAILPRICE) {
		SQL_GET_ISRETAILPRICE = sQL_GET_ISRETAILPRICE.trim();
	}
	public void setSQL_SHOW_MOULD(String sQL_SHOW_MOULD) {
		SQL_SHOW_MOULD = sQL_SHOW_MOULD.trim();
	}
	public void setSQL_SHOW_BIGMOULD(String sQL_SHOW_BIGMOULD) {
		SQL_SHOW_BIGMOULD = sQL_SHOW_BIGMOULD.trim();
	}
	public void setSQL_SHOW_ORGMOULD(String sQL_SHOW_ORGMOULD) {
		SQL_SHOW_ORGMOULD = sQL_SHOW_ORGMOULD.trim();
	}
	public void setSQL_SEARCH_MOULD(String sQL_SEARCH_MOULD) {
		SQL_SEARCH_MOULD = sQL_SEARCH_MOULD.trim();
	}
	public void setSQL_SHOW_BOOKINGLIST(String sQL_SHOW_BOOKINGLIST) {
		SQL_SHOW_BOOKINGLIST = sQL_SHOW_BOOKINGLIST.trim();
	}
	
	
	public void setSQL_SHOW_DISTRIBUTE(String sQL_SHOW_DISTRIBUTE) {
		SQL_SHOW_DISTRIBUTE = sQL_SHOW_DISTRIBUTE.trim();
	}
	public void setSQL_SHOW_DELIVERYREF(String sQL_SHOW_DELIVERYREF) {
		SQL_SHOW_DELIVERYREF = sQL_SHOW_DELIVERYREF.trim();
	}
	public void setSQL_SHOW_DELIVERY(String sQL_SHOW_DELIVERY) {
		SQL_SHOW_DELIVERY = sQL_SHOW_DELIVERY.trim();
	}
	
}
