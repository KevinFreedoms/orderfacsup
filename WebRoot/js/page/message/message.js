/* ------------------------------------------------------------------------------------------------------------------------ */
/* 显示消息雷列表 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function back(){
	location.href = basePath + "facorder/customer/toIndex.do";
}
function show()
{
	var content  = '';
	$.post(
		basePath + "facorder/message/toMessageInfo.do",
		function(data,textStatus){
			info = data.content;
			if(data.success){
				content = '<p style=" text-align:right; margin-right:5px">' +info.noticeDate + '<p>' +
					      '<p>' +info.content+ '<p>';
					      $('#content').html(content);
			}else{
				swal({   
					title: "错误信息",
					text: data.info,
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
			
 	}, 
	"json");
}
function searchMessage()
{
	if(isPC()){
		type = $("#timechange").find("option:selected").attr("value");
	}else{
		type = $("input[type='radio']:checked").attr("id").toString();
	}
	var info  = '';
	$.ajax({
		type: "post",
		url: basePath+"facorder/message/toMessageList.do",
		data: {"type":type},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			if(data.success){
				if(data.totalrecord>0){
					messages = data.list;
					for(var i in messages){
						name = messages[i].content;
						date = messages[i].noticeDate;
						id = messages[i].reckey;
						if(isPC()){
							info += '<div style="height:30px">'+
                                    '<input class="fld" id="'+ id +'" type="radio" name="F5_radio" style="float:left;margin:5px">'+
                                    '<label for="'+ id +'" style="width:50%;float:left;margin:5px">'+ name.substring(0,20)+'</label>'+
                                    '<span style="width:40%;float:left;font-size:.8em">发布时间：'+date.substring(0,10)+'</span>'+
									'<div class="clearfloat"></div>'+
                                    '</div>';
						}else{
							info +='<a rel="external" data-role="button" class="ui-btn ui-shadow ui-corner-all ui-icon-carat-r ui-btn-icon-right home_button" style="color:#333;background-color:#eee;border:1px solid #fff;" id="'+ id +'" onClick="showDetail(this)"><span style="font-size:90%; color:#000;float:left; ">'+ name.substring(0,5)+'</span><span style="font-size:70%;padding-right:10px;color:#009;float:right;">发布时间:'+date.substring(0,10)+'</span></a>';
						}
					}
					$('#querymanager').show();
				}else{
					$('#querymanager').hide();
				}
				if(isPC()){
					$("#messagelist").html(info);
					//初始化
					dates = $($("#messagelist").find("input")[0]);
					dates.click();
				}else{
					$("#messagelist").html(info).trigger( "create" );
				}
			}else{
				swal({   
					title: "错误信息",
					text: data.info,
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
				$('#querymanager').hide();
				$("#messagelist").html(info);	
			}
		},
		error: function (request, status, err) {
			if (status == "timeout")
			{
				swal({   
					title: "错误信息",
					text: "请求超时，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}else{
				swal({   
					title: "错误信息",
					text: "获取失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
			$("#messagelist").html(info);	
		}
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 显示消息明细 */
/* ------------------------------------------------------------------------------------------------------------------------ */
function showDetail(obj) {
	if(isPC()){
		id = $("#messagelist").find("input[type='radio']:checked").attr("id");
	}else{
		id = $(obj).attr("id");
	}
	$.ajax({
		type: "post",
		url: basePath+"facorder/message/toMessageInfoById.do",
		data: {"id":id},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			if(data.success){
				title = "";
				//表头

				content = data.content;
				if(isPC()){
					title = '<div style="text-align:left"><input class="js-backbtn commonbtn" id="btnSubmit" type="button" value="返回" onClick="toBack()"></div>'+
							'<h4 style="text-align:center">通知</h4>'+
							'<div style="height:15px;margin-top:3px;font-size: 0.9em;"><div style="text-align:right;width:80%;float:right;">'+content.noticeDate+'</div></div>';
					$('#formHeader').html(title);
					//表内容
					info = "<span>"+content.content+"</span>"
					$('#messagecontent').html(info);
					$('#messagelistC').hide();
					$('#messageinfoC').show();
				}else{
					$.mobile.changePage($("#messagedetail"));
					info = '<p style=" text-align:right; margin-right:5px">' +content.noticeDate + '<p>' +
							  '<p>' +content.content+ '<p>'
					$('#content').html(info);
				}
				
			}else{
				swal({   
					title: "错误信息",
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
					title: "错误信息",
					text: "请求超时，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}else{
				swal({   
					title: "错误信息",
					text: "获取失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}	
		}
	});
}
//订单返回
function toBack(){
	if(isPC()){
		$('#messagelistC').show();
		$('#messageinfoC').hide();
		//模块尺寸
		$('.wallpaper').css('height', $(window).height()-$('.clearfix').height());
	}else{
		location.href = basePath + "facorder/manager/toMessage.do";
	}
};