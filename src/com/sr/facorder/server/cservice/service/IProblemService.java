package com.sr.facorder.server.cservice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface IProblemService {
	
	Map<String,Object> getProblemCollect(HttpServletRequest request);
	Map<String,Object> getHDProblemCollect(HttpServletRequest request);
	Map<String,Object> getProblemDetail(HttpServletRequest request);
	Map<String,Object> deleteProblemSelect(String str,String orgId);
	Map<String,Object> saveProblem(HttpServletRequest request);
	Map<String,Object> updateProblem(HttpServletRequest request);
	Map<String,Object> updateHDProblem(HttpServletRequest request);
	

}
