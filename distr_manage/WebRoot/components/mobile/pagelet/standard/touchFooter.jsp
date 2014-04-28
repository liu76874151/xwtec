<%@page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
    <!--搜索-->
    <div class="index-search search mb10">
    	<form action="actionDispatcher.do" method="post">
    	    <input type="hidden" name="pageNum" value="searchResult"/>
    	    <input type="hidden" name="ver" value="t"/>
    	    <input type="hidden" name="operType" value="3">
    		<input type="text" value="" placeholder="流量叠加包" name="keywords"/> 
    		<label><input type="submit" value="搜索" class="sprites"/></label>
    	</form>
    </div>
    <!--//搜索-->
    <nav class="foot-nav">
      <a href="index.shtml">标准版</a>|
      <a href="index.thtml" class="grey">触屏版</a>|
      <a href="XZ_ZJBB@33.thtml">客户端</a>|
      <a href="http://www.js.10086.cn">电脑版</a>
      <br/>
      <a href="FKJY.thtml">意见反馈</a>|
      <a href="ZXKF.thtml">智能客服</a>|
      <a href="javascript:void(0)" onclick=" window.external.addFavorite(window.location, '江苏移动掌上营业厅')">加入收藏</a>
    </nav>
    <div class="copyright">
      <p>Copyright 2013 wap.js.10086.com</p>
      <p>中国移动通信版权所有</p>
    </div>
    
    <div class="mark"></div>
      <!-- 二次确认组件 -->
  <section id="confrimDialog" class="popbox" style="display:none;">
      <header>
        <h2>提示</h2><a class="close" onclick="GlobalDialog.closeDialog($('#confrimDialog'))">×</a>
      </header>
      
      <!-- 内容信息 -->
      <section id="dialogDataInfo" class="popbox-body">
        <p id="dialogWarning" style="display: none;" class="orange1"></p>
      </section>
      
      <!-- 业务信息 -->
      <section id="dialogBusiInfo" class="popbox-body">
        <p id="dialogTip" class="orange1"></p>
        <p id="dialogBusiName" class="grey3">业务名称：</p>
        <p id="dialogFee" class="grey3">业务资费：</p>
        <p id="dialogState" class="grey3">当前状态：</p>
        <p id="dialogeffectWay" class="grey3">生效方式：</p>
      </section>
      
      <!-- 短信验证码 / 服务密码-->
      <section id="dialogSmsMsg" class="popbox-body">
        <p id="dialogSmsWarning" style="display: none;" class="orange1"></p>
        <p id="Dialog_smsTitle"></p>
        <p>
            <input type="password" id="dialogSmsNum" maxlength="6"/> 
            <a id="canSendSMS"  style="display: none;">点击发送短信验证码</a>
            <strong id=dialogSMSCounter style="display: none;">短信验证码已发送(<em>0</em>)</strong>
        </p>
        <!-- 提示信息 -->
        <p id="dialogSms_Tip" >
        </p>
        </section>
        
      <footer>
        <p><a onclick="GlobalDialog.closeDialog($('#confrimDialog'))" class="btn btn-gray close">返 回</a> <a href="javascript:GlobalDialog.DIALOG_CALLBACK_YES();" class="btn btn-blue">确 定</a></p>
      </footer>
      
    </section>
    
    <!-- 公共结果组件 -->
    
    <!-- 普通确认组件 -->
      <section id="commonConfirm" class="popbox" style="display:none;">
          <header>
        <h2 id="commonConfirmTitle">提示</h2><a class="close" onclick="GlobalDialog.closeDialog($('#commonConfirm'))">×</a>
       </header>
          <section  class="popbox-body">
            <p class="grey2" id="commonConfirmInfo"></p>
          </section>
        <footer>
        <p><a href="javascript:GlobalDialog.COMMON_DAILGO_CALLBACK_NO();" class="btn btn-gray close">返 回</a> <a href="javascript:GlobalDialog.COMMON_DAILGO_CALLBACK_YES();" class="btn btn-blue">确 定</a></p>
          </footer>
      </section>
      
    <!-- 成功组件 -->
      <section id="resultCommonSuccess" class="popbox" style="display:none;">
          <header>
        <h2>提示</h2><a class="close" onclick="GlobalDialog.closeDialog($('#resultCommonSuccess'))">×</a>
       </header>
          <section  class="popbox-body">
            <p class="grey2" id="resultMsgSuccess"></p>
          </section>
        <footer>
        <p><a onclick="GlobalDialog.closeDialog($('#resultCommonSuccess'))"class="btn btn-gray close">返 回</a> <a href="javascript:GlobalDialog.closeDialog($('#resultCommonSuccess'));" class="btn btn-blue">确 定</a></p>
          </footer>
      </section>
      
      <!-- 失败组件 -->
      <section id="resultCommonFalse" class="popbox" style="display:none;" class="popbox">
              <header>
            <h2>提示</h2><a class="close" onclick="GlobalDialog.closeDialog($('#resultCommonFalse'))">×</a>
          </header>
          <section class="popbox-body">
              <p class="grey2" id="resultMsgFalse"></p>
            <p><span class="orange1">业务办理失败，可能是由于以下原因引起：</span></p>
            <hr />
            <p class="grey3">1、您已订购或退订该产品</p>
            <p class="grey3">2、预配号用户无法办理</p>
            <p class="grey3">3、停机用户无法办理【充值】</p>
            <p class="grey3">您可访问<a href="#">www.js.10086.cn</a>进行办理 或发送信“10086”至“10086”办理</p>
          </section>
          <footer>
            <p><a onclick="GlobalDialog.closeDialog($('#resultCommonFalse'))" class="btn btn-gray close">返 回</a> <a href="javascript:GlobalDialog.closeDialog($('#resultCommonFalse'))"; class="btn btn-blue">确 定</a></p>
          </footer>
        </section>   
    
