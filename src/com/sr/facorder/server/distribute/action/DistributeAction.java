package com.sr.facorder.server.distribute.action;

import java.util.HashMap;
import java.util.Map;
import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.distribute.service.IDistributeService;
/**
 * 配送收货action
 * @author Administrator
 * @date  2017-2-27
 */
public class DistributeAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> result;
	private IDistributeService distributeService;
	
	public void setDistributeService(IDistributeService distributeService) {
		this.distributeService = distributeService;
	}

	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	public String toPcDistribute(){
		return SUCCESS;
	}
	public String toDistribute(){
		return SUCCESS;
	}
	
	
	//通过日期获取配送汇总
	public String toListDistribute(){
		
		Map<String,Object> map = new HashMap<String, Object>();
		String orgid = request.getParameter("orgid");
		String date = request.getParameter("distributedate");
		map = distributeService.getDistributeByDate(orgid,date);
		this.result=map;
		return SUCCESS;
	}
	
	//配送明细
	public String toDistributeDetail(){
		
		Map<String,Object> map =new HashMap<String, Object>();
		String bookingId =request.getParameter("bookingid");
		map = distributeService.getDistributeDetail(bookingId);
		this.result=map;
		return SUCCESS;
	}
	
	
	//获取打印数据
	public String toPrintList(){
		
		Map<String,Object> map =new HashMap<String, Object>();
		String bookingId =request.getParameter("id");
		String orgId =request.getParameter("org");
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		map = distributeService.getPrintData(bookingId,orgId,date,status);
		if((Boolean)map.get("success")){
			request.setAttribute("type", map.get("type"));
			request.setAttribute("detail", gson.toJson(map.get("detail")));
			request.setAttribute("collect", gson.toJson(map.get("collect")));
			request.setAttribute("status", gson.toJson(map.get("status")));
		}
		this.result=map;
		return SUCCESS;
	}
	
	//改变实收数量，更新
	public String toChangeArriveCount(){
		Map<String,Object> map =new HashMap<String, Object>();
		map = distributeService.changeArriveCount(request);
		this.result=map;
		return SUCCESS;
	}
	
	public String toMdetail(){
		return SUCCESS;
	}

}
