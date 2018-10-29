package com.sr.facorder.server.cservice.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sr.facorder.common.dao.BaseDAO;
import com.sr.facorder.server.cservice.pojo.EbProblemCollect;
import com.sr.facorder.server.cservice.pojo.EbProblemDetail;

public class ProblemDao extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<EbProblemCollect> findProblemCollect(String where){
		return hibernateTemplate.find(" FROM EbProblemCollect" +where);
	}
	
	@SuppressWarnings("unchecked")
	public List<EbProblemDetail> findProblemDetail(String where){
		return hibernateTemplate.find(" FROM EbProblemDetail" + where);
	}
	
	public void saveAndUpdateCollect(List<EbProblemCollect> collect){
		hibernateTemplate.saveOrUpdateAll(collect);
		hibernateTemplate.flush();
	}
	
	public void saveProblemCollect(EbProblemCollect problemCollect){
		hibernateTemplate.persist(problemCollect);
		hibernateTemplate.flush();
	}
	public void saveProblemDetail(EbProblemDetail problemDetail){
		hibernateTemplate.persist(problemDetail);
		hibernateTemplate.flush();
	}
	public void deleteProblemCollect(List<EbProblemCollect> collect){
		hibernateTemplate.deleteAll(collect);
		hibernateTemplate.flush();
	}
	
	public void deleteProblemDetail(List<EbProblemDetail> detail){
		hibernateTemplate.deleteAll(detail);
		hibernateTemplate.flush();
	}
	
	public void updateProblemCollect(EbProblemCollect collect){
		hibernateTemplate.update(collect);
		hibernateTemplate.flush();
	}
	public void updateProblemDetail(EbProblemDetail detail){
		hibernateTemplate.update(detail);
		hibernateTemplate.flush();
	}
	
	
	
	
	
	
	

}
