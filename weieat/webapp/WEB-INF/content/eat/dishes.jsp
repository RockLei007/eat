<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles 点菜管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
	<form id="mainForm" action="dishes.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			开始日期: <input type="text" name="filter_GTS_datetime" value="${param['filter_GTS_datetime']}" size="25"/>&nbsp;&nbsp;
			结束日期: <input type="text" name="filter_LTS_datetime" value="${param['filter_LTS_datetime']}" size="25"/>&nbsp;&nbsp;
			类型:
			<select name="filter_EQI_type">
					<option></option>
					<option value= 0>现点菜</option>
					<option value= 1>预订</option>
			</select>&nbsp;&nbsp;
			状态: <select name="filter_EQI_state">
					<option></option>
					<option value= 0>未处理</option>
					<option value= 1>已确定</option>
					<option value= 2>取消</option>
					<option value= 3>加菜</option>
					<option value= 4>结束</option>
			</select>&nbsp;&nbsp;
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_dishes">
			<input type="button" value="点菜" onclick= "location.href='dishes!input.action'"/>
		</security:authorize></p>
		</div>
			<table id="contentTable"  class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<th><a href="javascript:sort('datetime','asc')">日期时间</a></th>
					<th>餐桌包厢名</th>
					<%if (RoleAuth.hasAdminAuth()){%><th>单位名称</th><%} %>
					<th>金额</th>
					<th>类型</th>
					<th>状态</th>
					<th>明细</th>
					<th>操作</th>
				</tr>
				<s:iterator value="page.result">
					<tr>
						<td>${datetime}&nbsp;</td>
						<s:iterator value="eatTable">
							<td>${name}&nbsp;</td>
						</s:iterator>
						<%if (RoleAuth.hasAdminAuth()){%><td><a href="/system/organization!input.action?id=${orgId}" target="_blank">${orgId}</a>&nbsp;</td><%} %>
						<td>${money}&nbsp;</td>
						<td><s:if test="type == 0">现点菜</s:if><s:else>预订</s:else>&nbsp;</td>
						<td><s:if test="state == 0">未处理</s:if><s:if test="state == 1">已确定</s:if><s:if test="state == 2">取消</s:if><s:if test="state == 3">加菜</s:if><s:if test="state == 4">结束</s:if>&nbsp;</td>
						<td><a href="/html/dishes_details.html?id=${id}" target="_blank">查看</a>&nbsp;</td>
						<td><s:if test="state == 0 || state == 1"> <a href="dishes!input.action?id=${id}">加菜&nbsp;</a></s:if>
							<s:if test="state == 0"><a href="dishes!cancel.action?id=${id}" onclick="return confirm('确定取消吗？');">取消&nbsp;</a></s:if> 
							<s:if test="state == 2"><a href="dishes!delete.action?id=${id}&name=<s:iterator value="eatTable">${name}</s:iterator>" onclick="return confirm('<s:text name="onclick.delete"/>');">删除&nbsp;</a></s:if>
							<s:if test="state == 0 || state == 3"><a href="dishes!verify.action?id=${id}" onclick="return confirm('客人已经确定点菜了吗？');">确定&nbsp;</a></s:if>
							<s:if test="state == 1 || state == 3"><a href="dishes!finish.action?id=${id}" onclick="return confirm('客人已经买单结算了吗？');">结束&nbsp;</a></s:if>
						</td>
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
