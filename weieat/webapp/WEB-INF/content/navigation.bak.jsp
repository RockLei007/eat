<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>导航页面</title>
	<%@ include file="/common/manage/meta.jsp" %>
</head>

<body>
<div id="doc3">
<%@ include file="/common/manage/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b"><p></p>
	<s:if test="registerOrg == 0"><a href="/system/organization!add.action">请注册您的餐厅信息</a><p></p></s:if>
	<s:if test="registerOrg == 1"><a href="/system/organization!add.action?id=${orgId}">您可以修改您的餐厅信息</a><p></p></s:if>
	目前您的菜品共有${foodCount}个<s:if test="foodCount == 0">,<a href="/eat/food.action">您可以增加您的菜品信息</a></s:if><p></p>
	目前您的餐桌和包厢共有${tableCount}个<s:if test="tableCount == 0">,<a href="/eat/table.action">您可以增加您的餐桌及包厢信息</a></s:if><p></p>
	<s:if test="newDishesCount > 1"><a href="/eat/dishes.action?filter_EQI_state=0">有<font Color="#FF00FF">${newDishesCount}</font>个新的点菜需要您处理</a><p></p></s:if>
	<s:if test="addDishesCount > 1"><a href="/eat/dishes.action?filter_EQI_state=3">有<font Color="#FF00FF">${addDishesCount}</font>个加菜需要您处理</a><p></p></s:if>
	<s:if test="newReserveCount > 1"><a href="/eat/reserve.action?filter_EQI_state=0">有<font Color="#FF00FF">${newReserveCount}</font>个订餐需要您处理</a><p></p></s:if>
	</div>
	</div>
</div>
<%@ include file="/common/manage/footer.jsp" %>
</div>
</body>
</html>
