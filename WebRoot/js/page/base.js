/* ------------------------------------------------------------------------------------------------------------------------ */
/* 资源储存 xxx 2016-03-17*/
/* ------------------------------------------------------------------------------------------------------------------------ */
var phoneG = phoneG||{};
var storageName ="phone";
if(window.localStorage){
	try{
		phoneG = JSON.parse(localStorage[storageName])||{};
	}catch(e){
		localStorage.removeItem(storageName);
		phoneG = phoneG||{};
	}
}else{
	phoneG = phoneG||{};
}
//搜索
function goodsLocalstorage() {
	this.name = "_search";
}
searchLocalStorage = new goodsLocalstorage;
searchLocalStorage._getAll = function() {
	return localStorage.getItem(this.name);
},
searchLocalStorage._storeAll = function(t) {
	localStorage.setItem(this.name, t);
};
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 日期选择 xxx 2016-03-17 手机端*/
/* ------------------------------------------------------------------------------------------------------------------------ */
var deliverydate;
function initDeliveryDate() {
	$('input:jqmData(role="datebox")').mobiscroll().date();
	//初始化日期控件
    var opt = {
        preset: 'date', //日期
        theme: "jqm", //皮肤样式
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
    $('input:jqmData(role="datebox")').mobiscroll(opt);
}
function initDeliveryDateHis(){
	$('#deliverydate').val(getNowFormatDate());
	var picker = new Pikaday(
    {
        field: document.getElementById('deliverydate'),
        firstDay: 1,
        minDate: new Date('2000-01-01'),
        maxDate: new Date('2020-12-31'),
        yearRange: [2000,2020],
		onSelect: 	function() {
			//显示模板
			initOrderMould();
		}
    });
}

//pc端日期
function getDeliveryDate(){
	$('#deliverydate').val(getNowFormatDate());
	var picker = new Pikaday(
    {
        field: document.getElementById('deliverydate'),
        firstDay: 1,
        minDate: new Date('2000-01-01'),
        maxDate: new Date('2020-12-31'),
        yearRange: [2000,2020],
		onSelect: 	function() {
			//显示直送作业
			initDeliveryOrder();
			
		}
    });
}
//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 数据表样式 xxx 2016-03-18*/
/* ------------------------------------------------------------------------------------------------------------------------ */
//控制表头浮动的函数
$.fn.smartFloat = function(){
	var position = function(element){
		var top = element.position().top;//当前元素对象element距离浏览器上边缘的距离 
		var pos = element.css("position");//当前元素的position值
		$(document).scroll(function(){ //侦听滚动时
			var scrolls = $(this).scrollTop();
			if(scrolls > top){ //滚动页面超出元素ELEMENT距离浏览器上端的距离
				if( window.XMLHttpRequest ){  //如果不是IE6及以下版本
					element.css({ //设置CSS样式
						position : 'fixed',
						top : '0',
						width : '100%',
						'max-width' : '600px'
						
					}).addClass("shadow");
					$("#footer").css({
						position : 'fixed',
						bottom : '0',
						width : '100%',
						'max-width' : '600px'
					});
				}
				else{
					element.css({
						top : scrolls
					});
					$("#footer").css({
						position : 'fixed',
						bottom : '0'
					});
				}
			}
			else{
				element.css({
					position : pos ,
					top : top
				});
				$("#footer").css({
					position : 'position',
					bottom : '0'
				});
			}
		});
	};
	return $(this).each(function() {
		position($(this));
	});
};
function tablestyle(){
	//控制列表中每行的颜色
	$(".js-bgcolor").each(function() {
		var bg_num = $(this).index();
		var x = bg_num % 2 ;
		if( x == 0 ){
				$(this).css({
						'background-color' : '#edf3f0'
					});
			}else{
				$(this).css({
						'background-color' : '#add8e6'
					});
			}
	});
	
	$(".js-bgcolor-out").each(function() {
		var bg_num = $(this).index();
		var x = bg_num % 2 ;
		if( x == 1 ){
				$(this).css({
						'background-color' : '#edf3f0'
					});
			}else{
				$(this).css({
						'background-color' : '#add8e6'
					});
			}
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 初始化日期函数 xxx 2016-03-18*/
/* ------------------------------------------------------------------------------------------------------------------------ */
Date.prototype.Format = function (fmt) { //author: meizz 
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"d+": this.getDate(), //日 
		"h+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 当前设备的判断 xxx 2016-03-25*/
/* ------------------------------------------------------------------------------------------------------------------------ */
function checkUrl() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = "PC/login.jsp";
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = "login.jsp";
            break;
        }
    }
    return flag;
}
function isPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod",];
   	var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 设置分页 xxx 2016-03-25*/
/* ------------------------------------------------------------------------------------------------------------------------ */
jQuery.fn.pagination = function(maxentries, opts){
	opts = jQuery.extend({
		items_per_page:10,
		num_display_entries:10,
		current_page:0,
		num_edge_entries:0,
		link_to:"#",
		prev_text:"Prev",
		next_text:"Next",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true,
		callback:function(){return false;}
	},opts||{});
	
	return this.each(function() {
		/**
		 * 计算最大分页显示数目
		 */
		function numPages() {
			return Math.ceil(maxentries/opts.items_per_page);
		}	
		/**
		 * 极端分页的起始和结束点，这取决于current_page 和 num_display_entries.
		 * @返回 {数组(Array)}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}
		
		/**
		 * 分页链接事件处理函数
		 * @参数 {int} page_id 为新页码
		 */
		function pageSelected(page_id, evt){
			current_page = page_id;
			drawLinks();
			var continuePropagation = opts.callback(page_id, panel);
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		
		/**
		 * 此函数将分页链接插入到容器元素中
		 */
		function drawLinks() {
			panel.empty();
			var interval = getInterval();
			var np = numPages();
			// 这个辅助函数返回一个处理函数调用有着正确page_id的pageSelected。
			var getClickHandler = function(page_id) {
				return function(evt){ return pageSelected(page_id,evt); }
			}
			//辅助函数用来产生一个单链接(如果不是当前页则产生span标签)
			var appendItem = function(page_id, appendopts){
				page_id = page_id<0?0:(page_id<np?page_id:np-1); // 规范page id值
				appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
				if(page_id == current_page){
					var lnk = jQuery("<span class='current'>"+(appendopts.text)+"</span>");
				}else{
					var lnk = jQuery("<a>"+(appendopts.text)+"</a>")
						.bind("click", getClickHandler(page_id))
						.attr('href', opts.link_to.replace(/__id__/,page_id));		
				}
				if(appendopts.classes){lnk.addClass(appendopts.classes);}
				panel.append(lnk);
			}
			// 产生"Previous"-链接
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"prev"});
			}
			// 产生起始点
			if (interval[0] > 0 && opts.num_edge_entries > 0)
			{
				var end = Math.min(opts.num_edge_entries, interval[0]);
				for(var i=0; i<end; i++) {
					appendItem(i);
				}
				if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
				}
			}
			// 产生内部的些链接
			for(var i=interval[0]; i<interval[1]; i++) {
				appendItem(i);
			}
			// 产生结束点
			if (interval[1] < np && opts.num_edge_entries > 0)
			{
				if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
				}
				var begin = Math.max(np-opts.num_edge_entries, interval[1]);
				for(var i=begin; i<np; i++) {
					appendItem(i);
				}
				
			}
			// 产生 "Next"-链接
			if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_text, classes:"next"});
			}
		}
		
		//从选项中提取current_page
		var current_page = opts.current_page;
		//创建一个显示条数和每页显示条数值
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		//存储DOM元素，以方便从所有的内部结构中获取
		var panel = jQuery(this);
		// 获得附加功能的元素
		this.selectPage = function(page_id){ pageSelected(page_id);}
		this.prevPage = function(){ 
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		}
		this.nextPage = function(){ 
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		}
		// 所有初始化完成，绘制链接
		drawLinks();
        // 回调函数
        opts.callback(current_page, this);
	});
};

