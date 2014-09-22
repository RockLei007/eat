<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订餐人信息录入</title>
	<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
</head>

<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100" class="back"><a href="reserve!check.action">返回上级</a></td>
    <td>订餐人信息录入</td>
  </tr>
</table>	
<table cellspacing="0" cellpadding="0" class="menu">
	<form id="inputForm" action="reserve!save.action" method="post">
		<s:token></s:token>
		<input type="hidden" name="tableId" value="${tableId}">
			<tr>
				<td width="100"><p class="detail_tr">名字:</p></td>
				<td valign="middle"><input type="text" name="name" size="25" id="name" value=""/>&nbsp;</td>
			</tr>
			<tr>
				<td><p class="detail_tr">电话:</p></td>
				<td valign="middle"><input type="text" name="phone" size="25" id="phone" value=""/>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
						<input type="submit" value="<s:text name="commit"/>" class="submit_but"/>&nbsp;
					  <input type="button" value="<s:text name="back"/>" class="back_but" onclick="history.back()"/>
				</td>
			</tr>
	</form>
</table>
</body>
</html>
