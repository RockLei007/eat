<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/manage/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
	<title>Fullscreen Init</title>
	<link rel="stylesheet" type="text/css" href="/css/dhtmlxlayout_dhx_blue.css">
	<link rel="stylesheet" type="text/css" href="/css/dhtmlxlayout.css">
	<link rel="stylesheet" type="text/css" href="/css/dhtmlxtree.css">

	<script src="/js/dhtmlx/dhtmlxcommon.js"></script>
	<script src="/js/dhtmlx/dhtmlxcontainer.js"></script>
	<script src="/js/dhtmlx/dhtmlxlayout.js"></script>
	<script src="/js/dhtmlx/dhtmlxtree.js"></script>
	<style>
		html, body {
			width: 100%;
			height: 100%;
			margin: 0px;
			padding: 0px;
			overflow: hidden;
		}
	</style>
</head>
<body onload="doOnLoad();">
	<div id="topDiv" style="width: 100%; height: 100%; overflow: auto; display: none; font-family: Tahoma; font-size: 11px;">
    <div style="margin: 3px 5px 3px 5px;">
    King Arthur is a 2004 film directed by Antoine Fuqua and written by David Franzoni. It stars Clive Owen as the title character.
    </div>
</div>
<script>
	var dhxLayout,dhxTree;
	function doOnLoad() {
    var dhxLayoutData = {
        parent: document.body,
        pattern: "3T",
        cells: [
        	{ id: "a",
            text: "top",
           	height: "100" 
          }, 
          { id: "b",
            text: "菜单",
            width: 200,
        	},
        	{ id: "c",
            text: " "
        	}
        ]
    };
    dhxLayout = new dhtmlXLayoutObject(dhxLayoutData);
    dhxLayout.cells("a").hideHeader();
    dhxLayout.cells("a").fixSize("false","false");
    dhxLayout.cells("a").attachObject("topDiv");
    
    dhxTree = dhxLayout.cells("b").attachTree();    
		dhxTree.setImagePath("../imgs/csh_vista/");
		dhxTree.loadXML("/server/menu/1/");
	}
	
	function expand() {
    dhxLayout.items["a"].expand();
    dhxLayout.items["b"].expand();
	}
	
	function collapse() {
    dhxLayout.items["a"].collapse();
    dhxLayout.items["b"].collapse();
	}
	
	function toURL(url){
		dhxLayout.cells("c").progressOn();
		dhxLayout.cells("c").attachURL(url);
	}
</script>
</body>
</html>