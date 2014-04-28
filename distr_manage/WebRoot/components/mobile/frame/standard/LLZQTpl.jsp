<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String busiNum = request.getParameter("busiNum");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1, initial-scale=1"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>江苏移动掌上营业厅-体验版</title>
    <link href="./mobile/resource/standard/style/public.css" rel="stylesheet" type="text/css">
      <style>
            .bg-green{border:1px solid #6699cc;padding:5px;color:#336699;background:#ddeeff;}
						.area{border:1px solid #6699cc;background:#fff;padding:10px;margin:3px 0;}
						.area table{width:100%;height:130px;}
						.area table td{text-align:center;padding:0;}
						.area table td span{display:block;background:#6C0;border:1px solid #060;width:20px;margin:auto;border-bottom:0;}
						.area table .area-mouth{height:18px;}
						.area table .area-mouth td{font-weight:bold;color:#666;border-top:1px solid #ccc;}
						.progress{}
						.progress p{overflow:hidden;}
						.progress .progress-bar{height:18px;border:1px solid #ddd;background:#fff;margin-right:40px;display:block;}
						.progress .progress-bar em{height:18px;display:block;}
						.progress .progress-num{float:right;width:30px;display:block;text-align:center;}
						.progress .progress-legend span{display:inline-block;vertical-align:middle;}
						.progress .progress-legend .progress-legend-pic{height:10px;width:10px;border:1px solid #ddd;}
						.progress .green{background:-moz-linear-gradient(top,#00ff00,#00cc00);
						background:-webkit-gradient(linear,0% 0%,0% 100%,from(#00ff00),to(#00cc00));
						background:-webkit-linear-gradient(top,#00ff00,#00cc00);
						background:-o-linear-gradient(top,#00ff00,#00cc00); 
						background: -ms-linear-gradient(top,#00ff00, #00cc00);
						background: linear-gradient(top,#00ff00, #00cc00);
						filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0,startColorstr=#00ff00,endColorstr=#00cc00);
						}
						.progress .red{background:-moz-linear-gradient(top,#ff0000,#cc0000);
						background:-webkit-gradient(linear,0% 0%,0% 100%,from(#ff0000),to(#cc0000));
						background:-webkit-linear-gradient(top,#ff0000,#cc0000);
						background:-o-linear-gradient(top,#ff0000,#cc0000); 
						background: -ms-linear-gradient(top,#ff0000, #cc0000);
						background: linear-gradient(top,#ff0000, #cc0000);
						filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0,startColorstr=#ff0000,endColorstr=#cc0000);
						}
						.progress .white{background:#fff;}
						.icon-link{display:block;padding-top:5px;}
    </style>
    </style>
</head>
<body>
<!--header-->
<div class="header">
    <!--顶部-->
    <div id="noweathertopBar" class="top">T{noweathertopBar}</div>
    <!--//顶部-->
     <div class="header-breadcrumbs"><a href="./index.shtml">首页</a> &gt; 流量专区</div>
</div>
<!--//header-->
<!--body-->
<div class="layout page-gnywdh">
	T_CORE{<%=busiNum%>}
</div>
<!--//body-->
<!--footer-->
<div class="footer">T{busifooter}</div>  
<!--//footer-->
</body>
</html>
