<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css">
	<title>点菜管理</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
  <meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
<table class="menu_top" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100" class="back"><a href="${ctx}/wx/dishes!order.action">返回上级</a></td>
    <td>菜品详细信息</td>
  </tr>
</table>
<table cellspacing="0" cellpadding="0" class="menu">
  <tr>
    <td width="100"><p class="detail_tr">菜名:</p></td>
    <td valign="middle"><p  class="detail_tl"><span class="name_color"><b>${food.name}</b></span></p></td>
  </tr>
  <tr>
    <td><p  class="detail_tr">分类:</p></td>
    <td valign="middle"><p  class="detail_tl">${food.categories}</p></td>
  </tr>
  <tr>
    <td><p  class="detail_tr">图片:</p></td>
    <td valign="middle"><p  class="detail_tl"><img height="150" width="150" src="${food.image}" /></p></td>
  </tr>
  <tr>
    <td><p  class="detail_tr">价格:</p></td>
    <td valign="middle"><p  class="detail_tl"><span class="red"><b>${food.price}</b></span></p></td>
  </tr>
  <tr>
    <td><p  class="detail_tr">特色:</p></td>
    <td valign="middle"><p  class="detail_tl">${food.special}</p></td>
  </tr>
  <tr>
    <td><p  class="detail_tr">类型:</p></td>
    <td valign="middle"><p  class="detail_tl"><s:if test="food.type == 0">按份计量</s:if>
						<s:if test="food.type == 1">按称重计量</s:if>
					</p></td>
  </tr>
  <tr>
    <td valign="top"><p  class="detail_tr">详细描述:</p></td>
    <td valign="middle"><p  class="detail_tl">${food.discription}</p></td>
  </tr>
</table>
</body>
</html>
