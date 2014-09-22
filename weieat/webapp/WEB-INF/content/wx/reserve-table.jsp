<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>点菜管理</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
</head>

<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100" class="back"><a href="#" onclick="history.back();">返回上级</a></td>
    <td>餐桌包厢详细信息</td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
			<tr>
				<td width="100"><p class="detail_tr">餐桌包厢:</p></td>
				<td valign="middle"><p class="detail_tl"><span class="name_color"><b>${table.name}</b></span></p></td>
			</tr>
			<tr>
				<td><p class="detail_tr">分类:</p></td>
				<td valign="middle"><p class="detail_tl">${table.categories}</p></td>
			</tr>
			<tr>
				<td><p class="detail_tr">类型:</p></td>
				<td valign="middle"><p class="detail_tl">${table.type}</p></td>
			</tr>	
			<tr>
				<td><p class="detail_tr">是否清真:</p></td>
				<td valign="middle"><p class="detail_tl"><s:if test="islamic == 0">是</s:if>
						<s:if test="islamic == 1">否</s:if></p></td>
			</tr>	
			<tr>
				<td><p class="detail_tr">详细描述:</p></td>
				<td><p class="detail_tl">${discription}</p></td>
			</tr>
		</table>
</body>
</html>
