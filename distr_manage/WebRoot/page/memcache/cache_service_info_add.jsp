<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"    %>
<%@include file="../taglibs.jsp" %>
<html>
	<head>
    	<title>新增缓存服务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/memcache/cache_service_add.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" ></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" language="javascript" ></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
  	</head>
  	<body onload="">
   		<div id="main_div" style="padding:10px;overflow:auto;">
  			<div class="breadcrumb-iframe">缓存服务--添加</div>
	  		<form name="addForm" id="addForm" action="" method="post">
	    		<!-- 展示信息内容区域开始 -->
	    		<table width="98%" class="tb">
                    <tr>
                        <th align="right" width="10%"><span class="required">*</span>缓存服务器类型：</th>
                        <td class="form_table_content"><select id="serverType" name="serverType">
                            <option value="Memcached">Memcached</option>
                            <option value="Redis">Redis</option>
                        </select><span class="errorMsg"></span></td>
                    </tr>
  	  			<tr>
  	  				<th align="right" width="10%"><span class="required">*</span>缓存编码：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="num" value="" id="num"/><span class="errorMsg"></span></td>
	 			</tr>
	 			<tr>
	 				<th align="right"><span class="required">*</span>缓存类型名称：</th>
					<td class="form_table_content">
					<select id="typeNum" name="typeNum" class="form_input">
					
					</select>
					</td>
	 			</tr>
	 			<tr>
	 				<th align="right">服务器列表：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="servers" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">服务器权重：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="weights" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">初始连接数：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="initConn" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">最小连接数：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="minConn" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">最大连接数：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="maxConn" /></td>
	 			</tr>
	 			
	 			<tr>
	 				<th align="right">最大处理时间：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="maxIdle" /></td>
	 			</tr>
	 				<tr>
	 				<th align="right">主线程睡眠时间：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="mainSleep" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">tcp参数nagle：</th>
					<td class="form_table_content">
					<select id="nagle" name="nagle">  
					<option value="true"> true </option>
					<option value="false"> false </option>
					</select>
					</td>
	 			</tr>
	 			
	 			<tr>
	 				<th align="right">tcp参数socketTO：</th>
					<td class="form_table_content"><input type="text" class="form_input" maxlength="100" name="socketTo" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis服务器地址：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redHost" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis服务端口：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redPort" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis密码：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redPass" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis链接数据库：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redDefaultdb" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis客户端超时时间：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redTimeout" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis最大连接数：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redMaxactive" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis最大空闲数：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redMaxidle" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">Redis最大连接等待时间：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redMaxwait" /></td>
	 			</tr>
	 			<tr>
	 				<th align="right">指明是否在从池中取出连接前进行检验：</th>
					<td class="form_table_content" ><input type="text" class="form_input" maxlength="100" name="redTestonborrow" /></td>
	 			</tr>
	 			
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
	  			<tr>
				    <td align="center">
					  <a href="javascript:void(0)" class="btn" onclick="add();">确认提交</a>
					  <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
					</td>
	  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  	</body>
  	<script type="text/javascript">
  	getTypeName();
  	
  	$(document).ready(
		function() {
			   ValidateUtil.validate({
    	targetForm : "addForm",
    	rules : {
			num    : {required : true , maxlength : 100}
		},
		messages : {
			num    : {required    : "请输入缓存编码" ,maxlength : "长度必须小于等于{0}"}
		}
    });
		    });
  	</script>
</html>