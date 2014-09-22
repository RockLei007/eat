<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heralces 菜单管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
			<%@ include file="/common/manage/header.jsp" %>
<div class="main">
	<form id="mainForm" action="log.action" method="get">
	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
	<input type="hidden" name="page.order" id="order" value="${page.order}"/>
	<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>

	<div id="filter"><p class="hello">
			IP地址: <input type="text" name="filter_LIKES_ip" value="${param['filter_LIKES_ip']}" size="20" class="main_text"/>
			用户名: <input type="text" name="filter_LIKES_userName" value="${param['filter_LIKES_userName']}" size="10" class="main_text"/>
			开始时间: <input type="text" name="filter_GES_datetime" value="${param['filter_GES_datetime']}" size="10" class="main_text"/>
			结束时间: <input type="text" name="filter_LES_datetime" value="${param['filter_LES_datetime']}" size="10" class="main_text"/>			
			<input type="button" value="<s:text name="search"/>" onclick="search();" class="main_but1"/></p>
		</div>
		<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
			<tr>
				<th>IP地址</th>
				<th>用户名</th>
				<th>关键字段ID</th>
				<th>关键字段值</th>
				<th>日期时间</th>
				<th>类名</th>
				<th>方法名</th>
				<th>描述</th>
				<th>结果</th>
			</tr>

			<s:iterator value="page.result">
				<tr>
					<td>${ip}</td>
					<td>${userName}</td>
					<td>${keyId}</td>
					<td>${keyName}</td>
					<td>${datetime}</td>
					<td>${className}</td>
					<td>${methodName}</td>
					<td>${description}</td>
					<td>${result}</td>
				</tr>
			</s:iterator>
		</table>
	</form>
	<%@ include file="/common/turnpage.jsp" %>
	</div>	
				<div class="clear"></div>
			</div>
		</div>
<div class="clear"></div>
<%@ include file="/common/manage/footer.jsp" %>
</body>
</html>
