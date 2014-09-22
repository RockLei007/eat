<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>点菜管理</title>
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
			
			K('#uploadButton').click(function() {
					editor1.loadPlugin('image', function() {
						editor1.plugin.imageDialog({
							showRemote : true,
							imageUrl : K('#image').val(),
							clickFn : function(url, title, width, height, border, align) {
								K('#image').val(url);
								editor1.hideDialog();
							}
						});
					});
				});
			
		});
	</script>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
	<%@ include file="/common/manage/header.jsp" %>
	<div class="main_title"><s:if test="id == null">新建</s:if><s:else>修改</s:else>菜品</div>
	<div class="main">
	<form id="inputForm" action="food!save.action" method="post" enctype="mutilpartform-data">
		<input type="hidden" name="id" value="${id}"/>
		<table class="data_table1" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td1">菜名:</td>
				<td class="td2"><input type="text" class="main_text" name="name" size="40" id="name" value="${name}"/></td>
			</tr>
			<tr>
				<td class="td1">分类:</td>
				<td class="td2"><select name="categories">
						<s:iterator value="foodCate" var="fcString">
						<option value="<s:property value="#fcString"/>" <s:if test="#fcString==categories">SELECTED</s:if>><s:property value="#fcString" /></option>
						</s:iterator>
					</select></td>
			</tr>
			<tr>
				<td class="td1">菜系:</td>
				<td class="td2"><select name="classes">
						<s:iterator value="foodClasses" var="fcaString">
						<option value="<s:property value="#fcaString"/>" <s:if test="#fcaString==classes">SELECTED</s:if>><s:property value="#fcaString" /></option>
						</s:iterator>
					</select></td>
			</tr>	
			<tr>
				<td colspan="2" class="td1"><img src="${image}" height="150" width="150"></td>
			</tr>		
			<tr>
				<td class="td1">图片:</td>
				<td class="td2"><input type="text" class="main_text" id="image" name="image" size="40" value="${image}" readonly=true/><input type="button" id="uploadButton" value="上传图片" /></td>
			</tr>
			<tr>
				<td class="td1">价格:</td>
				<td class="td2"><input type="text" class="main_text" id="price" name="price" size="10" value="${price}"/>
				</td>
			</tr>
			<tr>
				<td class="td1">特色:</td>
				<td class="td2"><textarea id="special" name="special" rows='3' cols='40'>${special}</textarea></td>
			</tr>
			<tr>
				<td class="td1">类型: </td>
				<td class="td2"><select name="type">
						<option value= 0 <s:if test="type == 0">SELECTED</s:if>>按份计量</option>
						<option value= 1 <s:if test="type == 1">SELECTED</s:if>>按称重计量</option>
					</select></td>
			</tr>	
			<tr>
				<td colspan="2" class="td2">详细描述: </td>
			</tr>
			<tr>
				<td colspan="2" class="td2"><textarea id="discription" name="discription" style="width:800px;height:400px;">${discription}</textarea></td>
			</tr>		
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_change_food">
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
