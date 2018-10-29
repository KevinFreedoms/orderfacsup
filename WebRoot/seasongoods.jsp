<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-季节单品</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
   <!-- css -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css">
    <!-- Mobiscroll -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>js/mobiscroll-2.13.2/css/mobiscroll.2.13.2.css"/>
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css"/>
     <link rel="stylesheet" type="text/css" href="<%=basePath%>css/seasongoods.css"/>
  </head>
  
  <body>
  	<header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
            <a href="<%=basePath%>main.jsp" class="">
                <i class="am-header-icon am-icon-angle-left"></i>
            </a>
        </div>
        <h1 class="am-header-title">
            <a>
                季节单品
            </a>
        </h1>
    </header>
    <div class="maincontent">
        <div class="searchgood">
            <label>搜索单品</label>
            <input type="text" placeholder="搜索单品" class="searchpro" onKeyUp="searchpro(this)">
            <i class="am-icon-search"></i>
        </div> 
        <ul class="delivery" id="seasongoods">
       </ul>
       <div class="noinfo display">
	  	 	<img src="images/no_query.png" style="width:240px; height:200px;">
            <div style="color:#666;">暂无季节单品</div>
	   </div>
    </div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <!-- amazeui-->
    <script type="text/javascript" src="<%=basePath%>js/amazeui/amazeui.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/season/season.js"></script>
    <script type="text/javascript">
		$(function(){
			basePath = "<%=basePath %>";
			getAllSeasonGoods();
		});
		//置顶
		$(".backto-top").click(function(){
			$("html,body").animate({"scrollTop":0},500);
		});
	</script>
  </body>
</html>
