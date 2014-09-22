<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page import="com.heracles.framework.web.RoleAuth" %>

<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>heracles餐桌包厢管理</title>
	<%@ include file="/common/manage/top.jsp" %>
</head>

<body>
	<%@ include file="/common/manage/title.jsp" %>
   	<div class="content">
	    <div id="menu_zzjsnet" class="nav_l">
				<%@ include file="/common/manage/header.jsp" %>
				<div class="main">
	<form id="mainForm" action="table.action" method="post">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter"><p class="hello">
			餐桌包厢名: <input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}" size="25"/>&nbsp;&nbsp;
			分类: <select name="filter_EQS_categories">
					<option SELECTED></option>
					<s:iterator value="tableCate" var="tcString">
					<option value="<s:property value="#tcString"/>"><s:property value="#tcString" /></option>
					</s:iterator>
			</select>&nbsp;&nbsp;
			类型: <select name="filter_EQS_type">
					<option SELECTED></option>
					<s:iterator value="tableType" var="ttString">
					<option value="<s:property value="#ttString"/>"><s:property value="#ttString" /></option>
					</s:iterator>
			</select>&nbsp;&nbsp;
			是否预订: <select name="filter_EQI_reserve">
					<option SELECTED></option>
					<option value= 0 >是</option>
					<option value= 1 >否</option>
			</select>&nbsp;&nbsp;<br>
			是否清真: <select name="filter_EQI_islamic">
					<option SELECTED></option>
					<option value= 0 >是</option>
					<option value= 1 >否</option>
			</select>&nbsp;&nbsp;
			状态: <select name="filter_EQI_state">
					<option SELECTED></option>
					<option value= 0 >正常</option>
					<option value= 1 >停止</option>
			</select>&nbsp;&nbsp;
			<input type="button" value="<s:text name="search"/>" onclick="search();"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_change_table">
			<input type="button" value="新增" onclick= "location.href='table!input.action'"/>
		</security:authorize></p>
		</div>
			<table id="contentTable" class="data_table" cellspacing="0" cellpadding="0">
				<tr>
					<th>编号</th>
					<th>餐桌包厢名</th>
					<%if (RoleAuth.hasAdminAuth()){%><th>组织机构ID</th><%} %>
					<th>分类</th>
					<th>类型</th>
					<th>可否预订</th>
					<th>是否清真</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:iterator value="page.result">
					<tr>
						<td>${id}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<%if (RoleAuth.hasAdminAuth()){%><td><a href="/system/organization!input.action?id=${orgId}">${orgId}</a>&nbsp;</td><%} %>
						<td>${categories}&nbsp;</td>
						<td>${type}</td>
						<td><s:if test="reserve == 0">是</s:if><s:else>否</s:else>&nbsp;</td>
						<td><s:if test="islamic == 0">是</s:if><s:else>否</s:else>&nbsp;</td>
						<td><s:if test="state == 0">正常</s:if><s:else>停止</s:else>&nbsp;</td>
						<td>
							<security:authorize ifAnyGranted="ROLE_view_table">
									<a href="table!input.action?id=${id}"><s:text name="view"/></a>&nbsp;
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_change_table">
								<s:if test="state == 0">
								<a href="table!disable.action?id=${id}&name=${name}" onclick="return confirm('<s:text name="onclick.disable"/>');"><s:text name="disable"/></a>
								</s:if>
								<s:else>
								<a href="table!enable.action?id=${id}&name=${name}" onclick="return confirm('<s:text name="onclick.enable"/>');"><s:text name="active"/></a>
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
