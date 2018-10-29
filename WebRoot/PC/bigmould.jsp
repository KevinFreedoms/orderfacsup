<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>冶春食品-管理中心</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jqueryui/jquery-ui.css"/>
    <!--bootstrap-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap/bootstrap-reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/assets/font-awesome/css/font-awesome.css"/>
    <!-- showLoading -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/showLoading/showLoading.css"/>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
    <!--page-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
  </head>
  <body>
    <div id="container">
		<div id="header" class="clearfix">
        	<span class="navigation" id="titlemodify">冶春食品公司网上订货平台</span>
		</div>
		<div id="stagecontainer">
			<!-- jiaself -->
			<table id="exporttable"></table>
			<table cellspacing="0" id="stage"  style="min-height:600px;">
				<tbody>
                	<tr>
						<td id="col1" class="col1">
							<ul id="pageButtons">
                                <li class="selected">
                                	<a class="cbegin" href="<%=basePath%>facorder/manager/toPcMain.do">移动订货<i class="menu-triangle" style="display: inline-block;"></i></a>
                                </li>
                                <li><a href="<%=basePath%>facorder/manager/toPcDelivery.do">直送</a></li>
                                <li><a href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                                <!-- jiaself -->
                                <li><a href="<%=basePath%>facorder/season/toPcSeason.do">季节单品</a></li>
                                <li><a href="<%=basePath%>facorder/manager/toPcHistory.do">历史查询</a></li>
                                <li><a class="cend" href="<%=basePath%>facorder/manager/toPcMessage.do">公告</a></li>
                                <li><a class="cend" href="<%=basePath%>facorder/cservice/toProblemCollect.do">售后问题反馈</a></li>
							</ul>
                            <div style="height:200px">
                            	<img src="<%=basePath%>images/logo.jpg" style="width:160px; margin-top:10px;padding-right:15px"/>
                                <img src="<%=basePath%>images/qrcode_for_gh_f5c78120aa42_344.jpg" style="width:160px;margin-top:10px;padding-right:10px"/>
                            </div>
						</td>
                        <td id="wallpaper" class="wallpaper">
                        	<div id="ordermanager" class="container">
                                <form class="form" >
                                    <div class="info" id="formHeader">
                                        <div style="float:left">
                                        	<input class="js-backbtn important commonbtn" id="btnSubmit" type="button" value="返回" onclick="toBack()"/>
                                            <input class="js-backbtn important commonbtn" id="btnSubmit" type="button" value="参考昨日" onclick="refer()"/>
                                            <input class="js-backbtn important commonbtn" id="btnSubmit" onclick="setEmpty()"  type="button" value="清空"/>
                                        </div>
                                        <div class="updatebtnlist" style="display:none;float:left; margin-left:5px;">
                                            <input class="js-backbtn important commonbtn" id="btnSubmit" onclick="printOrder()" type="button" value="打印"/>
                                            <input class="js-backbtn important commonbtn" id="btnSubmit" type="button" value="导出Excel"  onClick="bigOrderExportExcel()" >
                                        </div>
                                        <div style="float:right">
                                            <div class="updatebtnlist" style="display:none">
                                                <input class="js-dealM important commonbtn specialbtn" id="btnSubmit" type="button" value="更新订单">
                                               <!-- <input class="js-dealS important" id="btnSubmit" type="button" value="提交订单" style="background: #933; color: #fff;border: 1px solid #933;">-->
                                            </div>
                                            <div class="createbtnlist">
                                                <input class="js-dealC important commonbtn specialbtn" id="btnSubmit" type="button" value="生成订单">
                                            </div>
                                        </div>
                                        <div style="clear:both"></div>
                                        <h4 style="text-align:center;font-family:微软雅黑">订单明细</h4>
                                        <div style="height:15px;margin-top:3px;font-size: 0.9em;"><div style="text-align:left;width:25%;float:left">预估总金额:<span id="summoney" style="color:#F00;font-size:14px;">0.00</span>元</div></div>
                                        <div style="height:15px;margin-top:3px;font-size: 0.9em;"><div style="text-align:right;width:90%;float:right;" id="orderheadinfo"></div></div>
                                    </div>
                                    <div id="searchinfo">
                                        <div style="width:91%; float:left; text-align:right">
                                            <div style="" id="bookingmark">
                                                
                                            </div>
                                        	<input id="searchTxt" type="text" class="content searchinput" placeholder="请输入搜索内容" style="height:32px;"/>
                                        </div>
                                        <div style="width:9%; float:left; text-align:right;">
                                        	<input type="button" class="btnSearch important commonbtn" id="btnSubmit" value="搜索"/>
                                        </div>
                                        <div class="clearfloat"></div>
                                    </div>
                                    
                                    <div class="goodsmanager" style="display:none">
                                        <div class="col-lg-2" style=" padding:0 2px">
                                            <div id="sidebar"  class="nav-collapse">
												<ul class="sidebar-menu Pcmenu" id="menulist">
                                                 	
                                            	</ul>
                                                <ul>
                                                	<li class="sub-menu specialtype alerychosepro" onclick="alerdychosepro(this)"><a>已选择单品</a><span class="sub readycount">0</span></li>
                                            	</ul>
											</div>
										</div>
                                        <div class="col-lg-10" style="padding:0px;padding-left:5px;margin-bottom:10px;">
                                            <table class="table" id="kingTable">
                                                <thead>
                                                    <th style="width:15%">编码</th>
                                                    <th style="width:25%">品名</th>
                                                    <th style="width:10%">单位</th>
                                                    <th style="width:20%">规格</th>
                                                    <th style="width:10%">单价</th>
                                                    <th style="width:20%">数量</th>
                                                </thead>
                                                <tbody id="goodscontent">
                                                	
                                                </tbody>
                                            </table>
                                            <div id="Pagination" class="pagination"></div>
                                        </div>
                                    </div>
                                    <div style="height:20px;"></div>
                                    <input id="pk" type="hidden" value="">
                                </form>
                        	</div>
                            <div class="promo">
                            	热线电话：0514-82228999 接听时间：8:30--17:30
                            </div>
                        </td><!--wallpaper-->
                    </tr>
                </tbody>
            </table>
		</div>
	</div>
	<!-- js -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap/bootstrap.min.js"></script>
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <!-- showLoading -->
    <script type="text/javascript" src="<%=basePath%>js/showLoading/jquery.showLoading.js"></script>
    <!-- jiaself -->
    <script type="text/javascript" src="<%=basePath%>js/export/tableExport.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/export/base64.js"></script>
    <!-- page -->
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/order/myorder.js?"+Math.random()+"'></scr"+"ipt>");
		document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/export/excelExport/bigMouldOrderExport.js?"+Math.random()+"'></scr"+"ipt>");
	</script>
    <script>
		$(function(){
			basePath = "<%=basePath %>";
			bigbata=${bigdata};
			initMould();
			//模块尺寸
			$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		});
    </script>
  </body>
</html>