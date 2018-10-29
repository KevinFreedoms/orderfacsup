var orderCollect="";
var parameter=JSON.parse(sessionStorage.getItem('parameter'));
(function(){
	 getOrderCollet();
	 createOrderCollect();
}())
//获得订单汇总
function getOrderCollet(){
	$.ajax({
		type: "post",
		url: basePath+"facorder/staff/getOrderCollect.do",
		dataType: "json",
		data:{"orgId":parameter.orgId,"lastupdate":parameter.lastupdate,"status":parameter.status,"deliveryDate":parameter.deliveryDate,"addDay":parameter.addDay},
		timeout: 20000,  //
		async:false,
		success: function (data) {
			if(data.success){
				orderCollect=data.orderCollect;
			}
		}
	})
}
//构建页面
function createOrderCollect(){
	var orgName=orderCollect[0].orgName;
	$('#orgName').html(orgName);
	var temp="";
	for(var key in orderCollect){
		$('#deliveryDate').html(orderCollect[key].deliveryDate.split(' ')[0]);
		temp+=
			'<li class="urgentlist">'+
            '<div class="ordercode" style="margin-top:5px;">'+
            '<span class="floatLeft"><span>单号：</span><span>'+orderCollect[key].orderId +'</span></span>'+
            '<span class="status floatRight">'+
            '<span style="color:'+(orderCollect[key].status=="1"?"#999":"#339958")+'">'+ (orderCollect[key].status=="1"?"未生效":"已生效")+'<span>'+
            '</span>'+
            '<div class="clearfloat"></div></span></span>'+
            '</div>'+
            '<div class="orderInfo">'+
            '<span class="floatLeft">'+
            '<span>类型：</span>'+
            '<span>'+orderCollect[key].mouldName+'</span>'+
            '</span>'+
            '<span class="floatRight orderprice mark">¥'+orderCollect[key].ref2+'</span>'+
            '<div class="clearfloat"></div>'+
            '</div>'+
            '<div class="operateOrder"><button class="am-btn am-btn-default am-btn-sm modify" data-orderid="'+orderCollect[key].orderId  +'" onclick="toHisOrderInfoM(this)">查看</button></div>'+
            '</li>';
	}
	$('#orderCollectList').html(temp);
}
//跳转至明细页面
function toHisOrderInfoM(obj){
	var orderId=$(obj).attr('data-orderid');
	var detailMessage={};
	for(var key in orderCollect){
		if(orderCollect[key].orderId==orderId){
			detailMessage=orderCollect[key];
		}
	}
	sessionStorage.setItem("detailMessage",JSON.stringify(detailMessage));
	location.href=basePath+"facorder/staff/toOrderDetail.do"
}
