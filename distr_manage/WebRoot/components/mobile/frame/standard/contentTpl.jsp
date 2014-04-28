<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String nonbusiNum = request.getParameter("nonbusiNum");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1, initial-scale=1"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>江苏移动掌上营业厅-体验版</title>
    <link href="./mobile/resource/standard/style/public.css" rel="stylesheet" type="text/css">
</head>
<body>
<!--header-->
<div class="header">
    <!--顶部-->
    <div id="noweathertopBar" class="top">T{noweathertopBar}</div>
    <!--//顶部-->
    <div id="areaNavBar">T{areaNavBar}</div>
</div>
<!--//header-->
<!--body-->
<div class="layout page-gnywdh">    
        <!--业务区块-->
        <div>T_NONCORE{<%=nonbusiNum%>}</div>
        <!--业务区块 end-->    
</div>
<!--//body-->
<!--footer-->
<div class="footer" id="busifooter">T{busifooter}</div>
<!--//footer-->
</body>
</html>
