
/***季节单品js******************/
function getAllSeasonGoods(){
	$.ajax({
		type: "post",
		url: basePath+"facorder/season/toSeasonGoodsList.do",
		dataType: "json",
		timeout: 20000,
		success: function (data) {
			title = "";
			content="";
			list=data.seasonGoods;
			if(data.success){
				arrayDate = judgedate(list);
				if(isPC()){
					title = '<h4 style="text-align:center">季节单品</h4>';
					if(list != null){
						for ( var int = 0; int < list.length; int++) {
							content += '<tr style="height:40px;"><td>'+list[int].goodsName+'</td>'+
									   '<td>'+list[int].startDate+'</td>'+
									   '<td>'+list[int].endDate+'</td>';
							switch(arrayDate[int]){
								case"过期":
									content+='<td style="color:#FF0000;" >'+arrayDate[int]+'</td></tr>';
									break;
								case"当期":
									content+='<td style="color:#000000;" >'+arrayDate[int]+'</td></tr>';
									break;
								case"未到":
									content+='<td style="color:#3498db;" >'+arrayDate[int]+'</td></tr>';
									break;
							}
						}
						$("#ordermanager").show();
						$("#formHeader").html(title);
						$('#goodscontent').html(content);
						//TablePage("#kingTable",10);
						//2017-08-15
						initPagination("#kingTable",10);
					}else{
						$("#ordermanager").hide();
						$('#withoutdata').show();
					}
					
				}else{
					if(list != null){
						$(".delivery").show()
						for ( var int = 0; int < list.length; int++) {
							var startDate = list[int].startDate.substring(2);
							var endDate = list[int].endDate.substring(2);
							content+='<li class="urgentlist">'+
									 '<div class="ordercode" style="margin-top:10px;">'+
									 '<span>品名：</span><span>'+list[int].goodsName+'</span>'
							switch(arrayDate[int]){
								case"过期":
									content+='<span style="width:20%;text-align:right;color:#FF0000; float:right;">'+arrayDate[int]+'</span>'
									break;
								case"当期":
									content+='<span style="width:20%;text-align:right;color:#000000;float:right;">'+arrayDate[int]+'</span>';
									break;
								case"未到":
									content+='<span style="width:20%;text-align:right;color:#3498db;float:right;">'+arrayDate[int]+'</span>';
									break;
							};
							content+= '</div>'+
									 '<div class="orderinfo">'+
									 '<span>上市：</span>'+
									 '<span>'+startDate+'</span>'+
									 '</div>'+
									 '<div class="orderinfo endData">'+
									 '<span>下市：</span>'+
									 '<span>'+endDate+'</span>'+
									 '</div>'+
									'<div class="clearfloat"></div>'+
							        '</li>'
						}
					}else{
						$(".noinfo").show()
						$(".delivery").hide()
					}
					$('#seasongoods').html(content);
				};
			}else{
				swal({   
					title: "提示信息",
					text: data.info,
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
		    };
	     },
	     error:function(data){
	    	 swal({   
					title: "提示信息",
					text: "获取失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
	     }
	});
}
//判断季节单品的日期

function judgedate(list){
	var currentDate = getNowFormatDate();
	var statusArray =  new Array();
	if(list != null){
		for ( var int = 0; int < list.length; int++) {
			if(currentDate<list[int].startDate){
				statusArray.push("未到");
			}else if(list[int].startDate<=currentDate && currentDate<=list[int].endDate){
				statusArray.push("当期");
			}else if(currentDate>list[int].endDate){
				statusArray.push("过期");
			}
		}
	}
	return statusArray;
}
//搜索单品
function searchpro(obj){
	var searchStr=$(obj).val()
	if(searchStr!=""){
		obj = $("#seasongoods>li").hide().filter(":contains('"+searchStr+"')").show();  
	}else{
		obj = $("#seasongoods>li").show();  
	}
	if(obj.length==0){
		$(".delivery").hide()
		$(".noinfo").show()
	}else{
		$(".noinfo").hide()
		$(".delivery").show()
	}
}