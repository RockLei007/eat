<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>微信餐饮管理系统登录</title>
	<%@ include file="/common/manage/input-meta.jsp" %>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});

		function refreshCaptcha() {
			$('#captchaImg').hide().attr('src','${ctx}/jcaptcha.jpg?' + Math.floor(Math.random()*100)).fadeIn();
		}
	</script>
</head>

<body style="background:#fff2ea;">
<div class="login">
	<form id="loginForm" action="${ctx}/j_spring_security_check" method="post">
  <div class="title">微信餐饮管理系统登录</div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_table">
        <tr>
            <td width="150"><s:text name="username"/>：</td>
            <td><label for="textfield"></label>
            	<input type='text' id='j_username' name='j_username' <s:if test="not empty param.error">
						value='<%=session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if> class="login_text" />
            </td>
            <td width="100">&nbsp;</td>
        </tr>
        <tr>
            <td><s:text name="password"/>：</td>
            <td><input type='password' id='j_password' name='j_password' class="login_text"/></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td><s:text name="captcha"/>：</td>
            <td><input type='text' id='j_captcha' name='j_captcha' size='5' class="login_text"/></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><p class="input_code">
          	<%if ("1".equals(request.getParameter("error"))) {%>
							<div class="error"><s:text name="login.fail"/></div>
						<%} 
						  if ("2".equals(request.getParameter("error"))) {%>
							<div class="error"><s:text name="checkCaptcha.fail"/></div>
						<%}%>
					</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        	<td><input type="checkbox" name="_spring_security_remember_me"/><s:text name="remember.me"/></td>
          <td><img id="captchaImg" class="check_code" src="${ctx}/jcaptcha.jpg"/><a href="javascript:refreshCaptcha()"><s:text name="change.captcha"/></a></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td colspan="2"><input value="<s:text name="login"/>" type="submit"  class="login_but"/>   &nbsp;&nbsp;<input value="<s:text name="cancel"/>" type="button"  class="cancle_but" onclick= "location.href='/'"/> 
            </td>
        </tr>
    </table>
    </form>
  </div>
  <%@ include file="/common/manage/footer.jsp" %>
</body>
</html>
