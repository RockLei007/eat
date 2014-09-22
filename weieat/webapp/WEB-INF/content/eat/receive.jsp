<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles接收信息管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
	<form id="mainForm" action="receive.action" method="post">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			开始日期: <input type="text" name="filter_GTS_datetime" value="${param['filter_GTS_datetime']}" size="10"/>&nbsp;&nbsp;
			结束日期: <input type="text" name="filter_LTS_datetime" value="${param['filter_LTS_datetime']}" size="10"/>&nbsp;&nbsp;
			内容: <input type="text" name="filter_LIKES_content" value="${param['filter_LIKES_content']}" size="25"/>&nbsp;&nbsp;
			<input type="button" value="<s:text name="search"/>" onclick="search();"/></p>
		</div>
			<table id="contentTable" width="777" class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<th>编号</th>
					<th><a href="javascript:sort('datetime','asc')">时间</a></th>
					<th>内容</th>
				</tr>
				<s:iterator value="page.result">
					<tr>
						<td>${id}&nbsp;</td>
						<td>${datetime}&nbsp;</td>
						<td>${content}&nbsp;</td>
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
