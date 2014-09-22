<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link  href="${ctx}/css/style1.css" rel="stylesheet" type="text/css"/>

	<title>点菜信息录入</title>
	<script>
		$(document).ready(function() {
			$("#inputForm").validate();
		});
	</script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />

</head>
<body>

<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">		
  <tr>
    <td class="back"><a href="#" onclick="history.back();">返回上级</a></td>
    <td>点菜信息录入</td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
	<form id="inputForm" action="dishes!check.action" method="post">
  <tr>
    <td width="75" rowspan="5" valign="top" class="table_num"><b>请选择<br />
      您就餐<br />
      的桌号</b>
      <div id="message"><s:actionmessage cssClass="success"/></div>
	</td>
   <td><s:checkboxlist name="checkedTableIds" list="tableList" listKey="id" listValue="name" theme="custom"/>&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
    <td colspan="2"><span class="red">为了避免点错菜，请输入您就餐桌号的验证码</span><br>
      <input type="text" name="identitys" id="identitys" class="check_code" value="${identity}"/></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" name="button" id="button" class="submit_but" value="<s:text name="commit"/>" /><input type="submit" name="button" id="button" class="back_but" value="<s:text name="back"/>" onclick="history.back()"/></td>
  </tr>
  	</form>
</table>
</body>
</html>
