<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles 组织机构管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
<%@ include file="/common/manage/header.jsp" %>
<div class="main">
	<form id="mainForm" action="organization.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			hello, <%=SpringSecurityUtils.getCurrentUserName()%>.&nbsp;&nbsp;
			<s:text name="name"/>: <input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}" size="16"/>
			<s:text name="address"/><s:text name="phone"></s:text>: <input type="text" name="filter_LIKES_address_OR_phone"
							 value="${param['filter_LIKES_address_OR_phone']}" size="16"/>
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_org">
			<input type="button" value="增加新组织机构" onclick= "location.href='organization!input.action'"/>
		</security:authorize></p>
		</div>
			<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<th><a href="javascript:sort('name','asc')">名称</a></th>
					<th>地址</th>
					<th>电话</th>
					<th>传真</th>
					<th>标志</th>
					<th>分类</th>
					<th>创建时间</th>
					<th>结束时间</th>
					<th>说明</th>
					<th>状态</th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${name}&nbsp;</td>
						<td>${address}&nbsp;</td>
						<td>${phone}&nbsp;</td>
						<td>${fax}&nbsp;</td>
						<td>${symbol}&nbsp;</td>
						<td>${categories}&nbsp;</td>
						<td>${createDate}&nbsp;</td>
						<td>${endDate}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><s:if test="status == 0"><s:text name="enable"/></s:if><s:else><s:text name="disable"/></s:else>&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_view_org">
								<security:authorize ifNotGranted="ROLE_change_org">
									<a href="organization!input.action?id=${id}"><s:text name="view"/></a>&nbsp;
								</security:authorize>
							</security:authorize>

							<security:authorize ifAnyGranted="ROLE_change_org">
								<a href="organization!input.action?id=${id}"><s:text name="update"/></a>&nbsp;
								<s:if test="status == 0">
								<a href="organization!delete.action?id=${id}" onclick="return confirm('<s:text name="onclick.disable"/>');"><s:text name="disable"/></a>
								</s:if>
								<s:else>
								<a href="organization!active.action?id=${id}" onclick="return confirm('<s:text name="onclick.enable"/>');"><s:text name="active"/></a>
								</s:else>
							</security:authorize>
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
</div>
</body>
</html>
