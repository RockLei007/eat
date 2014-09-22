<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<div class="top">hello, <%=SpringSecurityUtils.getCurrentUserName()%>. | <a href="#">修改密码</a> | <a href="${ctx}/j_spring_security_logout"><s:text name="logout"/></a></div>
   	<div class="header">微信餐饮管理系统</div>