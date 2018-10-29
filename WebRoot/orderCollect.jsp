<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-历史查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
    <!-- Mobiscroll -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/mobiscroll-2.13.2/css/mobiscroll.2.13.2.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css" />
  </head>
  <style>
  	.chosecond label{
		margin-top:0px;
	}
	.withdata{
		margin-top:85px;
	}
  </style>
  <body>
  	<header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
            <a href="<%=basePath%>facorder/staff/searchOrder.do" class="">
                <i class="am-header-icon am-icon-angle-left"></i>
            </a>
        </div>
        <h1 class="am-header-title">
            <a>
                订单汇总
            </a>
        </h1>
    </header>
    <div class="maincontent">  
    	<div class="urgentfilter">
            <div class="chosecond">
                <label>网点</label>
                <span id="orgName"></span>
                <div class="clearfloat"></div>
            </div>
            <hr />
            <div class="chosecond">
                <label>配送日期</label>
               <span id="deliveryDate"></span>
                <div class="clearfloat"></div>
            </div>
        </div>
        <!--订单列表-->
        <ul class="withdata" style="padding-bottom:140px;" id="orderCollectList">
        </ul> 
        <!--订单列表-->
        
        <!--暂无数据--> 
        <div class="withoutdate display">
        	<div><img src="images/no_query.png" style="width:240px; height:200px;"></div><div style="color:#666;">暂无历史订单</div>
        </div>
         <!--暂无数据-->
    </div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <!-- amazeui-->
    <script type="text/javascript" src="<%=basePath%>js/amazeui/amazeui.min.js"></script>
	<!-- Mobiscroll -->
	<script type="text/javascript" src="<%=basePath%>js/mobiscroll-2.13.2/js/mobiscroll.2.13.2.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery.dataTables.min.js"></script>
 	<script type="text/javascript">
    var basePath="<%=basePath%>";
    </script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/staff/orderCollect.js"></script>
  </body>
</html>
