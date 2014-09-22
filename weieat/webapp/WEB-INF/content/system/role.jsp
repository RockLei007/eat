<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles 角色管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
<%@ include file="/common/manage/header.jsp" %>
<div class="main">
<form id="mainForm" action="role.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
	<div id="filter"><p class="hello">
	角色名称: <input type="text" name="filter_EQS_name" value="${param['filter_LIKES_name']}" size="9"/>
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_role">
			<input type="button" value="增加新角色" onclick= "location.href='role!input.action'"/>
		</security:authorize></p>
	</div>
		<table id="contentTable"  cellspacing="0" cellpadding="0" width="777" class="data_table" >
			<tr>
				<th>名称</th>
				<th>授权</th>
				<th>状态</th>
				<th>操作</th>
			</tr>

			<s:iterator value="page.result">
				<tr>
					<td>${name}</td>
					<td>${authNames}</td>
					<td><s:if test="status == 0"><s:text name="enable"/></s:if><s:else><s:text name="disable"/></s:else>&nbsp;</td>
					<td>&nbsp;
						<security:authorize ifAnyGranted="ROLE_view_role">
							<security:authorize ifNotGranted="ROLE_change_role">
								<a href="role!input.action?id=${id}"><s:text name="view"/></a>&nbsp;
							</security:authorize>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_change_role">
							<a href="role!input.action?id=${id}"><s:text name="update"/></a>&nbsp;
							<s:if test="status == 0">
							<a href="role!delete.action?id=${id}&name=${name}"><s:text name="disable"/></a>
							</s:if>
							<s:else>
							<a href="role!active.action?id=${id}&name=${name}"><s:text name="active"/></a>
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

