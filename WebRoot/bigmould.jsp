<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>移动中心-订货管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0,maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css" />
  </head>
  
  <body>
	<header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
             <a class="" onclick="toBack()">
                 <i class="am-header-icon am-icon-angle-left"></i>
             </a>
        </div>
        <h1 class="am-header-title"><a>订单明细</a></h1>
        <!--<div class="am-header-right am-header-nav">
         	<a onclick="lookorder()">
				详情<i class="am-header-icon am-icon-file-text"></i>
			</a>
        </div>-->
  	</header>
    <div class="ordertop">
        <div class="mark endtime">
        </div>
        <div class="search">
           <div class="pull_left searchtype">
                <select data-am-selected="{maxHeight: 300}" id="menulist">
                </select>
            </div>
            <div class="searchgood pull_left">
                <input type="text" placeholder="搜索单品" class="searchpro"/>
                <i class="am-icon-search"></i>
            </div>
            <div class="clearfloat"></div>
    	 </div>
	</div>
    <div class="setbackground">
            <ul class="prolist" id="prolist">
                
            </ul>
        </div>
    </div>
    <div class="footer">
       	<input id="pk" type="hidden" value="">
        <div class="pull_left total alerychosepro" onclick="alerdychosepro(this)">
            <div class="shopcart pull_left">
                <img src="<%=basePath %>images/shopping.png" style="width:60px;height:60px;"/>
                <span class="counnum readycount">0</span>
            </div>
            <span class="countprice">预估总金额：<span id="summoney">0.00</span></span>
        </div>
        <div class="pull_right sure js-dealM updatebtnlist" style="display:none">
            更新订单
        </div>
        <div class="pull_right sure js-dealC createbtnlist" style="display:none">
            生成订单
        </div>
    </div>
    <!---购物车-->
    <div class="am-modal-actions" id="shopcart">
        <div class="am-modal-actions-group">
            <div class="carthead">
                <span>已选单品</span>
                <a class="clearshop" onclick="setEmpty()"><span><i class="am-icon-trash-o"></i>清空</span></a>
            </div>
            <ul class="am-list shopList">
               
            </ul>
        </div>
    </div>
    <!---购物车-->
    <!---搜索单品-->
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="searchpro" style="height:100%;">
	    <div class="am-modal-dialog" style="width:100%; height:100%;background-color:#fff; position:absolute; left:0px;">
			<div class="am-modal-hd" style="width:100%;font-size:1em;padding-right:0px;">
				<a class="pull_left backpage">
					<i class="am-icon-chevron-left"></i>
					返回
				</a>
				<div class="cate-search1 pull_left">
					<input type="text" class="cate-input1 filterpro" placeholder="请输入单品" />
				</div>
                <div class="pull_left list-js" >
					<a class="searchbtn" onClick="filterpro()"/>搜索</a>
                </div>
				<div class="clearfloat"></div>
			</div>
			<div class="am-modal-bd" style="padding:0px; font-size:1.5rem; background-color:#FFF;">
				<div class="proshow">
                	<!--历史查询-->
                    <div class="historystore" style="text-align:left; padding:10px;">
                    	<span class="location"><i class="am-icon-clock-o" style="margin-right:5px; font-size:1.6rem;"></i><span>历史</span></span>
                  		<span class="pull_right location clearhistory" style="font-size:1.6rem;"><i class="am-icon-trash-o"></i></span>
			      		<ul class="prohistory">
                        </ul>
                        <div class="clearfloat"></div>
                    </div>
                    <!--历史查询-->
                	<!--单品展示-->
                	<ul class="prolist display" id="allgoodslist">
                    </ul>
                    <!--单品展示-->
                    <!--搜索没有单品状态-->
                    <div  class="withoutpro display">
                        <img src="<%=basePath %>images/no_query.png" style="float:none; margin:0px;width:240px; height:200px;">
                        <p>没有该单品哦！</p>
                    </div>
                     <!--搜索没有单品状态-->
                 </div>
				
			</div>
	    </div>
	</div>
	<!---搜索单品-->
    <!--加载-->
    <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="loading">
      <div class="am-modal-dialog">
        <div class="am-modal-hd">正在载入...</div>
        <div class="am-modal-bd">
          <span class="am-icon-spinner am-icon-spin"></span>
        </div>
      </div>
    </div>
    <!--查看订单详情-->
   	<div id="filterorder" class=" filterorder am-modal-actions">
		<div class="am-modal-actions-group">
            <ul class="am-list" id="conditionList">
            </ul>
      	</div>
	</div>
    <!--查看订单详情-->
   	<!-- jquery-->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <!-- amazeui-->
    <script type="text/javascript" src="<%=basePath%>js/amazeui/amazeui.min.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/myorder.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script>
		$(function(){
			basePath = "<%=basePath %>";
			bigbata=${bigdata};
			initMould();
			var height=window.innerHeight-60;
			$(".shopList").css("max-height",height);
			height = height-133;
			$("#prolist").css("max-height",height);
		}); 
	</script>
  </body>
</html>
