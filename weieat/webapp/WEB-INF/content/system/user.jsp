<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles 帐号管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
					<form id="mainForm" action="user.action" method="get">
						<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
						<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
						<input type="hidden" name="page.order" id="order" value="${page.order}"/>
				
						<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
						<div id="filter">
							<p class="hello"><%if (RoleAuth.hasAdminAuth()){%><s:text name="username"/>: <input type="text" name="filter_EQS_loginName" value="${param['filter_EQS_loginName']}" size="9"/>
							姓名或Email: <input type="text" name="filter_LIKES_name_OR_email" value="${param['filter_LIKES_name_OR_email']}" size="9"/>
							<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;<%};%>
							<security:authorize ifAnyGranted="ROLE_change_user">
							<input type="button" value="增加新用户" onclick= "location.href='user!input.action'"/>
						</security:authorize></p>
						</div>
							<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
								<tr>
									<th><a href="javascript:sort('loginName','asc')">登录名</a></th>
									<th><a href="javascript:sort('name','asc')">姓名</a></th>
									<th><a href="javascript:sort('email','asc')">电邮</a></th>
									<th>状态</th>
									<th>角色</th>
									<th>组织机构</th>
									<th>创建时间</th>
									<th>姓别</th>
									<th>最后登陆时间</th>
									<th>操作</th>
								</tr>
				
								<s:iterator value="page.result">
									<tr>
										<td>${loginName}&nbsp;</td>
										<td>${name}&nbsp;</td>
										<td>${email}&nbsp;</td>
										<td><s:if test="status == 0"><s:text name="enable"/></s:if><s:else><s:text name="disable"/></s:else>&nbsp;</td>
										<td>${roleNames}&nbsp;</td>
										<s:iterator value="organization">
											<td>${name}&nbsp;</td>
										</s:iterator>
										<td>${createDate}&nbsp;</td>
										<s:iterator value="userInfo">
											<td><s:if test="sex == 0"><s:text name="male"/></s:if><s:else><s:text name="female"/></s:else>&nbsp;</td>
											<td>${lastLoginDate}&nbsp;</td>
										</s:iterator>
										<td>&nbsp;
											<security:authorize ifAnyGranted="ROLE_view_role">
												<security:authorize ifNotGranted="ROLE_change_user">
													<a href="user!input.action?id=${id}"><s:text name="view"/></a>&nbsp;
												</security:authorize>
											</security:authorize>
				
											<security:authorize ifAnyGranted="ROLE_change_user">
												<a href="user!input.action?id=${id}"><s:text name="update"/></a>&nbsp;
												<s:if test="status == 0">
												<a href="user!delete.action?id=${id}&loginName=${loginName}" onclick="return confirm('<s:text name="onclick.disable"/>');"><s:text name="disable"/></a>
												</s:if>
												<s:else>
												<a href="user!active.action?id=${id}&loginName=${loginName}" onclick="return confirm('<s:text name="onclick.enable"/>');"><s:text name="active"/></a>
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

</body>
</html>
