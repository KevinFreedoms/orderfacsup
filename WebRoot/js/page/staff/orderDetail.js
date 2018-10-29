var orderDetail="";
var detailMessage=JSON.parse(sessionStorage.getItem('detailMessage'));
(function(){
	 getOrderDetail();
	 createOrderDetail();
}())
//获得订单汇总
function getOrderDetail(){
	$.ajax({
		type: "post",
		url: basePath+"facorder/staff/getOrderDetail.do",
		dataType: "json",
		data:{"orderId":detailMessage.orderId},
		timeout: 20000,  //
		async:false,
		success: function (data) {
			if(data.success){
				orderDetail=data.orderDetail;
			}
		}
	})
}
//构建页面
function createOrderDetail(){
	$('.orderId').html(detailMessage.orderId);
	$('.bookingstatus').html(detailMessage.status=='1'?"未生效":"已生效").css({color:detailMessage.status=='1'?"#999":"#339958"});
	$('.deliverywork').html(detailMessage.mouldName);
	$('.deliverydate').html(detailMessage.deliveryDate.split(' ')[0]);
	$('.sumprice').html("总金额: "+detailMessage.ref2);
	$('.sumcount').html("单品总数: "+orderDetail.length);
	var lis=""
		for(var key in orderDetail){
			lis+=
				'<li>'+
                '<span class="leftInfo" style="width:60%;float:left;">'+ orderDetail[key].goodsName+'</span>'+
                '<span class="leftInfo" style="width:15%;float:left;text-align:left;color:#a09494;">x'+orderDetail[key].count +'</span>'+
                '<span class="rightInfo" style="width:25%;">￥'+orderDetail[key].deliverPrice.toFixed(2)+'</span>'+
                '<div class="clearfloat"></div>'+
                '</li>';
		}
	$('.detailList').html(lis);
}

