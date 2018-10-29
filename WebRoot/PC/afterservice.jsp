<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-售后问题反馈</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css"/>
    <!--bootstrap-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap/bootstrap-reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/assets/font-awesome/css/font-awesome.css"/>
    <!-- showLoading -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/showLoading/showLoading.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
	<!-- uploadify -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/uploadify/css/uploadify.css"  />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/smart/smartMenu.css">
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/cservice/afterservice.css"/>
  </head>
  <body>
    <div id="container">
		<div id="header" class="clearfix">
        	<span class="navigation" id="titlemodify">冶春食品公司网上订货平台</span>
		</div>
		<div id="stagecontainer" style="min-height:600px;">
			<!-- jiaself -->
			<table id="exporttable" class="pdftable"></table>
			
			<table cellspacing="0" id="stage" >
				<tbody>
                	<tr>
						<td id="col1" class="col1">
							<ul id="pageButtons">
                                <li><a class="cbegin" href="<%=basePath%>facorder/manager/toPcMain.do">移动订货</a></li>
                                <li><a class="delivery" href="<%=basePath%>facorder/manager/toPcDelivery.do">直送<i class="menu-triangle" style="display:inline-block"></i></a></li>
                                <!-- jiaself -->
                                <li><a  href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                                <li><a href="<%=basePath%>facorder/season/toPcSeason.do">季节单品</a></li>
                                <li ><a href="<%=basePath%>facorder/manager/toPcHistory.do">历史查询</a></li>
                                <li><a href="<%=basePath%>facorder/manager/toPcMessage.do">公告</a></li>
                                <li class="selected"><a class="cend" href="<%=basePath%>facorder/cservice/toProblemCollect.do">售后问题反馈</a></li>
							</ul>
                            <div style="height:200px">
                            	<img src="images/logo.jpg" style="width:160px; margin-top:10px; margin-right:5px"/>
                                <img src="images/qrcode_for_gh_f5c78120aa42_344.jpg" style="width:160px; margin-top:10px; margin-right:5px"/>
                            </div>
						</td>
                        <td id="wallpaper" class="wallpaper">
                        	<div id="ordermanager" class="container">
                            	 <div style="font-size: medium;color:#999; text-align: center; margin-top:15%;height:300px;" id="withoutalter">正在完善中，敬请期待</div>
                            	<!--问题列表-->
                                <form class="form" >
                                    <div class="info" id="formHeader" style="display:none">
                                    	<h4 style="text-align:center">售后问题反馈</h4>
                                    </div>
                                    <div style="display:none">
                                        <div style="width:89%; float:left; text-align:right">
                                        	<input id="searchTxt" type="text" class="content" placeholder="请输入搜索内容" style="height:29px; width:45%; margin-right:5px;" onKeyUp="keyName()">
                                        </div>
                                        <div style="float:left; text-align:right;">
                                        	<input type="button" class="btnSearch important commonbtn" id="btnSubmit" value="搜索" onClick="searchtab()">
                                        </div>
                                    </div>
                                   
                                    <table class="table" cellspacing="0" id="kingTable" style="display:none">
                                    	<thead>
                                            <tr>
                                            	<th style="width:5%"></th>
                                                <th style="width:9%">状态</th>
                                                <th style="width:45%">售后问题</th>
                                                <th style="width:13%">创建日期</th>
                                                <th style="width:13%">回复日期</th>
                                                <th style="width:15%">回复人</th>
                                            </tr>
                                        </thead>>
                                        <tbody id="saleList">
                                        </tbody>
                                    </table>
                                    <div id="Pagination" class="pagination" ></div>
                                    <div class="operatebtn">
                                    	<input class="btn-create btnSubmit deletebutton " type="button" value="删除" onClick="deleteques()" style="display:none">
                                        <input class="btn-create btnSubmit" type="button" value="新增" onClick="addquestion()" style="display:none">
                                    </div>
                                    <div class="clearfloat"></div>
                                    <div style="height:20px;"></div>
                                </form>
                                <!--问题列表-->
                                <!--新增问题-->
                                 <div id="addfeedback" style="display:none;">
                                    <table class="table" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td style="width:10%;" class="questiontitle"><label>问题标题：</label></td>
                                                <td class="question">
                                                	<input type="text" placeholder="请输入标题，20字以内" onKeyUp="inputtitle(this)"/>
                                                    <div class="limitword" id="questiontitle">还可以输入20字</div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width:10%;"><label>问题正文：</label></td>
                                                <td class="question">
                                                    <textarea placeholder="请输入内容，500字以内" onKeyUp="inputcontent(this)">
                                                    </textarea>
                                                    <div class="limitword" id="questionInfo">还可以输入500字</div>
                                                </td>
                                            </tr>
                                            <tr>
                                            	<td style="width:10%;"><label>上传图片：</label></td>
	                                            <td class="question">
	                                               <div class="uploadimg downwep"  style="position:relative;" >
		                                           		<input type="file" id="firstImage" name="uploadify"> 
                                                   </div>
                                                   <div class="uploadimg downwep"  style="position:relative;">
		                                           		<input type="file" id="secondImage" name="uploadify"> 
                                                   </div>
                                                   <div class="uploadimg downwep"  style="position:relative;">
		                                           		<input type="file" id="thirdImage" name="uploadify"> 
                                                   </div>
	                                            </td>
                                               <!-- houliying -->
                                               <!--  <td style="width:10%;"><label>上传图片：</label></td>
                                                <td class="question">
                                                    <div class="uploadimg"  id="firstImage">上传图片
                                                    	<span style="font-size:18px">+</span>
                                                    </div>
                                                    <div class="uploadimg">上传图片<span style="font-size:18px">+</span></div>
                                                    <div class="uploadimg">上传图片<span style="font-size:18px">+</span></div>
                                                </td> -->
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="operatebtn submitState">
                                    	<input class="btn-create btnSubmit" type="button" value="提交" onClick="submitques()">
                                        <input class="btn-create btnSubmit" type="button" value="取消" onClick="cancel()">
                                    </div>
                           	 	</div>
                                <!--新增问题-->
                                <!--问题详情-->
                                <div id="questionDetail"  style="display:none;">
                                	<div class="issuedetail">
                                        <div class="issue">
                                            <h3>
                                                <span id="ptitle">白菜烂叶太多白菜烂叶太多</span>
                                            </h3>
                                            <div class="issuetitle replytime" style="margin-top:5px;">
                                                <span>反馈网点：</span><span id="porgName">西园饭店</span>
                                                <span style="margin-left:20px;">反馈时间：</span><span id="pdate">2017-09-10 10:01:01</span>
                                            </div>
                                            <div class="issuecontent">
                                                <p id="pDescription">今天收到的白菜烂叶太多，收了100斤，摘烂叶后只有70斤，导致我们还需要再补订20斤。希望加工中心能严格管控此类事情，把好原料质量关。</p>
                                                <div>
                                                	<!-- <img src="images/timg.jpg">
                                                    <img src="images/timg.jpg"> 
                                                    <img src="images/timg.jpg">
                                                    <div class="clearfloat"></div> -->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="replytitle"></div>
                                        <div class="answer" style="display:none;">
                                        	 <h3>
                                                <span>回复</span>
                                             </h3>
                                             <div class="issuetitle">
                                           		<img src="images/icon8.png">
                                                <div style="margin-top:10px; float:left" id="replyUser">系统管理员</div>
                                                 <div class="clearfloat"></div>
                                             </div>
                                             <div class="issuecontent" id="rDescription">
                                                 <p>您的问题我们已经重视处理，并对蔬菜供应商进行了扣款处罚，另我们在您补丁的20斤的基础上，送您10斤。感谢您的反馈的问题。</p>
                                                 <p class="replytime"><span>回复时间：</span><span>2017-09-10 10:01:01</span></p>
                                             </div>
                                        </div>
                                    </div>
                                    <div class="operatebtn">
                                    	<input class="btn-create btnSubmit alerdyreplybtn" type="button" value="修改" onClick="modify()">
                                        <input class="btn-create btnSubmit" type="button" value="返回" onClick="backpage()">
                                    </div>
                                </div>
                                <!--问题详情-->
                        	</div>
                            <div class="promo">
                            	热线电话：0514-82228999 接听时间：8:30--17:30
                            </div>
                        </td><!--wallpaper-->
                    </tr>
                </tbody>
            </table>
		</div>
	</div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/cservice/jquery.pagination.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- uploadify -->
	<script type="text/javascript" src="<%=basePath%>js/uploadify/scripts/swfobject.js"></script>  
	<script type="text/javascript" src="<%=basePath%>js/uploadify/scripts/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/smart/jquery-smartMenu.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/cservice/infotip.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/cservice/cservice.js"></script>
    <script>
    	$(function(){
	    	basePath = "<%=basePath%>";
	    	/*var totalData=initCollect(1);
 	  		 $('.pagination').pagination({
				totalData:totalData,
				showData:10,
				jump:true,
				coping:true,
				homePage:'首页',
				endPage:'末页',
				prevContent:'上页',
				nextContent:'下页',
				callback: function(api){
					page = api.getCurrent();
					initCollect(page);
                }
			});   */
			//模块尺寸
			$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		});
		/**************************************限标题字数***************************************/
		function inputtitle(obj){
			var pattern="";
			var limitNum=20;
			var remain = $(obj).val().length;
			if(remain>20){
				var title=$(obj).val().substring(0, 20);
				$(obj).val(title);
				pattern = "字数超过20不可输入！";
			}else{
				var result = limitNum - remain;
				pattern = '还可以输入' + result + '字';	
			}
			$('#questiontitle').html(pattern);
		}
		/**************************************限制内容字数***************************************/
		function inputcontent(obj){
			var pattern="";
			var limitNum=500;
			var remain = $(obj).val().trim().length;
			if(remain>500){
				var newcontent=$(obj).val().substring(0, 500);
				$(obj).val(newcontent);
				pattern = "字数超过500不可输入！";
			}else{
				var result = limitNum - remain;
				pattern = '还可以输入' + result + '字';	
			}
			$('#questionInfo').html(pattern);
		}
		/*****************************************问题详情返回************************************/
		function backpage(){
			window.location.href = "facorder/cservice/toProblemCollect.do";
		}
		/*****************************************搜索************************************/
		function searchtab(){
			var searchname=$("#searchTxt").val();
			$("#saleList tr").hide();
			$("#saleList tr").filter(":contains('"+searchname+"')").show(); 
		}
		function keyName(){
			var searchname=$("#searchTxt").val();
			if(searchname==""){
				$("#saleList tr").filter(":contains('"+searchname+"')").show();
			}	
		}
	
	</script>
  </body>
</html>
