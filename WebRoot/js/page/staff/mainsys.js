//获得管理管理列表
var staff=JSON.parse(new Base64().decode(localStorage.getItem('staff')));
//构建管理列表
function createMainSysList(){
	var mainSyslist="";
	for (var key in staff){
		
		mainSyslist+=
			'<a href="'+ basePath+staff[key].operation +'"  rel="external" data-role="button" class="ui-btn ui-shadow ui-corner-all left">'+
        	'<div class="ui-grid-a">'+
        	'<div>'+
        	'<img src='+basePath+staff[key].ref2+'>'+
        	'</div>'+
        	'<div>'+
        	'<span>'+ staff[key].functionName+'</span>'+
        	'</div> '+
        	'</div>'+
        	'</a>'
	}
	$('#list').html(mainSyslist);
}
createMainSysList();