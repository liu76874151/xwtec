<%@page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1, initial-scale=1"/>
<meta name="format-detection" content="telephone=no"/>
<title>江苏移动掌上营业厅-体验版</title>
<link href="./mobile/resource/standard/style/public.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!--header-->
	<div class="header">
	  	<!--顶部-->
    	<div id="topBar" class="top">T{noweathertopBar}</div>
    	<!--//顶部-->
   		<div id="navBar">T{navBar}</div>
	</div>
	<!--header end-->
	<!--container-->
	<div class="layout page-gnywdh">
  		<!--业务区块-->
			T_CORE{simpleBusi}
  		<!--业务区块 end-->
	</div>
	<!--container end-->
	<!--footer-->
	<div class="footer">
		<!--业务推荐-->
	    <!--业务推荐 end-->
	    <!--页脚-->
	    <div class="footer">T{busifooter}</div>
	    <!--页脚 end-->
	</div>
	<!--footer end-->
</body>
</html>