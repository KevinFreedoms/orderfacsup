jQuery.fn.showLoading=function(a){var b,c={addClass:"",beforeShow:"",afterShow:"",hPos:"center",vPos:"center",indicatorZIndex:5001,overlayZIndex:5e3,parent:"",marginTop:0,marginLeft:0,overlayWidth:null,overlayHeight:null};jQuery.extend(c,a);var d=jQuery("<div></div>"),e=jQuery("<div></div>");b=c.indicatorID?c.indicatorID:jQuery(this).attr("id"),jQuery(d).attr("id","loading-indicator-"+b),jQuery(d).addClass("loading-indicator"),c.addClass&&jQuery(d).addClass(c.addClass),jQuery(e).css("display","none"),jQuery(document.body).append(e),jQuery(e).attr("id","loading-indicator-"+b+"-overlay"),jQuery(e).addClass("loading-indicator-overlay"),c.addClass&&jQuery(e).addClass(c.addClass+"-overlay");var f,g,h=jQuery(this).css("border-top-width"),i=jQuery(this).css("border-left-width");h=isNaN(parseInt(h))?0:h,i=isNaN(parseInt(i))?0:i;var j=jQuery(this).offset().left+parseInt(i),k=jQuery(this).offset().top+parseInt(h);f=null!==c.overlayWidth?c.overlayWidth:parseInt(jQuery(this).width())+parseInt(jQuery(this).css("padding-right"))+parseInt(jQuery(this).css("padding-left")),g=null!==c.overlayHeight?c.overlayWidth:parseInt(jQuery(this).height())+parseInt(jQuery(this).css("padding-top"))+parseInt(jQuery(this).css("padding-bottom")),jQuery(e).css("width",f.toString()+"px"),jQuery(e).css("height",g.toString()+"px"),jQuery(e).css("left",j.toString()+"px"),jQuery(e).css("position","absolute"),jQuery(e).css("top",k.toString()+"px"),jQuery(e).css("z-index",c.overlayZIndex),c.overlayCSS&&jQuery(e).css(c.overlayCSS),jQuery(d).css("display","none"),jQuery(document.body).append(d),jQuery(d).css("position","absolute"),jQuery(d).css("z-index",c.indicatorZIndex);var l=k;c.marginTop&&(l+=parseInt(c.marginTop));var m=j;c.marginLeft&&(m+=parseInt(c.marginTop)),"center"==c.hPos.toString().toLowerCase()?jQuery(d).css("left",(m+(jQuery(e).width()-parseInt(jQuery(d).width()))/2).toString()+"px"):"left"==c.hPos.toString().toLowerCase()?jQuery(d).css("left",(m+parseInt(jQuery(e).css("margin-left"))).toString()+"px"):"right"==c.hPos.toString().toLowerCase()?jQuery(d).css("left",(m+(jQuery(e).width()-parseInt(jQuery(d).width()))).toString()+"px"):jQuery(d).css("left",(m+parseInt(c.hPos)).toString()+"px"),"center"==c.vPos.toString().toLowerCase()?jQuery(d).css("top",(l+(jQuery(e).height()-parseInt(jQuery(d).height()))/2).toString()+"px"):"top"==c.vPos.toString().toLowerCase()?jQuery(d).css("top",l.toString()+"px"):"bottom"==c.vPos.toString().toLowerCase()?jQuery(d).css("top",(l+(jQuery(e).height()-parseInt(jQuery(d).height()))).toString()+"px"):jQuery(d).css("top",(l+parseInt(c.vPos)).toString()+"px"),c.css&&jQuery(d).css(c.css);var n={overlay:e,indicator:d,element:this};return"function"==typeof c.beforeShow&&c.beforeShow(n),jQuery(e).show(),jQuery(d).show(),"function"==typeof c.afterShow&&c.afterShow(n),this},jQuery.fn.hideLoading=function(a){var b={};return jQuery.extend(b,a),b.indicatorID?indicatorID=b.indicatorID:indicatorID=jQuery(this).attr("id"),jQuery(document.body).find("#loading-indicator-"+indicatorID).remove(),jQuery(document.body).find("#loading-indicator-"+indicatorID+"-overlay").remove(),this};