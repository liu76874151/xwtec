$(document).ready(function(){
	userInfoListComponent.initDataGridObj();
	userInfoListComponent.initToolBar();
	userInfoListComponent.initQuery();
});

/**
 * 用户查询
 */
var userInfoListComponent = {
    initQuery : function(){
        this.paging=new dhtmlxGridPaging('paging');
		this.paging.initPaging(userInfoListComponent.queryUserInfoList);
    },
	userInfoWindow : null,
	/**
	 * 创建datagrid对象
	 */
	initDataGridObj : function(){
		userinfoGrid = DhtmlxUtis.createGrid('gridbox', {
			"header"     : "序号,登录名称,用户名称,用户状态,联系电话",
			"initWidth"  : "50,240,240,240,240",
			"colAlign"   : "center,center,center,center,center",
			"colTypes"   : "ro,ro,ro,ro,ro",
			"colSorting" : "str,str,str,str,strs"
		});
	},
	/**
	 * 创建toolBar对象
	 */
	initToolBar : function(){
		var groupId = $("#groupId").val();
		var areaNum = $("#areaNum").val();
		var userinfoToolBar = DhtmlxUtis.createTableToolbar('toolbarObj', userinfoGrid, {
			"view" : {
				"title" : "用户-查看",
				"url" : GLOBAL_INFO.CONTEXTPATH + "/page/admin/user_info_view.jsp",
				"param" : [
							{"name" : "loginName", "colIndex" : "1"}
						],
				"click" : "11",
				"callback" : function(){
					userInfoListComponent.initQuery();
				}
			},
			"add" : {
				"title" : "用户-新增",
				"url" : GLOBAL_INFO.CONTEXTPATH + "/page/admin/user_info_add.jsp?groupId="+groupId+"&areaNum="+areaNum,
				"callback" : function(){
					window.location.reload();
				}
			},
			"modify" : {
				"title" : "用户-修改",
				"url" : GLOBAL_INFO.CONTEXTPATH + "/page/admin/user_info_edit.jsp",
				"param" : [
							{"name" : "loginName", "colIndex" : "1"}
						],
				"click" : "11",
				"callback" : function(){
					window.location.reload();
				}
			},
			"delete" : {"url" : "", "click" : function(){
				var selectRowId = userinfoGrid.getSelectedRowId();
				var loginName =userinfoGrid.cells(selectRowId, 1).getValue();
				if(loginName){
					UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
						"yes" : function()
						{
							$.singleReq({
								data :
								{
									"reqUrl" : "userInfo",
									"loginName" :loginName,
									"reqMethod" : "deleteUserInfo"
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
													userInfoListComponent.initQuery();
											}
										}else{
											UOMPComp.showFailedDialog(ret.resMsg,"");
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
	queryUserInfoList : function(start,end){
		var userName = $("#userName").val();
		var userGroup = $("#groupId").val();
		$.singleReq({
			data :
			{
				"reqUrl" : "userInfo",
				"userName" : userName,
				"userGroup" : userGroup,
				"start" : start,
				"end" : end,
				"reqMethod" : "queryUserInfo"
			},
			success : function(ret)
			{
				if(ret){
					if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
						var page =ret.retObj.pageList;
						if(page != null && page != "" && page.records!=null){
						    var userInfoList= page.records;
							var userInfo = null;
							var id = null;
							var userState = "";
							var datas = [];
							 for(j = 0; j < userInfoList.length; j++){
								 userInfo = userInfoList[j];
								 if(userInfo){
									 id = userInfo.loginName;
									//用户状态（0-禁用； 1-启用）
									if(userInfo.userState == "1"){
										userState = "启用";
									}else if(userInfo.userState == "0"){
										userState = "禁用";
									}
									var data = [ j + 1, userInfo.loginName,userInfo.userName, userState, userInfo.phone];
									datas[j] = data;
								 }
							 }
							 userinfoGrid.clearAll();
							 userinfoGrid.parse(datas, "jsarray");
							 userInfoListComponent.paging.setTotalPage(page.totalRecord);
							 userInfoListComponent.paging.refresh(start,end);
						}else{
						    UOMPComp.showFailedDialog("没有查询到记录","");
						    userInfoListComponent.paging.setTotalPage(0);
						}
					}else{
						UOMPComp.showFailedDialog(ret.resMsg,"");
					}
				}
			}
		});
	}
}