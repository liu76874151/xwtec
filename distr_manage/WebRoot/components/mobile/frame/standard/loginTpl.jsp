<%@page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%
    String nonbusiNum = request.getParameter("nonbusiNum");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1, initial-scale=1"/>
<meta name="format-detection" content="telephone=no"/>
<title江苏移动掌上营业厅-体验版</title>
<meta content="江苏移动掌上营业厅" name="keywords" /> 
<meta content="江苏移动掌上营业厅" name="description" /> 
<link href="./mobile/resource/standard/style/public.css" rel="stylesheet" type="text/css">
<style>
/*登录*/
.login-pannel .box-body{min-width:220px;max-width:260px;margin:auto;padding:8px 10px;}
.login-pannel-body{border:1px solid #99d5ff;border-top:0;}
.login-pannel-form{padding:10px;}
.login-pannel-form p{padding-bottom:5px;}
.login-pannel-form label{min-width:55px;display:inline-block;vertical-align:middle;}
@media screen and (min-width:320px){
.login-pannel-form p input[type=text]{width:230px;}
.login-pannel-form p input[type=password]{width:230px;}
.login-pannel-form .width0{min-width:0;}
.login-pannel-form .btn{width:228px;height:24px;line-height:24px;border-radius:0;}
}
</style>
</head>
<body>
	<!--header-->
	<div class="header">
	  	<!--头部条-->
	  	<div id="topBar" class="top">T{noweathertopBar}</div>
	  	<!--头部条 end-->
  		
	</div>
	<!--header end-->
  		<!--用户登录-->
  		<div class="layout page-gnywdh">T_NONCORE{<%=nonbusiNum%>}</div>
  		<!--用户登录 end-->
  		
	<!--footer-->
	<div class="footer">
	    <!--业务推荐 end-->
	    <!--页脚-->
	    <div>T{footBar}</div>
	    <!--页脚 end-->
	</div>
	<!--footer end-->
</body>
</html>