function initPagination(id,size){
	var $table = $(id);  
	var pageSize = size;  //每页行数（不包括表头）
	var numRows = $table.find("tbody tr").length;  //记录宗条数  
	var numPages = Math.ceil(numRows/pageSize);    //总页
	$table.parent().find("#Pagination").pagination(numPages, {
		num_edge_entries: 1, //边缘页数
		num_display_entries: 4, //主体页数
		callback: pageselectCallback,
		items_per_page:1, //每页显示1项
		prev_text: "上一页",
		next_text: "下一页"
	});
	function pageselectCallback(page_index, jq){
		$table.find("tbody tr").hide().slice(page_index * pageSize, (page_index + 1) * pageSize).show();
		return false;
	}
}
function searchTablePage(id,$search,size){
	var $table = $(id);  
	var pageSize = size;  //每页行数（不包括表头）
	var numRows = $search.length;  //记录宗条数
	var numPages = Math.ceil(numRows/pageSize);    //总页
	$table.parent().find("#Pagination").pagination(numPages, {
		num_edge_entries: 1, //边缘页数
		num_display_entries: 4, //主体页数
		callback: pageselectCallback,
		items_per_page:1, //每页显示1项
		prev_text: "上一页",
		next_text: "下一页"
	});
	function pageselectCallback(page_index, jq){
		$search.hide().slice(page_index * pageSize, (page_index + 1) * pageSize).show();
		return false;
	}
}

 /* ------------------------------------------------------------------------------------------------------------------------ */
