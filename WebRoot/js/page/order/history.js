/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化历史订单 xxx 2016-04-06 */
/* ------------------------------------------------------------------------------------------------------------------------ */
bookingid = "";//订单编号
function initHisOrder(){
	enddate = $('#deliverydate').val();
	initOrderMould(enddate,enddate);
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 显示配送日期下的模板 xxx 2016-04-06 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function initOrderMould(startdate,enddate){
	temp = new Base64();
	str = temp.decode(window.localStorage.getItem("moulds"));  
	moulds =JSON.parse(str);
	orgid = temp.encode(moulds[0].orgId);
	info ="";
	$.ajax({
		type: "post",
		url: basePath+"facorder/bookinghis/toListOrderHis.do",
		data: {"orgid":orgid,"startdate":startdate,"enddate":enddate},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			if(data.success){
				if(data.totalrecord>0){
					orders = data.list;
					if(isPC()){
						$('#kingTable').show();
						$('.collect').show();
						$('#withoutdata').hide();
						proinfo(orders);
					}else{
						$('.withdata').removeClass('display');
						$('.withoutdate').addClass('display');
						moveinfo(orders);
					}
				}else{
					if(isPC()){
						$('#kingTable').hide();
						$('.collect').hide();
						$('#withoutdata').show();
					}else{
						$('.withdata').addClass('display');
						$('.withoutdate').removeClass('display');
					}
				}
			}else{
				swal({   
					title: "错误信息",
					text: data.info,
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		},
		error: function (request, status, err) {
			if (status == "timeout")
			{
				swal({   
					title: "错误信息",
					text: "请求超时，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}else{
				swal({   
					title: "错误信息",
					text: "获取失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		}
	});
}
function proinfo(_list){
	var content = "";
	for(var i in _list){
		type = _list[i].type;
		statusflag = "";
		if(Number(type)==1){
			statusflag = '<span style="color:#F00">紧急<span>';
		}else if(Number(type)==0){
			statusflag = '<span style="color:#339958">普通<span>';
		}
		content +='<tr data="'+_list[i].pk+'" onDblClick="toHisOrderInfoPc(this)">'+
                  '<td>'+statusflag+'</td>'+
				  '<td>'+_list[i].pk+'</td>'+
                  '<td>'+_list[i].mouldName+'</td>'+
                  '<td>'+_list[i].deliveryDate+'</td>'+
                  '<td>'+_list[i].ref1+'</td>'+
                  '<td>'+_list[i].ref2+'</td>'+
                  '<td>'+_list[i].createDate+'</td>'+
                  '<td>'+_list[i].lastupDate+'</td>'+
                  '</tr>';
	}
	$('.historylist').html(content);
	$('.Pagination').show();
	initPagination("#kingTable",10);
}
function moveinfo(_list){
	var content = '';
	for(var i in _list){
		type = _list[i].type;
		statusflag = "";
		if(Number(type)==1){
			statusflag = '<span style="color:#F00">紧急<span>';
		}else if(Number(type)==0){
			statusflag = '<span style="color:#339958">普通<span>';
		}
		content +='<li class="urgentlist">'+
         	      '<div class="ordercode" style="margin-top:10px;">'+
            	  '<span class="floatLeft">'+
                  '<span>单号：</span>'+
                  '<span>'+_list[i].pk+'</span>'+
                  '</span>'+
                  '<span class="status floatRight">'+statusflag+'</span>'+
                  '<div class="clearfloat"></div>'+
                  '</div>'+
                  '<div class="orderInfo">'+
                  '<span class="floatLeft">'+
                  '<span>类型：</span>'+
                  '<span>'+_list[i].mouldName+'</span>'+
                  '</span>'+

                  '<span class="floatRight ordernumber">x'+_list[i].ref1+'</span>'+
                  '<div class="clearfloat"></div>'+
                  '</div>'+
                  '<div class="orderInfo">'+
                  '<span class="floatLeft">'+
                  '<span>日期：</span>'+
                  '<span>'+_list[i].deliveryDate+'</span>'+
                  '</span>'+
                  '<span class="floatRight orderprice mark">￥'+_list[i].ref2+'</span>'+
                  '</div>'+
                  '<div class="clearfloat"></div>'+
                  '<div class="operateOrder">'+
                  '<button class="am-btn am-btn-default am-btn-sm modify" data="'+_list[i].pk+'" onclick="toHisOrderInfoM(this)">查看</button>'+
                  '</div>'+
   		          '</li>';
	}
	$('.withdata').html(content);
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 订单明细 xxx 2016-04-08 */
/* ------------------------------------------------------------------------------------------------------------------------ */
var collect={};
var orderlog={};
var goods  = [];
var bookingtype = 0;  //0：普通 1：紧急
function toHisOrderInfo(){
	temp = new Base64();	
	str = temp.decode(bigbata); 
	mainjson = JSON.parse(str);
	collect = mainjson.collect;		//汇总
	orderlog = mainjson.delivery;   //日志
	goods = mainjson.goods;			//单品
	
	bookingid = orderlog.pk;
	$('.orderId').html(bookingid);
	deliverywork = orderlog.mouldName
	$('.deliverywork').html(deliverywork);
	deliverydate = orderlog.deliveryDate;
	arrivalTime = mainjson.arrivalTime;
	deliverydate = new Date(deliverydate).Format("yyyy-MM-dd");
	$('.deliverydate').html(deliverydate + " " +arrivalTime + "前");
	
	//判断当前订单状态
	statuscontent = '',operateOrder="";
	status = orderlog.status;
	adjustState = orderlog.adjustState;
	bookingtype = orderlog.type;
	if(Number(status)==0){
		statuscontent = '<span class="notfinish">可修改</span>';
		operateOrder +='<button class="am-btn am-btn-default am-btn-sm modify" onclick="deleteorder()">取消订单</button>'+
                       '<button class="am-btn am-btn-primary am-btn-sm modify" onclick="modifyorder()">修改订单</button>';
	}else if(Number(status)==1){
		if(Number(bookingtype)==0){
			statuscontent = '<span class="finish">可修改</span>';
			if(Number(adjustState)==1){
				operateOrder +='<button class="am-btn am-btn-primary am-btn-sm modify" onclick="modifyorder()">调整订单</button>';
			}else{
				operateOrder +='<button class="am-btn am-btn-default am-btn-sm modify" onclick="deleteorder()">取消订单</button>'+
                				'<button class="am-btn am-btn-primary am-btn-sm modify" onclick="modifyorder()">修改订单</button>';
			}
		}else{
			statuscontent = '<span class="finish">已提交</span>';
		}
	}else if(Number(status)==5){
		if(Number(adjustState)==1){
			statuscontent = '<span class="finish">可调整</span>';
			operateOrder +='<button class="am-btn am-btn-primary am-btn-sm modify" onclick="modifyorder()">调整订单</button>';
		}else{
			statuscontent = '<span class="finish">已完成</span>';
		}
		
	}
	$('.bookingstatus').html(statuscontent);
	if(operateOrder!=""){
		$('.operateOrder').show();
		$('.operateOrder').html(operateOrder);
	}
	
	goods = mainjson.goods;
	detailcontent = "";
	for(var i in goods){
		detailcontent += '<li><span class="leftInfo" style="width:70%;float:left;">'+goods[i].goodsName+'</span>'+
						 '<span class="leftInfo" style="width:15%;float:left;text-align:left;color:#a09494;">x'+goods[i].count+'</span>'+
                         '<span class="rightInfo">￥'+goods[i].deliverPrice+'</span>'+
                         '<div class="clearfloat"></div></li>';
	}
	$('.detailList').html(detailcontent);
	
	footcontent = "";
	if(Number(bookingtype)==1&&Number(status)==0){
		footcontent +='<div class="pull_left total">'+
        	          '<span class="countprice sumcount" style="margin-right:10px;">单品总数:'+collect.ref1+'</span>'+
			          '<span class="countprice sumprice">总金额:'+collect.ref2+'</span>'+
		              '</div><div class="pull_right sure" onclick="sureorder()">提交</div>';
	}else{
		footcontent +='<div class="pull_left total" style="width:100%">'+
        	          '<span class="countprice sumcount" style="margin-right:10px;">单品总数：'+collect.ref1+'</span>'+
			          '<span class="countprice sumprice">总金额:'+collect.ref2+'</span>'+
		              '</div>';
	}
	$('#footcontent').html(footcontent);
}
function toHisOrderInfoM(obj){
	bookingid =  $(obj).attr("data");
	urlstr = basePath + "facorder/booking/info.do";
	$.StandardPost(urlstr,{"orderid":bookingid,"ishistory":1});
}
function toHisOrderInfoPc(obj){
	bookingid =  $(obj).attr("data");
	$('#pk').val(bookingid);
	title = "";
	content="";
	$.ajax({
		type: "post",
		url: basePath+"facorder/bookinghis/toShowOrderHis.do",
		data: {"orderid":bookingid},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {   
			if(data.success){
				collect = data.collect;		//汇总
				orderlog = data.delivery;   //日志
				goods = data.goods;			//单品
				deliverydate = orderlog.deliveryDate;
				deliverydate = new Date(deliverydate).Format("yyyy-MM-dd");
				orderheadinfo = "订单编号："+orderlog.pk+" 订货类型："+orderlog.mouldName+" 配送日期："+deliverydate;
				$('#summoney').html(collect.ref2);
				for(var i in goods){
					/*if(goods[i].goodsFlag>0){
						content += '<tr style="height:40px"><td>'+goods[i].goodsName+'<a style="margin:3px;position:relative;bottom:3px " href="javascript:void(0);" flag="'+goods[i].goodsFlag+'" id="'+goods[i].goodsId+'" onclick="showGoods(this)"><img src="'+basePath+'images/more.gif"</a></td>';
					}else{
						content += '<tr style="height:40px"><td>'+goods[i].goodsName+'</td>';
					}*/
					count = goods[i].count;
					deliverPrice = goods[i].deliverPrice;
					allMoney = Number(deliverPrice*count);
					content += '<tr><td>'+goods[i].goodsId+'</td>'+
					           '<td>'+goods[i].goodsName+'</td>'+ 
					           '<td>'+goods[i].packingName+'</td>'+
							   '<td>'+goods[i].standards+'</td>'+
							   '<td>'+deliverPrice.toFixed(2)+'</td>'+
							   '<td>'+count.toFixed(2)+'</td>'+
							   '<td>'+allMoney.toFixed(2)+'</td></tr>';
					
				}
				$("#orderheadinfo").html(orderheadinfo);
				$('.detaillist').html(content);
				initPagination("#kingTable2",10);
				$('#delmanager').hide();
				$('#ordermanager').show();
			}
		},
		error: function (request, status, err) {
    		if (status == "timeout")
			{
				swal({   
					title: "错误信息",
					text: "请求超时，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}else{
				swal({   
					title: "错误信息",
					text: "获取失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		}
	});
}
//订单返回
function toBack(){
	if(isPC()){
		$('#delmanager').show();
		$('#ordermanager').hide();
	}else{
		if(Number(ishistory)==1){
			location.href = basePath + "facorder/manager/toHistory.do";
			return false;
		}
		if(bookingtype==1){
			location.href = basePath + "facorder/urgent/urgentm.do";
		}else{
			location.href = basePath + "facorder/manager/toOrder.do";
		}
	}
	
};
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 手机端跳转 xxx 2017-01-17 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function modifyorder(){
	var urlstr = ""; //超大模板跳转
	var adjustStr = "";//调整订单跳转
	mouldType = 1;
	isAdjust =orderlog.adjustState;
	if(isPC()){
		urlstr = basePath + "facorder/bigMould/toPcBigMould.do";
		adjustStr = basePath + "facorder/booking/toPcAdjustGoodsDetail.do";
	}else{
		urlstr = basePath + "facorder/bigMould/toBigMould.do";
		adjustStr = basePath + "facorder/booking/toAdjustGoodsDetail.do";
	}
	//判断是否为调整订单
	var orderid = orderlog.pk;
	var mouldId =orderlog.mouldId;
	var orgid = orderlog.orgId;
	var deliverydate = orderlog.deliveryDate;
	deliverydate = new Date(deliverydate).Format("yyyy-MM-dd");
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/toBigPage.do",
		data: {"mouldType":mouldType,"mouldId":mouldId,"deliverydate":deliverydate,"orgid":orgid,"orderid":orderid,"flag":1},
		dataType: "json",
		timeout: 20000,
		success: function (data) { 
			if(data.success){
				//判断是否为调整订单
				if(isAdjust==1){
					$.StandardPost(adjustStr,{"mouldType":mouldType,"mouldId":mouldId,"deliverydate":deliverydate,"orgid":orgid,"orderid":orderid,"flag":1});
				}else{
					$.StandardPost(urlstr,{"mouldType":mouldType,"mouldId":mouldId,"deliverydate":deliverydate,"orgid":orgid,"orderid":orderid,"flag":1,"type":bookingtype});
				}
			}else{
				errorMessage(data.info);
			}
		 },
		error: function (request, status, err) {
			if (status == "timeout"){
				errorMessage("请求超时，请稍后重试~");
			}else{
				errorMessage("获取失败，请稍后重试~");
			}
		}
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 订单类操作 xxx 2017-01-22 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function doDeleteOrder(){
	strBase = new Base64();
	var orderid = orderlog.pk;
	var mouldId =strBase.encode(orderlog.mouldId);
	var orgid = strBase.encode(orderlog.orgId);
	var deliverydate = orderlog.deliveryDate;
	deliverydate = new Date(deliverydate).Format("yyyy-MM-dd");
	data = {
			"mouldid":mouldId,
			"deliverydate":deliverydate,
			"orgid":orgid,
			"bookingid":orderid
			};
	$.ajax({
		type: "post",
		url: basePath+"facorder/booking/doDeleteOrder.do",
		data:data,
		dataType: "json",
		timeout: 20000,
		beforeSend:function(){
			if(isPC()){
				$("body").showLoading();	
			}else{
				$("#loadingToast").show();
			} 
		},  
		success: function (data) {
			if(isPC()){
				$("body").hideLoading();	
			}else{
				$("#loadingToast").hide();
			}
			if (data.success) {
				swal({   
					title: "提示信息",
					text: "删除订单成功 感谢您的支持",
					showCancelButton: false,
					confirmButtonColor: "#039"
				},function(isConfirm){   
					if(isPC()){
						if(bookingtype==1){
							window.location = basePath + "facorder/urgent/urgentp.do";
						}else{
							window.location = basePath + "facorder/manager/toPcMain.do";
						}
					}else{
						if(bookingtype==1){
							location.href = basePath + "facorder/urgent/urgentm.do";
						}else{
							location.href = basePath + "facorder/manager/toOrder.do";
						}
					}
				});
			}else{
				swal({   
					title: "提示",
					text: data.info,
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		},
		error: function (request, status, err) {
			if(isPC()){
				$("body").hideLoading();	
			}else{
				$("#loadingToast").hide();
			}
			if (status == "timeout")
			{
				swal({   
					title: "提示",
					text: "请求超时，请稍后重试 ",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}else{
				swal({   
					title: "提示",
					text: "获取失败，请稍后重试 ",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		}
	});
}
function deleteorder(){
	swal({
	  type: "warning",
	  showCancelButton: true,
	  title: "提示",
	  text: '是否取消当前订单?',
	  confirmButtonText: "确认",   
	  cancelButtonText: "取消",   
	  closeOnConfirm: false,   
	  closeOnCancel: true,
	  confirmButtonColor: "#039"
	  },function(isConfirm){   
		  if(isConfirm){
			  doDeleteOrder();
		  }
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 打印 2016-12-29 */
/* ------------------------------------------------------------------------------------------------------------------------ */
var objWin;
function printOrder(){
	temp = new Base64();
	var bookingid = $('#pk').val();
	bookingid = temp.encode(bookingid);
	var target = basePath+"facorder/bookinghis/print.do?id="+bookingid;  
    //判断是否打开  
    if (objWin == null || objWin.closed) {  
       objWin = window.open(target);  
    } else {  
       objWin.location.replace(target);  
    }   
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 导出execl 2017-01-22 xxx 修改 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function historyOrderExportExcel(){
	$("#exporttable").hide();
	var orderId=$('#pk').val();
	if(goods.length>0){
		exportOrder(goods);
		$("#exporttable").tableExport({
			type:'excel',
			escape:'false', 
			tableName:orderId,
		});  
	}else{
		swal({
			title: '消息',
			text: "获取导出订单失败,请稍后重试~", 
			confirmButtonText:"确定"
		});
	}
	$("#exporttable").html("");
}


function exportOrder(array){
	html = "";
	deliverydate = orderlog.deliveryDate;
    deliverydate = new Date(deliverydate).Format("yyyy-MM-dd");
	html+='<table id="exporttable">'+
    '<thead><tr></tr><tr>'+
    '<th></th>'+
    '<th></th>'+
    '<th></th>'+
    '<th style="font-size:20px;">历史订货单</th>'+
    '<th></th></tr>'+
    '</thead>';
	html+='<tbody>'+
	'<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td>'+
	'<td>'+$('#pk').val()+'</td>'+
	'</tr>'+
    '<tr><td></td><td>供货时间:</td><td>'+deliverydate+'</td></tr>'+'<tr></tr>';
	
	html+= '<tr style="font-weight:bold;"><td></td>'+
           '<td style="border:1px solid black;">序号</td>'+
           '<td style="border:1px solid black;">编码</td>'+
           '<td style="border:1px solid black;">品名</td>'+
           '<td style="border:1px solid black;">规格</td>'+
           '<td style="border:1px solid black;">单位</td>'+
           '<td style="border:1px solid black;">单价</td>'+
           '<td style="border:1px solid black;">订货数量</td>'+
           '</tr>';

	for ( var i = 0; i < array.length; i++) {
		var Num=i+1;
		html += '<tr>'+
		'<td></td>'+
		'<td style="border:1px solid black;">'+Num+'</td>'+
		'<td style="border:1px solid black;">'+array[i].goodsId+'</td>'+
		'<td style="border:1px solid black;">'+array[i].goodsName+'</td>'+
		'<td style="border:1px solid black;">'+array[i].standards+'</td>'+
		'<td style="border:1px solid black;">'+array[i].packingName+'</td>'+
		'<td style="border:1px solid black;">'+array[i].deliverPrice.toFixed(2)+'</td>'+
		'<td style="border:1px solid black;">'+array[i].sumNumber.toFixed(2)+'</td>'+
		'</tr>';
	}
	amount = Number(collect.ref1);
	money  = Number(collect.ref2);
	html += '<tr>'+
            '<td></td>'+
			'<td style="border:1px solid black;">合计数量：<span id="amount">'+amount.toFixed(2)+'</span></td>'+
            '<td style="border:1px solid black;">预估金额：<span>'+money.toFixed(2)+'</span>元</td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '</tr></tbody>';
            
	html+='<tr></tr><tr>'+
    '<td></td><td>收货人:</td>'+
    '<td></td><td>送货人:</td>'+
    '</tr>'+
    '</tbody>'+
    '</table>';
	$("#exporttable").prepend(html);
}