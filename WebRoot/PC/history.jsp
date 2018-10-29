<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-历史订单</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap/bootstrap.min.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!-- Pikaday -->
    <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>js/dbcalendar/daterangepicker-bs3.css" />
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
    <style>
    	.daterangepicker .ranges .input-mini {
			width:90px;
		}
		.historylist td:hover{
			cursor:pointer;
		}
    </style>
  </head>
  
  <body>
    <div id="container">
		<div id="header" class="clearfix">
        	<span class="navigation" id="titlemodify">冶春食品公司网上订货平台</span>
		</div>
		<!-- jiaself -->
		<table id="exporttable"></table>
		
		<div id="stagecontainer">
			<table cellspacing="0" id="stage">
				<tbody>
                	<tr>
						<td id="col1" class="col1">
							<ul id="pageButtons">
                                <li><a class="cbegin" href="<%=basePath%>facorder/manager/toPcMain.do">移动订货</a></li>
                                <li><a href="<%=basePath%>facorder/manager/toPcDelivery.do">直送</a></li>
                                <li><a href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                                <!-- jiaself -->
                                <li><a href="<%=basePath%>facorder/season/toPcSeason.do">季节单品</a></li>
                                <li class="selected"><a href="<%=basePath%>facorder/manager/toPcHistory.do">历史查询<i class="menu-triangle" style="display:inline-block"></i></a></li>
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
                                                	<input type="text" data-role="datebox" id="deliverydate" style="width:180px;padding: 2px 1px;" value="">
                                                </span>
                                        	</div>
                                    	</li>
                                	</ul>
                                    <table class="table" cellspacing="0" id="kingTable" style="display: none;">
                                    	<thead>
                                            <tr>
                                            	<th style="width:10%;">分类</th>
                                                <th style="width:10%;">订单号</th>
                                                <th style="width:20%;">订货类型</th>
                                                <th style="width:10%;">配送日期</th>
                                                <th style="width:5%;">订货<br>数量</th>
                                                <th style="width:5%;">订单<br>总价</th>
                                                <th style="width:20%;">创建时间</th>
                                                <th style="width:20%;">更新时间</th>
                                            </tr>
                                        </thead>
                                        <tbody id="goodscontent" class="historylist">
                                        	
                                        </tbody>
                                    </table>
                                    <div id="Pagination" class="pagination collect" style="display: none;"></div>
                                    <div id="withoutdata" style="display:none" >
                                         <div style="text-align: center;color: #999; padding-top:10%;font-size: medium;" >所选择日期区间没有数据</div>
                                    </div>         
                                </form>
                            </div>
                            <div id="ordermanager" class="container" style="display: none;">
                            	<form class="form">
                                    <div class="info" id="formHeader">
                                    	<div style="float:left">
                                        	<input class="js-backbtn important commonbtn" id="btnSubmit" type="button" value="返回" onclick="toBack()"/>
                                        </div>
                                        <div class="updatebtnlist" style="float:left; margin-left:5px;">
                                            <input class="js-backbtn important commonbtn" id="btnSubmit" onclick="printOrder()" type="button" value="打印"/>
                                            <input class="js-backbtn important commonbtn" id="btnSubmit" type="button" value="导出Excel"  onClick="historyOrderExportExcel()" >
                                        </div>
                                        <div style="clear:both"></div>
                                        <h4 style="text-align:center;font-family:微软雅黑">订单明细</h4>
                                        <div style="height:15px;margin-top:3px;font-size: 0.9em;"><div style="text-align:left;width:25%;float:left">预估总金额:<span id="summoney" style="color:#F00;font-size:14px;">0.00</span>元</div></div>
                                        <div style="height:15px;margin-top:3px;font-size: 0.9em;"><div style="text-align:right;width:90%;float:right;" id="orderheadinfo"></div></div>
                                    </div>
                                    <table class="table" cellspacing="0" id="kingTable2">
                                    	<thead>
                                            <tr>
                                            	<th style="width:15%">编码</th>
                                                <th style="width:20%">品名</th>
                                                <th style="width:10%">单位</th>
                                                <th style="width:15%">规格</th>
                                                <th style="width:10%">单价</th>
                                                <th style="width:10%">数量</th>
                                                <th style="width:10%">合计</th>
                                            </tr>
                                        </thead>
                                        <tbody id="goodscontent" class="detaillist">
                                        	
                                        </tbody>
                                    </table>  
                                    <div id="Pagination" class="pagination detail" ></div>
                                    <div style="height:20px;"></div>
                                    <input id="pk" type="hidden" value="">
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
	<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap/bootstrap.min.js"></script>
    <!--日历插件-->
    <script type="text/javascript" src="<%=basePath%>js/dbcalendar/moment.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/dbcalendar/daterangepicker.js"></script>
    <!-- showLoading -->
    <script type="text/javascript" src="<%=basePath%>js/showLoading/jquery.showLoading.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/export/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/export/base64.js"></script>
    <script>
		document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/history.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script type="text/javascript">
		 $(function() {
			 basePath = "<%=basePath %>";
			 //模块尺寸(设置配送日期模块的高度)
			 $('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
			 $('#deliverydate').val(getNowFormatDate()+" - "+getNowFormatDate());
			 initOrderMould(getNowFormatDate(),getNowFormatDate());
			 $('#deliverydate').daterangepicker(null, function(start, end, label) {
            	str = $('#deliverydate').val();
				temp = str.split(" - ");
				initOrderMould(temp[0],temp[1])
            });
		 });
	</script>
  </body>
</html>
