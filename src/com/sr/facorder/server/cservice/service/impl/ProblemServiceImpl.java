package com.sr.facorder.server.cservice.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import net.sf.json.JSONObject;
import com.sr.facorder.server.cservice.dao.ProblemDao;
import com.sr.facorder.server.cservice.pojo.EbProblemCollect;
import com.sr.facorder.server.cservice.pojo.EbProblemDetail;
import com.sr.facorder.server.cservice.service.IProblemService;
import com.sr.facorder.server.customer.dao.CustomerDao;
import com.sr.facorder.server.customer.pojo.EbUser;

public class ProblemServiceImpl implements IProblemService{

	
	private ProblemDao problemDao;
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	public void setProblemDao(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getProblemCollect(HttpServletRequest request) {
		
		Map<String,Object> map  = new HashMap<String,Object>();
		try {
			String orgId = request.getParameter("orgId");
			String page = request.getParameter("page");
			String where = " WHERE orgId = '"+orgId+"' ORDER BY updateDate DESC";
			String wheresize = " WHERE orgId = '"+orgId+"' AND orgStatus = 2";//已回复数量查询
			if(page==null){
				map.put("success", false);
				map.put("info", "page为空，请检查数据~");
				return map;
			}
			int offset = (Integer.parseInt(page)-1)*10;
			String wheretemp = " FROM EbProblemCollect"+where;
			List<EbProblemCollect> listCollect = problemDao.getListForPage(wheretemp, offset, 10);
			
			List<EbProblemCollect> listTotalData =problemDao.findProblemCollect(where);
			List<EbProblemCollect> list = problemDao.findProblemCollect(wheresize);
			if(listCollect.size()==0){
				map.put("success", false);
				map.put("info", "暂无数据~");
				return map;
			}
			map.put("listCollect", listCollect);
			map.put("alreadyReplyLen", list.size());//已回复数量
			map.put("totalData", listTotalData.size());//总数量
			map.put("success", true);
		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "明细数据获取失败，请稍后重试~");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getHDProblemCollect(HttpServletRequest request) {
		
		Map<String,Object> map  = new HashMap<String,Object>();
		try {
			String type = request.getParameter("type");
			String page = request.getParameter("page");
			String where = "";
			String wheresize =""; 
			if("HD".equals(type)){
				where = " ORDER BY updateDate DESC";
				wheresize = " WHERE replyStatus = 1";
			}
			if(page==null){
				map.put("success", false);
				map.put("info", "page为空，请检查数据~");
				return map;
			}
			int offset = (Integer.parseInt(page)-1)*10;
			String wheretemp = " FROM EbProblemCollect"+where;
			List<EbProblemCollect> listCollect = problemDao.getListForPage(wheretemp, offset, 10);
			List<EbProblemCollect> list = problemDao.findProblemCollect(wheresize);
			List<EbProblemCollect> listTotalData = problemDao.findProblemCollect(where);
			if(listCollect.size()==0){
				map.put("success", false);
				map.put("info", "暂无数据~");
				return map;
			}
			map.put("listCollect", listCollect);
			map.put("totalData", listTotalData.size());
			map.put("len", list.size());
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "明细数据获取失败，请稍后重试~");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> getProblemDetail(HttpServletRequest request) {
		
		Map<String,Object> map  = new HashMap<String,Object>();
		try {
			List<EbProblemDetail> listDetail = new ArrayList<EbProblemDetail>();
			List<EbProblemCollect> listCollect = new ArrayList<EbProblemCollect>();
			String voucherId = request.getParameter("voucherId");
			String type = request.getParameter("type");
			String where = " WHERE voucherId='"+voucherId+"'";
			listDetail = problemDao.findProblemDetail(where);
			listCollect= problemDao.findProblemCollect(where);
			if(listDetail.size()==0 || listCollect.size()==0){
				map.put("success", false);
				map.put("info", "明细数据获取失败，请稍后重试~");
				return map;
			}
			if("HD".equals(type)){
				if(listCollect.get(0).getReplyStatus()==1){
					listCollect.get(0).setReplyStatus(3);
					problemDao.saveAndUpdateCollect(listCollect);
				}
			}else{
				//更新门店状态
				if(listCollect.get(0).getOrgStatus()==2){
					listCollect.get(0).setOrgStatus(3);
					problemDao.saveAndUpdateCollect(listCollect);
				}
			}
			map.put("success", true);
			map.put("listDetail", listDetail);
			map.put("listCollect", listCollect);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "明细数据获取失败，请稍后重试~");
			return map;
		}
		
		return map;
	}


	//删除
	@Override
	public Map<String, Object> deleteProblemSelect(String str,String orgId) {
		Map<String,Object> map  = new HashMap<String,Object>();
		List<EbProblemCollect> collect = new ArrayList<EbProblemCollect>();
		List<EbProblemDetail> detail = new ArrayList<EbProblemDetail>();
		try{
			String[] tempstr = str.split("_");
			for (int i = 0; i < tempstr.length; i++) {
				String voucherId = tempstr[i];
				String where = " WHERE voucherId = '"+voucherId+"'";
				List<EbProblemCollect> tempcollect = problemDao.findProblemCollect(where);
				List<EbProblemDetail> tempdetail = problemDao.findProblemDetail(where);
				if(tempcollect.size()==0 || tempdetail.size()==0){
					map.put("success", false);
					map.put("info", "删除失败，请稍后重试");
					return map;
				}
				collect.add(tempcollect.get(0));
				detail.add(tempdetail.get(0));
			}
			problemDao.deleteProblemCollect(collect);
			problemDao.deleteProblemDetail(detail);
			String where = " WHERE orgId = '"+orgId+"' ORDER BY updateDate DESC";
			List<EbProblemCollect> listCollect = problemDao.findProblemCollect(where);
			map.put("success", true);
			map.put("listCollect", listCollect);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "明细数据获取失败，请稍后重试~");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
	}

	@Override
	public Map<String, Object> saveProblem(HttpServletRequest request) {
		Map<String,Object> map  = new HashMap<String,Object>();
		try{
			//数据
			String obj = request.getParameter("detail");
			JSONObject object = JSONObject.fromObject(obj);
			String orgId =object.getString("orgId");
			String ptitle = object.getString("ptitle");
			String pDescription = object.getString("pDescription");
			String pictureUrl = object.getString("pictureUrl");
			String whereUser = " WHERE orgId = '"+orgId+"'";
			List<EbUser> users= customerDao.findUser(whereUser);
			if(users.size()==0){
				map.put("success", false);
				map.put("info", "保存失败，请稍后重试~");
				return map;
			}
			String orgName = users.get(0).getUserName();
			//编号
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = format.format(now);
			String where =" WHERE CONVERT(varchar(10),createDate,120) = '"+createdate+"' ORDER BY recKey DESC";
			List<EbProblemCollect> list = problemDao.findProblemCollect(where);
			Integer temp = 1;
			if(list.size()>0){
				String voucherId = list.get(0).getVoucherId();
				String str = voucherId.substring(voucherId.length()-4,voucherId.length());
				temp = Integer.parseInt(str)+1;
			}
			SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
			String s = outFormat.format(now);
			String voucherid = orgId+s+String.format("%04d",temp);
			
			EbProblemCollect collect = new EbProblemCollect();
			collect.setVoucherId(voucherid);
			collect.setPtitle(ptitle);
			collect.setOrgId(orgId);
			collect.setOrgName(orgName);
			collect.setOrgStatus(1);
			collect.setReplyStatus(1);
			EbProblemDetail detail = new EbProblemDetail();
			detail.setVoucherId(voucherid);
			detail.setPictureUrl(pictureUrl);
			detail.setpDescription(pDescription);
			problemDao.saveProblemCollect(collect);
			problemDao.saveProblemDetail(detail);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "您提交的问题保存失败，请稍后重试~");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return map;
		}
	 }
	
	
	
	@Override
	public Map<String, Object> updateProblem(HttpServletRequest request) {
		
		Map<String,Object> map  = new HashMap<String,Object>();
		try{
			String obj = request.getParameter("detail");
			JSONObject object = JSONObject.fromObject(obj);
			String voucherId =object.getString("voucherId");
			String ptitle = object.getString("ptitle");
			String pDescription = object.getString("pDescription");
			String pictureUrl = object.getString("pictureUrl");
			
			String where = " WHERE voucherId = '"+voucherId+"'";
			List<EbProblemCollect> listCollect = problemDao.findProblemCollect(where);
			if(listCollect.size()==0){
				map.put("success", false);
				map.put("info", "获取汇总数据失败~");
				return map;
			}
			listCollect.get(0).setPtitle(ptitle);
			Timestamp updateDate = new Timestamp(System.currentTimeMillis());
			listCollect.get(0).setUpdateDate(updateDate);
			
			List<EbProblemDetail> listDetail = problemDao.findProblemDetail(where);
			if(listDetail.size()==0){
				map.put("success", false);
				map.put("info", "获取明细数据失败~");
				return map;
			}
			listDetail.get(0).setpDescription(pDescription);
			listDetail.get(0).setPictureUrl(pictureUrl);
			listDetail.get(0).setUpdateDate(updateDate);
			
			problemDao.updateProblemCollect(listCollect.get(0));
			problemDao.updateProblemDetail(listDetail.get(0));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "您提交的问题保存失败，请稍后重试~");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
	}
	@Override
	public Map<String, Object> updateHDProblem(HttpServletRequest request) {
		Map<String,Object> map  = new HashMap<String,Object>();
		
		try{
			
			String obj = request.getParameter("replydetail");
			JSONObject objdetail = JSONObject.fromObject(obj);
			String voucherId = objdetail.getString("voucherId");
			String where = " WHERE voucherId = '"+voucherId+"'";
			List<EbProblemCollect> listCollect = problemDao.findProblemCollect(where);
			List<EbProblemDetail> listDetail = problemDao.findProblemDetail(where);
			if(listCollect.size()==0 || listDetail.size()==0){
				map.put("success", false);
				map.put("info", "数据获取失败，请稍后重试~");
				return map;
			}
			if(!listCollect.get(0).getReplyUser().equals("")){
				map.put("success", false);
				String info = listCollect.get(0).getReplyUser()+"已做出回复,不能再次回复";
				map.put("info", info);
				return map;
			}
			
			String rDescription = objdetail.getString("rDescription");
			String replyUser = objdetail.getString("replyUser");
			String replyUserId = objdetail.getString("replyUserId");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			listCollect.get(0).setReplyDate(now);
			listCollect.get(0).setReplyUserId(replyUserId);
			listCollect.get(0).setReplyUser(replyUser);
			listCollect.get(0).setOrgStatus(2);
			listCollect.get(0).setReplyStatus(2);
			listDetail.get(0).setrDescription(rDescription);
			
			problemDao.updateProblemCollect(listCollect.get(0));
			problemDao.updateProblemDetail(listDetail.get(0));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("info", "您的回复保存失败，请稍后重试~");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
	}

}










