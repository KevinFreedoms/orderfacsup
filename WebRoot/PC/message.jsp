<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>冶春食品-消息查询</title>
    <!-- sweetalert -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/sweetalert.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/sweetalert/facebook.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/PC/base.css"/>
	<style>
		label {
		  cursor: default;
		  font-size:.8em;
		}
		 .page a{
		  text-decoration:none;  
		}  
		  .page a span{  
		  padding:4px 16px 4px 16px;  
		  border:1px solid #AAAAEE;  
		  color: #1155BB;  
		  font-size:12px;  
		  font-weight:bold;  
		  font-family: 'helvetica neue', arial, sans-serif;  
		}  
		  
		.page a span:hover,.click_page{  
		  background:#2266BB;  
		  color:#ffffff;  
		 } 
	</style>

  </head>
  
  <body>
    <div id="container">
		<div id="header" class="clearfix">
        	<span class="navigation">冶春食品公司网上订货平台</span>
		</div>
		<div id="stagecontainer" style="min-height:600px;">
			<table cellspacing="0" id="stage">
				<tbody>
                	<tr>
						<td id="col1" class="col1">
							<ul id="pageButtons">
                                <li>
                                	<a class="cbegin" href="<%=basePath%>facorder/manager/toPcMain.do">移动订货</a>
                                    
                                </li>
                                <li><a href="<%=basePath%>facorder/manager/toPcDelivery.do">直送</a></li>
                                <li><a href="<%=basePath%>facorder/distribute/toPcDistribute.do">配送收货</a></li>
                            	<!-- jiaself -->
                                <li><a href="<%=basePath%>facorder/season/toPcSeason.do">季节单品</a></li>
                                <li><a href="<%=basePath%>facorder/manager/toPcHistory.do">历史查询</a></li>
                                <li class="selected">
                                	<a class="cend" href="<%=basePath%>facorder/manager/toPcMessage.do">公告
                                    	<i class="menu-triangle" style="display:inline-block"></i>
                                    </a>
                            	</li>
                                <li><a class="cend" href="<%=basePath%>facorder/cservice/toProblemCollect.do">售后问题反馈</a></li>
							</ul>
                            <div style="height:200px">
                            	<img src="images/logo.jpg" style="width:160px; margin-top:10px; margin-right:5px"/>
                                
                                <img src="images/qrcode_for_gh_f5c78120aa42_344.jpg" style="width:160px; margin-top:10px; margin-right:5px"/>
                                
                            </div>
						</td>
                        <td id="wallpaper" class="wallpaper">
                            <div id="messagelistC" class="container">
                                <form class="form">
                                    <ul id="fields" class="fields">
                                        <li class="one" typ="dropdown">
                                            <label class="desc" style="float:left;margin-right: 10px;margin-top: 3px;">时间区间</label>
                                            <div class="content" style="float: left;">
                                                <select class="input fld m" name="F1_dropdown" id="timechange" onChange="searchMessage()" style="width:160px">
                                                    <option value="day">当天</option>
                                                    <option value="week">一周内</option>
                                                    <option value="month">一个月内</option>
                                                </select>
                                            </div>
                                        </li>
                                        <li class="one" typ="radio" reqd="1">
                                            <label class="desc" style="margin:3px">消息列表 </label>
                                            <div class="content" id="messagelist">
                                                
                                            </div>
                                        </li>
                                        <li style="display:none; margin-top:20px" id="querymanager"><div style="text-align:right"><a href="javascript:void(0)"><input class="btn-create commonbtn" id="btnSubmit" type="button" value="查询" onClick="showDetail(this)"></a></div></li>
                                    </ul>
                                </form>
                            </div>
                            <div id="messageinfoC" class="container" style="display:none;">
                            	<form class="form">
                                    <div class="info" id="formHeader">
                                        
                                    </div>
                                    <div class="content" style="text-align:left; font-size:14px; width:100%" id="messagecontent">
                                    	
                                    </div>
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
    <!-- sweetalert -->
    <script type="text/javascript" src="<%=basePath%>js/sweetalert/sweetalert.min.js"></script>
    <!-- base64 -->
    <script type="text/javascript" src="<%=basePath%>js/base64/base64.js"></script>
    <!-- page -->
    <script>
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/base.js?"+Math.random()+"'></scr"+"ipt>");
	    document.write("<s"+"cript type='text/javascript' src='<%=basePath%>js/page/message/message.js?"+Math.random()+"'></scr"+"ipt>"); 
	</script>
    <script type="text/javascript">
		 $(function() {
			basePath = "<%=basePath %>";
			searchMessage();
			//模块尺寸
			$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		  });
	</script>
  </body>
</html>
