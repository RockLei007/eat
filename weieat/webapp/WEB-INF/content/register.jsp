<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="/css/yui.css" type="text/css" rel="stylesheet"/>
<link href="/css/style.css" type="text/css" rel="stylesheet"/>
<link href="/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/table.js" type="text/javascript"></script>
<script src="/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="/js/validate/messages_cn.js" type="text/javascript"></script>

	<title>用户注册</title>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			$("#mainForm").validate({
				rules: {
					loginName: {
						required: true,
						remote: "register!checkLoginName.action?1=1" + encodeURIComponent('${loginName}')
					},
					name: "required",
					password: {
						required: true,
						minlength:6
					},
					passwordConfirm: {
						equalTo:"#password"
					},
					j_captcha:{
						required: true,
						remote: "register!checkCaptcha.action?1=1" + encodeURIComponent('${j_captcha}')
					},
					email:"email"
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					},
					j_captcha: {
						remote: "验证码不正确"
					}
				},
				form.submit();
			});
		});
	</script>
</head>

<body>
<div id="doc3">
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<h2>注册用户</h2>
	<form id="mainForm" action="register!save.action" method="post">
		<table class="noborder">
			<tr>
				<td>登录名:</td>
				<td><input type="text" name="loginName" size="40" id="loginName" value=""/></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input type="text" id="name" name="name" size="40" value=""/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" id="password" name="password" size="40" value=""/></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="40" value=""/>
				</td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" id="email" name="email" size="40" value=""/></td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><input type='text' id='j_captcha' name='j_captcha' size='5' class="required"/></td>
			</tr>
			<tr>
				<td colspan="3"><img id="captchaImg" width="100" height="40" src="/jcaptcha.jpg"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="button" type="submit" value="<s:text name="commit"/>"/>&nbsp;
					<input class="button" type="button" value="<s:text name="back"/>" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</div>
<%@ include file="/common/manage/footer.jsp" %>
</div>
</body>
</html>
