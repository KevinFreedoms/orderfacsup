/* ------------------------------------------------------------------------------------------------------------------------ */
/* 登陆操作 xxx 2016-03-17 */
/* ------------------------------------------------------------------------------------------------------------------------ */
$(".js-login").click(function(){
	user = $("#user").val().trim();
	pass = $("#pass").val().trim();
	if(user == '' || pass == ''){
		swal({   
			title: "错误信息",
			text: "用户账号和密码不能为空~",
			showCancelButton: false,
			confirmButtonColor: "#039"
		});
		return false;
	}
	doLogin();
});
function doLogin(){
	user = $("#user").val().trim();
	pass = $("#pass").val().trim();
	$.ajax({
		type: "post",
		url: basePath+"facorder/customer/dologin.do",
		data: {"model.userId":user,"model.passWord":pass,"model.phone":user},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			if(data.success){
				var temp = new Base64();
				var str = temp.encode(JSON.stringify(data.moulds));
				window.localStorage.setItem("moulds",str);
				//记住密码
				save();
				if(data.limit){
					swal({   
						  title: "提示信息",
						  text: data.info,
						  showCancelButton: false,
						  confirmButtonColor: "#039"
					},function(isConfirm){   
						 if(isPC()){
							location.href = basePath + "facorder/manager/toPcMain.do";
						}else{
							location.href = basePath + "facorder/message/toCheckMessage.do";
						}
					});
				}else{
					if(isPC()){
						location.href = basePath + "facorder/manager/toPcMain.do";
					}else{
						location.href = basePath + "facorder/message/toCheckMessage.do";
					}
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
					text: "登陆失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		}
	});
}
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 修改操作 xxx 2016-03-17 */
/* ------------------------------------------------------------------------------------------------------------------------ */
$(".js-pass").click(function(){
	$.mobile.changePage($("#passwordpage"));
});
/* ------------------------------------------------------------------------------------------------------------------------ */
/* 提交修改密码 xxx 2016-03-17 */
/* ------------------------------------------------------------------------------------------------------------------------ */
$(".js-sure").click(function(){
	if($("#username").val().length == 0)
	{
		swal({   
		  title: "错误信息",
		  text: '用户编码不能为空~请重试~',
		  showCancelButton: false,
		  confirmButtonColor: "#039"
		});
		return
	}
	if($("#password").val().length == 0)
	{
		swal({   
		  title: "错误信息",
		  text: '用户密码不能为空~请重试~',
		  showCancelButton: false,
		  confirmButtonColor: "#039"
		});
		return
	}
	if($("#newpassword").val().length == 0)
	{
		swal({   
		  title: "错误信息",
		  text: '修改的新密码不能为空~请重试~',
		  showCancelButton: false,
		  confirmButtonColor: "#039"
		});
		return
	}
	if($("#replaypassword").val().length == 0)
	{
		swal({   
		  title: "错误信息",
		  text: '请再次输入新的密码~请重试~',
		  showCancelButton: false,
		  confirmButtonColor: "#039"
		});
		return
	}
	if($("#newpassword").val() != $("#replaypassword").val())
	{
		swal({   
		  title: "错误信息",
		  text: '两次输入的新密码不一致，请重新输入~',
		  showCancelButton: false,
		  confirmButtonColor: "#039"
		});
		return
	}
	$.ajax({
		type: "post",
		url: basePath+"facorder/customer/doPassword.do",
		data: {"username":$("#username").val(),"password":$("#password").val(),"newpassword":$("#newpassword").val()},
		dataType: "json",
		timeout: 20000,  //
		success: function (data) {
			if(data.success){
				swal({   
					title: "提示信息",
					text: '修改成功~订货试一下~',
					showCancelButton: false,
					confirmButtonColor: "#039"
					},function(isConfirm){   
					if (isConfirm) {
						location.href = basePath;
					} 
				});
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
					text: "注册失败，请稍后重试~",
					showCancelButton: false,
					confirmButtonColor: "#039"
				});
			}
		}
	});
});

function save(){
	if ($('input:checkbox[name="checkbox-a"]').is(":checked")) {
		var temp = new Base64();
		var str_username = temp.encode($("#user").val());
        var str_password = temp.encode($("#pass").val());
        $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
        $.cookie("username", str_username, { expires: 7 });
        $.cookie("password", str_password, { expires: 7 });
	}else {
 		$.cookie("rmbUser", "false", { expire: -1 });
		$.cookie("username", "", { expires: -1 });
		$.cookie("password", "", { expires: -1 });
	}
}