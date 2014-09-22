<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 菜单管理</title>
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
	<div class="main_title"><s:if test="id == null">创建</s:if><s:else>修改</s:else>菜单</div>
	<div class="main">
	<form action="menu!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">菜单名:</td>
				<td class="td2"><input type="text" class="main_text" id="name" name="name" size="40" value="${name}" class="required"/></td>
			</tr>
			<tr>
				<td class="td1">图片:</td>
				<td class="td2"><input type="text" class="main_text" id="picture" name="picture" size="80" value="${picture}" class="required"/></td>
			</tr>
			<tr>
				<td class="td1">路径:</td>
				<td class="td2"><input type="text" class="main_text" id="path" name="path" size="80" value="${path}" class="required"/></td>
			</tr>
			<tr>
				<td class="td1">上级菜单:</td>
				<td class="td2"><input type="text" class="main_text" id="parent" name="parent" size="40" value="${parent}" class="required"/></td>
			</tr>
			<tr>
				<s:select label="显示目标" list="#{'main':'显示在主窗口中','dialog':'弹出对话框','window':'弹出新窗口'}" value="target" 
		           	 name="target" emptyOption="false" />
			</tr>
			<tr>
				<td class="td1">菜单顺序:</td>
				<td class="td2"><input type="text" class="main_text" id="serial" name="serial" size="40" value="${serial}" class="required"/></td>
			</tr>
			<tr>
				<td class="td1">授权:</td>
				<td class="td2">
					<s:checkboxlist name="checkedRoleIds" list="allRoleList" listKey="id"
										listValue="name" theme="custom"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_menu">
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
