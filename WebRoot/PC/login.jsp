<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-登陆</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/login.css">
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
  </head>
  
  <body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_logo">
        	<img src="images/fy.png">
        </div>
		<div class="login_boder">
			<div class="login_padding" id="login_model">
  			<h2>账号</h2>
  			<label>
    			<input type="text" id="user" class="txt_input txt_input2"  placeholder="请输入您的用户账号或手机号">
  			</label>
  			<h2>密码</h2>
  			<label>
    			<input type="password" id="pass" name="textfield2" class="txt_input" placeholder="请输入密码">
  			</label>
  			<div class="rem_sub">
  				<div class="rem_sub_l">
  					<input type="checkbox" name="checkbox-a" id="checkbox-a">
   					<label for="checkbox-a">记住密码？</label>
   				</div>
    			<label>
                	<a href="<%=basePath%>PC/password.jsp" class="modifyurl"><span>修改密码？</span></a>
      				<a href="javascript:void(0);" style="text-decoration:none;" class="js-login">
      					<input type="button" class="sub_button" name="button" id="button" value="登陆" style="opacity: 0.7;">
      				</a>
    			</label>
 			 </div>
		</div>
    </div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery.cookie.js" ></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <!-- page -->
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/user/login.js"></script>
    <script type="text/javascript">
		$(function(){
			basePath = "<%=basePath %>";
			//密码
			temp = new Base64();
			if ($.cookie("rmbUser") == "true") {
				$("#checkbox-a").attr("checked", true);
				$("#user").val(temp.decode($.cookie("username")));
				$("#pass").val(temp.decode($.cookie("password")));
			}
		});
	</script>
  </body>
</html>
