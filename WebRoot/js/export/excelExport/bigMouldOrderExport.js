
/**超大模板导出excel功能*****/

function bigOrderExportExcel(){
	
	$("#exporttable").hide()
	var bigOrderArray = phoneG.order;
	var length = 0;
	for(var i in phoneG.order){
		length++;
	}
	var orderId=$('#pk').val();
	if(length>0){
		exportOrder(bigOrderArray);
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


function exportOrder(bigOrderArray){
	
	html = "";
	amount = 0;
	money = 0;
	html+='<table id="exporttable">'+
    '<thead><tr></tr><tr>'+
    '<th></th>'+
    '<th></th>'+
    '<th></th>'+
    '<th style="font-size:20px;">订货单</th>'+
    '<th></th></tr>'+
    '</thead>';
	
	
	html+='<tbody>'+
	'<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td>'+
	'<td>'+$('#pk').val()+'</td>'+
	'</tr>'+
    '<tr><td></td><td>供货时间:</td><td>'+delivery.arrival+'<span>前</span></td></tr>'+'<tr></tr>';
	
	html+= '<tr style="font-weight:bold;"><td></td>'+
           '<td style="border:1px solid black;">序号</td>'+
           '<td style="border:1px solid black;">编码</td>'+
           '<td style="border:1px solid black;">品名</td>'+
           '<td style="border:1px solid black;">规格</td>'+
           '<td style="border:1px solid black;">单位</td>'+
           '<td style="border:1px solid black;">单价</td>'+
           '<td style="border:1px solid black;">订货数量</td>'+
           '</tr>';
	
	var arrayNew = new Array(); 
	for (var int in bigOrderArray){
		var good={"goodsId":bigOrderArray[int].goodsId,"goodsName":bigOrderArray[int].goodsName,"standards":bigOrderArray[int].standards,
				  "singlePacking":bigOrderArray[int].singlePacking,"price":bigOrderArray[int].price,"count":bigOrderArray[int].count
		         }
		arrayNew.push(good)
	}
	
	for ( var i = 0; i < arrayNew.length; i++) {
		if(arrayNew[i].count>0){
				
				var Num=i+1;
				//单价为零不显示
				var price=arrayNew[i].price
				bigPrice = price==0?"":price.toFixed(2)
				html += '<tr>'+
				'<td></td>'+
				'<td style="border:1px solid black;">'+Num+'</td>'+
				'<td style="border:1px solid black;">'+arrayNew[i].goodsId+'</td>'+
				'<td style="border:1px solid black;">'+arrayNew[i].goodsName+'</td>'+
				'<td style="border:1px solid black;">'+arrayNew[i].standards+'</td>'+
				'<td style="border:1px solid black;">'+arrayNew[i].singlePacking+'</td>'+
				'<td style="border:1px solid black;">'+bigPrice+'</td>'+
				'<td style="border:1px solid black;">'+arrayNew[i].count.toFixed(2)+'</td>'+
				'</tr>';
				amount +=Number(arrayNew[i].count.toFixed(2));
				money +=arrayNew[i].price*arrayNew[i].count;
			
		}
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
            '</tr></tbody>'
            
	html+='<tr></tr><tr>'+
    '<td></td><td>收货人:</td>'+
    '<td></td><td>送货人:</td>'+
    '</tr>'+
    '</tbody>'+
    '</table>';
	
	$("#exporttable").prepend(html)
	
}







