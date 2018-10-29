
/**导出excel功能*****/

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




