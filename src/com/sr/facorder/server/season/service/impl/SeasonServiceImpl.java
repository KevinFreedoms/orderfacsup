package com.sr.facorder.server.season.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sr.facorder.server.season.dao.SeasonDao;
import com.sr.facorder.server.season.pojo.EpGoodsSeason;
import com.sr.facorder.server.season.service.ISeasonService;

public class SeasonServiceImpl implements ISeasonService{
	
	private SeasonDao seasonDao;
	
	public void setSeasonDao(SeasonDao seasonDao) {
		this.seasonDao = seasonDao;
	}

	public Map<String, Object> getAllSeasonGoods() {
		Map<String,Object> map = new HashMap<String, Object>();
		List<EpGoodsSeason> seasonGoodsList = new ArrayList<EpGoodsSeason>();
		
		try {
			String where = "";
			seasonGoodsList = seasonDao.findSeasonGoods(where);
			//日期转换
			SimpleDateFormat ff =new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < seasonGoodsList.size(); i++) {
				String startDate = seasonGoodsList.get(i).getStartDate();
				String endDate = seasonGoodsList.get(i).getEndDate();
				seasonGoodsList.get(i).setStartDate(ff.format(ff.parse(startDate)));
				seasonGoodsList.get(i).setEndDate(ff.format(ff.parse(endDate)));
			}
			if(seasonGoodsList.size() == 0){
				map.put("info", "没有相关数据...");
				map.put("success", true);
				return map;
			}
			map.put("seasonGoods", seasonGoodsList);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info","数据库访问异常,请重试!");
			map.put("success", false);
			return map;
		}
	}
	

}
