<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.xwtech.uomp.base.WEBApp" %>
<%@ page import="com.xwtech.uomp.base.pojo.floor.FloorFaBean" %>
<%@ page import="com.xwtech.uomp.base.service.floor.IFloorFaService" %>
<%@ page import="java.util.List" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1, initial-scale=1"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>江苏移动掌上营业厅-体验版</title>
    <link href="./mobile/resource/standard/style/public.css" rel="stylesheet" type="text/css">
    <style>
         	.nav {
				overflow: hidden;
				color: #fff;
				padding: 5px 10px;
				background: -moz-linear-gradient(top, #30aefb, #279de5); /*Mozilla*/
				background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#30aefb),
					to(#279de5) ); /*Old gradient for webkit*/
				background: -webkit-linear-gradient(top, #30aefb, #279de5);
				/*new gradient for Webkit*/
				background: -o-linear-gradient(top, #30aefb, #279de5); /*Opera11*/
				background: -ms-linear-gradient(top, #30aefb, #279de5); /*For IE10*/
				background: linear-gradient(top, #30aefb, #279de5); /*CSS3*/
			}
			
			.nav ul {
				
			}
			
			.nav li {
				display: inline-block;
				margin: 0 18px;
			}
			
			.nav li a {
				color: #fff;
			}
			
			@media screen and (max-width:319px) and (min-width:240px) {
				.nav ul {
					max-width: 260px;
					min-width: 220px;
					text-align: center;
				}
				.nav ul li {
					margin: 0 10px;
				}
			}
			
			.box-head a {
				line-height: 26px;
			}
			
			.box-subTitle a {
				line-height: 23px;
			}
			
			.box-body p a {
				white-space: nowrap;
			}
    </style>
</head>
<%
    IFloorFaService floorFaService = (IFloorFaService) WEBApp.SPRING_CONTEXT.getBean("floorFaService");
    List<FloorFaBean> list = floorFaService.queryFloorFaList("0202");
%>
<body>
<!--header-->
<div class="header">
    <!--顶部-->
    <div id="topBar" class="top">T{topBar}</div>
    <!--顶部-->
    <!--导航-->
    <div id="navBar">T{navBar}</div>
    <!--导航-->
    <!--公告-->
    <div id="noticeBar" class="notice">T{noticeBar}</div>
    <!--公告-->
    <!--login-->
    <div id="busiArea">${result.loginBar}</div>
    <!--login-->
</div>
<!--header-->
<!--body-->
<div class="layout page-gnywdh">
    <!--搜索-->
    <div id="search" class="box">T{search}</div>
    <!--搜索-->
</div>
<!--楼层-->
<div class="layout page-gnywdh">
<%
    for (FloorFaBean floorFaBean : list) {
        String floorComp = floorFaBean.getFloorComp();
%>
<div id="<%=floorComp%>" class="box">T{<%=floorComp%>}</div>
<%
    }
%>
</div>
<!--楼层-->
<!--footer-->
<div class="footer" id="footBar">T{footBar}</div>
<!--footer-->
</body>
</html><mobile:component name="loginBar" containerId="busiArea"  isBusi="1" />