/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化  2018-01-05 修改*/
/* ------------------------------------------------------------------------------------------------------------------------ */
var mouldid="";//模板编号
var mouldname = ""; //模板名称
var deliverydate = ""; //配送时间
var delivery = {};//到达截止时间
var collect = [];//订单明细
var log = [];  //订单日志
var orderflag = 0;//新增 还是修改订单 0：新增 1：修改
var type = 0; // 0:单品  1:购物车 2:搜索
var isbooking = 0;	 //是否是下单状态
var orgid ="";		 //门店信息
var isRetailPrice="0";//是否启用建议零售价
phoneG["order"] = phoneG["order"]||{};
phoneG["goods"] = {};
phoneG["menu"] ={};

function initMould(){
	temp = new Base64();	
	str = temp.decode(bigbata); 
	mainjson = JSON.parse(str);
	orderflag = mainjson.flag;
	orgid = mainjson.orgid;
	mouldid = mainjson.mouldId;
	mouldname = mainjson.mouldname;
	deliverydate = mainjson.deliverydate;
	delivery = mainjson.delivery;
	collect = mainjson.collect;
	log = mainjson.log;
	if(orderflag==0){
		$(".createbtnlist").show();
		$(".updatebtnlist").hide();
	}else{
		$(".createbtnlist").hide();
		$(".updatebtnlist").show();
		$("#pk").val(log[0].pk);
	}
	initGoodsCate();
}
//初始化单品类型
function initGoodsCate(){
	//绑定按钮事件
	bindButtonfunction();
	//初始化订单
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/toGetAllGoods.do",
		data:{"mouldId":mouldid,"orgid":orgid,"deliverydate":deliverydate},
		dataType: "json",
		timeout: 20000,
		success: function (data) {
			if(data.success){
				phoneG.menu = data.category;
				phoneG.order = {};
				if(isPC()){
					proinfo();
				}else{
					moveinfo();
				}
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
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 2018-01-18 展示订单信息*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function lookorder(){
	var content = "";
	if(orderflag==1){
		bookingid = $("#pk").val()
		content +='<li><label>订单号：</label>'+
                  '<div class="orderinfo">'+bookingid+'</div></li>';
	}
	content +='<li><label>订货类型：</label>'+
              '<div class="orderinfo">'+mouldname+'</div></li>'+
			  '<li><label>送达时间：</label>'+
              '<div class="orderinfo">'+delivery.arrival+'</div></li>';
	$('#conditionList').html(content);
	$(".am-modal-actions.am-modal-out").css("transform","translateY(0)")
	$(".am-modal-actions.am-modal-active").css("transform","translateY(-100%)")
	$("#filterorder").modal()
	$(".am-dimmer").css("z-index","1240")
	$(".am-header-default").css("z-index","1260")
	$(".filterorder").css("z-index","1250");
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 2018-01-11 拼接超大模板单品数据*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function proinfo(){
	//拼接表头信息
	bookingmark=""
	if(orderflag==0){
		orderheadinfo = "送达时间："+delivery.arrival+"前 截止时间："+delivery.orderend;
		bookingmark = "备注：已选单品统计的是品种数";
	}else{
		orderheadinfo = "订单编号："+log[0].pk+" 送达时间："+delivery.arrival+"前 截止时间："+delivery.orderend;
		//设置已定单品
		for(j in collect){
			var index = collect[j].goodsId;
			phoneG.order[index] = collect[j];
			phoneG.order[index].price = collect[j].deliverPrice;
			phoneG.order[index].goodsType = 0;
		}
		if(window.localStorage){
			localStorage["phone"] = JSON.stringify(phoneG);
		}
		bookingmark = "备注：绿色表示原始订单中已经订的单品";
	}
	$("#bookingmark").html(bookingmark);
	$("#orderheadinfo").html(orderheadinfo);
	$(".goodsmanager").show();
	//设置类别
	setMenu(phoneG.menu);
	//获取类别下的单品
	changeGoodsByCate("0");
	//设置分页
	initPagination("#kingTable",10);
	$('#goodscontent').find("tr").each(function (index) {
		bindPlusMinusEvent(this);
	});
}
function moveinfo(){
	//拼接表头信息
	orderheadinfo = "<span>截止时间：</span><span>"+delivery.orderend+"</span>";
	$(".endtime").html(orderheadinfo);
	if(orderflag==1){
		//设置已定单品
		for(j in collect){
			var index = collect[j].goodsId;
			phoneG.order[index] = collect[j];
			phoneG.order[index].price = collect[j].deliverPrice;
			phoneG.order[index].goodsType = 0;
		}
		if(window.localStorage){
			localStorage["phone"] = JSON.stringify(phoneG);
		}
	}
	//设置类别
	setMenu(phoneG.menu);
	$("#menulist").on('change', function() {
		type = 0;
        id = $("#menulist").val();
		changeGoodsByCate(id);
    });
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 单品类别拼接 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function setMenu(_list){
	var menuHtml="";
	if(isPC()){
		menuHtml += "<li class=\"active sub-menu\"><a data_name=\"0\">全部类别</a></li>";
		for(var i in _list){
			var temp =_list[i].gcategoryId;
			var name = _list[i].gcategoryName;
			menuHtml += "<li class=\"sub-menu\"><a data_name=\""+ temp+"\">"+name+"</a></li>";
		}
	}else{
		menuHtml += "<option value='0'>全部类别</a></option>";
		for(var i in _list){
			var temp =_list[i].gcategoryId;
			var name = _list[i].gcategoryName;
			menuHtml += "<option value='"+temp+"'>"+name+"</option>";
		}
	}
	$("#menulist").html(menuHtml);
	$('#summoney').html(countPrice());
	$('.readycount').html(countSum());
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 获取类别下单品 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function buildList(_list){
	getIsRetailPrice();
	var result = ""
	for(var i in _list){
		var index = _list[i].goodsId;
		price = _list[i].price;
		//jiaself 1109
		deliverPrice = price==0?"":price.toFixed(2);
		ls = 0;
		if(phoneG.order[index]){
			ls = phoneG.order[index].count;
		}
		bookingflag = checkOrderGoods(index);
		if(isPC()){
			if(_list[i].goodsType > 0){
				result+="<tr " + ((bookingflag? "style='color:#090; font-weight:bold'" : "" ) )+"><td>"+_list[i].goodsId+"</td><td data_id="+_list[i].goodsId+">"+_list[i].goodsName+"</td><td>"+_list[i].singlePacking+"</td><td>"+_list[i].standards+"</td><td>"+deliverPrice+"</td>"+
				"<td style=\"height:47px;\"><a href=\"javascript:void(0);\" class=\"decrease\" style=\"display:none;height:53px;\"><i class=\"minus\">-</i></a>"+
				"<input class=\"number\" type=\"text\"  name=\"F1_goods\" style=\"display:none\"><input type=\"hidden\" class=\"js-primary-price\" value="+deliverPrice+"><input type=\"hidden\" class=\"js-primary-key\" value="+_list[i].goodsId+"><a href=\"javascript:void(0);\"class=\"increase\" style=\"display:none\"><i class=\"plus\">+</i></a>"+
				"</td></tr>";
			}else{
				result+="<tr " + ((bookingflag? "style='color:#090; font-weight:bold'" : "" ) )+"><td>"+_list[i].goodsId+"</td><td data_id="+deliverPrice+">"+_list[i].goodsName+"</td><td>"+_list[i].singlePacking+"</td><td>"+_list[i].standards+"</td><td>"+deliverPrice+"</td>"+
				"<td><div class=\"goods-item\"><a href=\"javascript:void(0);\" class=\"decrease\"><i class=\"minus\">-</i></a>"+
				"<input class=\"number\" type=\"text\" value="+ls.toFixed(2)+" name=\"F1_goods\"><input type=\"hidden\" class=\"js-primary-price\" value="+deliverPrice+"><input type=\"hidden\" class=\"js-primary-key\" value="+_list[i].goodsId+"><a href=\"javascript:void(0);\"class=\"increase\"><i class=\"plus\">+</i></a>"+
				"</div></td></tr>";
			}
		}else{
			result +='<li><div class="goodsname">'+_list[i].goodsName+'</div>'+
                     '<div class="secondcolor stand">'+
                     '<span><span>单位：</span><span>'+_list[i].singlePacking+'</span>'+
                     '</span><span><span> 规格：</span><span>'+_list[i].standards+'</span></span></div>'+
                     '<div><div class="price pull_left importantcolor">￥'+deliverPrice;
                     if(isRetailPrice=='1'&&_list[i].retailPrice!=0){
                    	 result += '<span style="color:#999;font-size:12px;">（建议零售价￥'+_list[i].retailPrice+'）</span>';
                     }
			result +='</div><div class="pull_right inputnumber">'+
                     '<a class="decrease pull_left"><img src="images/list_btn_del.png"></a>'+
                     '<input type=\"hidden\" class=\"js-primary-key\" value="'+_list[i].goodsId+'"><input type=\"hidden\" class=\"js-flag\" value="0"><input class="pull_left enternumber number" data="'+_list[i].goodsId+'" value="'+ls.toFixed(2)+'">'+
                     '<a class="increase pull_left"><img src="images/list_btn_add.png"></a></div>'+
                     '<div class="clearfloat"></div></div></li>';
		}		
	}
	if(isbooking==0&&orderflag==1&&!isPC()){
		isbooking = 1;
		alerdychosepro('');
	}
	return result;
}

//判断单品是否在订单中
function checkOrderGoods(index){
	var flag = false;
	for(j in collect){
		var id = collect[j].goodsId;
		if(id==index){
			flag = true;
			return flag;
		}
	}
	return flag;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 单品类别切换 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function changeGoodsByCate(id){
	id = id=="0"?"":id;
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/findmincheng.do",
		data:{"mouldId":mouldid,"orgid":orgid,"deliverydate":deliverydate,"mingcheng":id},
		dataType: "json",
		timeout: 20000,  
		beforeSend:function(){
			if(isPC()){
				$("body").showLoading();	
			}else{
				$("#loading").modal();
				$('.am-dimmer').css("z-index","1304");
			} 
		},
		success: function (data) {
			if(isPC()){
				$("body").hideLoading();	
			}else{
				$("#loading").modal("close");
			}
			if(data.success){
				//获取类别下的单品
				if(id==""){
					phoneG.goods = data.goods;
				}
				if(isPC()){
					$('#searchTxt').val('');
					$("#goodscontent").html(buildList(data.goods));
					//设置分页
					initPagination("#kingTable",10);
					$('#goodscontent').find("tr").each(function (index) {
						bindPlusMinusEvent(this);
					});
				}else{
					$("#prolist").html(buildList(data.goods));
					$('#prolist').find("li").each(function (index) {
						bindPlusMinusEvent(this);
					});
				}
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
				$("#loading").modal("close");
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
$("body").on("click","#menulist li ",function(){
	type = 0;
	id = $(this).find('a').attr("data_name");
	changeGoodsByCate(id);
	$("#menulist .active").removeClass(" active");
	$(".specialtype").removeClass(" active");
	$(this).addClass("active");
});
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 手机端单品搜索 xxx 2018-01-12 */
/* ------------------------------------------------------------------------------------------------------------------------ */
//搜索单品
$(".searchpro").focus(function(){
	$(".am-dimmer").css("z-index","1000");
	$(".am-header-default").css("z-index","1210");
	$("#searchpro").modal()
	$(".filterpro").val("");
	$(".filterpro").focus();
	$(".historystore").removeClass('display'); 
	$("#allgoodslist").html("");
	$("#allgoodslist").addClass('display');
	$(".withoutpro").addClass('display');
	createHisGoods();
});
function createHisGoods(){
	//获取历史搜索
	var content = "";
	var t = searchLocalStorage._getAll();
	if(null === t)
	{
		$(".historystore").html(content);
		 return false
	}	
	var searchgoods = t.split(",");
	searchgoods = searchgoods.reverse();
	if(searchgoods.length>0){
		content +='<span class="location"><i class="am-icon-clock-o" style="margin-right:5px; font-size:1.6rem;"></i><span>历史</span>				</span>'+
                  '<span class="pull_right location clearhistory" style="font-size:1.6rem;"><i class="am-icon-trash-o"></i></span>'+
			      '<ul class="prohistory">';
		for(var i in searchgoods){
			var location = searchgoods[i]
			content +='<li><div class="storename">' + location + '</div></li>';
		}
		content +='</ul>';
   }
   $(".historystore").html(content);
   work();
}
/**********************搜索点击事件***********************/
function filterpro(){
	var name=$(".filterpro").val();
	if(name!=""){
		var e = [];
		s = searchLocalStorage._getAll();
		if (null == s){
			e.push(name);
		}else {
			var n = s.split(","),
			c = $.inArray(name, n);
			c >= 0 ? (n.splice(c, 1), 0 == n.length ? e.push(name) : (e.push(n), e.push(name))) : (e.push(n), e.push(name)),
			n.length >= 15 && n.splice(0, 1);
		}
		searchLocalStorage._storeAll(e);
	}
	searchpro(name);
}
//返回按钮关闭搜索页面
$(".backpage").click(function(){
	$("#searchpro").modal('close')
	$(".cate-input1").val('')
})
//搜索框改变事件
$(".filterpro").on("input propertychange", function () {
	var name=$(".filterpro").val();
	if(name==""){
		$(".historystore").removeClass('display')
		$("#allgoodslist").addClass('display');
		$(".withoutpro").addClass('display');
		createHisGoods();
	}
});
//过滤单品
function searchpro(name){
	if(name!=""){
		$(".historystore").addClass('display');
		$.ajax({
			type: "post",
			url: basePath+"facorder/bigMould/srearchg.do",
			data:{"mouldId":mouldid,"orgid":orgid,"deliverydate":deliverydate,"mingcheng":name},
			dataType: "json",
			timeout: 20000,  
			beforeSend:function(){
				$("#loading").modal();
			},
			success: function (data) {
				$("#loading").modal("close");
				if(data.success){
					var templist = data.goods;
					if(templist.length>0){
						type = 2;
						$(".withoutpro").addClass('display');
						$("#allgoodslist").removeClass('display');
						$("#allgoodslist").html(buildSearch(templist));
						$('#allgoodslist').find("li").each(function (index) {
								bindPlusMinusEvent(this);
						});
					}else{
						$(".withoutpro").removeClass('display');
					}
					
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
}

function work(){
	//历史搜索单品点击事件
	$(".prohistory li").click(function(){
		name=$(this).find('.storename').text();
		$(".filterpro").val(name);
		$(".filterpro").focus();
		searchpro(name)
	})
	//清空历史
	$(".clearhistory").click(function(){
		localStorage.removeItem("_search");
		$(".prohistory").html('');	
	});
}
function buildSearch(_list){
	var result = ""
	for(var i in _list){
		var index = _list[i].goodsId;
		price = _list[i].price;
		//jiaself 1109
		deliverPrice = price==0?"":price.toFixed(2);
		ls = 0;
		if(phoneG.order[index]){
			ls = phoneG.order[index].count;
		}
		result +='<li><div class="goodsname">'+_list[i].goodsName+'</div>'+
                     '<div class="secondcolor stand">'+
                     '<span><span>单位：</span><span>'+_list[i].singlePacking+'</span>'+
                     '</span><span><span> 规格：</span><span>'+_list[i].standards+'</span></span></div>'+
                     '<div><div class="price pull_left importantcolor">￥'+deliverPrice+'</div>'+
                     '<div class="pull_right inputnumber">'+
                     '<a data="'+_list[i].goodsId+'" class="decrease pull_left"><img src="images/list_btn_del.png"></a>'+
                     '<input type=\"hidden\" class=\"js-primary-key\" value="'+_list[i].goodsId+'"><input type=\"hidden\" class=\"js-flag\" value="2"><input class="pull_left enternumber number" data="'+_list[i].goodsId+'" value="'+ls.toFixed(2)+'">'+
                     '<a data="'+_list[i].goodsId+'" class="increase pull_left"><img src="images/list_btn_add.png"></a></div>'+
                     '<div class="clearfloat"></div></div></li>';		
	}
	return result;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 单品数量增减 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function inputnumber(obj){
	parent = $(obj).parent().parent();
	index = parent.find('.js-primary-key').val();
	goodsinfo = getIndex(index);
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
function bindPlusMinusEvent(t){
	$tr = $(t);
	//减少
	$tr.find(".decrease").click(function() {
		var parent = $(this).parent().parent();
		var js_count = parent.find(".number");
		if(isPC()){
			count = window.parseFloat(js_count.val());
		}else{
			count = window.parseFloat(js_count.val());
			type = parent.find('.js-flag').val();
		}
		newVal = count >= 1 ? count - 1:0;
		js_count.val(newVal.toFixed(2));
		if(!isPC()){
			if(type==1||type==2){
				index = parent.find('.js-primary-key').val();
				$("#prolist ").find("[data='"+index+"']").val(newVal.toFixed(2));
			}
		}
		if(type==1&&newVal==0){
			parent.parent().remove();
			if(isPC()){
				//设置分页
				initPagination("#kingTable",10);
			}
		}
		refreshSITotlePriceAndCount(parent,newVal);
	});
	
	//增加
	$tr.find(".increase").click(function() {
		var parent = $(this).parent().parent();
		var js_count = parent.find(".number");
		count = window.parseFloat(js_count.val());
		newVal = 0 > count ? 0:count + 1;
		js_count.val(newVal.toFixed(2));
		if(!isPC()){
			if(type==1||type==2){
				index = parent.find('.js-primary-key').val();
				$("#prolist ").find("[data='"+index+"']").val(newVal.toFixed(2));
			}
		}
		refreshSITotlePriceAndCount(parent,newVal);
	});
	
	$tr.find(".number").keypress(function(t) {
		var i = t.keyCode;
		i = String.fromCharCode(i);
	}).click(function(i) {
			$(this).select();
		}).change(function() {
			count = $(this).val();
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			parent = $(this).parent().parent();
			if(!isPC()){
				if(type==1||type==2){
					index = parent.find('.js-primary-key').val();
					$("#prolist ").find("[data='"+index+"']").val(newVal.toFixed(2));
				}
			}
			if(type==1&&newVal==0){
				parent.parent().remove();
				initPagination("#kingTable",10);
			}else{
				$(this).val(newVal.toFixed(2));
			}
			refreshSITotlePriceAndCount(parent,newVal);
		}).blur(function() {
			count = $(this).val();
			count = parseFloat(count) == count ? parseFloat(count) : 0;
			newVal = 0 > count ? 0:count;
			parent = $(this).parent().parent();
			if(!isPC()){
				if(type==1||type==2){
					index = parent.find('.js-primary-key').val();
					$("#prolist ").find("[data='"+index+"']").val(newVal.toFixed(2));
				}
			}
			if(type==1&&newVal==0){
				parent.parent().remove();
				initPagination("#kingTable",10);
			}else{
				$(this).val(newVal.toFixed(2));
			}
			refreshSITotlePriceAndCount(parent,newVal);
	});
}
function refreshSITotlePriceAndCount(t,newVal) {
	index = t.find('.js-primary-key').val();
	if(phoneG.order[index]){
		if(newVal==0){
			delete phoneG.order[index];
		}else{
			phoneG.order[index].count = newVal;
		}
	}else{
		if(newVal>0){
			phoneG.order[index] = getIndex(index);
			phoneG.order[index].count = newVal;
		}
	}
	if(window.localStorage){
		localStorage["phone"] = JSON.stringify(phoneG);
	}
	$('#summoney').html(countPrice());
	$('.readycount').html(countSum());
	if(!isPC()){
	   	if(type==1&&countSum()==0){
			$('#shopcart').modal('close')
		}
	}
}

//获取单品基础资料
function getIndex(_id){
	var indexList = phoneG.goods;
	for(var i in indexList){
		if(indexList[i].goodsId===_id){
			return indexList[i];
		}
	}
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 计算金额数量 xxx 2016-03-30 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function countPrice(){
	var price = 0;
	for(var i in phoneG.order){
		price += Number(phoneG.order[i].price)*phoneG.order[i].count;
	}
	//jiaself 1109
	price = price.toFixed(2);
	return price;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 计算数量 xxx 2018-01-11 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function countSum(){
	var count = 0;
	for(var i in phoneG.order){
		count += 1;
	}
	return count;
}

/* ------------------------------------------------------------------------------------------------------------------------ */
/* 已选单品数 xxx 2018-01-11 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function alerdychosepro(obj){
	if(isPC()){
		//获取类别下的单品
		$("#goodscontent").html(buildList(phoneG.order));
		//设置分页
		initPagination("#kingTable",10);
		$('#goodscontent').find("tr").each(function (index) {
			bindPlusMinusEvent(this);
		});
		$("#menulist .active").removeClass(" active");
		$(obj).addClass("active");
		if(countSum()>0){
			type = 1;
		}
	}else{
		if(countSum()==0){
			swal({   
				title: "提示",
				text: "尚未选择单品",
				showCancelButton: false,
				confirmButtonColor: "#039"
			});
			return false;
		}
		type = 1;
		$("#searchpro").modal("close");
		$('#shopcart').modal('toggle');
		$(".am-dimmer").css("z-index","1100")
	}
}
$('#shopcart').on('open.modal.amui', function(){
	$(".shopList").html(buildCart(phoneG.order));
	$('.shopList').find("li").each(function (index) {
		bindPlusMinusEvent(this);
	});
	$(".am-dimmer").css("z-index","1100");
	$(".am-header-default").css("z-index","1220");
});
$('#shopcart').on('close.modal.amui', function(){
   type = 0;
});
//拼接购物车
function buildCart(_list){
	var result = ""
	for(var i in _list){
		var index = _list[i].goodsId;
		price = _list[i].price;
		//jiaself 1109
		deliverPrice = price==0?"":price.toFixed(2);
		ls =  _list[i].count;
		goodsname = _list[i].goodsName;
		goodsname = goodsname.length>10?goodsname.substring(0,12)+"...":goodsname;
		result +='<li>'+
				 '<div class="shopListpro pull_left">'+
				 '<div class="tit"><span>'+goodsname+'</span></div>'+
			     '<div class="shop-list-price left mark">￥'+deliverPrice+'</div>'+
				 '</div>'+
				 '<div class="pull_left operatenum">'+
			     '<div class="modifynumber">'+
			     '<a class="decrease pull_left"><img src="images/list_btn_del.png"></a>'+
			     '<input type="hidden" class="js-primary-key" value="'+_list[i].goodsId+'">'+
				 '<input type="hidden" class="js-flag" value="1">'+
				 '<input class="shopnumber pull_left number" value="'+ls.toFixed(2)+'">'+
			     '<a class="increase pull_left"><img src="images/list_btn_add.png"></a>'+
			     '</div></div></li>';
	}
	return result;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 清空 xxx 2016-05-30 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function setEmpty() {
	swal({
		title: "提示",
		text: "确定要清空当前已选单品吗?",
		type: "warning",   
		showCancelButton: true, 
		cancelButtonText:"取消",
		confirmButtonColor: "#336699",
		confirmButtonText:"确定",
		closeOnConfirm: true,   
		closeOnCancel: true,
		closeOnConfirm: true},
		function(isConfirm){
		if(isConfirm){
			if(isPC()){
				$(".specialtype").click();
			}else{
				$('#shopcart').modal('close');
				emptyGoodsMobile();
			}
			phoneG["order"] = {};
			$('#summoney').html(countPrice());
			$('.readycount').html(countSum());
			if(window.localStorage){
				localStorage["phone"] = JSON.stringify(phoneG);
			}
		}
	});
}
function emptyGoodsMobile(){
	for(var i in phoneG.order){
		id = phoneG.order[i].goodsId;
		$("#prolist ").find("[data='"+id+"']").val(0);
	}
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 参考昨日 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function refer(){
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/toListYestodayOrder.do",
		data:{"mouldId":mouldid,"orgid":orgid,"deliverydate":deliverydate},
		dataType: "json",
		timeout: 20000,  
		success: function (data) {
			if(data.success){
				var collect = data.list;
				phoneG["order"] =  {};
				//设置已定单品
				for(j in collect){
					var index = collect[j].goodsId;
					phoneG.order[index] = collect[j];
					phoneG.order[index].price = collect[j].deliverPrice;
					phoneG.order[index].goodsType = 0;
				}
				if(window.localStorage){
					localStorage["phone"] = JSON.stringify(phoneG);
				}
				alerdychosepro('');
				$('#summoney').html(countPrice());
				$('.readycount').html(countSum());
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
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 订单类操作 2106-12-29  xxx 2018-01-14 修改*/
/* ------------------------------------------------------------------------------------------------------------------------ */
//获取传输数据
function getPostData() {
	result="";
	for(var i in phoneG.order){
		result+=phoneG.order[i].goodsId+ '_' +phoneG.order[i].count+ ',';
	}
	strBase = new Base64();
	var data = { "info": result,
				 "deliverydate":deliverydate,
				 "mouldid":strBase.encode(mouldid),
				 "orgid":strBase.encode(orgid),
				 "orderflag":orderflag,
				 "bookingid":$('#pk').val()
			    };
	return data;
}
//事件处理
function  bindButtonfunction(){
	$(".js-dealC").on("click",function(){
		if (countSum() == 0) {
			swal({   
					title: "提示",
					text: "尚未选择单品无法生成订单",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			return false;
		}
		$(".js-dealC").off("click");
		doCreateOrder(getPostData());
	});
	$(".js-dealM").on("click",function(){
		if (countSum() == 0) {
			swal({
			  type: "warning",
			  showCancelButton: true,
			  title: "提示",
			  text: '是否取消当前订单',
			  confirmButtonText: "确认",   
			  cancelButtonText: "取消",   
			  closeOnConfirm: false,   
			  closeOnCancel: true,
			  confirmButtonColor: "#039"
			  },function(isConfirm){   
				  if(isConfirm){
					  $(".js-dealM").off("click");
					  doDeleteOrder(getPostData());
				  }
		  });
		}else{
			$(".js-dealM").off("click");
			doUpdateOrder(getPostData());
		}
	});
	
	$(".js-dealS").on("click",function(){
		swal({
		  type: "warning",
		  showCancelButton: true,
		  title: "提示",
		  text: '紧急订货提交后将不可修改,是否继续?',
		  confirmButtonText: "确认",   
		  cancelButtonText: "取消",   
		  closeOnConfirm: false,   
		  closeOnCancel: true,
		  confirmButtonColor: "#039"
		  },function(isConfirm){   
			  if(isConfirm){
				  doModifyStatus();
			  }
		});
	});
}

//创建订单
function doCreateOrder(data){
	if(isPC()){
		urlstr = basePath + "facorder/bigMould/toPcBigMould.do";
	}else{
		urlstr = basePath + "facorder/booking/info.do";
	}
	$.ajax({
		type: "post",
		url: basePath+"facorder/booking/doCreateOrder.do",
		data:data,
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
			if (data.success) {
				swal({   
					title: "提示信息",
					text: "生成订单成功 感谢您的支持",
					showCancelButton: false,
					confirmButtonColor: "#039"
				},function(isConfirm){
					bookingid = data.bookingid;
					if(isPC()){
						$.StandardPost(urlstr,{"mouldType":0,"mouldId":mouldid,"deliverydate":deliverydate,"orgid":orgid,"orderid":bookingid,"flag":1});
					}else{
						$.StandardPost(urlstr,{"orderid":bookingid,"ishistory":0});
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
				$("#loading").modal("close");
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
//更新订单
function doUpdateOrder(data){
	$.ajax({
		type: "post",
		url: basePath+"facorder/booking/doModifyOrder.do",
		data:data,
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
			if (data.success) {
				swal({   
					title: "提示信息",
					text: "更新订单成功 感谢您的支持",
					showCancelButton: false,
					confirmButtonColor: "#039"
				},function(isConfirm){
					orderflag = 1;
					collect =$.extend(true,[],phoneG.order);
					if(isPC()){
						//绑定按钮事件
						bindButtonfunction();
						$(".specialtype").click();
					}else{
						bookingid = $('#pk').val();
						urlstr = basePath + "facorder/booking/info.do";
						$.StandardPost(urlstr,{"orderid":bookingid,"ishistory":0});
					}
				});
			}else{
				//绑定按钮事件
				bindButtonfunction();
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
				$("#loading").modal("close");
			}
			//绑定按钮事件
			bindButtonfunction();
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
//删除订单
function doDeleteOrder(data){
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
				$("#loading").modal();
			} 
		},  
		success: function (data) {
			if(isPC()){
				$("body").hideLoading();	
			}else{
				$("#loading").modal("close");
			}
			if (data.success) {
				swal({   
					title: "提示信息",
					text: "删除订单成功 感谢您的支持",
					showCancelButton: false,
					confirmButtonColor: "#039"
				},function(isConfirm){   
					if(isPC()){
						window.location = basePath + "facorder/manager/toPcMain.do";
					}else{
						location.href = basePath + "facorder/manager/toOrder.do";
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
				$("#loading").modal("close");
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
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 打印 2106-12-29 */
/* ------------------------------------------------------------------------------------------------------------------------ */
var objWin;
function printOrder(){
	temp = new Base64();
	var bookingid = $('#pk').val();
	bookingid = temp.encode(bookingid);
	var target = basePath+"facorder/bigMould/print.do?id="+bookingid;  
    //判断是否打开  
    if (objWin == null || objWin.closed) {  
       objWin = window.open(target);  
    } else {  
       objWin.location.replace(target);  
    }   
}
//获得是否启用建议零售价
function getIsRetailPrice(){
	$.ajax({
		type: "post",
		url: basePath+"facorder/bigMould/getIsRetailPrice.do",
		dataType: "json",
		async:false,
		success: function (data) {
			if(data.success){
				isRetailPrice=data.isRetailPrice.stateValue;
			}
		}
	})
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 返回操作 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function toBack(){
	if(isPC()){
		window.location = basePath + "facorder/manager/toPcMain.do";
	}else{
		location.href = basePath + "facorder/manager/toOrder.do";
	}
}