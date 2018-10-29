package com.sr.facorder.server.cservice.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import com.sr.facorder.common.action.BaseAction;
import com.sr.facorder.server.cservice.service.IProblemService;

public class ProblemAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IProblemService problemService;
	private Map<String,Object> result;
	private File uploadify;//文件file对象  
    private String uploadifyFileName;//文件名  
    private String uploadifyContentType;//文件类型  
    private String description;//描述  
    private String uploadDir;//保存上传文件的目录,相对于web应用程序的根路径,在struts.xml文件中配置
	
	//result
	public void setProblemService(IProblemService problemService) {
		this.problemService = problemService;
	}
	
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	public File getUploadify() {
		return uploadify;
	}
	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}
	public String getUploadifyContentType() {
		return uploadifyContentType;
	}
	public void setUploadifyContentType(String uploadifyContentType) {
		this.uploadifyContentType = uploadifyContentType;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	//to
	public String toProblemCollect(){
		return SUCCESS;
	}
	
	//汇总
	public String doGetProblemCollect(){
		String type = request.getParameter("type");
		if("HD".equals(type)){
			result= problemService.getHDProblemCollect(request);
		}else{
			result = problemService.getProblemCollect(request);
		}
		return SUCCESS;
	}
	
	public String doGetProblemDetail(){
		result = problemService.getProblemDetail(request);
		return SUCCESS;
	}
	
	public String doDeleteProblem(){
		String str = request.getParameter("voucherId");
		String orgId = request.getParameter("orgId");
		result=problemService.deleteProblemSelect(str,orgId);
		return SUCCESS;
	}
	
	public String doSaveProblem(){
		result = problemService.saveProblem(request);
		return SUCCESS;
	}
	
	public String doUpdateProblem(){
		result = problemService.updateProblem(request);
		return SUCCESS;
	}
	
	public String doUploadImage(){
		
		String newFileName="";
		BufferedOutputStream bos=null;  
        BufferedInputStream bis=null; 
		Map<String,Object> result = new HashMap<String, Object>();
		try{
	        //得到当前时间开始流逝的毫秒数,将这个毫秒数作为上传文件新的文件名  
	        long now = new Date().getTime();
	        //得到保存上传文件的真实路径
	        String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
	        File dir = new File(path);
	        //如果不存在就创建
	        if(!dir.exists()){
	        	dir.mkdir();
	        }
	        int index = uploadifyFileName.lastIndexOf(".");
	        //判断上传文件是否有扩展名,以时间戳作为新的文件名
	        if (index!=-1) {  
	            newFileName=now+uploadifyFileName.substring(index);  
	        }else {  
	            newFileName=Long.toString(now);  
	        }  
	        //读取保存在临时目录下的上传文件,写入到新的文件中  
	        FileInputStream fis=new FileInputStream(uploadify);
	        bis = new BufferedInputStream(fis);
	        InputStream in = new FileInputStream(uploadify);
	        FileOutputStream fos=new FileOutputStream(new File(dir,newFileName));
	        bos=new BufferedOutputStream(fos);
	        byte [] buf=new byte[4096];
	        int len=-1;
	        while ((len=bis.read(buf))!=-1) {
	        	bos.write(buf,0,len);
	        }
	        byte[] data = new byte[in.available()];
	        in.read(data);
	        in.close();
	        result.put("filename",newFileName);
	        result.put("success", true);
		}catch (FileNotFoundException e) {
        	result.put("success", false);
        	e.printStackTrace();  
		} catch (IOException e) {
			result.put("success", false);
			e.printStackTrace();  
		}finally{  
			if (null!=bis) {  
				try {  
					bis.close();  
				} catch (IOException e) {
					result.put("success", false);
					e.printStackTrace();  
				}  
			}  
			if (null!=bos) {  
				try {  
					bos.close();  
				} catch (IOException e) {  
					result.put("success", false);
					e.printStackTrace();  
				}  
			}  
      	}
		this.result = result;
		return SUCCESS;
	}
	
	//后端页面
	public String toFactoryPage() throws UnsupportedEncodingException {
		String userId = request.getParameter("userId");
		String tempUserName = request.getParameter("userName");
		if(userId==null && tempUserName==null){
			session.put("userId", "");
			session.put("userName", "");
		}else{
			String userName = new String(tempUserName.getBytes("iso8859-1"),"UTF-8");
			session.put("userId", userId);
			session.put("userName", userName);
		}
		return SUCCESS;
	}
	
	public String doUpdateReplyInfo(){
		result = problemService.updateHDProblem(request);
		return SUCCESS;
	}
	
	
	
	
	
	

}
