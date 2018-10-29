var orderlist=[];
/*------------------------------------------------------------------------------------------------------------
/*初始化
------------------------------------------------------------------------------------------------------------*/
var status="1";
function init(){
	var height=window.innerHeight-49-42-37-40;
	$('.listscroll').css('height',height)
	$('#deliverydate').val(getNowFormatDate())
	$(".refreshDate").html(getCurrentDate())
	getOrg();
	getOrderSelect();
	createList();
	
	
}
/*------------------------------------------------------------------------------------------------------------
/*选择网点
------------------------------------------------------------------------------------------------------------*/
$(".choseorg").click(function(){
	$('.bgDiv').css({
		display: "block",
		transition: "opacity .5s"
	});
	$('.rightNav').css({
		right: "0px",
		transition: "right 1s"
	});
})
/*------------------------------------------------------------------------------------------------------------
/*选择网点
------------------------------------------------------------------------------------------------------------*/
var orgId="all";
$('.rightNav').on('click','li',function(){
	var orgname=$(this).text();
	orgId=$(this).attr('data-orgid');
	if(orgname.length>7){
		orgname=orgname.substring(0,7)+'...'
	}
	$('.choseorg span').text(orgname)
	hideNav();
	getOrderSelect();
	createList();
});
/*------------------------------------------------------------------------------------------------------------
/*点击阴影关闭网点弹出框
------------------------------------------------------------------------------------------------------------*/
$('.bgDiv').on('click', function () {
	hideNav();
});
/*------------------------------------------------------------------------------------------------------------
/*隐藏弹出框
------------------------------------------------------------------------------------------------------------*/
function hideNav() {
   $('.rightNav').css({
		right: "-50%",
		transition: "right .5s"
	});
	$('.bgDiv').css({
		display: "none",
		transition: "display 1s"
	});
}
/*------------------------------------------------------------------------------------------------------------
/*选择已生效未生效
------------------------------------------------------------------------------------------------------------*/
function filterorder(temp){
	if(temp=='0'){
		refresh();
		orgId="all";
		$('#wd').html("全部网点");
		status='1';
		$('#s1Date').val(0);
		lastupdate=($(".refreshDate").html());
		addDay=$('#s1Date').val();
		deliverydate= getCurrentDate().split(" ")[0];
		$('.refreshDate').show();
		$('#entry').show();
		$('.noeffective').show();
		$('.effective').hide();
	}else{
		orgId="all";
		status='5';
		$('#wd').html("全部网点");
		$('.refreshDate').hide();
		$('.noeffective').hide();
		$('.effective').show();
		$('#entry').hide();
		var height=window.innerHeight-49-42-50;
		$('.listscroll').css('height',height);
	}
	getOrderSelect();
	createList();
}
/*------------------------------------------------------------------------------------------------------------
/*刷新
------------------------------------------------------------------------------------------------------------*/
function refresh(){
	//$("#loading").modal('open');//显示加载
	//$("#loading").modal('close');//隐藏加载
	$(".refreshDate").html(getCurrentDate())
	lastupdate=($(".refreshDate").html());
	getOrderSelect();
	createList();
}

