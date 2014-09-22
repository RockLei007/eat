<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles邮件通知用户管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
	<form id="mainForm" action="attention.action" method="post">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			用户名: <input type="text" name="filter_LIKES_userName" value="${param['filter_LIKES_userName']}" size="25"/>&nbsp;&nbsp;
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_attention">
			<input type="button" value="添加" onclick= "location.href='attention!input.action'"/>
		</security:authorize>	</p>
		</div>
			<table id="contentTable"  class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<%if (RoleAuth.hasAdminAuth()){%><th>组织机构ID</th><%} %>
					<th><a href="javascript:sort('userName','asc')">用户名</a></th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:iterator value="page.result">
					<tr>
						<%if (RoleAuth.hasAdminAuth()){%><td><a href="/system/organization!input.action?id=${orgId}">${orgId}</a>&nbsp;</td><%} %>
						<td>${userName}&nbsp;</td>
						<td><s:if test="state == 1">激活</s:if>&nbsp;</td>
						<td>
						<security:authorize ifAnyGranted="ROLE_change_attention">	
							<s:if test="state == 0">
								<a href="attention!active.action?orgId=${orgId}&userId=${userId}" onclick="return confirm('确定激活吗？');">激活&nbsp;</a>
								<a href="attention!delete.action?orgId=${orgId}&userId=${userId}&userName=${userName}" onclick="return confirm('确定删除吗？');">删除&nbsp;</a>
								</s:if> 
							</td>
						</security:authorize>	
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
