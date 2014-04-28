<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String busiNum = request.getParameter("busiNum");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
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
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/swiper.min.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/common/businessUtils.js"></script>
	<script language="javascript" type="text/javascript" src="./mobile/resource/touch/js/business/<%=busiNum %>.js"></script>
</head>
<body>
	<!--header-->
  	<div id="touchLoginBar">
		${result.touchLoginBar}
  	</div>
  	
  	
  	<!--//header-->
    <div id="busiArea" class="grid">
    	T_CORE{<%=busiNum%>}
    </div>
    
    <section class="module mb10">
    <div class="reminder">
      <table width="100%">
        <col width="50" />
        <tr>
          <th>温馨提示</th>
          <td>
            <p class="grey3">温馨提示</p>
          </td>
        </tr>
      </table>
    </div>
  </section>
  
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
  
  <!--footer-->
	  <footer class="footer" id="touchFooterBar">
	   T{touchFooterBar}
	  </footer>
  	<!--//footer-->

</body>
</html>