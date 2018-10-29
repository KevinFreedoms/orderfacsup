<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-季节单品</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css">
    <!-- showLoading -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/showLoading/showLoading.css">
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
  </head>
  
  <body>
    <div id="container">
		<div id="header" class="clearfix">
        	<span class="navigation" id="titlemodify">冶春食品公司网上订货平台</span>
		</div>
		<!-- export -->
		<table id="exporttable"></table>
		
		<div id="stagecontainer" style="min-height:600px;">
			<table cellspacing="0" id="stage">
				<tbody>
                	<tr>
						<td id="col1" class="col1">
							<ul id="pageButtons">
                                <li><a class="cbegin" href="<%=basePath%>facorder/manager/toPcMain.do">移动订货</a></li>
                                <li><a href="<%=basePath%>facorder/manager/toPcDelivery.do">直送</a></li>
                                <li><a href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                                <li class="selected">
                                	<a href="<%=basePath%>facorder/season/toPcSeason.do" >季节单品
                                	<i class="menu-triangle" style="display: inline-block;"></i></a>
                                </li>
                                <li><a href="<%=basePath%>facorder/manager/toPcHistory.do">历史查询</a></li>
                                <li><a class="cend" href="<%=basePath%>facorder/manager/toPcMessage.do">公告</a></li>
                                <li><a class="cend" href="<%=basePath%>facorder/cservice/toProblemCollect.do">售后问题反馈</a></li>
							</ul>
                            <div style="height:200px">
                            	<img src="<%=basePath%>images/logo.jpg" style="width:160px; margin-top:10px;padding-right:15px"/>
                                <img src="<%=basePath%>images/qrcode_for_gh_f5c78120aa42_344.jpg" style="width:160px;margin-top:10px;padding-right:10px"/>
                            </div>
						</td>
                        <td id="wallpaper" class="wallpaper">
                        	<div id="ordermanager" class="container" style="display:none" >
                                <form class="form">
                                    <div class="info" id="formHeader">
                                    </div>
                                    <div id="searchBtn" >
                                        <div style="width:91%; float:left; text-align:right">
                                        	<input id="searchTxt" type="text" class="content searchinput" placeholder="请输入搜索内容" style="height:29px;">
                                        </div>
                                        <div style="width:9%; float:left; text-align:right;">
                                        	<input type="button" class="btnSearch commonbtn" id="btnSubmit" value="搜索">
                                        </div>
                                    </div>
                                    <table class="table" cellspacing="0" id="kingTable">
                                    	<thead>
                                            <tr>
                                                <th style=" width:30%">品名</th>
                                                <th style=" width:25%">上市日期</th>
                                                <th style=" width:25%">下市日期</th>
                                                <th style=" width:20%">状态</th>
                                            </tr>
                                        </thead>
                                        <tbody id="goodscontent">
                                        	
                                        </tbody>
                                    </table>
                                     <div id="Pagination" class="pagination"></div>
                                    <div style="height:20px;"></div>
                                </form>
                        	</div>
                            <div id="withoutdata" class="container" style="display:none" >
                            	 <div style="text-align: center;color: #999; padding-top:15%;font-size: medium;" >尚未定义季节单品</div>
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
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <!-- showLoading -->
    <script type="text/javascript" src="<%=basePath%>js/showLoading/jquery.showLoading.js"></script>
    <!-- export -->
    <script type="text/javascript" src="<%=basePath%>js/export/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/export/base64.js"></script>
    <script>
		document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/export/excelExport/orderExport.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/season/season.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script type="text/javascript">
		$(function(){
			basePath = "<%=basePath%>";
			getAllSeasonGoods();
			//模块尺寸(设置配送日期模块的高度)
			$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		});
	</script>
  </body>
</html>
