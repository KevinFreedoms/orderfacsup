<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <base href="<%=basePath%>">
    <title>移动中心后台-订单查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, 	maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/amazeui/amazeui.min.css">
    <!-- Mobiscroll -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/mobiscroll-2.13.2/css/mobiscroll.2.13.2.css"/>
	<!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ordergoods.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/searchOrder.css"/>
</head>
<body style="overflow:scroll">
	<header data-am-widget="header"class="am-header am-header-default" id="header">
        <div class="am-header-left am-header-nav">
            <a href="<%=basePath%>facorder/staff/toMainsys.do" class="">
                <i class="am-header-icon am-icon-angle-left"></i>
            </a>
        </div>
       <div class="am-header-title">
       		 <div class="switch switch-blue">
              <input type="radio" class="switch-input" name="view2" value="week2" id="0" checked >
              <label for="0" class="switch-label switch-label-off" onclick="filterorder('0')"><i class="am-icon-check-square-o"></i>未生效</label>
              <input type="radio" class="switch-input" name="view2" value="month2" id="1"  >
              <label for="1" class="switch-label switch-label-on" onclick="filterorder('1')"><i class="am-icon-check-square-o"></i>已生效</label>
             
            </div>
       </div>
        <div class="am-header-right am-header-nav" id="entry" style="">
          <a onclick="refresh()">
             <i class="am-header-icon am-icon-refresh"></i>
          </a>
        </div>
    </header>
    <div class="filtercondition">
        <div class="choseorg">
            <span id="wd">全部网点</span>
            <i class="am-icon-angle-down" style="top:3px;"></i>
        </div>
        <div  class="noeffective">
            <select onchange="dateChange(this)" id="s1Date">
                <option value='0'>今天</option>
                <option value='1'>明天</option>
                <option value='2'>后天</option>
            </select>
             <i class="am-icon-angle-down" style="top:13px;"></i>
         </div>
         <div  class="effective" style="display:none">
             <input readonly id="deliverydate" onchange='deliveryDateChange()'/>
             <i class="am-icon-calendar-o" style="top:13px;"></i>
        </div>
   </div>
    <div class="maincontent">
           <div class="refreshDate">2018-10-25 10:12:20</div>
           <div  class="orderlist">
               <table>
                    <thead>
                        <th width="35%" style="text-align:left">网点</th>
                        <th width="15%" style="text-align:right">单数</th>
                        <th width="20%" style="text-align:right">品种数</th>
                        <th width="30%" style="text-align:right">金额（元）</th>
                    </thead>
                 </table>
                 <div class="listscroll" style="overflow:scroll">
                     <table>
                        <tbody id="ordertb">
                        </tbody>
                   </table>
                </div>
           </div>
            <div class="withoutdate display" id="noData">
                <img src="images/no_query.png" style="width:240px; height:200px;">
                <div>所选日期暂无数据</div>
            </div>
     	</div>
    </div>
    <div class="bgDiv"></div>
    <div class="rightNav">
    	<ul id="orgName">
        </ul>
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
	<!-- Mobiscroll -->
	<script type="text/javascript" src="<%=basePath%>js/mobiscroll-2.13.2/js/mobiscroll.2.13.2.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <script type="text/javascript">
    var basePath="<%=basePath%>";
    </script>
    <script type="text/javascript" src="<%=basePath%>js/page/staff/searchOrder.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/page/base.js"></script>
    <script type="text/javascript">
		$(function(){
			init()
			//basePath = "http://192.168.1.199:80/orderfacnew/";
			//初始化时间
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
			$("#deliverydate").mobiscroll().date(opt);
		});
	</script>
</body>
</html>
