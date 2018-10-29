<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-配送收货</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap/bootstrap.min.css"/>
    <!-- Pikaday -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/Pikaday-master/css/pikaday.css">
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
     <link rel="stylesheet" type="text/css" href="<%=basePath%>css/delivery.css" />
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
                                <li class="selected"><a class="delivery" href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                                <li><a href="<%=basePath%>facorder/season/toPcSeason.do">季节单品</a></li>
                                <li ><a href="<%=basePath%>facorder/manager/toPcHistory.do">历史查询</a></li>
                                <li><a class="cend" href="<%=basePath%>facorder/manager/toPcMessage.do">公告</a></li>
                                <li><a class="cend" href="<%=basePath%>facorder/cservice/toProblemCollect.do">售后问题反馈</a></li>
							</ul>
                            <div style="height:200px">
                            	<img src="images/logo.jpg" style="width:160px; margin-top:10px; margin-right:5px"/>
                                <img src="images/qrcode_for_gh_f5c78120aa42_344.jpg" style="width:160px; margin-top:10px; margin-right:5px"/>
                            </div>
						</td>
                        <td id="wallpaper" class="wallpaper">
                        	<div id="delmanager" class="container">
                            	<form class="form">
                                    <ul id="fields" class="fields">
                                    	<li class="date" type="date" reqd="1">
                                            <div class="content oneline">
                                            	<span><label class="desc">配送日期</label></span>
                                            	<span>
                                                	<input type="text" readonly data-role="datebox" id="deliverydate" style="width:160px;" onChange="initDistributeOrder()">
                                                </span>
                                        	</div>
                                    	</li>
                                        <li class="three" type="radio" reqd="1" >
                                        	<!--<label class="desc" style="margin:3px">直送作业 </label>
                                            <div class="content moulds">
                                          	  无
                                            </div>-->
                                    	</li>
                                	</ul>
                                	<div id="searchBtn" style="display: none;">
                                        <div style="width:91%; float:left; text-align:right">
                                        	<input id="searchTxt" type="text" class="content searchinput" placeholder="请输入搜索内容" style="height:29px;">
                                        </div>
                                        <div style="width:9%; float:left; text-align:right;">
                                        	<input type="button" class="btnSearch commonbtn" id="btnSubmit" value="搜索">
                                        </div>
                                    </div>
                                     <!--<input align="left" type="button" value="直送一览" id="showAllTaskBtn" onclick="showAllTask()" style="display: none;">-->
                                	<table class="table" cellspacing="0" id="kingTable" style="display: none;">
                                    	<thead>
                                            <tr >
                                                <th style="width: 25%;">订单号</th>
                                                <th style="width: 30%;">作业名称</th>
                                                <th style="width: 10%;">单品数</th>
                                                <th style="width: 10%;">实配数</th>
                                                <th style="width: 10%;">实收数</th>
                                                <th style="width: 20%;">状&nbsp;&nbsp;态</th>
                                            </tr>
                                        </thead>
                                        <tbody id="goodscontent">
                                        </tbody>
                                    </table> 
                                    <div id="Pagination" class="pagination collect" style="display: none;"></div>
                                </form>
                            </div>
                            <div id="ordermanager" class="container" style="display:none">
                                <form class="form">
                                    <div class="info" id="formHeader">
                                    	<div style="text-align:left;float:left">
                                    		<input class="js-backbtn commonbtn" id="btnSubmit" type="button" value="返回" onClick="toBack()">
                                    		<input class="js-backbtn important commonbtn" id="btnSubmit" onclick="printOrder()" type="button" value="打印"/>
                                    		<input id="btnSubmit" type="button" class="commonbtn" value="导出Excel" onClick="distributeExportExcel()">
                                    	</div>
                                    	<div align="right" style="float:right;" id="arriveBtnDiv">
											<input id="arriveBtn" type="button" value="确认" class="specialbtn" onclick="getArriveNum()"> 
										</div>
                                    	<div class="clearfloat"></div>
                                    	<h4 style="text-align:center;font-family:微软雅黑">配送收货明细</h4>
                                    	<div style="height:15px;margin-top:3px;font-size: 0.9em;">
                                    		<div style="text-align:left;width:40%;float:left;">总金额:<span id="summoney" style="color:#F00;font-size:14px;">0.00</span>元</div>
                                    	</div>
                                    	<div style="height:15px;margin-top:3px;font-size: 0.9em;">
                                    		<div style="text-align:right;width:90%;float:right;" id="orderheadinfo"></div>
                                    	</div>
                                    </div>
                                    <div id="searchinfo">
                                        <div style="width:91%; float:left; text-align:right">
                                        	<input id="searchTxtDetail" type="text" class="content searchinput" placeholder="请输入搜索内容" style="height:32px;"/>
                                        </div>
                                        <div style="width:9%; float:left; text-align:right;">
                                        	<input type="button" class="btnSearchDetail important commonbtn" id="btnSubmit" value="搜索"/>
                                        </div>
                                        <div class="clearfloat"></div>
                                    </div>
                                    <table class="table" cellspacing="0" id="detailTable">
                                    	<thead>
                                            <tr>
                                                <th style="width: 20%;">品名</th>
                                                <th style="width: 10%;">单位</th>
                                                <th style="width: 15%;">规格</th>
                                                <th style="width: 10%;">单价</th>
                                                <th style="width: 10%;">实配数</th>
                                                <td style="width: 20%;">实收数</td>
                                                <td style="width: 15%;">备注</td>
                                            </tr>
                                        </thead>
                                        <tbody id="goodsdetail">
                                        </tbody>
                                    </table>
                                    <div id="Pagination" class="pagination" ></div>
                                    <div style="height:20px;"></div>
                                </form>
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
    <script type="text/javascript" src="<%=basePath%>js/bootstrap/bootstrap.min.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- Pikaday -->
    <script type="text/javascript" src="<%=basePath%>js/Pikaday-master/pikaday.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script>
		document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/distribute/distribute.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <!-- jiaself  -->
    <script type="text/javascript" src="<%=basePath%>js/export/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/export/base64.js"></script>
    <script type="text/javascript">
		 $(function() {
			basePath = "<%=basePath %>";
			//模块尺寸(设置配送日期模块的高度)
			$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
			getDistributeDate();
		  });
	</script>
  </body>
</html>
