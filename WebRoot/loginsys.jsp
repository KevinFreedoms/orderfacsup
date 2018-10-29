<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心后台-登陆</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- css -->
	<link rel="stylesheet" href="<%=basePath%>css/lib/jquerymobile/themes/MobileCssOne.css" />
	<link rel="stylesheet" href="<%=basePath%>css/lib/jquerymobile/themes/jquery.mobile.icons.min.css"/>
	<link rel="stylesheet" href="<%=basePath%>css/lib/jquerymobile/themes/MMCss.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/lib/jquerymobile/jquery.mobile.structure-1.4.5.min.css" />
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <style>
		#item-info {
			width:85%;
			margin:auto;	
			}
		#item-info .ui-block-b{
			text-align:left;
			}
		.sweet-alert {
			width:260px;
		}
	</style>
  </head>
  
  <body>
    <div data-role="page" id="loginpage">         
		<div data-role="content" role="main" style="max-width:600px;margin:auto;">      
        	<div data-role="fieldcontain" style="text-align:center;">
            	<img src="<%=basePath%>images/fysys.png"/>
            </div>
        
        	<div data-role="fieldcontain">
            	<p>用户账号</p>
            	<input id="user" type="text" placeholder="请输入您的用户账号" />
                <p>密&nbsp;&nbsp;&nbsp;码</p>
            	<input id="pass" type="password" placeholder="请输入密码" />
                <input type="checkbox" name="checkbox-a" data-theme="c" id="checkbox-a" checked="checked" />
          		<label style="z-index:0;background-color:#edf3f0;border:0px;" for="checkbox-a">记住密码？</label>
            </div>
            
        	<div data-role="fieldcontain">
                <fieldset class="ui-grid-a">          
                    <div class="ui-block-a">
                    	<a class="js-pass" style="text-decoration:none;" title="修改密码"><button data-theme="b">修改密码</button></a>
                    </div>
                    <div class="ui-block-b">
                    	<a  class="js-login" style="text-decoration:none;"><button data-theme="z">登录</button></a>
                    </div>        
                </fieldset>           
            </div>
        </div>
	</div>
    
    <div data-role="page" id="passwordpage">       
    	<div data-role="header" style="max-width:600px;margin:auto;">
           <a data-role="none" data-icon="back" title="返回登录界面" rel="external" href="<%=basePath%>facorder/staff/dologsys.do">
           	   <i class="am-header-icon am-icon-angle-left"></i>
           </a>
           <h1 style="font-size:16px;">修改密码</h1>
        </div>
        <div data-role="content" role="main" style="max-width:600px;margin:auto;">      
        	<div data-role="fieldcontain">
                    <p>用户账号</p>
                    <input id="username" name="model.yhbm" type="text" placeholder="请输入您的用户账号" />
                    <p>原密码</p>
                    <input id="password" name="model.yhkl" type="password" placeholder="请输入您的原密码" />
                    <p>新密码</p>
                    <input id="newpassword" name="model.newyhkl" type="password" placeholder="请输入您的新密码" />
                    <p>重新输入新密码</p>
                    <input id="replaypassword" name="model.replayyhkl" type="password" placeholder="请再次输入您的新密码" />
            </div>
            
        	<div data-role="fieldcontain">            
                <div style="height:55px;width:100%;margin:auto;"><button data-theme="z" class="js-sure" >确认修改</button></div>            
            </div>
        </div>
	</div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery.cookie.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- page -->
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/staff/staffLogin.js"></script>
    <script type="text/javascript">
		$(function(){
			basePath = "<%=basePath %>";
			//密码
			temp = new Base64();
			if ($.cookie("staff") == "true") {
				$("#checkbox-a").attr("checked", true);
				$("#user").val(temp.decode($.cookie("username")));
				$("#pass").val(temp.decode($.cookie("password")));
			}
			pushHistory();
			 window.addEventListener("popstate", function(e) {   
                pushHistory(); 
        	}, false);
		});
		function pushHistory() { 
			var url = basePath;
			var state = {  
				title: "title",  
				url: url
			};  
			window.history.pushState(state, "title", url);  
		}
	</script>
  </body>
</html>
