$(document).ready(function(){
	cacheTypeManageListComponent.initDataGridObj();
	cacheTypeManageListComponent.initToolBar();
	cacheTypeManageListComponent.queryCacheTypeManageInfoInit();
});

/**
 * 用户查询
 */
var cacheTypeManageListComponent = {
	
	/**
	 * 创建datagrid对象
	 */
	initDataGridObj : function(){
		cacheTypeGrid = DhtmlxUtis.createGrid('gridbox', {
				"header"     : "序号,缓存类型编码,缓存类型名称,缓存类型描述",
				"initWidth"  : "50,200,200,*",
				"colAlign"   : "center,left,left,left",
				"colTypes"   : "ro,ro,ro,ro",
				"colSorting" : "str,str,str,str",
				"colKeys"    : ["typeNum","typeName","desc"]
			});
	},
	
	/**
	 * 创建toolBar对象
	 */
	initToolBar : function(){
		var cacheManageToolBar = DhtmlxUtis.createTableToolbar('toolbar', cacheTypeGrid, {
				"add" : {
					"title" : "缓存类型-新增",
					"url" : GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_type_add.jsp", 
					"callback" : function(){
				          window.location.reload();
					}
				},
				
				"view" :{
				    "title" : "缓存类型-查看",
					"url"   : GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_type_view.jsp", 
					"param" : [
						{"name" : "selectRowId", "colIndex" : "1"}
					],
					"callback" : function(){
				           window.location.reload();
					}
				}, 
				
				"modify" : {
				    "title" : "缓存类型-修改",
					"url" : GLOBAL_INFO.CONTEXTPATH + "/page/memcache/cache_type_edit.jsp", 
					"param" : [
						{"name" : "selectRowId", "colIndex" : "1"}
					],
					"callback" : function(){
					       window.location.reload();
					}
				},
				
				"delete" : {"url" : "", "click" : function(){
					var selectRowId = cacheTypeGrid.getSelectedRowId();
					var typeNum =cacheTypeGrid.cells(selectRowId, 1).getValue();
					if(typeNum){
					    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
						"yes" : function()
						{
							$.singleReq({
								data : {
								"typeNum" : typeNum,
								"reqUrl" : "cacheTypeManage",
								"reqMethod" : "delCacheTypeInfo"
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
					var selectRowId = cacheTypeGrid.getSelectedRowId();
					var typeNum =cacheTypeGrid.cells(selectRowId, 1).getValue();
					if(typeNum){
					    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定清除该缓存吗？点击“取消”则返回！", {
						"yes" : function()
						{
							$.singleReq({
								data : {
									"reqUrl"         : "cacheTypeManage",
									"reqMethod"      : "cleanCache",
									"isCleanAllFlag" : "0",
									"typeNum"        : typeNum
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
				}}
			});
	},
		
	/**
	 * 查询用户列表
	 */
	queryCacheManageInfoList : function(){
		var typeNum = $("#typeNum").val();
		var typeName = $("#typeName").val();
		var data =
			{
				"reqUrl" : "cacheTypeManage",
				"reqMethod" : "queryCacheTypeManageInfoList",
				"typeNum" : typeNum,
				"typeName" : typeName
			};
		DhtmlxUtis.loadGirdData(cacheTypeGrid,data);
	},

	/**
	 * 初始化查询缓存设置列表
	 */
	queryCacheTypeManageInfoInit : function(){
		
	     var data =
			{
				"reqUrl" : "cacheTypeManage",
				"reqMethod" : "queryCacheTypeManageInfoList"
			};
		DhtmlxUtis.loadGirdData(cacheTypeGrid,data);
	},
	
	//清除所有缓存缓存
	cleanAllCache : function(){
		if(1 == 1)
		{
		    UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除所有缓存信息吗？点击“取消”则返回！", {
			"yes" : function()
			{
				$.singleReq
				({
					data : 
					{
						"reqUrl"         : "cacheTypeManage",
						"reqMethod"      : "cleanCache",
						"isCleanAllFlag" : "1"
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
       }
	}
}