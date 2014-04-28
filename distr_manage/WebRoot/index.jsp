<%@ page contentType="text/html; charset=UTF-8"  isELIgnored="false"  %>
<%@include file="/page/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="max-age=0" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<link rel="shortcut icon" href="favicon.ico" type="images/x-icon"/>
	<link   rel= "Bookmark"   href= "favicon.ico" type="images/x-icon"/>
	<link href="${contextPath}/resource/css/login.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>	
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	
	<script src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" type="text/javascript"></script>
	<script src="${contextPath}/resource/scripts/main.js" language="javascript" type="text/javascript"></script>
	<script src="${contextPath}/resource/scripts/md5.js" language="javascript" type="text/javascript"></script>
	<script src="${contextPath}/resource/scripts/uompDialog.js" language="javascript" type="text/javascript"></script>
	
</head>
<body>
<div class="index-top">
  <div class="index-top-inner">
    <h1 class="siteLogo">江苏移动统一管理平台</h1>
    <p class="rightNav">
      <!-- <a href="#">江苏移动</a> | <a href="#">网上营业厅</a> | <a href="#">促销优惠</a> | <a href="#">在线客服</a> -->
    </p>
  </div>
</div>
<div class="index-main">
  <div class="index-main-bg" id="indexMainBg">
    <ul class="index-main-bgImg">
      <li><div class="bgImg"  style="background-image:url(./resource/img/index_banner_fl_20130106_1.jpg);"></div></li>
      <li><div class="bgImg"  style="background-image:url(./resource/img/index_banner_fl_20130106_2.jpg);"></div></li>
      <li><div class="bgImg"  style="background-image:url(./resource/img/index_banner_fl_20130106_3.jpg);"></div></li>
    </ul>
  </div>
<script type="text/javascript"> 
function banner(bannerId){
	var b = $("#"+bannerId);
	var img = b.find(".index-main-bgImg li");
	var imgLength = img.size();
	var nextNum;
	var t;
	
	var num = $("<ul></ul>").appendTo(b).addClass("index-main-bgNum");
	for(var i=1;i<=imgLength;i++){
		var numItem = $("<li></li>").clone().appendTo(num);
		numItem.mouseover(
		  function(){
			  clearInterval(t);
			  nextNum = imgFadeIn(numItem.index($(this)));
			  autoFade();
		  }
		);
	}
	var numItem = num.find("li");
    
	//开始
	img.eq(0).css({"display":"block"});
	numItem.eq(0).addClass("selected");
	//当图片多余1张的时候才进行切换动作
	if(imgLength>1){
	  nextNum = 1;
	  autoFade();
	}else{
	  numItem.unbind();
	}
	
	function imgFadeIn(index){
		//index = index-1;
		//图片切换
		$(".index-main-bgImg li").fadeOut("slow");
		img.eq(index).fadeIn("slow");
		//号码切换
		numItem.removeClass("selected").filter(":eq("+index+")").addClass("selected");
		//返回下一个序列
		var nextIndex = index + 1;
		if(nextIndex>=imgLength)nextIndex = 0;
		return(nextIndex);
	}
	
	function autoFade(){
		t = setInterval(
		  function(){
			nextNum = imgFadeIn(nextNum);
		  },
		  5000
		);
	}
}
banner("indexMainBg");
</script>
  <div class="index-main-in">
    <div class="index-login">
      <h2 class="index-login-title"><span></span>用户登录</h2>
      <input name="loginPwd" type="hidden" id="loginPwd" value="" />
      <table class="login-table"> 
        <col width="80" />
        <col width="220" />
        <tr>
          <td>用户名</td>
          <td><input type="text" name="loginId" id="loginId" class="inp-1" onkeydown="treatKeyEvent(event);" value="sysadmin"/></td>
        </tr>
        <tr>
          <td>密码</td>
          <td><input type="password" name="loginPassword" id="loginPassword" class="inp-1" onkeydown="treatKeyEvent(event);" value="sysadmin"/></td>
        </tr>
        <tr>
          <td>验证码</td>
          <td><input type="text" class="inp-2" name="loginCode" id="loginCode" onkeyup="treatKeyEvent(event); return false;"/> 
          	<a href="javascript:getVerifyCode();"><img src="${contextPath}/page/login_code.jsp?t=Math.random()" class="chkcodeImg" id="vcimg"/> </a></td>
        </tr>
        <tr>
          <td colspan="2"><input type="button" value="" class="btn-1" onclick="javascript:if(FrmCheck()) submitLogin();"/></td>
        </tr>
      </table>
    </div>
  </div>
</div>

<div class="index-foot" style="text-align:center">请使用Firefox 26.0浏览器体验最佳版本<br>中国移动通信集团江苏有限公司版权所有</div>

</body>
<script type="text/javascript">
$("#loginId").focus();
//回车事件处理
function treatKeyEvent(event){
	if(getKeyCode(event)==13){
		if (FrmCheck()) {
			submitLogin();
		}
	}
}

//验证管理员登陆信息
function FrmCheck(){
	var flag=true;
	var loginName = trimBlankFunc($("#loginId").val());
	var password = trimBlankFunc($("#loginPassword").val());
	var valideCode = trimBlankFunc($("#loginCode").val());

	if(loginName.length==0){
   		$("#loginId").focus();
   		flag=false;
	}else if(password.length==0){
   		$("#loginPassword").focus();
     	flag=false;
	}else if(valideCode.length==0){
		$("#loginCode").focus();
		flag=false;
	}
	if(flag){
		$("#loginPwd").val(hex_md5(password).toUpperCase());
	}
   	return flag;
}

//获取验证码		 
function getVerifyCode(){
	var url = "${contextPath}/page/login_code.jsp?t=" + Math.random();
	$("#vcimg").attr("src", url);
}

//登录请求		 
function submitLogin(){
	var jsonData = {
 		"reqUrl" : "login",
 		"reqMethod" : "loginCheck",
 		loginId : $('#loginId').val(),
 		loginPwd : $('#loginPwd').val(),
 		loginCode : $('#loginCode').val()
 	};
	$.singleReq({
		data : jsonData,
		success : function(ret){
			if("0" == ret.retCode){
				document.location.href = "${contextPath}/page/frame.jsp";
			}else{
				top.UOMPDialog.alert(ret.resMsg,0,"");
				getVerifyCode();
			}
		}
	});
}
</script>
</html>