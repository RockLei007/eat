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
	<form id="mainForm" action="menu.action" method="get">
	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
	<input type="hidden" name="page.order" id="order" value="${page.order}"/>
	<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>

	<div id="filter"><p class="hello">
			菜单名称: <input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}" size="9" class="main_text"/>
			<input type="button" value="<s:text name="search"/>" onclick="search();" class="main_but1"/>
			<security:authorize ifAnyGranted="ROLE_change_menu">
			<input type="button" value="增加新菜单" onclick= "location.href='menu!input.action'" class="main_but1"/>
		</security:authorize></p>
	</div>
	<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
			<tr>
				<th>菜单名称</th>
				<th>路径</th>
				<th>上级菜单</th>
				<th>显示目标</th>
				<th>显示顺序</th>
				<th>角色名称</th>
				<th>操作</th>
			</tr>

			<s:iterator value="page.result">
				<tr>
					<td>${name}</td>
					<td>${path}</td>
					<td>${parentName}</td>
					<td><s:if test="target == 'main'">主窗口中</s:if><s:elseif test="target == 'dialog'">弹出对话框</s:elseif><s:else>弹出新窗口</s:else></td>
					<td>${serial}</td>
					<td>${roleName}</td>
					<td>&nbsp;
						<security:authorize ifAnyGranted="ROLE_view_menu">
							<security:authorize ifNotGranted="ROLE_change_menu">
								<a href="menu!input.action?id=${id}"><s:text name="view"/></a>&nbsp;
							</security:authorize>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_change_menu">
							<a href="menu!input.action?id=${id}"><s:text name="update"/></a>&nbsp;
							<a href="menu!delete.action?id=${id}&name=${name}" onclick="return confirm('<s:text name="onclick.delete"/>');"><s:text name="delete"/></a>
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
