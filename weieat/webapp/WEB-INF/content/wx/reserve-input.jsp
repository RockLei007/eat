<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>定餐信息查询</title>
	<script>
		$(document).ready(function() {
			$("#inputForm").validate();
		});
	</script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75" class="back"></td>
    <td>订餐信息查询</td>
    <td width="75" class="my_menu"><a href="reserve!order.action">我的预订</a></td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
	<form id="inputForm" action="reserve!check.action" method="post">
  <tr>
    <td width="120" bgcolor="#eeeeee">就餐人数</td>
    <td><select name="categories">
					<s:iterator value="tableCate" var="tcString">
					<option value="<s:property value="#tcString"/>" <s:if test="#tcString==categories">SELECTED</s:if> ><s:property value="#tcString" /></option>
					</s:iterator>
			</select></td>
  </tr>
  <tr>
    <td bgcolor="#eeeeee">就餐日期</td>
    <td>
    <s:select label="年" name="year" list="yearList" headerValue=""></s:select>
    <s:select label="月" name="month" list="{'01','02','03','04','05','06','07','08','09','10','11','12'}"  headerKey=""></s:select> &nbsp;
    <s:select label="日" name="day" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'}" headerKey=""></s:select> &nbsp;
   </td>
   </tr>
  <tr>
    <td bgcolor="#eeeeee">预计开始时间</td>
    <td><s:select label="小时" name="beginHour" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'}" headerKey=""></s:select>
    <s:select label="分钟" name="beginMinute" list="{'00','10','20','30','40','50'}" headerKey=""></s:select>
    </td>
  </tr>
  <tr>
    <td bgcolor="#eeeeee">预计结束时间</td>
    <td><s:select label="小时" name="endHour" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'}" headerKey=""></s:select>
    <s:select label="分钟" name="endMinute" list="{'00','10','20','30','40','50'}" headerKey=""></s:select>	
    </td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" name="button" id="button" class="submit_but" value="<s:text name="commit"/>" /><input type="button" name="button" id="button" class="back_but" value="<s:text name="back"/>" onclick="history.back()"/></td>
    </tr>
  </form>  
</table>

</body>
</html>
