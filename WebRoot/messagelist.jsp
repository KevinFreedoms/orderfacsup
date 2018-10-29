<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-公告</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- css -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/lib/jquerymobile/themes/MobileCssOne.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/lib/jquerymobile/themes/jquery.mobile.icons.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/lib/jquerymobile/themes/MMCss.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/lib/jquerymobile/jquery.mobile.structure-1.4.5.min.css" />
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>

  </head>
  
  <body>
    <div data-role="page" id="page1">  
        <div data-role="header" style="max-width:600px;margin:auto; height:49px; border-color:#039">
           <a data-role="none" data-icon="back" title="返回上一页" rel="external" href="<%=basePath%>main.jsp" style="padding:0 3px; margin-top:5px;">
           	   <i class="am-header-icon am-icon-angle-left"></i>
           </a>
            <h1 style="font-size:16px;">公告查询</h1>
        </div>
        <div data-role="content" style="max-width:600px;">  
            <h4>查询时间</h4>
            <ul id="radio_box">       
                <li>
                    <input type="radio" data-role="none" class="theRadio" id="day"  onClick="searchMessage()" name="peisong" checked="checked" />
                    <label class="radio-label" for="day"><img src="<%=basePath%>images/bingo.png" />当天</label>
                </li>
                <li>
                    <input type="radio" data-role="none" class="theRadio"  id="week"  onClick="searchMessage()" name="peisong"/>
                    <label class="radio-label radio_off"for="week"><img class="img_off" src="<%=basePath%>images/bingo.png" />本周</label>
                </li>
                <li>
                    <input type="radio" data-role="none" class="theRadio" id="month"  onClick="searchMessage()" name="peisong"/>
                    <label class="radio-label radio_off"for="month"><img class="img_off"src="<%=basePath%>images/bingo.png" />本月</label>
                </li>        
            </ul>
            <h4>公告列表</h4>
            <div data-role="controlgroup" data-type="vertical" id="messagelist">
            </div>
        </div>
        
        <div data-role="footer" data-transiton="slide" data-tap-toggle="false" data-position="fixed" style="max-width:600px;margin:auto;">
            <h1 style="padding:0;"><a href="<%=basePath%>main.jsp" rel="external" data-iconpos="notext" data-role="button" data-icon="home" title="回到主页">Home</a></h1>
        </div> 
    </div>
    <div data-role="page" id="messagedetail">
    	<div data-role="header" style="max-width:600px;margin:auto; height:49px; border-color:#039">
           <a data-role="none" data-icon="back" rel="external" onClick="toBack()" style="padding:0 3px; margin-top:5px;">
           	   <i class="am-header-icon am-icon-angle-left"></i>
           </a>
            <h1 style="font-size:16px;">公告明细</h1>
        </div>
        <div data-role="content" role="main" style="max-width:600px;">
        	<ul data-role="listview" data-inset="true">
        		<li data-role="list-divider" data-theme="c" style="font-size:14px">通知</li>
      		</ul>
			<div id="content">
            	
            </div>
        </div>
        <div data-role="footer" data-transiton="slide" data-tap-toggle="false" data-position="fixed" style="max-width:600px;margin:auto;">
            <h1 style="padding:0;"><a href="<%=basePath%>main.jsp" rel="external" data-iconpos="notext" data-role="button" data-icon="home" title="回到主页">Home</a></h1>
        </div>   
    </div>
	<!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery.dataTables.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/message/message.js"></script>
	<script type="text/javascript">
		$(function(){
			basePath = "<%=basePath %>";
			searchMessage();
			$("#radio_box li").each(function(index) {
				$(this).click(function(){
					$(".radio-label",this).removeClass("radio_off");
					$(".radio-label img",this).removeClass("img_off");
					$(this).siblings().children(".radio-label").addClass("radio_off");
					$(this).siblings().find("img").addClass("img_off");
				});
			});
		});
	</script>
  </body>
</html>