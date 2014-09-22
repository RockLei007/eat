<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>已点菜</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
</head>


<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75" class="back"><a href="dishes!menu.action">返回上级</a></td>
    <td>已点菜</td>
    <td width="75" >&nbsp;&nbsp;</td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
<tr>
	<td colspan="3">${tableNames}</td>
</tr>	
<s:iterator value="dishesList">
  <tr>
    <td colspan="3">${datetime}&nbsp;&nbsp;金额：${money}&nbsp;&nbsp;状态：
    	<s:if test="state == 0">未处理</s:if><s:if test="state == 1">已确定</s:if><s:if test="state == 2">取消</s:if><s:if test="state == 3">加菜</s:if><s:if test="state == 4">结束</s:if>
    </td>
  </tr>
  <tr bgcolor="#fff2db"><td>名称</td><td>单价</td><td>数量</td></tr>
	<s:iterator value="foodList"> 		   	
  <tr>
  <td>${name}</td>
  <td>${price}</td>
  <td>${number}</td>
  </tr>
  </s:iterator>	
 </s:iterator>
</table>
</form>
</body>
</html>
