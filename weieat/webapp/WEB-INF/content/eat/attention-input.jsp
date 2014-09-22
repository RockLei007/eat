<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>邮件通知管理</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
	<script>
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					userId: {
						required: true
					}
				}
			});
		});
	</script>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
	<%@ include file="/common/manage/header.jsp" %>
	<div class="main_title">新建通知用户</div>
	<form id="inputForm" action="attention!save.action" method="post">
		<!--input type="hidden" name="userId" value="5"-->
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">用户名:</td>
				<td class="td2"><s:combobox name="userId" list="userList" listKey="id" listValue="name" theme="custom"/>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_attention">
						<input class="main_but" type="submit" value="<s:text name="commit"/>" />&nbsp;
					</security:authorize>
					<input class="main_but" type="button" value="<s:text name="back"/>" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>
	</div>	
	</div>
</div>
<%@ include file="/common/manage/footer.jsp" %>
</body>
</html>
