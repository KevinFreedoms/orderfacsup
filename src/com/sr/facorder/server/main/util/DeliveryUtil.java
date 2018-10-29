package com.sr.facorder.server.main.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.sr.facorder.server.main.pojo.EpDelivery;
import com.sr.facorder.server.main.pojo.EpMould;

public class DeliveryUtil {

	//显示配送频率
	public static List<Map<String,Object>> listDeliveryDates(EpDelivery del,EpMould mou){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			//判断当前是星期几
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,Object> map = null;
			Integer intervalDays = mou.getIntervalDays();
			Integer frequent = del.getDeliveryFrequent();
			switch (frequent)
		    {
		    	case 1:
		    		for(int i=intervalDays;i<7+intervalDays;i++){
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DAY_OF_MONTH, i);
						map = new HashMap<String, Object>();
						map.put("deliverydate", sf.format(calendar.getTime()));
						list.add(map);
					}  
		    		break;
		    	case 2:
		    		for(int i=intervalDays;i<7+intervalDays;i++){
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DAY_OF_MONTH, i);
						int week = dayForWeek(sf.format(calendar.getTime()));
						if(checkDelivery(del,week)&&list.size()<3){
							map = new HashMap<String, Object>();
							map.put("deliverydate", sf.format(calendar.getTime()));
							list.add(map);
						}
					}  
		    		break;
		    	case 3:
		    		Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DAY_OF_MONTH, intervalDays);
					Integer all = del.getMonthDay1()+del.getMonthDay2()+del.getMonthDay3();
					if(all>0)
						list= listMonthDel(calendar,del);
		    		break;
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//判断月配送日期
	public static List<Map<String,Object>> listMonthDel(Calendar calendar,EpDelivery del){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		try{
			Integer year = 0;
			Integer month = 0;
			Integer maxDate = 0;
			Integer tempday = 0;
			Integer day = 0;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			//移除为零的项
			List<Integer> daysTemp = new ArrayList<Integer>();
			daysTemp.add(del.getMonthDay1());
			daysTemp.add(del.getMonthDay2());
			daysTemp.add(del.getMonthDay3());
			Iterator<Integer> iter = daysTemp.iterator(); 
			while (iter.hasNext()) { 
				if (iter.next().equals(0)) { 
					iter.remove();
				}
			}
			//拼接日期
			Integer size = daysTemp.size();
			month = calendar.get(Calendar.MONTH) + 1;
			year =calendar.get(Calendar.YEAR);
			day = calendar.get(Calendar.DATE);
			maxDate = getMonthLastDay(year,month);
			switch (size)
		    {
		    	case 1:
		    		if(daysTemp.get(0)<day){
		    			calendar.add(Calendar.MONTH, 1);
		    			month = calendar.get(Calendar.MONTH) + 1;
		    			year =calendar.get(Calendar.YEAR);
		    			maxDate = getMonthLastDay(year,month);
		    			tempday = daysTemp.get(0)>=maxDate?maxDate:daysTemp.get(0);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.add(Calendar.MONTH, 1);
		    			month = calendar.get(Calendar.MONTH) + 1;
		    			year =calendar.get(Calendar.YEAR);
		    			maxDate = getMonthLastDay(year,month);
		    			tempday = daysTemp.get(0)>=maxDate?maxDate:daysTemp.get(0);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.add(Calendar.MONTH, 1);
		    			month = calendar.get(Calendar.MONTH) + 1;
		    			year =calendar.get(Calendar.YEAR);
		    			maxDate = getMonthLastDay(year,month);
		    			tempday = daysTemp.get(0)>=maxDate?maxDate:daysTemp.get(0);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    		}else {
		    			tempday = daysTemp.get(0)>=maxDate?maxDate:daysTemp.get(0);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.add(Calendar.MONTH, 1);
		    			month = calendar.get(Calendar.MONTH) + 1;
		    			year =calendar.get(Calendar.YEAR);
		    			maxDate = getMonthLastDay(year,month);
		    			tempday = daysTemp.get(0)>=maxDate?maxDate:daysTemp.get(0);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);

		    			calendar.add(Calendar.MONTH, 1);
		    			month = calendar.get(Calendar.MONTH) + 1;
		    			year =calendar.get(Calendar.YEAR);
		    			maxDate = getMonthLastDay(year,month);
		    			tempday = daysTemp.get(0)>=maxDate?maxDate:daysTemp.get(0);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    		}
		    		break;
		    	case 2:
		    		if(daysTemp.get(1)<day){
		    			calendar.add(Calendar.MONTH, 1);
		    			calendar.set(Calendar.DATE,daysTemp.get(0));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.set(Calendar.DATE,daysTemp.get(1));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.add(Calendar.MONTH, 1);
		    			calendar.set(Calendar.DATE,daysTemp.get(0));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    		}else if(daysTemp.get(0)<day&&daysTemp.get(1)>=day){
		    			tempday = daysTemp.get(1)>maxDate?maxDate:daysTemp.get(1);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			calendar.add(Calendar.MONTH, 1);
		    			calendar.set(Calendar.DATE,daysTemp.get(0));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime())); 
		    			list.add(map);
		    			
		    			tempday = daysTemp.get(1)>maxDate?maxDate:daysTemp.get(1);
		    			calendar.set(Calendar.DATE,tempday);
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));	 
		    			list.add(map);
		    		}else{
		    			if(daysTemp.get(0)>=maxDate-1){
		    				if(day<=maxDate-1){
		    					calendar.set(Calendar.DATE,maxDate-1);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,maxDate);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.add(Calendar.MONTH, 1);
				    			month = calendar.get(Calendar.MONTH) + 1;
				    			year =calendar.get(Calendar.YEAR);
				    			maxDate = getMonthLastDay(year,month);
				    			tempday = daysTemp.get(0)>maxDate?maxDate:daysTemp.get(0);
				    			calendar.set(Calendar.DATE,tempday);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    				}else{
		    					calendar.set(Calendar.DATE,maxDate);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.add(Calendar.MONTH, 1);
				    			month = calendar.get(Calendar.MONTH) + 1;
				    			year =calendar.get(Calendar.YEAR);
				    			maxDate = getMonthLastDay(year,month);
				    			if(maxDate>=daysTemp.get(1)){
				    				calendar.set(Calendar.DATE,daysTemp.get(0));
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
					    			
					    			calendar.set(Calendar.DATE,daysTemp.get(1));
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
				    			}else if(maxDate<=daysTemp.get(0)){
				    				calendar.set(Calendar.DATE,maxDate-1);
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
					    			
					    			calendar.set(Calendar.DATE,maxDate);
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
				    			}else{
				    				calendar.set(Calendar.DATE,daysTemp.get(0));
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
					    			
					    			tempday = daysTemp.get(1)>maxDate?maxDate:daysTemp.get(1);
					    			calendar.set(Calendar.DATE,tempday);
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
				    			}
		    				}
		    			}else{
		    				calendar.set(Calendar.DATE,daysTemp.get(0));
			    			map = new HashMap<String, Object>();
			    			map.put("deliverydate", sf.format(calendar.getTime()));
			    			list.add(map);
			    			
			    			tempday = daysTemp.get(1)>maxDate?maxDate:daysTemp.get(1);
			    			calendar.set(Calendar.DATE,tempday);
			    			map = new HashMap<String, Object>();
			    			map.put("deliverydate", sf.format(calendar.getTime()));
			    			list.add(map);
			    			
			    			calendar.add(Calendar.MONTH, 1);
			    			month = calendar.get(Calendar.MONTH) + 1;
			    			year =calendar.get(Calendar.YEAR);
			    			maxDate = getMonthLastDay(year,month);
			    			tempday = daysTemp.get(0)>maxDate?maxDate:daysTemp.get(0);
			    			calendar.set(Calendar.DATE,tempday);
			    			map = new HashMap<String, Object>();
			    			map.put("deliverydate", sf.format(calendar.getTime()));
			    			list.add(map);
		    			}
		    		}
		    		break;
		    	case 3:
		    		if(daysTemp.get(2)<day){
		    			calendar.add(Calendar.MONTH, 1);
		    			calendar.set(Calendar.DATE,daysTemp.get(0));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.set(Calendar.DATE,daysTemp.get(1));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.set(Calendar.DATE,daysTemp.get(2));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			System.out.println(list.get(0));
		    		}else if(daysTemp.get(2)>=day&& daysTemp.get(1)<day){
		    			tempday = daysTemp.get(2)>maxDate?maxDate:daysTemp.get(2);
			    		calendar.set(Calendar.DATE,tempday);
			    		map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.add(Calendar.MONTH, 1);
		    			calendar.set(Calendar.DATE,daysTemp.get(0));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime()));
		    			list.add(map);
		    			
		    			calendar.set(Calendar.DATE,daysTemp.get(1));
		    			map = new HashMap<String, Object>();
		    			map.put("deliverydate", sf.format(calendar.getTime())); 
		    			list.add(map);
		    		}else if(daysTemp.get(1)>=day&& daysTemp.get(0)<day){
		    			if(daysTemp.get(1)>=maxDate-1){
		    				if(day<=maxDate-1){
		    					calendar.set(Calendar.DATE,maxDate-1);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,maxDate);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.add(Calendar.MONTH, 1);
				    			month = calendar.get(Calendar.MONTH) + 1;
				    			year =calendar.get(Calendar.YEAR);
				    			maxDate = getMonthLastDay(year,month);
				    			tempday = daysTemp.get(0)>maxDate?maxDate:daysTemp.get(0);
				    			calendar.set(Calendar.DATE,tempday);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    				}else{
		    					calendar.set(Calendar.DATE,maxDate);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.add(Calendar.MONTH, 1);
				    			month = calendar.get(Calendar.MONTH) + 1;
				    			year =calendar.get(Calendar.YEAR);
				    			maxDate = getMonthLastDay(year,month);
				    			if(maxDate>=daysTemp.get(1)){
				    				calendar.set(Calendar.DATE,daysTemp.get(0));
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
					    			
					    			calendar.set(Calendar.DATE,daysTemp.get(1));
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
				    			}else if(maxDate<=daysTemp.get(0)){
				    				calendar.set(Calendar.DATE,maxDate-1);
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
					    			
					    			calendar.set(Calendar.DATE,maxDate);
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
				    			}else{
				    				calendar.set(Calendar.DATE,daysTemp.get(0));
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
					    			
					    			tempday = daysTemp.get(1)>maxDate?maxDate:daysTemp.get(1);
					    			calendar.set(Calendar.DATE,tempday);
					    			map = new HashMap<String, Object>();
					    			map.put("deliverydate", sf.format(calendar.getTime()));
					    			list.add(map);
				    			}
		    				}
		    			}else{
		    				calendar.set(Calendar.DATE,daysTemp.get(1));
			    			map = new HashMap<String, Object>();
			    			map.put("deliverydate", sf.format(calendar.getTime()));
			    			list.add(map);
			    			
			    			tempday = daysTemp.get(2)>maxDate?maxDate:daysTemp.get(2);
			    			calendar.set(Calendar.DATE,tempday);
			    			map = new HashMap<String, Object>();
			    			map.put("deliverydate", sf.format(calendar.getTime()));
			    			list.add(map);
			    			
			    			calendar.add(Calendar.MONTH, 1);
			    			month = calendar.get(Calendar.MONTH) + 1;
			    			year =calendar.get(Calendar.YEAR);
			    			maxDate = getMonthLastDay(year,month);
			    			tempday = daysTemp.get(0)>maxDate?maxDate:daysTemp.get(0);
			    			calendar.set(Calendar.DATE,tempday);
			    			map = new HashMap<String, Object>();
			    			map.put("deliverydate", sf.format(calendar.getTime()));
			    			list.add(map);
		    			}
		    		}else{
		    			if(daysTemp.get(0)>=maxDate-2){
		    				if(day<=maxDate-2){
		    					calendar.set(Calendar.DATE,maxDate-2);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
			    				calendar.set(Calendar.DATE,maxDate-1);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,maxDate);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    				}else{
		    					calendar.set(Calendar.DATE,maxDate-1);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
			    				calendar.set(Calendar.DATE,maxDate);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.add(Calendar.MONTH, 1);
				    			tempday = daysTemp.get(0)>maxDate?maxDate:daysTemp.get(0);
				    			calendar.set(Calendar.DATE,tempday);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    				}	
		    			}else{
		    				if(daysTemp.get(1)>=maxDate-1){
		    					calendar.set(Calendar.DATE,daysTemp.get(0));
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
		    					calendar.set(Calendar.DATE,maxDate-1);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
			    				calendar.set(Calendar.DATE,maxDate);
			    				map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    				}else if(daysTemp.get(2)>=maxDate){
		    					calendar.set(Calendar.DATE,daysTemp.get(0));
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,daysTemp.get(1));
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,maxDate);
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    				}else{
		    					calendar.set(Calendar.DATE,daysTemp.get(0));
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,daysTemp.get(1));
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
				    			
				    			calendar.set(Calendar.DATE,daysTemp.get(2));
				    			map = new HashMap<String, Object>();
				    			map.put("deliverydate", sf.format(calendar.getTime()));
				    			list.add(map);
		    					
		    				}
		    			}
		    		}
		    		break;
		    }	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	//判断当前日期下改配送作业是否可以配送
	public static boolean checkDelivery(EpDelivery del,Integer week){
		boolean flag = false;
		try{
		    switch (week)
		    {
		    	case 1:
		    		flag = (del.getWeek1()==1)?true:false;
		    		break;
		    	case 2:
		    		flag = (del.getWeek2()==1)?true:false;
		    		break;
		    	case 3:
		    		flag = (del.getWeek3()==1)?true:false;
		    		break;
		    	case 4:
		    		flag = (del.getWeek4()==1)?true:false;
		    		break;
		    	case 5:
		    		flag = (del.getWeek5()==1)?true:false;
		    		break;
		    	case 6:
		    		flag = (del.getWeek6()==1)?true:false;
		    		break;
		    	default:
		    		flag = (del.getWeek7()==1)?true:false;
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = 7;
		}else{
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	
	//判断模板是否有效
	public static List<EpMould> effectiveMoulds(List<EpMould> moulds){
		List<EpMould> list = new ArrayList<EpMould>();
		for(int i = 0, length = moulds.size();i < length; i++) {
			EpMould mould = moulds.get(i);
			DateFormat fmt = new SimpleDateFormat("yyyyMMdd"); 
		    String dateBegin = mould.getStartDate();
			String dateEnd = mould.getEndDate();
			String now = fmt.format(new Date());
			if(Integer.parseInt(dateBegin)<=Integer.parseInt(now)&&
					Integer.parseInt(dateEnd)>=Integer.parseInt(now)){
				list.add(mould);
			}
		}
		return list;
	}
	
	//判断当前配送时间是否截止
	public static  boolean checkDeliveryTime(EpMould mould,String deliverydate){
		boolean flag = true;
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Integer intervalDays = mould.getIntervalDays();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, intervalDays);
			String str = sf.format(calendar.getTime());
			if(deliverydate==str||deliverydate.equals(str)){
				//判断当前时间
				String stateValue = mould.getOrderTime();
				String[] stateHM = stateValue.split(":");
				int hour =  Integer.parseInt(stateHM[0]);
				int minute = Integer.parseInt(stateHM[1]);
				Calendar calendar1 = Calendar.getInstance();
				//订单提交截止时间
				calendar1.set(Calendar.HOUR_OF_DAY, hour);   
				calendar1.set(Calendar.MINUTE, minute);  
				calendar1.set(Calendar.SECOND, 0);
				Date time=calendar1.getTime();
				if (time.before(new Date())) {
					flag = false;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/** 
	 * 得到指定月的天数 
	 * */  
	public static int getMonthLastDay(int year, int month)  
	{  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.YEAR, year);  
	    a.set(Calendar.MONTH, month - 1);  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    return maxDate;  
	}
	
	/** 
	 * 得到 配送 到达时间 
	 * */
	public static Map<String,Object> getDeliveryMessage(EpMould mould,EpDelivery delivery,String deliverydate){
		Map<String,Object> map = null;
		try{
			map = new HashMap<String, Object>();
			//送达时间
			String deliverytime = delivery.getArrivalTime();
			String arrival = deliverydate + " " + deliverytime;
			map.put("arrival", arrival);
			//订单截止时间
			String endtime = mould.getOrderTime();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date =sdf.parse(deliverydate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Integer intervalDays = mould.getIntervalDays();
			calendar.add(Calendar.DAY_OF_MONTH, intervalDays*-1);
			String dateStr = sdf.format(calendar.getTime());
			String orderend = dateStr + " " + endtime;
			map.put("orderend", orderend);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
