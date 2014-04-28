$(document).ready(function(){
	cacheManageListComponent.initDataGridObj();
	cacheManageListComponent.initToolBar();
	cacheManageListComponent.queryCacheManageInfoInit();
});

/**
 * 用户查询
 */
var cacheManageListComponent = {
	
	/**
	 * 创建datagrid对象
	 */
	initDataGridObj : function(){
		cacheManageGrid = DhtmlxUtis.createGrid('gridbox', {
				"header"     : "序号,缓存key,缓存类型编码,key值是否需要参数,数据类型,存放的时间,备注",
				"initWidth"  : "50,300,120,120,100,100,*",
				"colAlign"   : "center,left,center,center,center,center,center",
				"colTypes"   : "ro,ro,ro,ro,ro,ro,ro",
				"colSorting" : "str,str,str,str,str,str,str",
				"colKeys"    : ["memKey", "cachedName", 
				                  {
				                      "key":"isNeedParam",
				                      "formatter":function(v)
				                      {
				                         if(v == "1")
				                         {
				                             return "是";
				                         }
				                         else if(v == "2")
				                         { 
				                             return "否";
				                         }
				                      }
				                  },
				                  {
				                      "key":"keyType",
				                      "formatter":function(v)
				                      {
				                         if(v == "1")
				                         {
				                             return "DB数据";
				                         }
				                         else if(v == "2")
				                         { 
				                             return "普通数据";
				                         }
				                      }
				                  }, "expireInSeconds", "bz"]
			});
	},
	
	/**
	 * 创建toolBar对象
	 */
	initToolBar : function(){
	    var cacheManageToolBar = DhtmlxUtis.createTableToolbar('toolbar', cacheManageGrid, {
				"add" : {
					"title" : "缓存设置-新增",
					"url" : GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_manage_add.jsp", 
					"callback" : function(){
						window.location.reload(); 
					}
				},
				
				"view" :{
				    "title" : "缓存设置-查看",
					"url"   : GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_manage_view.jsp", 
					"param" : [
						{"name" : "selectRowId", "colIndex" : "1"}
					],
					"callback" : function(){
				           window.location.reload();
					}
				}, 
				
				"modify" : {
				    "title" : "缓存设置-修改",
					"url" : GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_manage_edit.jsp", 
					"param" : [
						{"name" : "selectRowId", "colIndex" : "1"}
					],
					"callback" : function(){
					       window.location.reload();
					}
				},
				
				"delete" : {"url" : "", "click" : function(){
				     var selectRowId = cacheManageGrid.getSelectedRowId();
				     var memKey =cacheManageGrid.cells(selectRowId, 1).getValue();
                     if(memKey){
					    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
						"yes" : function()
						{
							$.singleReq({
								data : 
								{
									"reqUrl" : "cacheManage",
									"memKey" :memKey,
									"reqMethod" : "delCacheManageInfo"
								},
								success : function(ret)
								{
									if(ret){
										if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
											var resMsg = ret.resMsg;
											var retCode = ret.retCode;
											UOMPComp.showSuccessDialog(resMsg,"");
											if(GLOBAL_INFO.SYS_FAILED == retCode){
												
											}else if(GLOBAL_INFO.SYS_SUCCESS){
												window.location.reload(); 
											}
										}else{
											if(ret.resMsg){
												UOMPComp.showFailedDialog(ret.resMsg,"");
											}else{
												UOMPComp.showFailedDialog("系统异常","");
											}
										}
									}
								}
							});
						},
						"no" : function()
						{
						}
					}); 
				}else{
					UOMPComp.showTipDialog("请选择行", ""); 
				}
					
				}},
				
				"clearMem" : {"url" : "", "click" : function(){
					var selectRowId = cacheManageGrid.getSelectedRowId();
					var memKey =cacheManageGrid.cells(selectRowId, 1).getValue();
				     if(memKey){
					    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定清除该缓存吗？点击“取消”则返回！", {
						"yes" : function()
						{
						    memVal = memKey;
						    var paraArr = memKey.split("_\{");
						    var paraLen = paraArr.length;
						    
						    var paraLength = paraLen - 1;
						    var memKeyOne = paraArr[0];
						    
						    if(paraLen>1)
						     {
						         top.UOMPDialog.showSubPage("参数添加", 
						              GLOBAL_INFO.CONTEXTPATH + 
						              "/page/memcache/cache_manage_key.jsp?paraLength=" + paraLength 
						              + "&memKeyOne=" + memKeyOne + "&memKey=" + memVal,
						               function(){
					                                 window.location.reload();
					                              });
						     }
						     else
						     {
						         $.singleReq({
									data : 
									{
										"reqUrl"    : "cacheManage",
										"reqMethod" : "cleanCache",
										"memKey"    :  memVal,
										"memKeyVal" :  "0"
									},
									success : function(ret)
									{
										if(ret){
											if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
												var resMsg = ret.resMsg;
												var retCode = ret.retCode;
												UOMPComp.showSuccessDialog(resMsg,"");
												if(GLOBAL_INFO.SYS_FAILED == retCode){
													
												}else if(GLOBAL_INFO.SYS_SUCCESS){
													window.location.reload(); 
												}
											}else{
												if(ret.resMsg){
													UOMPComp.showFailedDialog(ret.resMsg,"");
												}else{
													UOMPComp.showFailedDialog("系统异常","");
												}
											}
										}
									}
								});
						     }
						
							
						},
						"no" : function()
						{
						}
					}); 
				}else{
					UOMPComp.showTipDialog("请选择行", ""); 
				}
				}}
			});
	},
	
	/**
	 * 查询用户列表
	 */
	queryCacheManageInfoList : function(){
		var memKey = $("#memKey").val();
		var keyType = $("#keyType").find("option:selected").val()
		var isNeedParam = $("#isNeedParam").find("option:selected").val()
		
		var	data =
			{
				"reqUrl"      : "cacheManage",
				"reqMethod"   : "queryCacheManageInfoList",
				"memKey"      : memKey,
				"keyType"     : keyType,
				"isNeedParam" : isNeedParam
			};
		DhtmlxUtis.loadGirdData(cacheManageGrid,data);	
	},

	/**
	 * 初始化查询缓存设置列表
	 */
	queryCacheManageInfoInit : function(){
			var data = 
			{
				"reqUrl" : "cacheManage",
				"reqMethod" : "queryCacheManageInfoList"
			};
			DhtmlxUtis.loadGirdData(cacheManageGrid,data);
	}
}