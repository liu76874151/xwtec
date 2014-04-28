$(document).ready(function(){
	var mygrid;
	var subSystemToolBar;
	subSystemListComponent.init();
	subSystemListComponent.initToolBar();
});

/**
 * 子系统查询
 */
var subSystemListComponent = {
	/**
	 * 创建datagrid对象
	 */
		init:	function() {
				mygrid = DhtmlxUtis.createGrid('gridbox',{
					"header"     : "序号,子系统编码,子系统名称,子系统标题,子系统URL地址,序号,备注",
					"initWidth"  : "80,80,120,120,120,80,*",
					"colAlign"   : "center,center,center,center,center,center,center",
					"colTypes"   : "ro,ro,ro,ro,ro,ro,ro",
					"colSorting" : "str,str,str,str,str,str,ro"
				});
		},
		initToolBar : function(){
				 subSystemToolBar = DhtmlxUtis.createTableToolbar('toolbar', mygrid, {
					"add" : {
						"title" : "子系统管理-新增",
						"url" : GLOBAL_INFO.CONTEXTPATH+"/page/admin/sub_system_add.jsp", 
						"callback" : function(){
							subSystemListComponent.query();
						}
					},
					"view" : {
						"title" : "子系统管理-查看",
						"url" : GLOBAL_INFO.CONTEXTPATH+"/page/admin/sub_system_view.jsp", 
						"param" : [
									{"name" : "sysNum", "colIndex" : "1"}
								],
						"callback" : function(){
					       
						}
					},
					"modify" : {
						"title" : "子系统管理-修改",
						"url" : GLOBAL_INFO.CONTEXTPATH+"/page/admin/sub_system_edit.jsp", 
						"param" : [
							{"name" : "sysNum", "colIndex" : "1"}
						],
						"callback" : function(){
							subSystemListComponent.query();
						}
					},
					"delete" : {"url" : "", "click" : function(){
						var selectRowId = mygrid.getSelectedRowId();
						var sysNum =mygrid.cells(selectRowId, 1).getValue();
						if(sysNum){
							UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
								"yes" : function()
								{
									$.singleReq( {
										 
										data : {
											"sysNum" : sysNum,
											"reqUrl" : "subSystem",
											"reqMethod" : "delSubSystem"
										},
										success : function(ret) {
											if(ret){
												if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
													var resMsg = ret.resMsg;
													var retCode = ret.retCode;
													UOMPComp.showSuccessDialog(resMsg,"");
													if(GLOBAL_INFO.SYS_FAILED == retCode){
														
													}else if(GLOBAL_INFO.SYS_SUCCESS){
														subSystemListComponent.query();
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
			    }
			 }
		 });
			
				subSystemListComponent.query();
	},
	
    loadDate : function (){ 
		var sysNum=$("#sysNum").val();
		$.singleReq( {
			data : {
				"sysNum" : sysNum,
				"reqUrl" : "subSystem",
				"reqMethod" : "querySubSystemByNum"
			},
			success : function(ret) {
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						subSystemListComponent.buildDataGrid(ret);
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

	/**
	 * 查询数据
	 */
	query : function (){ 
		$.singleReq({
			data : {
				"reqUrl" : "subSystem",
				"reqMethod" : "querySubSystemAll"
			},
			success : function(ret) {	
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){		
						subSystemListComponent.buildDataGrid(ret);
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
	/**
	 * 处理返回数据，创建datagrid
	 */
	 buildDataGrid : function (ret){ 
		 mygrid.clearAll();
			var subSystemList = ret.retObj.subSystemList;
			if(subSystemList !=null && subSystemList !=""){
				var subSystemInfo = null;
				 for(j = 0; j < subSystemList.length; j++){
					 subSystemInfo = subSystemList[j];
					 if(subSystemInfo){
						 mygrid.addRow(j+1,[j+1,subSystemInfo.sysNum,subSystemInfo.sysName,subSystemInfo.sysTitle,subSystemInfo.sysUri,subSystemInfo.xh,subSystemInfo.bz],j	 
						 );
					 }
					
				 }
			}
			else {
				UOMPComp.showFailedDialog("没有查询到记录","");
			}
	}
}