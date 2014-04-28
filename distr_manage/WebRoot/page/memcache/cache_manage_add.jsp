<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/memcache/cache_manage_add.js"></script>  
  </head>
  
  <body>
   	<div id="main_div" style="padding:10px;overflow:auto;">
	    <div class="breadcrumb-iframe">缓存设置--新增</div>
	    <form name="cacheAddForm" id="cacheAddForm" action="" method="post">
 		    <!-- 展示信息内容区域开始 -->
 		    <table class="tb">
 			  <tr>
 				<th align="right" width="10%"><span class="required">*</span>缓存key：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="memKey" id="memKey"/><span class="errorMsg"></span></td>
	          </tr>
	          
	          <tr>
	          <th align="right">缓存类型名称：</th>
	            <td class="form_table_content">
		         	<select name="cachedName" id="cachedName">
					   	
				 	</select>
			    </td>
	          </tr>
	          
	          <tr>
 				<th align="right"><span class="required">*</span>DAO类的名称：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="daoName" id="daoName"/><span class="errorMsg"></span></td>
	          </tr>
	          
	          <tr>
 				<th align="right"><span class="required">*</span>DAO类中的方法名称：</th>
	              <td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="daoMethod" id="daoMethod"/><span class="errorMsg"></span></td>
	          </tr>
	          
	          <tr>
	            <th align="right">key值是否需要参数：</th>
	            <td class="form_table_content">
		         	<select name="isNeedParam" id="isNeedParam">
				       	<option value="1">是</option>
				       	<option value="2">否</option>
				 	</select>
			    </td>
	          </tr>
	          
	          <tr>
	            <th align="right">数据类型：</th>
	            <td class="form_table_content">
		         	<select name="keyType" id="keyType">
				       	<option value="1">DB数据</option>
				       	<option value="2">普通数据</option>
				 	</select>
			    </td>
	          </tr>
	          
	          <tr>
		        <th align="right"><span class="required">*</span>存放的时间：</th>
	              <td class="form_table_content" colspan="3"><input type="text" class="form_input" maxlength="100" id="expireInSeconds" name="expireInSeconds" /></td>
	          </tr>
	          
	          <tr>
  				<th align="right">备注：</th>
				  <td class="form_table_content" colspan="3" ><textarea class="form_text" name="bz" id="bz"></textarea><span class="errorMsg"></span></td>
			  </tr>
    		</table>
		    <!-- 展示信息内容区域结束 -->
		    
 		   <!-- 按钮操作区域开始 -->
           <table width="100%" height="40">
		     <tr>
               <td align="center">
	            <a href="javascript:void(0)" class="btn" onclick="addSubmit();">确认提交</a>
	            <a href="javascript:void(0)" class="btn" onclick="document.cacheAddForm.reset();">清除重填</a>
	           </td>
		     </tr>
    	   </table>
    	   <!-- 按钮操作区域结束 -->
    	</form>
    </div>
  </body>
</html>
