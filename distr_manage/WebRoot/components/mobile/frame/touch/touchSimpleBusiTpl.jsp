<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String busiNum = request.getParameter("busiNum");
%>
<!DOCTYPE HTML>
<html>
<head>
<title>江苏移动掌上营业厅-触屏版</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0" />
    <meta name="format-detection" content="telephone=no">
    
    <link href="./mobile/resource/touch/style/style2012_html5.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/main.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/common.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/dialog.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/jquery.pager.js"></script>
	
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/businessUtils.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/business/simpleBusi.js"></script>
</head>
<body>
	<!--header-->
  	<div id="touchTopBar">
		${result.touchTopBar}
  	</div>
	<!--header end-->
	<!--container-->
	<div id="busiArea" class="grid">
    	T_CORE{simpleBusi}
    </div>
	<!--container end-->
	
	<!--推荐分享-->
  <section class="module mb10">
    <header class="module-head">推荐分享</header>
    <div class="module-body">
      <p>短信推荐：</p>
      <p><input type="tel" /> <a href="#" class="btn-s btn-orange">推荐给好友</a></p>
      <p>一键分享：</p>
      <article class="share-sns">
        <ul>
          <li><a href="#" class="weibo-item"><img class="weibo-img" src="./mobile/resource/touch/images/share_weibo_1.jpg"> 移动微博</a></li>
          <li><a href="#" class="weibo-item"><img class="weibo-img" src="./mobile/resource/touch/images/share_weibo_sina.jpg"> 新浪微博</a></li>
          <li><a href="#" class="weibo-item"><img class="weibo-img" src="./mobile/resource/touch/images/share_weibo_qzone.jpg"> QQ空间</a></li>
        </ul>
        <ul>
          <li><a href="#" class="weibo-item"><img class="weibo-img" src="./mobile/resource/touch/images/share_weibo_sohu.jpg"> 搜狐</a></li>
          <li><a href="#" class="weibo-item"><img class="weibo-img" src="./mobile/resource/touch/images/share_weibo_feixin.jpg"> 飞信</a></li>
          <li><a href="#" class="weibo-item"><img class="weibo-img" src="./mobile/resource/touch/images/share_weibo_renren.jpg"> 人人网</a></li>
        </ul>
      </article>
    </div>
  </section>
  <!--//推荐分享-->
  <!--猜你喜欢-->
  <section class="module mb10">
    <header class="module-head">
      <p align="center">猜你喜欢</p>
    </header>
    <div class="module-body">
      <ul class="you-like">
        <li><a href="FXYW.thtml">飞信业务</a></li>
        <li><a href="GNYDSJTC.thtml">流量套餐</a></li>
        <li><a href="FXYW.thtml">在线充值</a></li>
      </ul>
    </div>
  </section>
  <!--//猜你喜欢-->
  
	<!--  
	<div id="touchKindly">
		${result.touchKindly}
  	</div>
  	<div id="touchGuess">
		${result.touchGuess}
  	</div>
  	-->
	<!--footer-->
	  <footer class="footer" id="touchFooterBar">
	   T{touchFooterBar}
	  </footer>
  	<!--//footer-->
</body>
</html>