/* 单品明细 xxx 2016-04-18 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function showGoods(obj){
	$.ajax({
		type: "post",
		url: basePath+"facorder/booking/toShowGoodsInfo.do",
		data: {  "deliverydate":$('#deliverydate').val(),
				 "goodsid":$(obj).attr("id"),
				 "flag":$(obj).attr("flag")
			    },
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			info = "";
			if(data.success){
				swal({   
					title: "单品组成",   
					text: "",  
					confirmButtonText: "返回"
				});
				products = data.products;
				for(var i in products){
					info += "<div style='text-align:left; margin:5px'>"+products[i].subGoodsName+":<span>"+products[i].standards+"</span> <span>"+products[i].mark+"</span></div>";
				}
				$(".sweet-alert p").html(info);
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
					text: "请求超时，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}else{
				swal({   
					title: "提示",
					text: "获取失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		}
	});
}
 /* ------------------------------------------------------------------------------------------------------------------------ */
/* 页面搜索 xxx 2016-04-19 */
/* ------------------------------------------------------------------------------------------------------------------------ */
$(".btnSearch").click(function(){
	searchStr = $("#searchTxt").val();
	if(isPC()){
		if(searchStr!=""){
			$("#goodscontent>tr").hide().filter(":contains('"+searchStr+"')").show();  
		}else{
			$("#goodscontent>tr").show();  
		}
		$obj = $("#goodscontent>tr").filter(":contains('"+searchStr+"')");
		searchTablePage(kingTable,$obj,10); 
	}else{
		if(searchStr!=""){
			$("#sbody>.ui-grid-c").hide().filter(":contains('"+searchStr+"')").show();  
		}else{
			$("#sbody>.ui-grid-c").show();  
		}  
	}
	
});
$(".btnSearchDetail").click(function(){
	searchStr = $("#searchTxtDetail").val();
	if(isPC()){
		if(searchStr!=""){
			$("#goodsdetail>tr").hide().filter(":contains('"+searchStr+"')").show();  
		}else{
			$("#goodsdetail>tr").show();  
		}
		$obj = $("#goodsdetail>tr").filter(":contains('"+searchStr+"')");
		searchTablePage(detailTable,$obj,10); 
	}else{
		if(searchStr!=""){
			$("#sbody>.ui-grid-c").hide().filter(":contains('"+searchStr+"')").show();  
		}else{
			$("#sbody>.ui-grid-c").show();  
		}  
	}
	
});
$(".btnSearchPri").click(function(){
	searchStr = $("#searchTxtPri").val();
	if(searchStr!=""){
		$("#sprice>.ui-grid-c").hide().filter(":contains('"+searchStr+"')").show();  
	}else{
		$("#sprice>.ui-grid-c").show();  
	}  
	
});
//直送 预分搜索单品
$(".mgoodsSearch").click(function(){
	searchStr = $(".searchpro").val();
	var obj
	if(searchStr!=""){
		obj = $(".prolist>li").hide().filter(":contains('"+searchStr+"')").show();  
	}else{
		obj = $(".prolist>li").show();  
	}
	if(obj.length==0){
		$(".withoutdate").removeClass('display');
	}else{
		$(".withoutdate").addClass('display');
	}
});

function isNum(s) {
 //var regu = "^([0-9]*)$";
 	var regu = "^([0-9]*[.0-9])$"; // 小数测试
 	var re = new RegExp(regu);
 	if (s.search(re) != -1)
  		return true;
 	else
		return false;
}
 /* ------------------------------------------------------------------------------------------------------------------------ */
/* 错误提醒 xxx 2017-01-31 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function errorMessage(info){
	swal({   
		title: "信息提示",
		text: info,
		showCancelButton: false,
		confirmButtonColor: "#039"
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 页面跳转post xxx 2018-01-10 */
/* ------------------------------------------------------------------------------------------------------------------------ */
$.extend({  
	StandardPost:function(url,args){
        var body = $(document.body);
		if(isPC()){
			 var form = $("<form method='post'></form>");
		}else{
			var form = $("<form method='post' data-ajax='false'></form>");
		}
        var input;
        form.attr({"action":url});
        $.each(args,function(key,value){
            input = $("<input type='hidden'>");
            input.attr({"name":key});
            input.val(value);
            form.append(input);
        });

        form.appendTo(document.body);
        form.submit();
    }
});
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 四舍五入重写 xxx 2018-01-17 */
/* ------------------------------------------------------------------------------------------------------------------------ */
Number.prototype.toFixed = function(s)  
{  
	var pos = this.toString().indexOf('.'),
    decimal_places = this.toString().length - pos - 1,
    _int = this * Math.pow(10, decimal_places),
    divisor_1 = Math.pow(10, decimal_places - s),
    divisor_2 = Math.pow(10, s);
    return Math.round(_int / divisor_1) / divisor_2;

}