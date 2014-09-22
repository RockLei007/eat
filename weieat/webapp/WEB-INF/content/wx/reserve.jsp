<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>定餐信息查询</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
	<script>
		function confirm(tableId){
			document.getElementById("tableId").value = tableId;
			document.getElementById("mainForm").action="reserve!confirm.action";
			document.getElementById("mainForm").submit();
		}
	</script>
</head>
</body>
<body>
<form id="mainForm" action="reserve.action" method="post">
<s:token></s:token>
<input type="hidden" name="tableId" id="tableId" value=""/>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100" class="back"><a href="reserve!input.action">返回上级</a></td>
    <td>订餐信息查询</td>
    <td width="75" class="my_menu"><a href="reserve!order.action">我的订餐</a></td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
	<tr>
		<td colspan="7">
			<div id="message"><s:actionmessage cssClass="success"/></div>
	  </td>	
  </tr>	
  <tr bgcolor="#fff2db">
    <th width="50" height="30">编号</th>
    <th width="70">类型</th>
    <th width="90">包厢名</th>
    <th width="70">分类</th>
    <th width="90">是否清真</th>
    <th>&nbsp;</th>
    <th width="100">操作</th>
    </tr>
    
  <s:iterator value="page.result">
  <tr>
    <td>${id}&nbsp;</td>
    <td>${type}&nbsp;</td>
    <td><a href="reserve!tableDetail.action?tableId=${id}">${name}</a>&nbsp;</td>
    <td>${categories}&nbsp;</td>
    <td><s:if test="islamic == 0">是</s:if><s:else>否</s:else>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div class="reserve"><a href="#" onclick="confirm(${id});"></a></div></td>
  </tr>
  </s:iterator>
  
</table>
<table class="menu_bot" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="middle">&nbsp;</td>
    <%@ include file="/common/turnpage.jsp" %>
    <td width="75" class="choose"><a href="reserve!input.action">重新查询</a></td>
  </tr>
</table>
</form>
</body>
</html>
