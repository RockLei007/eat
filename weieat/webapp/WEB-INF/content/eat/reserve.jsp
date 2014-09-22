<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles 订桌管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
	<form id="mainForm" action="reserve.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			开始日期: <input type="text" name="filter_GTS_beginTime" value="${param['filter_GTS_beginTime']}" size="10"/>&nbsp;&nbsp;
			结束日期: <input type="text" name="filter_LTS_endTime" value="${param['filter_LTS_endTime']}" size="10"/>&nbsp;&nbsp;
			预订日期: <input type="text" name="filter_EQS_dateTime" value="${param['filter_LTS_dateTime']}" size="10"/>&nbsp;&nbsp;
			用户ID号: <input type="text" name="filter_EQS_userId" value="${param['filter_EQS_userId']}" size="15"/>&nbsp;&nbsp;
			第一次订餐:
			<select name="filter_EQI_first">
					<option></option>
					<option value= 0>是</option>
					<option value= 1>否</option>
			</select>&nbsp;&nbsp;
			状态: <select name="filter_EQI_state">
					<option></option>
					<option value= 0>未处理</option>
					<option value= 1>已确定</option>
					<option value= 2>取消</option>
					<option value= 3>结束</option>
			</select>&nbsp;&nbsp;
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_reserve">
			<input type="button" value="订餐" onclick= "location.href='reserve!input.action'"/>
		</security:authorize></p>
		</div>
			<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<th><a href="javascript:sort('datetime','asc')">订餐日期</a></th>
					<th><a href="javascript:sort('name','asc')">餐桌包厢名</a></th>
					<%if (RoleAuth.hasAdminAuth()){%><th>组织机构ID</th><%} %>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>人数</th>
					<th>用户</th>
					<th>第一次</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:iterator value="page.result">
					<tr <s:if test="first == 0 && state == 0">bgColor="#FF00FF"</s:if>>
						<td>${datetime}&nbsp;</td>
						<s:iterator value="eatTable">
							<td>${name}&nbsp;</td>
						</s:iterator>
						<%if (RoleAuth.hasAdminAuth()){%><td><a href="/system/organization!input.action?id=${orgId}">${orgId}</a>&nbsp;</td><%} %>
						<td>${beginTime}&nbsp;</td>
						<td>${endTime}&nbsp;</td>
						<td>${amount}&nbsp;</td>
						<td><a href="customer!input.action?id=${userId}" target="_blank">查看</a>&nbsp;</td>
						<td><s:if test="first == 0">是</s:if><s:else>否</s:else>&nbsp;</td>
						<td><s:if test="state == 0">未处理</s:if><s:if test="state == 1">已确定</s:if><s:if test="state == 2">取消</s:if><s:if test="state == 3">结束</s:if>&nbsp;</td>
						<td>
							<s:if test="state == 0"><a href="reserve!cancel.action?id=${id}" onclick="return confirm('确定取消吗？');">取消&nbsp;</a></s:if> 
							<s:if test="state == 2"><a href="reserve!delete.action?id=${id}&name=<s:iterator value="eatTable">${name}</s:iterator>" onclick="return confirm('<s:text name="onclick.delete"/>');">删除&nbsp;</a></s:if>
							<s:if test="state == 0"><a href="reserve!verify.action?id=${id}" onclick="return confirm('已经与客人确定定餐了吗？');">确定&nbsp;</a></s:if>
							<s:if test="state == 1"><a href="reserve!finish.action?id=${id}" onclick="return confirm('客人已经买单结算了吗？');">结束&nbsp;</a></s:if>
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
