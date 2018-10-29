/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化订单 xxx 2016-03-18 */
/* ------------------------------------------------------------------------------------------------------------------------ */
var moulds = [];//模板汇总
var dates = [];	//日期汇总
var mouldid = "";	//模板编码
var deliveryid = "";	//配送作业编码
var deliverydate  = ""; //配送日期
var orgid = "";			//网点编码
temp = new Base64();
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 显示配送作业下的模板 xxx 2016-03-18 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function initDelMoulds(){
	mouldsStr=window.localStorage.getItem("moulds")||"";
	if(mouldsStr==""){
		errorMessage("没有匹配的模板 请谅解！");
		return false;
	}
	str = temp.decode(window.localStorage.getItem("moulds"));  
	moulds =JSON.parse(str);
	if(moulds.length==0){
		errorMessage("没有匹配的模板 请谅解！");
		return false;
	}
	orgid = moulds[0].orgId;
	$.ajax({
		type: "post",
		url: basePath+"facorder/manager/toListDelMoulds.do",
		data: {"orgid":orgid},
		dataType: "json",
		timeout: 20000,  
		success: function (data) {
			if(data.success){
				moulds = data.moulds;
				if(isPC()){
					if(moulds.length>0){
						prolist();
					}else{
						alert("获取信息失败");
					}
				}else{
					if(moulds.length>0){
						movelist();
					}else{
						$("#mouldlist").html('<option id="0">请选择</option>');
					}
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
/* 拼接模板数据 xxx 2018-01-31 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function movelist(){
	var content  = "";
	for(var j in moulds){
		id = temp.encode(moulds[j].mouldId);
		del = temp.encode(moulds[j].deliverytaskId);
		content +='<option value="'+id+'" data="'+del+'">'+moulds[j].mouldName+'</option>';
	}
	$("#mouldlist").html(content);
	$("#mouldlist").change(function () {  
		mouldid = $(this).children('option:selected').val();  
		deliveryid = $(this).children('option:selected').attr("data");
		searchDeliveryDate(deliveryid,mouldid);
	});
	mouldid = temp.encode(moulds[0].mouldId);
	deliveryid = temp.encode(moulds[0].deliverytaskId);
	searchDeliveryDate(deliveryid,mouldid);
}
function prolist(){
	info = "";
	for(var j in moulds){
		id = temp.encode(moulds[j].mouldId);
		del = temp.encode(moulds[j].deliverytaskId);
		name = moulds[j].mouldName;
		info += '<span style="margin:5px">'+
					  '<div class="checkboxWrapper theme3 extraSmallCheckboxSize">'+
						  '<input type="radio" name="moudl" id="'+ id +'" value="'+ name+'" data="'+del+'" class="fld">'+
						  '<label for="'+ id +'">'+
						  '<i>'+
							  '<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="50px" height="50px" viewBox="0 0 50 50" enable-background="new 0 0 50 50" xml:space="preserve">'+
								  '<circle fill="none" stroke="#B7B7B7" stroke-width="3" stroke-miterlimit="10" cx="25.11" cy="24.883" r="23.519"></circle>'+
								  '<path fill="none" stroke-width="3" stroke-miterlimit="10" d="M48.659,25c0,12.998-10.537,23.534-23.534,23.534'+
								  ' S1.591,37.998,1.591,25S12.127,1.466,25.125,1.466c9.291,0,17.325,5.384,21.151,13.203L19,36l-9-14"></path>'+
							  '</svg>'+
						  '</i>'+ name +'</label>'+
					  '</div>'+
				'</span>';
	}
	$(".moulds").html(info);
	//pc端   2017-08-29
	$(".moulds").find("input").click(function(i) {
		mouldid = $(this).attr("id");
		deliveryid=$(this).attr("data");
		searchDeliveryDate(deliveryid,mouldid);
	});	
	//初始化
	dates = $($(".moulds").find("input")[0]);
	dates.click();	
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 获取一个星期的配送时间 xxx 2016-03-28 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function searchDeliveryDate(obj1,obj2){
	var content = "";
	$.ajax({
		type: "post",
		url: basePath+"facorder/manager/toListDelTime.do",
		data: {"deliveryid":obj1,"mouldid":obj2},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			if(data.success){
				if(data.totalrecord>0){
					dates = data.dates;
					if(dates.length>0){
						createDeliveryDate();
					}else{
						$("#datelist").html('<option id="0">请选择</option>');
					}
				}else{
					$("#datelist").html('<option id="0">请选择</option>');
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
function createDeliveryDate(){
	var content  = "";
	if(isPC()){
		for(var i in dates){
			content += '<span style="margin:5px">'+
					   '<div class="checkboxWrapper theme3 extraSmallCheckboxSize">'+
					   '<input type="radio" name="test" id="'+dates[i].deliverydate+'" value="'+dates[i].deliverydate+'">'+
					   '<label for="'+dates[i].deliverydate+'">'+
					   '<i>'+
					   '<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="50px" height="50px" viewBox="0 0 50 50" enable-background="new 0 0 50 50" xml:space="preserve">'+
						   '<circle fill="none" stroke="#B7B7B7" stroke-width="3" stroke-miterlimit="10" cx="25.11" cy="24.883" r="23.519"></circle>'+
						   '<path fill="none" stroke-width="3" stroke-miterlimit="10" d="M48.659,25c0,12.998-10.537,23.534-23.534,23.534'+
						   ' S1.591,37.998,1.591,25S12.127,1.466,25.125,1.466c9.291,0,17.325,5.384,21.151,13.203L19,36l-9-14"></path>'+
					   '</svg>'+
					   '</i>'+dates[i].deliverydate+'</label>'+
					   '</div>'+
					   '</span>';
		}
		$(".datelist").html(content);
		$(".datelist").find("input").click(function(i) {
			deliverydate = $(this).attr("id");
			$("#date").val(deliverydate);
			searchOrder();
		});
		//初始化
		datelistf = $($(".datelist").find("input")[0]);
		datelistf.click(); 
	}else{
		for(var i in dates){
			date = dates[i].deliverydate;
			content +='<option value="'+date+'">'+date+'</option>';
		}
		$("#datelist").html(content);
		$("#datelist").change(function () {  
			deliverydate = $(this).children('option:selected').val();
			searchOrder();
		});
		deliverydate = dates[0].deliverydate;
		searchOrder();
	}
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 查询订单列表 xxx 2016-03-22 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function searchOrder(){
	var data = {"model.orgId":orgid,"model.deliveryDate":deliverydate,"model.mouldId":temp.decode(mouldid)}
	$.ajax({
		type: "post",
		url: basePath+"facorder/booking/toListOrder.do",
		data: data,
		dataType: "json",
		timeout: 20000,
		beforeSend:function(){
			if(isPC()){
				$("body").showLoading();	
			}else{
				$("#loading").modal();
			} 
		},
		success: function (data) {
			if(isPC()){
				$("body").hideLoading();	
			}else{
				$("#loading").modal("close");
			}
			if(data.success){
				if(data.totalrecord>0){
					orders = data.list;
					if(isPC()){
						$('#orderlist').show();
						$('#createmanager').hide();
						proinfo(orders);
					}else{
						$('#entry').hide();
						$('.withoutdate').addClass('display');
						$('.ordercontent').removeClass('display');
						moveinfo(orders);
					}
				}else{
					if(isPC()){
						$('#orderlist').hide();
						$('#createmanager').show();
					}else{
						$('#entry').show();
						$('.ordercontent').addClass('display');
						$('.withoutdate').removeClass('display');
					}
				}
			}else{
				if(isPC()){
					$('#orderlist').hide();
				}else{
					$('.ordercontent').addClass('display');
					$('.withoutdate').removeClass('display');
				}
				errorMessage(data.info);
			}
		},
		error: function (request, status, err) {
			if(isPC()){
				$("body").hideLoading();	
				$('#orderlist').hide();
			}else{
				$("#loading").modal("close");
				$('.ordercontent').addClass('display');
				$('.withoutdate').removeClass('display');
			}
			if (status == "timeout"){
				errorMessage("请求超时，请稍后重试~");
			}else{
				errorMessage("获取失败，请稍后重试~");
			}
		}
	});
}
function moveinfo(_list){
	var content = '';
	for(var i in _list){
		status = _list[i].status;
		statusflag = "";
		if(Number(status)==0){
			statusflag = '<span style="color:#F00">未提交<span>';
		}else if(Number(status)==1){
			statusflag = '<span style="color:#339958">已提交<span>';
		}
		if(Number(status)!=5){
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
	}
	if(content!=""){
		$('.ordercontent').html(content);
	}else{
		$('.ordercontent').addClass('display');
		$('.withoutdate').removeClass('display');
	}
	
}
function proinfo(_list){
	var info = "";
	info = '<span style="margin:5px">'+
		   '<div class="checkboxWrapper theme3 extraSmallCheckboxSize">'+
		   '<input type="radio" name="order" id="'+_list[0].pk+'" value="'+_list[0].pk+'" checked="checked">'+
		   '<label for="'+_list[0].pk+'" >'+
		   '<i>'+
		   '<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="50px" height="50px" viewBox="0 0 50 50" enable-background="new 0 0 50 50" xml:space="preserve">'+
		   '<circle fill="none" stroke="#B7B7B7" stroke-width="3" stroke-miterlimit="10" cx="25.11" cy="24.883" r="23.519"></circle>'+
		   '<path fill="none" stroke-width="3" stroke-miterlimit="10" d="M48.659,25c0,12.998-10.537,23.534-23.534,23.534'+
		   ' S1.591,37.998,1.591,25S12.127,1.466,25.125,1.466c9.291,0,17.325,5.384,21.151,13.203L19,36l-9-14"></path>'+
		   '</svg>'+
		   '</i>'+_list[0].pk+'</label>'+
		   '</div>'+
		   '</span>';
	$('#pk').val(_list[0].pk);
	$('.orderinfo').html(info);
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 订单明细 xxx 2016-03-22 */
/* ------------------------------------------------------------------------------------------------------------------------ */	
function toMouldInfo(){
	var urlstr = "", mouldType="";
	//判断是否为超大模板,若是,跳转界面
	if(isPC()){
		urlstr = basePath + "facorder/bigMould/toPcBigMould.do";
	}else{
		urlstr = basePath + "facorder/bigMould/toBigMould.do";
	}
	//清空历史订单数据
	phoneG["order"] = {};
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	var mould = temp.decode(mouldid);
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/toBigPage.do",
		data: {"mouldType":0,"mouldId":mould,"deliverydate":deliverydate,"orgid":orgid,"flag":0},
		dataType: "json",
		timeout: 20000,
		success: function (data) { 
			if(data.success){
				$.StandardPost(urlstr,{"mouldType":mouldType,"mouldId":mould,"deliverydate":deliverydate,"orgid":orgid,"flag":0});
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
/* 订单明细 xxx 2016-03-22 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function toOrderInfo(){
	var urlstr = "", mouldType="";
	//判断是否为超大模板,若是,跳转界面
	if(isPC()){
		urlstr = basePath + "facorder/bigMould/toPcBigMould.do";
	}else{
		urlstr = basePath + "facorder/bigMould/toBigMould.do";
	}
	//清空历史订单数据
	phoneG["order"] = {};
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	var mould = temp.decode(mouldid);
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/toBigPage.do",
		data: {"mouldType":0,"mouldId":mould,"deliverydate":deliverydate,"orgid":orgid,"orderid":$('#pk').val(),"flag":1},
		dataType: "json",
		timeout: 20000,
		success: function (data) { 
			if(data.success){
				$.StandardPost(urlstr,{"mouldType":mouldType,"mouldId":mould,"deliverydate":deliverydate,"orgid":orgid,"orderid":$('#pk').val(),"flag":1});
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
function toHisOrderInfoM(obj){
	var bookingid =  $(obj).attr("data");
	urlstr = basePath + "facorder/booking/info.do";
	$.StandardPost(urlstr,{"orderid":bookingid,"ishistory":0});
}