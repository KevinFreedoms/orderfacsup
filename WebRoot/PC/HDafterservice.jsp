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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/lib/formview.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/default.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/lib/formtemplate.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/delivery.css" />
    <!--pagintion-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/cservice/pagination.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/cservice/afterservice.css"/>
  </head>
  <style>
  	.answer .issuetitle{
		border-bottom:0px;
	}
	.replycontent{
		padding:10px;
	}
	.withhoutinfo{
		text-align: center;
		padding-top:16%;
		display:none;
	}
	#stage{
		height:100%;
	}
  </style>
  <body>
    <div id="container">
		<div style="height:95%;">
			<div class="withhoutinfo">
				<img src="images/withoutinfo.png" />
				<div style="color:#999;font-size:24px;margin-top:10px;">无权限</div>
			</div>
		<!-- jiaself -->
		<table id="exporttable" class="pdftable"></table>
			<table cellspacing="0" id="stage" >
				<tbody>
                	<tr>
                        <td id="wallpaper" class="wallpaper">
                        	<div id="ordermanager" class="container">
                            	<!-- 问题列表 -->
                                <form class="form">
                                    <div class="info" id="formHeader">
                                    	<h4 style="text-align:center">售后问题反馈</h4>
                                    </div>
                                    <div style="text-align:right">
                                        <div style="width:89%; float:left; text-align:right">
                                        	<input id="searchTxt" type="text" class="content" placeholder="请输入搜索内容" style="height:29px; width:45%;margin-right:5px;" onKeyUp="keyName()">
                                        </div>
                                        <div style="float:left; text-align:right;">
                                        	<input type="button" class="btnSearch" id="btnSubmit" value="搜索" onClick="searchtab()">
                                        </div>
                                        <div class="clearfloat"></div>
                                    </div>
                                    <table class="table" cellspacing="0" id="kingTable">
                                    	<thead>    
                                            <tr>
                                                <th style="width:9%">状态</th>
                                                <th style="width:17%">网点</th>
                                                <th style="width:40%">售后问题</th>
                                                <th style="width:12%">反馈日期</th>
                                                <th style="width:12%">回复日期</th>
                                                <th style="width:10%">回复人</th>
                                            </tr>
                                        </thead>
                                        <tbody id="saleList">
                                        	<!-- <tr>
                                                <td class="replystatus withoutreply">已回复</td>
                                                <td>西园饭店</td>
                                                <td>白菜坏的太多，白菜坏的太多，不新鲜</td>
                                                <td>2017-12-11</td>
                                                <td>2017-12-11</td>
                                                <td>系统管理员</td>
                                            </tr>
                                            <tr>
                                                <td class="replystatus">已回复</td>
                                                <td>西园饭店</td>
                                                <td>白菜坏的太多，不新鲜</td>
                                                <td>2017-12-11</td>
                                                <td>2017-12-11</td>
                                                <td>系统管理员</td>
                                            </tr> -->
                                        </tbody>
                                    </table>
                                    <div id="Pagination" class="pagination"></div>
                                    <div class="clearfloat"></div>
                                    <div style="height:20px;"></div>
                                </form>
                               <!--  问题列表 -->
                               <!-- 问题详情 -->
                                <div id="questionDetail"  style="display:none;">
                                	<div class="issuedetail">
                                        <div class="issue">
                                            <h3>
                                                <span id="ptitle">白菜烂叶太多白菜烂叶太多</span>
                                            </h3>
                                            <div class="issuetitle replytime" style="margin-top:5px;">
                                                <span>反馈网点：</span><span  id="porgName">西园饭店</span>
                                                <span style="margin-left:20px;">反馈时间：</span><span id="pdate">2017-09-10 10:01:01</span>
                                            </div>
                                            <div class="issuecontent">
                                                <p id="pDescription">&nbsp;&nbsp;&nbsp;&nbsp;今天收到的白菜烂叶太多，收了100斤，摘烂叶后只有70斤，导致我们还需要再补订20斤。希望加工中心能严格管控此类事情，把好原料质量关。</p>
                                                <div>
                                                	<img src="images/timg.jpg">
                                                    <img src="images/timg.jpg">
                                                    <div class="clearfloat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="replytitle"></div>
                                        <div class="answer">
                                        	 <h3>
                                                <span>回复</span>
                                            </h3>
                                           <div class="issuetitle">
                                           		<img src="images/icon8.png">
                                                <div style="margin-top:10px; float:left" id="replyUser">系统管理员</div>
                                                <div class="clearfloat"></div>
                                            </div>
                                            <div class="issuecontent">
                                            	<input type="hidden" id="voucherId" value="1">
                                            	<div class="replyarea">
                                            	 	<textarea placeholder="请您回复" style="width:100%;" class="replycontent"></textarea>
                                                <p>&nbsp;&nbsp;&nbsp;&nbsp;您的问题我们已经重视处理，并对蔬菜供应商进行了扣款处罚，另我们在您补丁的20斤的基础上，送您10斤。感谢您的反馈的问题。</p>    
                                                </div>
                                             <p class="replytime"><span>回复时间：</span><span class="nowreplytime" >2017-09-10 10:01:01</span></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="operatebtn">
                                    	<input class="btn-create btnSubmit replysubmit" type="button" value="提交" onClick="replysubmit()">
                                        <input class="btn-create btnSubmit" type="button" value="返回" onClick="backpage()">
                                    </div>
                                </div>
                                <!-- 问题详情 -->
                        	</div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <!--消息弹出框-->
             <div id="winpop">
                 <div class="title">消息提醒<span class="close" id="close">X</span></div>
                 <div class="con">
                    <span class="noread">您有新的问题待回复!</span>
                  </div>
                </div>
			</div>
		</div>
	</div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/cservice/jquery.pagination.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/cservice/infotip.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/cservice/hdcservice.js"></script>
    <script>
		$(function(){
			basePath = "<%=basePath%>";
			//登陆信息判断
			userId = '<%=session.getAttribute("userId")%>';
			userName = '<%=session.getAttribute("userName")%>';
			var totalData = "";
			if(loginTimeLimit()){
				totalData=initHDCollect(1);//初始化
			}
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
					initHDCollect(page);
                }
			});   
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
			var remain = $(obj).val().length;
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
		/*****************************************问题列表点击************************************/
		/* $("#saleList tr").dblclick(function(){
			var replyname=$(this).find(".replystatus").text()
			var temp=""
			//已回复代表的问题已经回复并且是不同的人点进去回复不可修改
			if(replyname=="已回复"){
				temp+='<p>&nbsp;&nbsp;&nbsp;&nbsp;今天收到的白菜烂叶太多，收了100斤，摘烂叶后只有70斤，导致我们还需要再补订20斤。希望加工中心能严格管控此类事情，把好原料质量关。</p>'
				$(".answer .issuetitle").css("border-bottom","1px dashed #ddd")
				$(".nowreplytime").text('2017-12-13 10:10:10')
				$(".replysubmit").hide();
			}else{
				//未回复状态或者已回复是同一个人回复的，点击去可修改回复或者填写回复信息
				temp+='<textarea placeholder="请您回复" style="width:100%;" class="replycontent"></textarea>'
				$(".answer .issuetitle").css("border-bottom","0px")
				$(".nowreplytime").text(NowFormatDate())
				$(".replysubmit").show();
			}
			$(".replyarea").html(temp)
			var MsgPop=document.getElementById("winpop")
			MsgPop.style.display="none";
			$(".form").hide();
			$("#questionDetail").show();
			$(".replycontent").focus();
		}) */
		/*****************************************问题详情返回************************************/
		function backpage(){
			/* $("#questionDetail").hide();
			$(".form").show();
			$("#addfeedback").hide(); */
			if(loginTimeLimit()){
				window.location.href = "facorder/cservice/toFactoryPage.do?userId="+userId+"&userName="+userName;			
			}
		}
		/*****************************************搜索*******************************************/
		function searchtab(){
			var searchname=$("#searchTxt").val();
			$("#saleList tr").hide();
			$("#saleList tr").filter(":contains('"+searchname+"')").show() ;
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
