<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>组织机构管理</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<%@ include file="/common/manage/top.jsp" %>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					name: {
						required: true,
						remote: "organization!checkOrgName.action?1=1" + encodeURIComponent('${name}')
					},
					address: "required",
					phone: "required",
					identity: "required"
				},
				messages: {
					name: {
						remote: "组织机构已存在"
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
	<div class="main_title"><s:if test="id == null">创建</s:if><s:else>修改</s:else>组织机构</div>
	<div class="main">
	<form id="inputForm" action="organization!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			
			<tr>
				<td class="td1">名称:</td>
				<td class="td2"><input type="text" class="main_text" id="name" name="name" size="60" value="${name}"/></td>
			</tr>
			<tr>
				<td class="td1">地址:</td>
				<td class="td2"><input type="text" class="main_text" id="address" name="address" size="80" value="${address}"/></td>
			</tr>
			<tr>
				<td class="td1">电话:</td>
				<td class="td2"><input type="text" class="main_text" id="phone" name="phone" size="25" value="${phone}"/></td>
			</tr>
			<tr>
				<td class="td1">传真:</td>
				<td class="td2"><input type="text" class="main_text" id="fax" name="fax" size="25" value="${fax}"/></td>
			</tr>
			<tr>
				<td class="td1">标志:</td>
				<td class="td2"><input type="text" class="main_text" id="symbol" name="symbol" size="25" value="${symbol}"/></td>
			</tr>
			<tr>
				<td class="td1">分类:</td>
				<td class="td2"><select name="categories">
					<option ></option>
					<option value="国营企业" <s:if test="categories == '国营企业'">SELECTED</s:if>>国营企业</option>
					<option value="民营企业" <s:if test="categories == '民营企业'">SELECTED</s:if>>民营企业</option>
					<option value="外资企业" <s:if test="categories == '外资企业'">SELECTED</s:if>>外资企业</option>
					<option value="股份制" <s:if test="categories == '股份制'">SELECTED</s:if>>股份制</option>
					<option value="个体户" <s:if test="categories == '个体户'">SELECTED</s:if>>个体户</option>
					<option value="其它" <s:if test="categories == '其它'">SELECTED</s:if>>其它</option>
			</select>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td class="td1">描述:</td>
				<td class="td2"><textarea id="description" name="description" rows='3' cols='40' class="main_textarea">${description}</textarea></td>
			</tr>
			<tr>
				<td class="td1">识别码:</td>
				<td class="td2"><input type="text" class="main_text" id="identity" name="identity" size="20" value="${identity}"/></td>
			</tr>
			<tr>
				<td colspan="2">
						<input class="main_but" type="submit" value="<s:text name="commit"/>"/>&nbsp;
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
