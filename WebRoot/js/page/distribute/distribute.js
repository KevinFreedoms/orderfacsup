/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化配送日期xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function initDate(){
	var temp = new Base64();
	str = window.localStorage.getItem("distributebase")||"";
	if(str!=""){
		str = temp.decode(str); 
		baseinfo =JSON.parse(str);
		$('#deliverydate').val(baseinfo.deliveryDate);
	}else{
		$('#deliverydate').val(getNowFormatDate());
	}
	initDistributeOrder();
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化订单xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
var bookingId = "";
var orgId = "";
var orgName = "";
var deliveryTaskName = ""; //配送作业名称
var status = 0; //订单状态 
var baseinfo={};  //存储基础信息
function getDistributeDate(){
	$('#deliverydate').val(getNowFormatDate());
	var picker = new Pikaday(
    {
        field: document.getElementById('deliverydate'),
        firstDay: 1,
        minDate: new Date('2000-01-01'),
        maxDate: new Date('2020-12-31'),
        yearRange: [2000,2020],
		onSelect: 	function() {
			//显示配送收货
			initDistributeOrder();
		}
    });
}

function initDistributeOrder(){
	var temp = new Base64();
	str = temp.decode(window.localStorage.getItem("moulds"));  
	moulds =JSON.parse(str);
	if(moulds.length==0){
		errorMessage("获取网点信息失败~请稍后重试！");
		return false;
	}
	orgId = moulds[0].orgId;
	var orgid = temp.encode(moulds[0].orgId);
	var distributedate  = temp.encode($('#deliverydate').val());
	$.ajax({
		type: "post",
		url: basePath+"facorder/distribute/toListDistribute.do",
		data: {"orgid":orgid,"distributedate":distributedate},
		dataType: "json",
		timeout: 20000,  
		success: function (data) {
			if(data.success){
				var list = data.listCollect;
				if(list.length>0){
					orgName = list[0].orgName;
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
/* 配送汇总xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function movelist(list){
	str = "";
	$(".withoutdate").addClass('display');
	$(".delivery").removeClass('display');
	for(var i = 0;i<list.length;i++){
		var classinfo = "";
		if(list[i].status==2){
			statusinfo='已确定';
			classinfo='movesure'
		}else if(list[i].status==1){
			statusinfo='确认中';
			classinfo='movealerdy'
		}else if(list[i].status==0){
			statusinfo='未确认';
			classinfo='norecive'
		}
		str +='<li class="urgentlist">'+
			 '<div class="nav-body js-bgcolor">'+
			 '<div class="ordercode" style="margin-top:10px;">'+
			 '<span>订单号：</span><span>'+list[i].bookingId+'</span>'+
			 '<span class="'+classinfo+'" style="float:right">'+statusinfo+'</span>'+
			 '</div>'+
			 '<div class="orderinfo"><span>作业名称：</span><span>'+list[i].deliveryTaskName+'</span></div>'+
			 '<div class="orderinfo"><span>单品类别数：</span><span>'+list[i].disCount+'</span></div>'+
			 '<div class="orderinfo"><span>应收/实收：</span><span>'+list[i].ordercount+'/'+list[i].arrivecount+'</span></div>'+
			 '<div class="operateOrder"><button class="am-btn am-btn-default am-btn-sm" id="'+list[i].bookingId+'" name="'+list[i].deliveryTaskName+'" status="'+list[i].status+'" onClick="getDisDetial(this)">查看</button></div></div></li>';
	}
	$(".delivery").html(str);
}
function prolist(list){
	collect = "";
	for(var i in list){
		ordercount = list[i].ordercount;
		arrivecount = list[i].arrivecount;
		collect += '<tr ondblclick="getDisDetial(this)" name="'+list[i].deliveryTaskName+'" status="'+list[i].status+'" id="'+list[i].bookingId+'"><td>'+list[i].bookingId+'</td>'+
		'<td>'+list[i].deliveryTaskName+'</td>'+
		'<td>'+list[i].disCount+'</td>'+
		'<td>'+ordercount.toFixed(2)+'</td>'+
		'<td>'+arrivecount.toFixed(2)+'</td>';
		var flag = list[i].status;
		switch(flag){
		   case 0:
			   collect +='<td>'+'<input class="nosure" type=button value="未确认" onclick="getDisDetial(this)" id="'+list[i].bookingId+'" name="'+list[i].deliveryTaskName+'" status="'+list[i].status+'">'+'</td></tr>';
			   break;
		   case 1:
			   collect +='<td>'+'<input class="sure" type=button value="确认中" onclick="getDisDetial(this)" id="'+list[i].bookingId+'" name="'+list[i].deliveryTaskName+'" status="'+list[i].status+'">'+'</td></tr>';
			   break;
		   case 2:
			   collect +='<td>'+'<input class="sure" type=button value="已确认" onclick="getDisDetial(this)" id="'+list[i].bookingId+'" name="'+list[i].deliveryTaskName+'" status="'+list[i].status+'">'+'</td></tr>';
			   break;
		}

	}
	$('#goodscontent').html(collect);
	$('li.three').attr("style","display:none;");
	$("#searchBtn").show();
	$("#kingTable").show();
	initPagination("#kingTable",10);
	$(".collect").show();
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 配送明细xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function getDisDetial(obj){
	bookingId = $(obj).attr("id");
	deliveryTaskName = $(obj).attr("name");
	status = Number($(obj).attr("status"));
	//存储基础资料
	baseinfo.deliveryDate = $('#deliverydate').val();
	baseinfo.deliveryTaskName = deliveryTaskName;
	baseinfo.bookingId = bookingId;
	baseinfo.orgId = orgId;
	baseinfo.status = status;
	var temp = new Base64();
	var baseinfostr = temp.encode(JSON.stringify(baseinfo));
	localStorage.removeItem("distributebase");
	window.localStorage.setItem("distributebase",baseinfostr);
	//清空历史订单数据
	phoneG["details"] = {};
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	$.ajax({
		type:"post",
		url: basePath+"facorder/distribute/toDistributeDetail.do",
		data:{"bookingid":bookingId},
		dataType:"json",
		timeout: 20000,  
		success:function(data){
			if(data.success){
				var listDetail = data.listDetail;
				//直送存储
				phoneG["details"] = phoneG["details"]||{};
				phoneG.details = listDetail;
				if(window.localStorage){
				   localStorage["phone"] = JSON.stringify(phoneG);
				}
				if(isPC()){
					proinfo(listDetail);
				}else{
					window.location.href='facorder/distribute/detail.do';
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
function proinfo(listDetail){
	date  = $('#deliverydate').val();
	//拼接表头
	var headcontent = '订单号:'+listDetail[0].bookingId+'  作业名称:'+
                      deliveryTaskName+'  配送时间:'+date+' 单品种类:'+listDetail.length;
	$('#orderheadinfo').html(headcontent);
	detail = "";
	for(var i=0;i < listDetail.length;i++){
		detail+="<tr align='center'>"+
				'<td>'+listDetail[i].goodsName+'</td>'+
				'<td>'+listDetail[i].packingName+'</td>'+
				'<td>'+listDetail[i].standards+'</td>'+
				'<td>'+listDetail[i].taxprice.toFixed(2)+'</td>'+
				'<td>'+listDetail[i].ordercount.toFixed(2)+'</td>';
	   if(status==0){
		   ls = listDetail[i].arrivecount;
		   detail += "<td>"+
				"<div class='goods-item'>"+
				"<a href='javascript:void(0);' class='decrease' onclick='changeNum(this)'>"+
				"<i class='icon-minus'>-</i>"+
				"</a><input class='number' type='text' value='" + ls.toFixed(2) + "' name='F1_goods' id='" + listDetail[i].goodsId + "'>"+
				'<input type="hidden" class="js-primary-key" value="' + listDetail[i].goodsId + '">'+
				"<a href='javascript:void(0);' class='increase' onclick='changeNum(this)'><i class='icon-plus'>+</i>"+
				"</a></div>"+
				"</td>"+
				"<td onclick='mark(this)' data-id="+listDetail[i].goodsId+" value ="+listDetail[i].goodsName+" style='cursor:pointer;'>"+listDetail[i].ref3+"</td></tr>";
	   }else{
		   detail +=  "<td><div class='goods-item'>"+ listDetail[i].arrivecount.toFixed(2) + "</div></td>"+
				           "<td>"+listDetail[i].ref3+"</td></tr>";
	   }
	}
	if(status==0){
		$("#arriveBtnDiv").attr("style","display:block;");
	}else{
		$("#arriveBtnDiv").attr("style","display:none;");
		
	}
	$("#goodsdetail").html(detail);
	initPagination("#detailTable",10);
	$("#delmanager").attr("style","display:none;");
	$("#ordermanager").attr("style","display:block;");
	$('#goodsdetail').find("tr").each(function (index) {
		bindPlusMinusEvent(this);
	});
	$('#summoney').html(countPrice());
}
function moveinfo(list){
	var temp = new Base64();
	str = temp.decode(window.localStorage.getItem("distributebase"));  
	baseinfo =JSON.parse(str);
	bookingId = baseinfo.bookingId;
	deliveryTaskName = baseinfo.deliveryTaskName;
	orgId = baseinfo.orgId;
	distributedate = baseinfo.deliveryDate
	status = Number(baseinfo.status);
	var headcontent = '<li><label>订单号：</label><div class="orderinfo">'+bookingId+'</div></li>'+
                      '<li><label>作业编号：</label><div class="orderinfo">'+deliveryTaskName+'</div></li>'+
					  '<li><label>配送日期：</label><div class="orderinfo">'+distributedate+'</div></li>';
	$('#conditionList').html(headcontent);
	//拼接明细
	str="";
	for(var i = 0;i<list.length;i++){
		price = list[i].taxprice;
		taxprice = price==0?"":price.toFixed(2);
		str +='<li><div class="goodsname">'+list[i].goodsName+'</div>'+
              '<div class="secondcolor stand">'+
              '<span><span>单位：</span><span>'+list[i].packingName+'</span></span>'+
			  '<span><span> 规格：</span><span>'+list[i].standards+'</span></span>'+
              '<span class="pull_right oughtrecive">应收：'+list[i].ordercount+'</span></div>'+
              '<div><div class="pull_left ordergoodprice price">￥'+taxprice+'</div>';
		ls = list[i].arrivecount;
		if(status>0){
			flag = true;
			str +='<div  class="pull_right factrecive"><span>实收：'+ls.toFixed(2)+'</span></div>';
		}else{
			str +='<div class="pull_right inputnumber">'+
				  '<a class="decrease pull_left" onclick="changeNum(this)">'+
				  '<img src="'+basePath+'images/list_btn_del.png"></a>'+
				  '<input type="hidden" class="js-primary-key" value="'+list[i].goodsId+'"><input type="hidden" class="js-flag" value="0">'+
				  '<input class="pull_left enternumber number" data="'+list[i].goodsId+'" value="'+ ls.toFixed(2)+'">'+
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
	if(status>0){
		footcontent = '<div class="pull_left total alerychosepro" style="width:100%" ><span class="countprice">合计金额：<span id="summoney">0.00</span></span></div>';
	}else{
		footcontent = '<div class="pull_left total alerychosepro"><span class="countprice">合计金额：<span id="summoney">0.00</span></span></div>'+
			'<div class="pull_right sure js-dealC createbtnlist">确定</div>';
	}
	$('#footcontent').html(footcontent);
	$('#summoney').html(countPrice());
	$(".js-dealC").on("click",function(){
		swal({
		  type: "warning",
		  showCancelButton: true,
		  title: "提示",
		  text: '实收确认后将不可修改，是否继续',
		  confirmButtonText: "确认",   
		  cancelButtonText: "取消",   
		  closeOnConfirm: false,   
		  closeOnCancel: true,
		  confirmButtonColor: "#039"
		  },function(isConfirm){   
			  if(isConfirm){
				 getArriveNum();
			  }
	  });
	});
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
		$("#distributeGoodsName").html(goodsName);
		var remark = "<textarea style='width:95%; padding:10px;' placeholder='请填写备注' data-class="+goodsId+">"+content+"</textarea>";
		$("#distributeremark").html(remark);
		$("#remark").modal('show');
	}else{
		
	}
}
$("#Pcsure").click(function(){
	content = $("#distributeremark").children().val();
	id = $("#distributeremark").children().attr("data-class");
	index = getIndex(id);
	if(phoneG.details[index]){
		phoneG.details[index].ref3=content;
	}
	$(markobj).html(content);
	$("#remark").modal('hide');
});
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 计算金额数量 xxx 2016-03-30 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function countPrice(){
	var price = 0;
	for(var i in phoneG.details){
		price += Number(phoneG.details[i].taxprice)*phoneG.details[i].arrivecount;
	}
	price = price.toFixed(2);
	return price;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 单品数量增减 xxx 2017-01-28*/
/* ------------------------------------------------------------------------------------------------------------------------ */
//获取单品所在位置
function getIndex(_id){
	var indexList = phoneG.details;
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
	goodsinfo = phoneG.details[index];
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
	if(phoneG.details[index]){
		phoneG.details[index].arrivecount = newVal;
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
	for(var i in phoneG.details){
		remark = phoneG.details[i].ref3;
		remark = remark==""?" ":remark;
		info+=phoneG.details[i].goodsId+ '_' +phoneG.details[i].arrivecount+ '_' +remark+',';
	}
	strBase = new Base64();
	$.ajax({
		type:"post",
		url:basePath+"facorder/distribute/toChangeArriveCount.do",
		data:{"info":strBase.encode(info),"bookingId":bookingId,"org":orgId},
		dataType:"json",
		success:function(data){
			if(data.success){
				status = 1;
				swal({   
					title: "提示",
					text: data.info+"感谢您的使用",
					showCancelButton: false,
					confirmButtonColor: "#039",
				},function(isConfirm){
					if(isPC()){
						proinfo(phoneG.details);
					}else{
						baseinfo.status = 1;
						var temp = new Base64();
						var baseinfostr = temp.encode(JSON.stringify(baseinfo));
						localStorage.removeItem("distributebase");
						window.localStorage.setItem("distributebase",baseinfostr);
						moveinfo(phoneG.details);
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
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 打印  */
/* ------------------------------------------------------------------------------------------------------------------------ */
var objWin;
function printOrder(obj){
	temp = new Base64();
	var date  = $('#deliverydate').val();
	var bookingid = temp.encode(bookingId);
	var orgid = temp.encode(orgId);
	var disdate = temp.encode(date);
	var tempstatus = temp.encode(status);
	var target = basePath+"facorder/distribute/toPrintList.do?id="+bookingid+'&org='+orgid+"&date="+disdate+"&status="+tempstatus;  
    //判断是否打开  
    if (objWin == null || objWin.closed) {  
       objWin = window.open(target);  
    } else {  
       objWin.location.replace(target);  
    }   
}
//返回
function toBack(){
	$('#searchTxt').val('');
	//清空历史订单数据
	phoneG["details"] = {};
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	if(isPC()){
		$('#delmanager').show();
		$('#ordermanager').hide();
		//模块尺寸
		$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
		initDistributeOrder();
	}else{
		window.location.href='facorder/distribute/toDistribute.do';
	}
};
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 导出execl 2018-01-29 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function distributeExportExcel(obj){
	$("#exporttable").hide();
	var arrayDetail=phoneG.details;
	if(arrayDetail.length>0){
		exportOrder(arrayDetail,status);
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
function exportOrder(array,status){
	html = "";
	amount = 0;
	money = 0;
	html+='<table id="exporttable">'+
    '<thead><tr></tr><tr>'+
    '<th></th>'+
    '<th></th>'+
    '<th></th>'+
    '<th></th>'+
    '<th style="font-size:20px;">配送收货</th></tr>'+
    '</thead>';
	//jiaself 2017-05-24
	var temp = "";
	if(status==2){
		temp +=  "<td style='font-size:18px;'><td>订单状态:<td>"+ '已确认' + "</td></td><br/>";
	}else if(status==1){
		temp +=  "<td style='font-size:18px;'><td>订单状态:<td>"+ '确认中' + "</td></td><br/>";
	}else{
		temp +=  "<td style='font-size:18px;'><td>订单状态:<td>"+ '未确认' + "</td></td><br/>";
	}
	deliveryDate = $('#deliverydate').val()
	html+=
	'<tr>'+temp+
	'<td></td><td></td><td></td><td></td><td></td>'+
	'<td>'+"单号:"+bookingId+'</td>'+
	'</tr>'+
    '<tr><td></td><td>作业名称:</td><td>'+deliveryTaskName+'</td>'+
    '<tr><td></td><td>网点名称:</td><td>'+orgName+'</td>'+
    '<tr><td></td><td>供货时间:</td><td>'+deliveryDate.substring(0,10)+'</td></tr>'+'<tr></tr>';
	
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
		var price=array[i].taxprice;
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
		var sumPrice=array[i].taxprice*array[i].arrivecount;
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
            '</tr></tbody>';
            
	html+='<tr></tr><tr>'+
    '<td></td><td>收货人:</td>'+
    '<td></td><td>送货人:</td>'+
    '</tr>'+
    '</tbody>'+
    '</table>';
	$("#exporttable").prepend(html);
		
}