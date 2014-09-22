<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>帐号管理</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						required: true,
						remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')
					},
					name: "required",
					password: {
						minlength:6
					},
					passwordConfirm: {
						equalTo:"#password"
					},
					email:"email",
					checkedRoleIds:"required"
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
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
	<div class="main_title"><s:if test="id == null">创建</s:if><s:else>修改</s:else>用户</div>
	<div class="main">
	<form id="inputForm" action="user!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">登录名:</td>
				<td class="td2"><input type="text" class="main_text" name="loginName" size="40" id="loginName" value="${loginName}"/></td>
			</tr>
			<tr>
				<td class="td1">用户名:</td>
				<td class="td2"><input type="text" class="main_text" id="name" name="name" size="40" value="${name}"/></td>
			</tr>
			<tr>
				<td class="td1">密码:</td>
				<td class="td2"><input type="password" id="password" class="main_text" name="password" size="40" value=""/></td>
			</tr>
			<tr>
				<td class="td1">确认密码:</td>
				<td class="td2"><input type="password" id="passwordConfirm" class="main_text" name="passwordConfirm" size="40" value=""/>
				</td>
			</tr>
			<tr>
				<td class="td1">邮箱:</td>
				<td class="td2"><input type="text" class="main_text" id="email" name="email" size="40" value="${email}"/></td>
			</tr>
			
			<tr>
				<td class="td1">角色:</td>
				<td class="td2">
					<s:checkboxlist name="checkedRoleIds" list="allRoleList" listKey="id" listValue="name" theme="custom"/>
				</td>
			</tr>
			
			<s:if test="adminAuth == true">
			<tr>
				<td class="td1">组织机构:</td>
				<td class="td2">
					<s:combobox name="checkedOrgId" list="allOrgList" listKey="id" listValue="name" theme="custom"/>
				</td>
			</tr>
			</s:if>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_user">
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
