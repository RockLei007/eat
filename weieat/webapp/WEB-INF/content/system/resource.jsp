<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles 资源权限列表</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
<%@ include file="/common/manage/header.jsp" %>
<div class="main">
	<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
			<tr>
				<th>顺序</th>
				<th>URL</th>
				<th>授权列表</th>
			</tr>

			<s:iterator value="allResourceList">
				<tr>
					<td>${position}</td>
					<td>${value}</td>
					<td>${authNames}</td>
				</tr>
			</s:iterator>
		</table>
	<%@ include file="/common/turnpage.jsp" %>
	</div>	
				<div class="clear"></div>
			</div>
		</div>
<div class="clear"></div>
<%@ include file="/common/manage/footer.jsp" %>
</div>
</body>
</html>