/*------------------------------------------------------------------------------------------------------------
/*时间获取
------------------------------------------------------------------------------------------------------------*/
var addDay=$('#s1Date').val();
function dateChange(obj){
	addDay=$(obj).val();
	getOrderSelect();
	createList();
}
function deliveryDateChange(){
	getOrderSelect();
	createList();
}
/*------------------------------------------------------------------------------------------------------------
/*获取网点
------------------------------------------------------------------------------------------------------------*/
function getOrg(){
	$.ajax({
		type: "post",
		url: basePath+"facorder/staff/getOrg.do",
		dataType: "json",
		timeout: 20000,  //
		async:false,
		success: function (data) {
			var lis="<li data-orgId='all'>全部网点</li>";
			for(var key in data.org){
				lis+="<li data-orgId='"+ data.org[key].orgId+"'>"+ (data.org[key].orgName.length>11?data.org[key].orgName.substring(0,11)+'...':data.org[key].orgName)+"</li>"
			}
			$('#orgName').html(lis);
		}
	})
}
/*------------------------------------------------------------------------------------------------------------
/*获取数据
------------------------------------------------------------------------------------------------------------*/
function getOrderSelect(){
	$("#loading").modal('open');
	var deliveryDate="";
	var lastupdate="";
	if(status=='1'){
		deliveryDate=$(".refreshDate").html().split(' ')[0];
		lastupdate=$(".refreshDate").html();
	}
	if(status=='5'){
		addDay="0";
		deliveryDate=$('#deliverydate').val();
		lastupdate="all";
	}
	$.ajax({
		type: "post",
		url: basePath+"facorder/staff/getOrderSelect.do",
		dataType: "json",
		data:{"orgId":orgId,"lastupdate":lastupdate,"status":status,"deliveryDate":deliveryDate,"addDay":addDay},
		timeout: 20000,  //
		async:false,
		success: function (data) {
			if(data.success){
				$('.orderlist').show();
				$('#noData').hide();
				orderlist=data.orderlist;
				if(JSON.stringify(orderlist)=='[]'){
					$('.orderlist').hide();
					$('#noData').show();
				}
			}
		}
	})
}
function createList(){
	$("#ordertb").html("");
	var temp='';
	for(var i in orderlist){
		var orgName=orderlist[i].orgName;
		if(orgName.length>7){
			orgName=orgName.substring(0,7)+'...'
		}
		temp+='<tr onclick="openCollect(this)" data-orgid='+orderlist[i].orgId +'>'+
			  '<td width="35%" style="text-align:left">'+orgName+'</td>'+
			  '<td  width="15%" style="text-align:right">'+orderlist[i].goodsNum+'</td>'+
			  '<td width="20%" style="text-align:right">'+orderlist[i].goodsType+'</td>'+
			  '<td width="30%" style="text-align:right">'+orderlist[i].sumMoney+'</td>'+
			  '</tr>'	
	}
	$("#ordertb").html(temp);
	$("#loading").modal('close');
}
function openCollect(obj){
	var deliveryDate="";
	var lastupdate="";
	var orgName=$(obj).find("td:first-child").html();
	if(status=='1'){
		deliveryDate=$(".refreshDate").html().split(' ')[0];
		lastupdate=$(".refreshDate").html();
	}
	if(status=='5'){
		addDay="0";
		deliveryDate=$('#deliverydate').val();
		lastupdate="all";
	}
	var orgId=$(obj).attr('data-orgid');
	var parameter={"orgId":orgId,"lastupdate":lastupdate,"addDay":addDay,"deliveryDate":deliveryDate,"status":status,"orgName":orgName};
	sessionStorage.setItem("parameter",JSON.stringify(parameter));
	location.href=basePath+"facorder/staff/toOrderCollect.do"
}
/*------------------------------------------------------------------------------------------------------------
/*获取当前时间
------------------------------------------------------------------------------------------------------------*/
function getCurrentDate() {
	  var now = new Date();
	  var year = now.getFullYear(); //得到年份
	  var month = now.getMonth();//得到月份
	  var date = now.getDate();//得到日期
	  var day = now.getDay();//得到周几
	  var hour = now.getHours();//得到小时
	  var minu = now.getMinutes();//得到分钟
	  var sec = now.getSeconds();//得到秒
	  month = month + 1;
	  if (month < 10) month = "0" + month;
	  if (date < 10) date = "0" + date;
	  if (hour < 10) hour = "0" + hour;
	  if (minu < 10) minu = "0" + minu;
	  if (sec < 10) sec = "0" + sec;
	  var time = "";
	 time = year + "-" + month + "-" + date+ " " + hour + ":" + minu + ":" + sec;
	  return time;
}