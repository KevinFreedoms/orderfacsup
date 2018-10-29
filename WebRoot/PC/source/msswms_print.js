//打印自动分页JS
AutoPage={
	header : null,//页面顶部显示的信息
	content: null,//页面正文部分
	footer : null,//页面底部
	pageSize : 10,//每页显示记录数
	pageBreak: null,//分页class属性
	pageNumClass : null,//分页样式
	totalRecord:0,//总记录数
	currentPage:1,//当前页
	totalPage : 0,//总页数
	init : function(header,content,footer,pageBreak,pageNumClass,pageSize){
		AutoPage.header = header;
		AutoPage.content = content;
		AutoPage.footer = footer;
		AutoPage.pageSize = pageSize;
		AutoPage.pageBreak = pageBreak;
		AutoPage.pageNumClass = pageNumClass;
		//初始化分页信息
		AutoPage.initPageInfo();
		//隐藏原来的数据
		AutoPage.hidenContent();
		//开始分页
		AutoPage.beginPage();
		
	},
	//初始化页面信息
	initPageInfo:function(){
		AutoPage.totalRecord = $("#"+AutoPage.content).find("tbody tr").length;//初始化总记录数
		AutoPage.totalPage =  Math.ceil(AutoPage.totalRecord/AutoPage.pageSize);//初始化总页数
		AutoPage.currentPage = 1;
	},
	//隐藏原来的数据
	hidenContent:function(){
		$("#"+AutoPage.header).hide();
		$("#"+AutoPage.content).hide();
		$("#"+AutoPage.footer).hide();
	},
	//开始分页
	beginPage: function(){
		AutoPage.$header = $("#"+AutoPage.header).clone();
		AutoPage.$content = $("#"+AutoPage.content).clone().find("tbody").remove().end();
		AutoPage.$footer = $("#"+AutoPage.footer).clone();
		var startLine = 1;//开始行号
		var offsetLine = 0;//偏移行号
		for(var i = AutoPage.totalPage; i >= 1 ;i-- ){
			AutoPage.currentPage = i;
			startLine = AutoPage.pageSize* (AutoPage.currentPage - 1);
			offsetLine = (startLine + AutoPage.pageSize) >AutoPage.totalRecord ? AutoPage.totalRecord :startLine + AutoPage.pageSize;
			AutoPage.createPage(startLine,offsetLine);
		};
	},
    //创建新的一页
	createPage:function(startLine,offsetLine){
		var $pageHeader = AutoPage.$header.clone().show();
		var $pageContent = AutoPage.$content.clone().append(AutoPage.getTrRecord(startLine,offsetLine)).show();
		var $pageFooter = AutoPage.$footer.clone().show();
		$pageFooter.find("."+AutoPage.pageNumClass).text("第 "+AutoPage.currentPage+" 页       共 "+AutoPage.totalPage+" 页");//页码显示格式
		if(offsetLine == AutoPage.totalRecord){
			$("#"+AutoPage.content).after($pageFooter).after($pageContent).after($pageHeader);
		}else
			$("#"+AutoPage.content).after(AutoPage.addPageBreak()).after($pageFooter).after($pageContent).after($pageHeader);
	},
	//添加分页符
	addPageBreak: function(){
		return "<div class='"+AutoPage.pageBreak+"'></div>";
	},
	//获取记录
	getTrRecord:function(startLine,offsetLine){
		
		var trStr ="";
		$("#"+AutoPage.content).find("tbody tr").slice(startLine,offsetLine).each(function(i){
			trStr += "<tr>"+$(this).html()+"</tr>";
		});
		return trStr;
	}
};
function initOrder(){
	var header="",headerInfo="",tabContent="";
	switch(type){
		<!--新增-->
		case "order":
			header="<h2>订货单</h2>"
			size = detail.length;
			tabContent +='<thead>'+
                  		 '<tr>'+
                         '<th>行号</th>'+
                         '<th>品名</th>'+
                         '<th>单位</th>'+
                         '<th>规格</th>'+
                         '<th>单价</th>'+
                         '<th>订货数量</th>'+
						 '<th>实收数量</th>'+
                         '</tr>'+
                		 '</thead><tbody>';
			allMoney = 0;
			for(var i in detail){
				row = parseInt(i)+1;
				
				price = detail[i].deliverPrice;
				count = detail[i].count;
				deliverPrice = price==0?"":price.toFixed(2);
				tabContent += '<tr>'+
                  			  '<td>'+row+'</td>'+
                  			  '<td>'+detail[i].goodsName+'</td>'+
                  			  '<td>'+detail[i].packingName+'</td>'+
                  			  '<td>'+detail[i].standards+'</td>'+
                  			  '<td>'+deliverPrice+'</td>'+
							  '<td>'+count.toFixed(2)+'</td>'+
							  '<td></td>'+
                  			  '</tr>';
				allMoney += Number(detail[i].deliverPrice)*detail[i].count;
			}
			tabContent+='</tbody>'
			headerInfo +='<span class="floatLeft">预估金额:'+allMoney.toFixed(2)+'元</span>'+
	 					 '<span class="floatRight">单号:'+collect.bookingId+'&nbsp;送达时间:'+collect.arrival+
	 					 '前&nbsp;截止时间:'+collect.orderend+
						 '&nbsp;行数:'+size+
	 					 '</span>';
			$('.header').html(header);
			$('#headerInfo').html(headerInfo);
			$('#tabContent').html(tabContent);
			break;
		case "delivery":
			
			header="<h2>订货单</h2>"
			size = detail.length;
			tabContent +='<thead>'+
                  		 '<tr>'+
                         '<th>行号</th>'+
                         '<th>品名</th>'+
                         '<th>单位</th>'+
                         '<th>规格</th>'+
						 '<th>单价</th>'+
                         '<th>订货数量</th>'+
						 '<th>实收数量</th>'+
                         '</tr>'+
                		 '</thead><tbody>';
			allMoney = 0;
			for(var i in detail){
				row = parseInt(i)+1;
				ordercount = detail[i].ordercount;
				arrivecount = detail[i].arrivecount;
				price = detail[i].deliverPrice;
				deliverPrice = price==0?"":price.toFixed(2);
				tabContent += '<tr>'+
                  			  '<td>'+row+'</td>'+
                  			  '<td>'+detail[i].goodsName+'</td>'+
                  			  '<td>'+detail[i].packingName+'</td>'+
                  			  '<td>'+detail[i].standards+'</td>'+
							  '<td>'+deliverPrice+'</td>'+
							  '<td>'+ordercount.toFixed(2)+'</td>'+
							  '<td>'+arrivecount.toFixed(2)+'</td>'+
                  			  '</tr>';
				allMoney += Number(detail[i].deliverPrice)*detail[i].arrivecount;
			}
			headerInfo +='<span class="floatLeft">总金额:'+allMoney.toFixed(2)+'元</span>'+
	 					 '<span class="floatRight">供应商:'+collect.supplierName+'&nbsp;到货日期:'+collect.deliveryDate+'&nbsp;单号:'+collect.bookingId+
						 '&nbsp;行数:'+size+
	 					 '</span>';
			tabContent+='</tbody>'
			$('.header').html(header);
			$('#headerInfo').html(headerInfo);
			$('#tabContent').html(tabContent);
			break;
		case "distribute":
			header="<h2>配送收货</h2>";
			size = detail.length;
			tabContent +='<thead>'+
			'<tr>'+
			'<th>行号</th>'+
			'<th>品名</th>'+
			'<th>单位</th>'+
			'<th>规格</th>'+
			'<th>单价</th>'+
			'<th>订货数量</th>'+
			'<th>实收数量</th>'+
			'</tr>'+
			'</thead><tbody>';
			allMoney = 0;
			for(var i in detail){
				row = parseInt(i)+1;
				ordercount = detail[i].ordercount;
				arrivecount = detail[i].arrivecount;
				price = detail[i].taxprice;
				deliverPrice = price==0?"":price.toFixed(2);
				tabContent += '<tr>'+
				'<td>'+row+'</td>'+
				'<td>'+detail[i].goodsName+'</td>'+
				'<td>'+detail[i].packingName+'</td>'+
				'<td>'+detail[i].standards+'</td>'+
				'<td>'+deliverPrice+'</td>'+
				'<td>'+ordercount.toFixed(2)+'</td>'+
				'<td>'+arrivecount.toFixed(2)+'</td>'+
				'</tr>';
				allMoney += Number(detail[i].taxprice)*detail[i].arrivecount;
			}
			//jiaself 2017-05-24
			var temp = "";
			if(status == 0){
				temp +=  "<div style='float:right;font-size:18px;font-weight:bold;'>"+ '订单状态:未确认' + "</div><br/><br/><br/>";
			}else if (status == 1){
				temp +=  "<div style='float:right;font-size:18px;font-weight:bold;'>"+ '订单状态:确认中' + "</div><br/><br/><br/>";
			}else if (status == 2){
				temp +=  "<div style='float:right;font-size:18px;font-weight:bold;'>"+ '订单状态:已确认' + "</div><br/><br/><br/>";
				
			}
			headerInfo +=temp+'<span class="floatLeft" >总金额:'+allMoney.toFixed(2)+'元</span>'+
			'<span class="floatRight">作业名称:'+collect[0].deliveryTaskName+'&nbsp;网点名称:'+collect[0].orgName+'&nbsp;到货日期:'+collect[0].deliveryDate+'&nbsp;单号:'+detail[0].bookingId+
			'&nbsp;行数:'+size+
			'</span>';
			tabContent+='</tbody>';
			$('.header').html(header);
			$('#headerInfo').html(headerInfo);
			$('#tabContent').html(tabContent);
			break;
		case "history":
			header="<h2>历史订货单</h2>";
			size = detail.length;
			tabContent +='<thead>'+
                  		 '<tr>'+
                         '<th>行号</th>'+
                         '<th>品名</th>'+
                         '<th>单位</th>'+
                         '<th>规格</th>'+
						 '<th>单价</th>'+
                         '<th>数量</th>'+
                         '</tr>'+
                		 '</thead><tbody>';
			allMoney = 0;
			for(var i in detail){
				row = parseInt(i)+1;
				deliverPrice = detail[i].deliverPrice;
				count = detail[i].count;
				tabContent += '<tr>'+
                  			  '<td>'+row+'</td>'+
                  			  '<td>'+detail[i].goodsName+'</td>'+
                  			  '<td>'+detail[i].packingName+'</td>'+
                  			  '<td>'+detail[i].standards+'</td>'+
							  '<td>'+deliverPrice.toFixed(2)+'</td>'+
							  '<td>'+count.toFixed(2)+'</td>'+
                  			  '</tr>';
				allMoney += Number(detail[i].deliverPrice)*detail[i].count;
			}
			deliveryDate = collect.deliveryDate;
			headerInfo +='<span class="floatLeft">预估金额:'+allMoney.toFixed(2)+'元</span>'+
	 					 '<span class="floatRight">单号:'+collect.pk+'&nbsp;订货类型:'+collect.mouldName+
	 					 '&nbsp;配送日期:'+deliveryDate.substring(0,10)+
						 '&nbsp;行数:'+size+
	 					 '</span>';
			tabContent+='</tbody>'
			$('.header').html(header);
			$('#headerInfo').html(headerInfo);
			$('#tabContent').html(tabContent);
			break;
			
	}
	AutoPage.init("headerInfo", "tabContent", "footerInfo","pageBreak","pageNum",34);	
}
function printManager(){
	$("#print-content").print();
};
