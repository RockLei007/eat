<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
	<title>我的订餐</title>
	<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100" class="back"><a href="reserve!check.action">返回上级</a></td>
    <td>我的订餐</td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
  <tr bgcolor="#fff2db">
    <th width="100" height="30">订餐日期</th>
    <th width="80">餐桌</th>
    <th width="140">起止时间</th>
    <th width="50">人数</th>
    <th>&nbsp;</th>
    <th width="70">状态</th>
    <th width="50">操作</th>
    </tr>
    <s:iterator value="tableReserve">
  <tr>
    <td><p class="date">${datetime}&nbsp;</p><br />
    </td>
    <s:iterator value="eatTable">
    <td><p class="date">${name}&nbsp;</p><br />
    </td>
    </s:iterator>
    <td><p class="date">${beginTime}&nbsp;<br />
      ${endTime}&nbsp;</p></td>
    <td>${amount}&nbsp;</td>
    <td>&nbsp;</td>
    <td><s:if test="state == 0">未处理</s:if><s:if test="state == 1">已确定</s:if><s:if test="state == 2">取消</s:if><s:if test="state == 3">结束</s:if>&nbsp;</td>
    <td><s:if test="state == 0"><a href="reserve!cancel.action?id=${id}" onclick="return confirm('确定取消吗？');"><div class="del_order"> </div></a></s:if></td>
    <td>
    
    </td>
  </tr>
  </s:iterator>
</table>
<table class="menu_bot" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="middle">&nbsp;</td>
    <td width="75" class="choose"><a href="reserve!input.action">重新查询</a></td>
  </tr>
  
</table>
</body>
</html>
