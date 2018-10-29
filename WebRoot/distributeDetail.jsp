<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-配送收货</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css">
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css"/>
  </head>
  <style>
  	.am-dimmer{
		z-index:1300;
	}
  </style>
  <body>
  	<header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
            <a  onclick="toBack()" class="">
                <i class="am-header-icon am-icon-angle-left"></i>
            </a>
        </div>
        <h1 class="am-header-title">
            <a>
                订单明细
            </a>
        </h1>
        <div class="am-header-right am-header-nav specialicon">
             <a onclick="lookorder()">
                  详情<i class="am-header-icon am-icon-file-text"></i>
             </a>
   		</div>
    </header>
  	<div class="ordertop">
        <div class="search deliveryDetailbtn">
            <div class="searchgood pull_left">
                <input type="text" placeholder="搜索单品" class="searchpro"/>
                <i class="am-icon-search"></i>
            </div>
            <div>
            	<button class="am-btn am-btn-primary mgoodsSearch">搜索</button>
            </div>
            <div class="clearfloat"></div>
    	 </div>
	</div>
    <div class="withoutdate display" style="margin-top: 30%;">
        <img src="images/no_query.png" style="width:240px; height:200px;">
        <div>未找到搜索单品</div>
    </div>
    <ul class="prolist" id="deliveryList">	 
    </ul>
	<div class="footer" id="footcontent">
	</div>
    <!--详情-->
    <div id="filterorder" class=" filterorder am-modal-actions">
       <div class="am-modal-actions-group">
         <ul class="am-list" id="conditionList">
          
         </ul>
       </div>
	</div>
 	<!--详情-->
    <!--加载-->
    <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="loading">
      <div class="am-modal-dialog">
        <div class="am-modal-hd">正在载入...</div>
        <div class="am-modal-bd">
          <span class="am-icon-spinner am-icon-spin"></span>
        </div>
      </div>
    </div>
    <!--输入数量-->
    <div class="am-modal am-modal-prompt" tabindex="-1" id="prompt">
        <div class="am-modal-dialog">
            <div class="am-modal-hd"></div>
            <div class="am-modal-bd">
                <input type="number" class="am-modal-prompt-input">
                <div class="lsdw"></div>
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn cancel" data-am-modal-cancel>取消</span>
                <span class="am-modal-btn" data-am-modal-confirm>提交</span>
            </div>
        </div>
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
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <!-- amazeui-->
    <script type="text/javascript" src="<%=basePath%>js/amazeui/amazeui.min.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/distribute/distribute.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script type="text/javascript">
		$(function(){
			//加载动画
			basePath = "<%=basePath %>";
			moveinfo(phoneG.details);
			//获取页面高度，给列表赋值
			var height=window.innerHeight-107;
			$("#deliveryList").css("height",height);
		});
		//查看详情
		function lookorder(){
			$(".am-modal-actions.am-modal-out").css("transform","translateY(0)")
			$(".am-modal-actions.am-modal-active").css("transform","translateY(-100%)")
			$("#filterorder").modal()
			$(".am-dimmer").css("z-index","1240")
			$(".am-header-default").css("z-index","1260")
			$(".filterorder").css("z-index","1250")
		}
	</script>
  </body>
</html>
