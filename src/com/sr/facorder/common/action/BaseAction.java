package com.sr.facorder.common.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.sqlArcher.server.service.ISQLService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153872436829876179L;

	// service
	protected ISQLService sqlService;
	
	// parameter
	public String HTML = "html";
	private InputStream inputStream;
	
	public Map<String, Object> session;
	public HttpServletRequest request;
	public HttpServletResponse response;
	
	// tool
	protected Gson gson = new Gson();
	
	// inject
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setSqlService(ISQLService sqlService) {
		this.sqlService = sqlService;
	}
	
	
	// Base Action Method //
	
	public ServletContext showServletContext(){
		return ServletActionContext.getServletContext();		
	}
	
	public String showParameter(String key) {
		return request.getParameter(key);
	}
	
	public void printResult(String strResult) {
		
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			printWriter.write(strResult);
			
		} catch (IOException e1) {
			throw new RuntimeException();
		} finally{
			if(printWriter != null){
				printWriter.close();
				printWriter = null;
			}
		}
	}
	
	public void toInputStream(Object obj) throws IOException {
		this.inputStream = IOUtils.toInputStream(gson.toJson(obj), "UTF-8");
	}
	
	
	// Getter & Setter //
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
