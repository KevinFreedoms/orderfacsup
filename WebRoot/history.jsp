<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动中心-历史查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css" />
    <!-- Mobiscroll -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>js/mobiscroll-2.13.2/css/mobiscroll.2.13.2.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css" />
  </head>
  <body>
  	<header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
            <a href="<%=basePath%>main.jsp" class="">
                <i class="am-header-icon am-icon-angle-left"></i>
            </a>
        </div>
        <h1 class="am-header-title">
            <a>
                历史查询
            </a>
        </h1>
    </header>
    <div class="maincontent">  
    	<div class="deliverdate">
            <label>配送日期</label>
            <input readonly id="deliverydate" class="select" onChange="initHisOrder()"/>
            <div class="clearfloat"></div>    
        </div>
        <!--订单列表-->
        <ul class="withdata display">
        </ul> 
        <!--订单列表-->
        
        <!--暂无数据--> 
        <div class="withoutdate display">
        	<div><img src="images/no_query.png" style="width:240px; height:200px;"></div><div style="color:#666;">暂无历史订单</div>
        </div>
         <!--暂无数据-->
    </div>
    <!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <!-- amazeui-->
    <script type="text/javascript" src="<%=basePath%>js/amazeui/amazeui.min.js"></script>
	<!-- Mobiscroll -->
	<script type="text/javascript" src="<%=basePath%>js/mobiscroll-2.13.2/js/mobiscroll.2.13.2.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery.dataTables.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/history.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script>
		$(function () {  
			//初始化日期控件
			basePath = "<%=basePath %>";
			var opt = {
				preset: 'date', //日期
				theme: "'bootstrap", //皮肤样式
				display: 'modal', //显示方式 
				mode: "scroller", //日期选择模式
				dateFormat: 'yy-mm-dd', // 日期格式
				cancelText: null,  
				setText: '确定', //确认按钮名称
				dateOrder: 'yymmdd', //面板中日期排列格式
				dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
				endYear:2020, //结束年份
				headerText: function (valueText) { //自定义弹出框头部格式  
					array = valueText.split('-');
					var year = array[0],month=array[1],date=array[2];
					var dt = new Date(array[0]+"/"+array[1]+"/"+array[2]);
					var weekDay = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
					return year + "年" + month + "月" + date + "日" +" "+ weekDay[dt.getDay()];  
				}  
			};
			nowdate = getNowFormatDate();
			initOrderMould(nowdate,nowdate);
			$("#deliverydate").mobiscroll().date(opt);
			$('#deliverydate').val(getNowFormatDate());
		});
	</script>
  </body>
</html>