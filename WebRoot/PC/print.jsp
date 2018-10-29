<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>冶春食品-打印预览</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/lib/formview.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/default.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/lib/formtemplate.css">
    <!-- 打印-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>PC/source/print.css">
  </head>
  
  <body>
    <div id="container">
		<div id="header" class="clearfix">
        	<span class="navigation">打印预览</span>
            <img src="<%=basePath%>/images/print.png" style="padding-top:5px;float:right;margin-right:15px; height:25px" onClick="printManager()">
		</div>
		<div id="stagecontainer" style="min-height:600px;">
			<div id="print-content">
			    
             	<div class="header">
                 	
             	</div>
                <!-- 页面顶部信息 -->
                <div class="headInfo" id="headerInfo">
                   
				</div>
             	<!-- 页面记录信息 -->
                <table class="tableTopBorder_3" id="tabContent">
                    
              	</table>
			  	<!-- 页码信息 -->
              	<div class="signatureArea" id="footerInfo">
					<span class="floatRight pageNum">1/1</span>
			  	</div>
              	<div class="signatureArea">
					<span class="firstSpan">送货人签字：</span>
					<span>接收人签字：</span>
			  	</div>
			</div>
        </div>
  	</div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>PC/source/msswms_print.js"></script>
    <!-- 打印 -->
    <script type="text/javascript" src="<%=basePath%>PC/source/jQuery.print.js"></script>
    <script type="text/javascript">
    
		$(function(){
			type = '${type}';
			collect = ${collect};
			detail = ${detail};
			status = '${status}';
			initOrder();
		});
	</script>
  </body>
</html>
