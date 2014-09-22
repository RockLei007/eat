<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>选择点菜</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
	<script>
			
		function add(foodId){
			document.getElementById("foodId").value = foodId;
			document.getElementById("orderForm").action="dishes!save.action";
			document.getElementById("orderForm").submit();
		}
		
		function substract(foodId){
			document.getElementById("foodId").value = foodId;
			document.getElementById("orderForm").action="dishes!remove.action";
			document.getElementById("orderForm").submit();
		} 
		
		function confirm(){
			document.getElementById("orderForm").action="dishes!confirm.action";
			document.getElementById("orderForm").submit();
		}
		
		function onSelect(){
			document.getElementById("orderForm").action="dishes!order.action";
			document.getElementById("orderForm").submit();
		}
	</script>
</head>


<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75" class="back"><a href="dishes!check.action">返回上级</a></td>
    <td>菜 单</td>
    <td width="75" class="my_menu"><a href="dishes!menu.action">我的菜单</a><div class="menu_num">${count}</div></td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
	<tr>
		<td colspan="3">
			<div id="message"><s:actionmessage cssClass="success"/></div>
	  </td>	
  </tr>	
<s:iterator value="foodPage.result">
  <tr>
    <td><a href="dishes!foodDetail.action?foodId=${id}"><image src="${image}" height="100" width="100"></a></td>
    <td valign="middle"><p class="two_row">${name}<br /><span class="red">${price}</span></p></td>
    <td width="120">
			<s:iterator value="cartOrder"> 		   	
    	<s:if test="id == foodId"><div class="textbox">${number}</div></s:if>
    	</s:iterator>
    	<div class="substract"><a href="#" onclick="substract(${id});"></a></div><div class="add"><a href="#" onclick="add(${id});"></a></div></td>
  </tr>
 </s:iterator>
</table>
<table class="menu_bot" width="100%" border="0" cellspacing="0" cellpadding="0">
	<form id="orderForm" action="dishes!save.action" method="post">
		<s:token></s:token>
		<input type="hidden" name="foodId" id="foodId" value="">
  <tr>
    <td valign="middle">
    	<select name="filter_EQS_categories" onchange="onSelect();" class="menu_select" value="${param['filter_EQS_categories']}">
					<option value="">分类</option>
					<option value="all">全部</option>
					<s:iterator value="foodCate" var="fcString">
					<option value="<s:property value="#fcString"/>" <s:if test="fcString==param['filter_EQS_categories']">SELECTED</s:if>><s:property value="#fcString" /></option>
					</s:iterator>
			</select>	
    </td>
    <td valign="middle"><div class="sum">¥${sum}</div></td>
    <td width="75" class="choose"><a href="#" onclick="confirm();">我要上菜</a></td>
  </tr>
  </form>
</table>
</form>
</body>
</html>
