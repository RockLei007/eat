<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>点菜单</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
	<script>
		function add(foodId){
			document.getElementById("foodId").value = foodId;
			document.getElementById("orderForm").action="dishes!addToMenu.action";
			document.getElementById("orderForm").submit();
		}
		
		function substract(foodId){
			document.getElementById("foodId").value = foodId;
			document.getElementById("orderForm").action="dishes!removeToMenu.action";
			document.getElementById("orderForm").submit();
		} 
		
		function confirm(){
			document.getElementById("orderForm").action="dishes!confirm.action";
			document.getElementById("orderForm").submit();
		}
	</script>
</head>
<body>
<form id="orderForm" action="dishes!confirm.action" method="post">
	<s:token></s:token>
		<input type="hidden" name="foodId" id="foodId" value="">
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75" class="back"><a href="dishes!order.action">返回上级</a></td>
    <td>${tableNames}</td>
    <td width="75" class="my_menu"><a href="dishes!view.action">已点菜</a></td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
	<tr>
		<td colspan="6">
			<div id="message"><s:actionmessage cssClass="success"/></div>
	  </td>	
  </tr>	
  <tr bgcolor="#fff2db">
    <th width="100" height="30">菜名</th>
    <th width="100">分类</th>
    <th width="60">数量</th>
    <th width="100">价格</th>
    <th>&nbsp;</th>
    <th width="150">操作</th>
  </tr>
  <s:iterator value="orderList">
  <tr>
    <td>${name}</td>
    <td>${categories}</td>
    <td>${number}</td>
    <td>${price}</td>
    <td>&nbsp;</td>
    <td>
    	<div class="substract"><a href="#" onclick="substract(${foodId});"></a></div><div class="add"><a href="#" onclick="add(${foodId});"></a></div></td>
  </tr>
  </s:iterator>
  <tr>
    <td colspan="6"><b>金额合计：¥${sum}</b></td>
  </tr>
  
</table>
<table class="menu_bot" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="middle">&nbsp;</td>
    <td width="75" class="choose"><a href="#" onclick="confirm();">现在上菜</a></td>
  </tr>
</table>
</form>	
</body>
</html>
