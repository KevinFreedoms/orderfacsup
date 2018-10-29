<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
  </head>
  
  <body>
  	<!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
  	<script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript">
		$(function(){
			var url = checkUrl();
			basePath = "<%=basePath %>";
			location.href = basePath + url;
		});
	</script>
  </body>
</html>
