<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐桌包厢管理</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
	
	<script charset="utf-8" src="${ctx}/js/kindeditor-min.js" type="text/javascript"></script>
	<script charset="utf-8" src="${ctx}/js/zh_CN.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
		
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="discription"]', {
				cssPath : '/css/prettify.css',
				uploadJson : '/js/jsp/upload_json.jsp',
				fileManagerJson : '/js/jsp/file_manager_json.jsp',
				allowFileManager : true
			});
		});
	</script>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
	<%@ include file="/common/manage/header.jsp" %>
	<div class="main_title"><s:if test="id == null">新建</s:if><s:else>修改</s:else>餐桌包厢</div>
	<div class="main">
	<form id="inputForm" action="table!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table  class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">餐桌包厢名:</td>
				<td class="td2"><input type="text" class="main_text" name="name" size="40" id="name" value="${name}"/></td>
			</tr>
			<tr>
				<td class="td1">分类:</td>
				<td class="td2"> <select name="categories">
					<s:iterator value="tableCate" var="tcString">
					<option value="<s:property value="#tcString"/>" <s:if test="#tcString==categories">SELECTED</s:if> ><s:property value="#tcString" /></option>
					</s:iterator>
			</select></td>
			</tr>
			<tr>
				<td class="td1">类型:</td>
				<td class="td2"> <select name="type">
					<s:iterator value="tableType" var="ttString">
					<option value="<s:property value="#ttString"/>"  <s:if test="#ttString==type">SELECTED</s:if> ><s:property value="#ttString" /></option>
					</s:iterator>
			</select></td>
			</tr>	
			<tr>
				<td class="td1">验证码:</td>
				<td class="td2"><input type="text" class="main_text" id="identity" name="identity" size="15" value="${identity}"/></td>
			</tr>
			<tr>
				<td class="td1">可否预订: </td>
				<td class="td2"><select name="reserve">
						<option value= 0 <s:if test="reserve == 0">SELECTED</s:if>>是</option>
						<option value= 1 <s:if test="reserve == 1">SELECTED</s:if>>否</option>
					</select></td>
			</tr>			
			<tr>
				<td class="td1">是否清真: </td>
				<td class="td2"><select name="islamic">
						<option value= 0 <s:if test="islamic == 0">SELECTED</s:if>>是</option>
						<option value= 1 <s:if test="islamic == 1">SELECTED</s:if>>否</option>
					</select></td>
			</tr>	
			<tr>
				<td colspan="2" class="td1">详细描述: </td>
			</tr>
			<tr>
				<td colspan="2" class="td2"><textarea id="discription" name="discription" style="width:800px;height:400px;">${discription}</textarea></td>
			</tr>	
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_table">
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
</div>
</body>
</html>

