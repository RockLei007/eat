<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>顾客管理</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
	<script>
		$(document).ready(function() {
			$("#weixinId").focus();
			$("#inputForm").validate();
		});
	</script>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
	<%@ include file="/common/manage/header.jsp" %>
	<div class="main_title"><s:if test="id == null">新建</s:if><s:else>修改</s:else>顾客</div>
	<div class="main">
	<form id="inputForm" action="customer!save.action" method="post">
		<input type="hidden" name="id" value="${id}">
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">微信ID:</td>
				<td class="td2"><input type="text" class="main_text" name="weixinId" size="20" id="weixinId" value="${weixinId}"/>&nbsp;</td>
			</tr>
			<tr>
				<td class="td1">名字:</td>
				<td class="td2"><input type="text" class="main_text" name="name" size="25" id="name" value="${name}"/>&nbsp;</td>
			</tr>
			<tr>
				<td class="td1">电话:</td>
				<td class="td2"><input type="text" class="main_text" name="phone" size="25" id="phone" value="${phone}"/>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_customer">
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
