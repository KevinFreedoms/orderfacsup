<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-订单管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css" />
  </head>
  
  <body>
    <header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
            <a href="<%=basePath%>main.jsp">
                <i class="am-header-icon am-icon-angle-left"></i>
            </a>
        </div>
        <h1 class="am-header-title"><a>移动订货</a></h1>
        <div class="am-header-right am-header-nav" id="entry" style="display:none">
          <a onclick="toMouldInfo()">
             新增<i class="am-header-icon am-icon-edit"></i>
          </a>
        </div>
    </header>
    <div class="maincontent">
		<!--筛选条件-->
		<div class="urgentfilter">
            <div class="chosecond">
                <label>订货类型</label>
                <select id="mouldlist">
                    
                </select>
                <i class="am-header-icon am-icon-caret-down"></i>
                <div class="clearfloat"></div>
            </div>
            <hr />
            <div class="chosecond">
                <label>配送日期</label>
                <select id="datelist">
                    
                </select>
                <i class="am-header-icon am-icon-caret-down"></i>
                <div class="clearfloat"></div>
            </div>
        </div>
		<!--订单列表-->
        <ul class="ordercontent urgentcontent">
        	
        </ul>
		<!--订单列表-->
        <!--暂无数据--> 
        <div class="withoutdate display">
        	<div><img src="images/no_query.png" style="width:240px; height:200px;"></div><div style="color:#666;">暂无订单</div>
        </div>
         <!--暂无数据-->
    </div>
    <!--加载-->
    <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="loading">
      <div class="am-modal-dialog">
        <div class="am-modal-hd">正在载入...</div>
        <div class="am-modal-bd">
          <span class="am-icon-spinner am-icon-spin"></span>
        </div>
      </div>
    </div>
    <!-- jquery-->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <!-- amazeui-->
    <script type="text/javascript" src="<%=basePath%>js/amazeui/amazeui.min.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/order.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script>
		$(function(){
			basePath = "<%=basePath %>";
			initDelMoulds();
		});
	</script>
  </body>
</html>
