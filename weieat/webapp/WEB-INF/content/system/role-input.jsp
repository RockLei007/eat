<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 角色管理</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
	<%@ include file="/common/manage/header.jsp" %>
	<div class="main_title"><s:if test="id == null">创建</s:if><s:else>修改</s:else>角色</div>
	<div class="main">
	<form action="role!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">角色名:</td>
				<td class="td2"><input type="text" class="main_text" id="name" name="name" size="40" value="${name}" class="required"/></td>
			</tr>
			<tr>
				<td class="td1">授权:</td>
				<td class="td2">
					<s:checkboxlist name="checkedAuthIds" list="allAuthorityList" listKey="id"
										listValue="remark" theme="custom"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_role">
						<input class="main_but" type="submit" value="<s:text name="commit"/>"/>&nbsp;
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
