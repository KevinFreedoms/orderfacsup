/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化配送日期xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function initDate(){
	var temp = new Base64();
	str = window.localStorage.getItem("deliverybase")||"";
	if(str!=""){
		str = temp.decode(str); 
		baseinfo =JSON.parse(str);
		$('#deliverydate').val(baseinfo.deliveryDate);
	}else{
		$('#deliverydate').val(getNowFormatDate());
	}
	initDeliveryOrder();
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化订单xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
var flag = false; //判断订单是否已经完成
var baseinfo={};  //存储基础信息
var bookingId = "";
var orgId = "";
var supplierName ="";
function initDeliveryOrder(){
	var temp = new Base64();
	str = temp.decode(window.localStorage.getItem("moulds"));  
	moulds =JSON.parse(str);
	if(moulds.length==0){
		errorMessage("获取网点信息失败~请稍后重试！");
		return false;
	}
	var username = moulds[0].orgId;
	var date  = $('#deliverydate').val();
	var status=''
	$.ajax({
		type: "post",
		url: basePath+"facorder/delivery/toListDelivery.do",
		data: {"username":username,"date":date},
		dataType: "json",
		timeout: 20000,  
		success: function (data) {
			if(data.success){
				var list =  data.listSuppler;
				if(list.length>0){
					if(isPC()){ 
						prolist(list);
					}else{
						movelist(list);
					}
				}else{
					if(isPC()){
						$('li.three').attr("style","display:block;");
						$('li.three').html(data.info);
						$("#searchBtn").hide();
						$("#kingTable").hide();
						$("#Pagination").hide();
						$('div.page').attr("style","display: none;");
					}else{
						$(".withoutdate").removeClass('display');
						$(".delivery").addClass('display');
					}
				}
			}else{
				if(isPC()){
					$('li.three').attr("style","display:block;");
					$('li.three').html(data.info);
					$("#searchBtn").hide();
					$("#kingTable").hide();
					$("#Pagination").hide();
					$('div.page').attr("style","display: none;");
				}else{
					$(".withoutdate").removeClass('display');
					$(".delivery").addClass('display');
				}
			}
		},
		error: function (error){
			errorMessage("数据传输错误！");
		}		
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 直送汇总xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function movelist(list){
	str = "";
	$(".withoutdate").addClass('display');
	$(".delivery").removeClass('display');
	for(var i = 0;i<list.length;i++){
		var classinfo = "";
		if(list[i].status==2){
			status='确认';
			classinfo='movesure'
		}else if(list[i].status==1){
			status='已收';
			classinfo='movealerdy'
		}else if(list[i].status==0){
			status='未收';
			classinfo='withourecive'
		}
		str +='<li class="urgentlist">'+
			 '<div class="nav-body js-bgcolor">'+
			 '<div class="ordercode" style="margin-top:10px;">'+
			 '<span>订单号：</span><span>'+list[i].bookingId+'</span>'+
			 '<span class="'+classinfo+'" style="float:right">'+status+'</span>'+
			 '</div>'+
			 '<div class="orderinfo"><span>供应商：</span><span>'+list[i].supplierName+'</span></div>'+
			 '<div class="orderinfo"><span>单品类别数：</span><span>'+list[i].goodsCategoryNum+'</span></div>'+
			 '<div class="orderinfo"><span>应收/实收：</span><span>'+list[i].ordercount+'/'+list[i].arrivecount+'</span></div>'+
			 '<div class="operateOrder"><button class="am-btn am-btn-default am-btn-sm" org="'+list[i].orgId+'" id="'+list[i].bookingId+'" onClick="getSupplierDetial(this)">查看</button></div></div></li>';
	}
	$(".delivery").html(str);
}

function prolist(list){
	str = "";
	for(var i in list){

		ordercount = list[i].ordercount;
		arrivecount = list[i].arrivecount;
		str += '<tr ondblclick="getSupplierDetial(this)" org="'+list[i].orgId+'" id="'+list[i].bookingId+'"><td>'+list[i].bookingId+'</td>'+
		'<td>'+list[i].supplierName+'</td>'+
		'<td>'+list[i].goodsCategoryNum+'</td>'+
		'<td>'+ordercount.toFixed(2)+'</td>'+
		'<td>'+arrivecount.toFixed(2)+'</td>';

		if(list[i].status==2){
			str += '<td><input type="button" class="alerdy" value="确认" onclick="getSupplierDetial(this)" org="'+list[i].orgId+'" id="'+list[i].bookingId+'"></td>'+
				   '</tr>';
		} else if(list[i].status==1){
			str += '<td><input type="button" class="alerdy" value="已收 " onclick="getSupplierDetial(this)" org="'+list[i].orgId+'" id="'+list[i].bookingId+'"></td>'+
				   '</tr>';
		}else if(list[i].status==0){
			str += '<td><input type="button" class="unrecive" value="未收" onclick="getSupplierDetial(this)" org="'+list[i].orgId+'" id="'+list[i].bookingId+'"></td>'+
				   '</tr>';
		}

	}
	$('#goodscontent').html(str);
	$('li.three').attr("style","display:none;");
	$("#searchBtn").show();
	$("#kingTable").show();
	initPagination("#kingTable",10);
	$(".collect").show();
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 直送订单明细xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */   
function getSupplierDetial(obj){
	var date  = $('#deliverydate').val();
	bookingId = $(obj).attr("id");
	orgId = $(obj).attr("org");
	
	//存储基础资料
	baseinfo.deliveryDate = date;
	baseinfo.bookingId = bookingId;
	baseinfo.orgId = orgId;
	var temp = new Base64();
	var baseinfostr = temp.encode(JSON.stringify(baseinfo));
	localStorage.removeItem("deliverybase");
	window.localStorage.setItem("deliverybase",baseinfostr);
	
	//清空历史订单数据
	phoneG["order"] = {};
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	$.ajax({
		type:"post",
		url: basePath+"facorder/delivery/toSupplierDetail.do",
		data:{"bookingid":bookingId,"date":date,"org":orgId},
		dataType:"json",
		timeout: 20000,  
		success:function(data){
			if(data.success){
				var listDetail = data.list;
				//直送存储
				phoneG["order"] = phoneG["order"]||{};
				phoneG.order = listDetail;
				if(window.localStorage){
				   localStorage["phone"] = JSON.stringify(phoneG);
				}
				if(isPC()){
					proinfo(listDetail);
				}else{
					window.location.href='facorder/delivery/detail.do';
				}
			}else{
				errorMessage(data.info);
			}
		},
		error: function (error){
			errorMessage("数据传输错误！");
		}
	});
}
function moveinfo(list){
	//设置汇总信息
	bookingId = list[0].bookingId;
	orgId = list[0].orgId;
	var headcontent = '<li><label>订单号：</label><div class="orderinfo">'+list[0].bookingId+'</div></li>'+
                      '<li><label>供应商：</label><div class="orderinfo">'+list[0].supplierName+'</div></li>'+
					  '<li><label>配送时间：</label><div class="orderinfo">'+list[0].deliveryDate.substring(0,10)+'</div></li>';
	$('#conditionList').html(headcontent);
	//拼接明细
	str="";
	for(var i = 0;i<list.length;i++){
		price = list[i].deliverPrice;
		deliverPrice = price==0?"":price.toFixed(2);
		str +='<li><div class="goodsname">'+list[i].goodsName+'</div>'+
              '<div class="secondcolor stand">'+
              '<span><span>单位：</span><span>'+list[i].packingName+'</span></span>'+
			  '<span><span> 规格：</span><span>'+list[i].standards+'</span></span>'+
              '<span class="pull_right oughtrecive">应收：'+list[i].ordercount+'</span></div><div class="clearfloat"></div>'+
              '<div><div class="pull_left ordergoodprice price">￥'+deliverPrice+'</div>';
		ls = list[i].arrivecount;
		if(Number(list[i].status)==2){
			flag = true;
			str +='<div  class="pull_right factrecive"><span>实收：'+ls.toFixed(2)+'</span></div>';
		}else{
			str +='<div class="pull_right inputnumber">'+
				  '<a class="decrease pull_left" onclick="changeNum(this)">'+
				  '<img src="'+basePath+'images/list_btn_del.png"></a>'+
				  '<input type="hidden" class="js-primary-key" value="'+list[i].goodsId+'"><input type="hidden" class="js-flag" value="0">'+
				  '<input class="pull_left enternumber number" data="'+list[i].goodsId+'" value="' + ls.toFixed(2) + '">'+
				  '<a class="increase pull_left" onclick="changeNum(this)"><img src="images/list_btn_add.png"></a>';
		}
        str +='</div><div class="clearfloat"></div></div></li>';
	}
	$('#deliveryList').html(str);
	$('#deliveryList').find("li").each(function (index) {
		bindPlusMinusEvent(this);
	});
	//按钮
	footcontent = "";
	if(Number(list[0].status)==2){
		footcontent = '<div class="pull_left total alerychosepro" style="width:100%" ><span class="countprice">合计金额：<span id="summoney">0.00</span></span></div>';
	}else{
		footcontent = '<div class="pull_left total alerychosepro"><span class="countprice">合计金额：<span id="summoney">0.00</span></span></div>'+
			'<div class="pull_right sure js-dealC createbtnlist" onclick="getArriveNum()">确定</div>';
	}
	$('#footcontent').html(footcontent);
	$('#summoney').html(countPrice());
}
function proinfo(listDetail){
	remark = listDetail[0].remark;
	date  = $('#deliverydate').val();
	date = remark==""? date:date+" "+remark;
	//拼接表头
	var headcontent = '订单号:'+listDetail[0].bookingId+'  供应商:'+
                      listDetail[0].supplierName+'  配送时间:'+date+' 单品种类:'+listDetail.length;
	$('#orderheadinfo').html(headcontent);
	str ="";
	//拼接明细
	for(var i = 0;i<listDetail.length;i++){
		price = listDetail[i].deliverPrice;
		deliverPrice = price==0?"":price.toFixed(2);
		str += "<tr align='center'>"+
			   "<td>"+listDetail[i].goodsName+"</td>"+
			   "<td>"+listDetail[i].packingName+"</td>"+
			   "<td>"+listDetail[i].standards+"</td>"+
			   "<td>"+deliverPrice+"</td>"+
			   "<td>"+listDetail[i].ordercount+"</td>";
		if(Number(listDetail[i].status)==2){
			flag = true;
			str +=  "<td><div class='goods-item'>"+ listDetail[i].arrivecount + "</div></td>"+
				    "<td>"+listDetail[i].remark+"</td></tr>";
			
		}else if(Number(listDetail[i].status)==0 || Number(listDetail[i].status)==1){
			ls = listDetail[i].arrivecount;
			str += "<td>"+
				  "<div class='goods-item'>"+
				  "<a href='javascript:void(0);' class='decrease' onclick='changeNum(this)'>"+
				  "<i class='icon-minus'>-</i>"+
				  "</a><input class='number' type='text' value='" + ls.toFixed(2) + "' name='F1_goods' id='" + listDetail[i].goodsId + "'>"+
				  '<input type="hidden" class="js-primary-key" value="' + listDetail[i].goodsId + '">'+
				  "<a href='javascript:void(0);' class='increase' onclick='changeNum(this)'><i class='icon-plus'>+</i>"+
				  "</a></div></td>"+
				  "<td onclick='mark(this)' data-id="+listDetail[i].goodsId+" value ="+listDetail[i].goodsName+" style='cursor:pointer;' class='icon-edit'>"+listDetail[i].remark+"</td></tr>";
		}
	}
	if(Number(listDetail[0].status)==2){
		$("#arriveBtnDiv").attr("style","display:none;");
	}else{
		$("#arriveBtnDiv").attr("style","display:block;");
	}
	$("#goodsdetail").html(str);
	initPagination("#detailTable",10);
	$("#delmanager").attr("style","display:none;");
	$("#ordermanager").attr("style","display:block;");
	$('#goodsdetail').find("tr").each(function (index) {
		bindPlusMinusEvent(this);
	});
	$('#summoney').html(countPrice());
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 备注 xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
var markobj; //备注弹出对象
function mark(obj){
	if(isPC()){
		markobj = obj;
		content = $(obj).html();
		goodsId=$(obj).attr("data-id");
		var goodsName = $(obj).attr("value");
		$("#deliveryGoodsName").html(goodsName);
		var remark = "<textarea style='width:95%; padding:10px;' placeholder='请填写备注' data-class="+goodsId+">"+content+"</textarea>";
		$("#deliveryremark").html(remark);
		$("#remark").modal('show');
	}else{
		
	}
}
$("#Pcsure").click(function(){
	content = $("#deliveryremark").children().val();
	id = $("#deliveryremark").children().attr("data-class");
	index = getIndex(id);
	if(phoneG.order[index]){
		phoneG.order[index].remark=content;
	}
	$(markobj).html(content);
	$("#remark").modal('hide');
});
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 计算金额数量 xxx 2016-03-30 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function countPrice(){
	var price = 0;
	for(var i in phoneG.order){
		price += Number(phoneG.order[i].deliverPrice)*phoneG.order[i].arrivecount;
	}
	price = price.toFixed(2);
	return price;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 单品数量增减 xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
//获取单品所在位置
function getIndex(_id){
	var indexList = phoneG.order;
	for(var i in indexList){
		if(indexList[i].goodsId===_id){
			return i;
		}
	}
}
function bindPlusMinusEvent(t){
	$tr = $(t);
	$tr.find(".number").keypress(function(t) {
		var i = t.keyCode;
		i = String.fromCharCode(i);
	}).click(function(i) {
			count = $(this).val();
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			parent = $(this).parent().parent();
			$(this).val(newVal.toFixed(2));
			$(this).select();
			refreshSITotlePriceAndCount(parent,newVal);
		}).change(function() {
			count = $(this).val();
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			parent = $(this).parent().parent();
			$(this).val(newVal.toFixed(2));
			refreshSITotlePriceAndCount(parent,newVal);
		}).focus(function() {
			count = $(this).val();
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			parent = $(this).parent().parent();
			$(this).val(newVal.toFixed(2));
			refreshSITotlePriceAndCount(parent,newVal);
		}).blur(function() {
			count = $(this).val();
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			parent = $(this).parent().parent();
			$(this).val(newVal.toFixed(2));
			refreshSITotlePriceAndCount(parent,newVal);
	});
};
function changeNum(obj){
	var obj_class = $(obj).attr("class");
	var parent = $(obj).parent().parent();
	var js_count = parent.find(".number");
	count = window.parseFloat(js_count.val());
	var newVal = 0;
	if(obj_class.indexOf("decrease")>=0){
		//减少
		newVal = count > 1 ? count - 1:0;
	}else if(obj_class.indexOf("increase")>=0){
		//增加
		newVal = 0 > count ? 0:count + 1;
	}
	js_count.val(newVal.toFixed(2));
	refreshSITotlePriceAndCount(parent,newVal);
}

function inputnumber(obj){
	parent = $(obj).parent().parent();
	index = parent.find('.js-primary-key').val();
	index = getIndex(index);
	goodsinfo = phoneG.order[index];
	$("#prompt").find(".am-modal-hd").html(goodsinfo.goodsName);
	$("#prompt").find(".lsdw").html(goodsinfo.standards);
	$("#prompt").find(".am-modal-prompt-input").val("");
	$("#prompt").modal({
        relatedTarget: this,
        onConfirm: function(e) {
			count = e.data;
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			js_count = parent.find(".number");
			js_count.html(newVal.toFixed(2));
			refreshSITotlePriceAndCount(parent,newVal);
			parent.parent().animate({"scrollTop":0},500);
        },
     });
	$(".am-dimmer").css("z-index","1240");
	$(".am-header-default").css("z-index","1220");
}

//刷新单品数量
function refreshSITotlePriceAndCount(t,newVal) {
	id = t.find('.js-primary-key').val();
	index = getIndex(id);
	if(phoneG.order[index]){
		phoneG.order[index].arrivecount = newVal;
	}
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	$('#summoney').html(countPrice());
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 更新订单 xxx 2017-01-10 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function getArriveNum(){
	info = "";
	for(var i in phoneG.order){
		remark = phoneG.order[i].remark;
		remark = remark==""?" ":remark;
		info+=phoneG.order[i].goodsId+ '_' +phoneG.order[i].arrivecount+ '_' +remark+',';
	}
	strBase = new Base64();
	$.ajax({
		type:"post",
		url:basePath+"facorder/delivery/toChangeArrivecount.do",
		data:{"info":strBase.encode(info),"bookingId":bookingId,"org":orgId},
		dataType:"json",
		success:function(data){
			if(data.success){
				swal({   
					title: "提示",
					text: data.info+"感谢您的使用",
					showCancelButton: false,
					confirmButtonColor: "#039",
				},function(isConfirm){
					if(isPC()){
						proinfo(phoneG.order);
					}
				});
				
			}else{
				errorMessage(data.info);
			} 
		},
		error:function(error){
			errorMessage("数据传输错误！");
		}
	});
}
//返回
function toBack(){
	$('#searchTxt').val('');
	//清空历史订单数据
	phoneG["order"] = {};
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	if(isPC()){
		$('#delmanager').show();
		$('#ordermanager').hide();
		//模块尺寸
		$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		initDeliveryOrder();
	}else{
		window.location.href='facorder/manager/toDelivery.do';
	}
	
};
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 打印 2106-12-29 */
/* ------------------------------------------------------------------------------------------------------------------------ */
var objWin;
function printOrder(){
	temp = new Base64();
	var id = temp.encode(bookingId);
	var org = temp.encode(orgId);
	var target = basePath+"facorder/delivery/print.do?id="+id+'&org='+org;  
    //判断是否打开  
    if (objWin == null || objWin.closed) {  
       objWin = window.open(target);  
    } else {  
       objWin.location.replace(target);  
    }   
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 导出execl 2018-01-29 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function deliveryExportExcel(){
	$("#exporttable").hide()
	var array=phoneG.order;
	var bookingId=array[0].bookingId;
	if(array.length>0){
		exportOrder(array);
		$("#exporttable").tableExport({
			type:'excel',
			escape:'false', 
			tableName:bookingId,
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
	html = ""
	amount = 0;
	money = 0;
	html+='<table id="exporttable">'+
    '<thead><tr></tr><tr>'+
    '<th></th>'+
    '<th></th>'+
    '<th></th>'+
    '<th></th>'+
    '<th style="font-size:20px;">订货单</th></tr>'+
    '</thead>';
	
	html+=
	'<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>'+
	'<td>'+array[0].bookingId+'</td>'+
	'</tr>'+
    '<tr><td></td><td>供应商:</td><td>'+array[0].supplierName+'</td>'+
    '<tr><td></td><td>供货时间:</td><td>'+array[0].deliveryDate.substring(0,10)+'</td></tr>'+'<tr></tr>';
	
	html+= '<tr style="font-weight:bold;"><td></td>'+
           '<td style="border:1px solid black;">序号</td>'+
           '<td style="border:1px solid black;">编码</td>'+
           '<td style="border:1px solid black;">品名</td>'+
           '<td style="border:1px solid black;">规格</td>'+
           '<td style="border:1px solid black;">单价</td>'+
           '<td style="border:1px solid black;">单位</td>'+
           '<td style="border:1px solid black;">订货数量</td>'+
           '<td style="border:1px solid black;">实收数量</td>'+
           '</tr>';
	
	for (var i=0;i<array.length;i++)
	{
		var price=array[i].deliverPrice
		deliverPrice = price==0?"":price.toFixed(2);
		Num  = i+1;
		html += '<tbody><tr>'+
				'<td></td>'+
			    '<td style="border:1px solid black;">'+Num+'</td>'+
			  	'<td style="border:1px solid black;">'+array[i].goodsId+'</td>'+
			  	'<td style="border:1px solid black;">'+array[i].goodsName+'</td>'+
			  	'<td style="border:1px solid black;">'+array[i].standards+'</td>'+
			  	'<td style="border:1px solid black;">'+deliverPrice+'</td>'+
			  	'<td style="border:1px solid black;">'+array[i].packingName+'</td>'+
			  	'<td style="border:1px solid black;">'+array[i].ordercount.toFixed(2)+'</td>'+
			  	'<td style="border:1px solid black;">'+array[i].arrivecount.toFixed(2)+'</td>'+
			  	'</tr>';
		amount +=Number(array[i].ordercount);
		var sumPrice=array[i].deliverPrice*array[i].arrivecount
		money +=Number(sumPrice);
	}
	html += 
            '<tr>'+
            '<td></td>'+
			'<td style="border:1px solid black;">合计数量：<span id="amount">'+amount.toFixed(2)+'</span></td>'+
            '<td style="border:1px solid black;">合计金额：<span id="money">'+money.toFixed(2)+'</span>元</td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '<td style="border:1px solid black;"></td>'+
            '</tr></tbody>'
            
	html+='<tr></tr><tr>'+
    '<td></td><td>收货人:</td>'+
    '<td></td><td>送货人:</td>'+
    '</tr>'+
    '</tbody>'+
    '</table>';
	
	$("#exporttable").prepend(html)
		
}