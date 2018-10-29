package com.sr.facorder.server.season.action;

import java.util.HashMap;
import java.util.Map;

import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.season.service.ISeasonService;

public class SeasonAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private ISeasonService seasonService;
	private Map<String,Object> result;
	
	public void setSeasonService(ISeasonService seasonService) {
		this.seasonService = seasonService;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	public String toPcSeason(){
		return SUCCESS;
	}
	public String toSeason(){
		return SUCCESS;
	}
	
	public String toSeasonGoodsList(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = seasonService.getAllSeasonGoods();
		this.result = map;
		return SUCCESS;
	}
	
	
	
	

}
