<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- css -->
	<link rel="stylesheet" href="<%=basePath%>css/lib/jquerymobile/themes/jquery.mobile.icons.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/lib/jquerymobile/jquery.mobile.structure-1.4.5.min.css" />
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobilecommon.css"/>
    <style>
  		.left{
			float:left;
		}
		.clearfloat{
			clear:both;
		}
		.home_button{
			height:auto;
		}
		#home_box a{
			width:32%;
			margin-bottom:20px;
			border:0px;
			background-color:#edf3f0;
		}
		#home_box img{
			margin-bottom:10px;
			border-radius:15px;
		}
		.ui-field-contain{
			border-bottom:0px;
			border-bottom-style:none;
		}
		.ui-header .ui-btn-left{
			color:#fff;
			margin-top:7px;
			margin-left:2px;
		}
  </style>
  </head>
  
  <body>
    <div data-role="page">
        <div data-role="header" style="">
            <a data-role="none" data-icon="back" title="返回上一页" rel="external" href="<%=basePath%>facorder/staff/dologsys.do">
           	   <i class="am-header-icon am-icon-angle-left"></i>
           </a>
           <h1 style="font-size:16px;">后台管理</h1>
        </div>
        <div data-role="content"> 
            <div id="home_box" style="margin:10px 0px;">
                <div data-role="controlgroup" data-type="vertical" id="list" class="ui-controlgroup ui-controlgroup-vertical ui-corner-all">
                <%-- 	<a href="javascript:void(0);" onclick="toPage(0)" rel="external" data-role="button" class="ui-btn ui-shadow ui-corner-all left">
                    	<div class="ui-grid-a">
                        	<div>
                            	<img src="<%=basePath%>images/11.png">
							</div>
                            <div>
                            	<span>订单查询</span>
                            </div> 
                        </div>
                    </a> --%>
            	</div>
            </div>
        </div>
        
        <div data-role="footer" data-transiton="slide"  data-tap-toggle="false" data-position="fixed" style="max-width:600px;margin:auto;">
            <h1 style="padding:0;"><a href="<%=basePath%>facorder/staff/toMainsys.do" rel="external" data-iconpos="notext" data-role="button" data-icon="home" title="回到主页">Home</a></h1>
        </div>
    </div>
    
	<!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
       <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <!-- page -->
     <script type="text/javascript">
			basePath = "<%=basePath%>";
	</script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/staff/mainsys.js"></script>
   
  </body>
</html>
