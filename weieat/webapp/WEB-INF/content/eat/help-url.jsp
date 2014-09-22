<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>微信接口</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
	<%@ include file="/common/manage/header.jsp" %>
	<div class="main_title">微信接口</div>
		<div class="main">
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
			<tr>
				<td class="td1">接口:</td>
				<td class="td2"><textarea id="content" name="content" rows='5' cols='50'>${url}</textarea>&nbsp;</td>
			</tr>
		</table>
	</div>	
	</div>
</div>
<%@ include file="/common/manage/footer.jsp" %>
</body>
</html>
