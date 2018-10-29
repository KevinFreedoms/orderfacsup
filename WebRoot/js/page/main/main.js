function toPage(a){
	location.href=basePath+nav_url[a];
	}
function back(){
	location.href=basePath+"user/logout.do";
	}
var nav_url=["facorder/manager/toOrder.do",
             "facorder/manager/toDelivery.do",
             "facorder/distribute/toDistribute.do",
             "facorder/season/toSeason.do",
             "facorder/manager/toHistory.do",
             "facorder/manager/toMessage.do",
             "facorder/manager/toOrderForms.do",
             "facorder/manager/toRestPassword.do"
             ];