<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-管理中心</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css"/>
    <!-- showLoading -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/showLoading/showLoading.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!--勾选框选中样式-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/fency-style-checkbox.css"/>
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
     <style>
		.checkboxWrapper input[type="radio"]:checked+label{
			color:#3498db;
			font-weight:bold;
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
		
		<div id="stagecontainer" style="min-height:600px;">
			<table cellspacing="0" id="stage">
				<tbody>
                	<tr>
						<td id="col1" class="col1">
							<ul id="pageButtons">
                                <li class="selected">
                                	<a class="cbegin" href="<%=basePath%>facorder/manager/toPcMain.do">移动订货
                                	<i class="menu-triangle" style="display: inline-block;"></i></a>
                                </li>
                                <li><a href="<%=basePath%>facorder/manager/toPcDelivery.do">直送</a></li>
                                <li><a href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                                <!-- jiaself -->
                                <li><a href="<%=basePath%>facorder/season/toPcSeason.do">季节单品</a></li>
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
                        	<div id="delmanager" class="container">
                            	<form class="form" id="search">
                                    <ul id="fields" class="fields" style="font-size: 16px;">
                                        <li class="three" type="radio" reqd="1">
                                        	<label class="desc" style="margin:3px">订货类型</label>
                                            <div class="content moulds">
                                            </div>
                                    	</li>
                                    	<!-- 添加代码  2017-08-31-->
                                        <li class="three" type="radio" reqd="1" id="frequency">
                                        	<label class="desc" style="margin:3px" id="frequencydate">配送日期</label>
                                            <div class="content datelist">
                                            </div>
                                    	</li>
                                        <li class="three" type="radio" reqd="1" id="orderlist" style="display:none">
                                        	<label class="desc" style="margin:3px">订单列表 </label>
                                            <div class="content orderinfo">
                                            </div>
                                            <div style="text-align:right"><input class="commonbtn btn-modify adjustbutton specialbtn" id="btnSubmit" type="button" value="修改订单" onClick="toOrderInfo()"></div>
                                    	</li>
                                        <li style="display:none; margin-top:20px" id="createmanager"><div style="text-align:right"><input class="commonbtn btn-create adjustbutton2 specialbtn" id="btnSubmit" type="button" value="生成请货" onClick="toMouldInfo()"></div></li>
                                	</ul>
                                    <input type="hidden" name="model.deliveryDate" id = "date">
                                    <input type="hidden" name="model.mouldId" id = "mould">
                					<input type="hidden" name="model.orgId" id = "orgid">
                                    <input type="hidden" name="model.pk" id = "pk">
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
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <!-- showLoading -->
    <script type="text/javascript" src="<%=basePath%>js/showLoading/jquery.showLoading.js"></script>
    <!-- jiaself -->
    <script type="text/javascript" src="<%=basePath%>js/export/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/export/base64.js"></script>
    <!-- page -->
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/order.js?"+Math.random()+"'></scr"+"ipt>"); 
	</script>
    <script type="text/javascript">
		$(function(){
			 //初始化订单信息
			basePath = "<%=basePath %>";
			phoneG["order"] = phoneG["order"]||{};
			initDelMoulds();
			//模块尺寸
			$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		});
	</script>
  </body>
</html>
