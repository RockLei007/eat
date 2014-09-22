$(function(){
	  $("#load").hide();//隐藏loading提示
    $("#template").hide();//隐藏模板
    bind();//绑定第一页的数据         
        
});
   
//AJAX方法取得数据并显示到页面上
function bind()
{
    $("[id=ready]").remove();
    $("#load").show();
    $.ajax({
        type: "post",//使用get方法访问后台
        dataType: "json",//返回json格式的数据
        url: "/eat/dishes!getFoodArray.action",//要访问的后台地址
        data: "id=" + request('id'),//要发送的数据
        success:function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
        error: function(e){alert(e);},
        complete: function(msg){//msg为返回的数据，在这里做数据绑定
						var data = msg.responseText;
						var dataValue = $.parseJSON(data);
            $.each(dataValue.foodArray, function(i, n){
                var row = $("#template").clone();
                row.find("#name").text(n.name);
                row.find("#number").text(n.number);
                row.find("#weixinId").text(n.weixinId);
                row.find("#price").text(n.price);
                row.find("#foodId").text(n.foodId);
                row.attr("id","ready");//改变绑定好数据的行的id
                row.appendTo("#datas");//添加到模板的容器中
            });
           $("[id=ready]").show();
        }
    });
}

function request(paras) {
    var url = location.href;
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    var paraObj = {}
    for (i = 0; j = paraString[i]; i++) {
        paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
    }
    var returnValue = paraObj[paras.toLowerCase()];
    if (typeof (returnValue) == "undefined") {
        return "";
    } else {
        return returnValue;
    }
}

function request(paras){
	var url = location.href;  
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");  
	var paraObj = {}  
	for (i=0; j=paraString[i]; i++){  
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);  
	}  
	var returnValue = paraObj[paras.toLowerCase()];  
	if(typeof(returnValue)=="undefined"){  
		return "";  
	}else{  
		return returnValue;  
	}  
}