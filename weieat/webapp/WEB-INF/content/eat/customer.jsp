<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>顾客管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
	<form id="mainForm" action="customer.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			用户名: <input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}" size="25"/>&nbsp;&nbsp;
			电话: <input type="text" name="filter_LIKES_phone" value="${param['filter_LIKES_phone']}" size="25"/>&nbsp;&nbsp;
			微信ID: <input type="text" name="filter_EQS_weixinId" value="${param['filter_EQS_weixinId']}" size="25"/>&nbsp;&nbsp;
			黑名单: <select name="filter_EQI_black">
					<option SELECTED></option>
					<option value= 0 >否</option>
					<option value= 1 >是</option>
			</select>&nbsp;&nbsp;
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_customer">
			<input type="button" value="添加" onclick= "location.href='customer!input.action'"/>
		</security:authorize>	</p>
		</div>
			<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<th>微信ID</th>
					<%if (RoleAuth.hasAdminAuth()){%><th>组织机构ID</th><%} %>
					<th><a href="javascript:sort('name','asc')">用户名</a></th>
					<th>电话</th>
					<th><a href="javascript:sort('createDate','desc')">创建时间</a></th>
					<th>黑名单</th>
					<th>操作</th>
				</tr>
				<s:iterator value="page.result">
					<tr>
						<td>${weixinId}&nbsp;</td>
						<%if (RoleAuth.hasAdminAuth()){%><td><a href="/system/organization!input.action?id=${orgId}">${orgId}</a>&nbsp;</td><%} %>
						<td>${name}&nbsp;</td>
						<td>${phone}&nbsp;</td>
						<td>${createDate}&nbsp;</td>
						<td><s:if test="black == 1">是</s:if><s:else>否</s:else></td>
						<td>
							<security:authorize ifAnyGranted="ROLE_view_table">
									<a href="customer!input.action?id=${id}"><s:text name="view"/></a>&nbsp;
							</security:authorize>
						<security:authorize ifAnyGranted="ROLE_change_customer">	
							<s:if test="black == 0">
								<a href="customer!black.action?id=${id}" onclick="return confirm('确定加入黑名单吗？');">置黑&nbsp;</a>
							</s:if> <s:else>
								<a href="customer!cancelBlack.action?id=${id}" onclick="return confirm('确定取消黑名单吗？');">置白&nbsp;</a>
							</s:else>	
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
