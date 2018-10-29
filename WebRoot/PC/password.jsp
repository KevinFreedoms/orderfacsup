<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-修改密码</title>
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/login.css">
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>

  </head>
  
  <body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_logo">
        	<img src="images/fy.png">
        </div>
		<div class="login_boder" style="height:450px;width:403px;background-size:403px 450px;">
			<div class="login_padding" id="login_model">
  			<h2>账号</h2>
  			<label>
    			<input type="text" id="username"  class="txt_input txt_input2"  placeholder="请输入您的用户账号或手机号">
  			</label>
  			<h2>原密码</h2>
  			<label>
    			<input type="password" id="password"  name="textfield2" class="txt_input" placeholder="请输入您的原密码">
  			</label>
            <h2>新密码</h2>
  			<label>
    			<input type="password" id="newpassword" name="textfield2" class="txt_input" placeholder="请输入您的新密码">
  			</label>
            <h2>重新输入新密码</h2>
  			<label>
    			<input type="password"  id="replaypassword"  name="textfield2" class="txt_input" placeholder="请再次输入您的新密码">
  			</label>
  			<div class="rem_sub">
            	<div class="rem_sub_l">
  					<a href="<%=basePath%>PC/login.jsp" class="backurl"><span>《返回登录</span></a>
   				</div>
    			<label>
      				<a href="javascript:void(0);" style="text-decoration:none;" class="js-sure" ><input type="button" class="sub_button" name="button" id="button" value="确认修改" style="opacity: 0.7;"></a>
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
		});
	</script>
  </body>
</html>
