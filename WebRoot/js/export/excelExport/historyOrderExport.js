
/**历史订单导出excel功能*****/

function historyOrderExportExcel(){
	
	$("#exporttable").hide();
	var orderId=$('#pk').val();
	var array=phoneG.goods;
	if(array.length>0){
		exportOrder(array);
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
	
	var date=phoneG.dates;
	html = "";
	
	amount = 0;
	money = 0;
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
    //'<tr><td></td><td>供应商:</td><td>'+arrayDate.arrival+'</td>'+
    '<tr><td></td><td>供货时间:</td><td>'+date.deliveryDate.substring(0,10)+'</td></tr>'+'<tr></tr>';
	
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
			/**
			var price=array[i].deliverPrice
			deliverPrice = price==0?"":price.toFixed(2);
			**/
			html += '<tr>'+
			'<td></td>'+
			'<td style="border:1px solid black;">'+Num+'</td>'+
			'<td style="border:1px solid black;">'+array[i].goodsId+'</td>'+
			'<td style="border:1px solid black;">'+array[i].goodsName+'</td>'+
			'<td style="border:1px solid black;">'+array[i].standards+'</td>'+
			'<td style="border:1px solid black;">'+array[i].packingName+'</td>'+
			'<td style="border:1px solid black;">'+array[i].deliverPrice.toFixed(2)+'</td>'+
			'<td style="border:1px solid black;">'+array[i].count.toFixed(2)+'</td>'+
			'</tr>';
			amount +=Number(array[i].count);
			money +=Number(array[i].deliverPrice*array[i].count);
			
	}
	html += 
            '<tr>'+
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







