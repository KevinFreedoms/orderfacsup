<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-订单详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0,maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css" />
  </head> 
  <body>
  		<header data-am-widget="header" class="am-header am-header-default orderhead">
            <div class="am-header-left am-header-nav">
                  <a onClick="toBack()">
                      <i class="am-header-icon am-icon-angle-left"></i>
                  </a>
              </div>
              <h1 class="am-header-title">
                  <a>
                   订单详情
                  </a>
              </h1>
  		</header>
    	<div class="maincontent" style="overflow:scroll;padding-bottom:30px">
         <div class="ordermessage">
            <div class="urgentdetail importantInfo">
                <label>单号：</label>
                <div class="orderId"></div>
                <span class="rightInfo bookingstatus"></span>
                <div class="clearfloat"></div>
            </div>
            <div class="urgentdetail secondcolor">
                <label>订货类型：</label>
                <div class="deliverywork"></div>
                <div class="clearfloat"></div>
            </div>
            <div class="urgentdetail secondcolor">
                <label>送达日期：</label>
                <div class="deliverydate"></div>
                <div class="clearfloat"></div>
            </div>
            <div class="operateOrder" style="display:none">
                
            </div>
         </div>
         <div class="urgentlist orderdetail">
            <div class="proInfo importantInfo">
                <span class="leftInfo">单品信息</span>
                <!--<span class="rightInfo mark">￥1490</span>-->
                <div class="clearfloat"></div>
            </div>
            <ul class="detailList">
                
            </ul>      
         </div>
	</div>
	<div class="footer" id="footcontent">
		
	</div>
    <!-- 加载动画 jiaself -->
    <div id="loadingToast" style="display:none;">
		<div class="weui-mask_transparent"></div>
		<div class="weui-toast">
	   		<i class="weui-loading weui-icon_toast"></i>
	   		<p class="weui-toast__content">数据加载中</p>
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
		document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/main/main.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/history.js?"+Math.random()+"'></scr"+"ipt>");
	</script> 
    <script>
		$(function(){
			basePath = "<%=basePath %>";
			bigbata=${bigdata};
			ishistory=${ishistory}; //是否是历史查询
			toHisOrderInfo();
			var height=window.innerHeight-60;
			$(".maincontent").css("max-height",height);
		});
	</script> 
  </body>
</html>
