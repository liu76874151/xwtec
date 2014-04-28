$(document).ready(function(){
	//初始化布局
	groupInfoQComponent.initLayout();

	//加载树  调用后台方法，获取数据
	groupInfoQComponent.getTreeData();
});

var groupInfoQComponent = {
		//树对象
		groupInfoQTree : null,
		//布局对象
		groupInfoLayout : null,

		//初始化布局
		initLayout : function (){
			groupInfoLayout = new dhtmlXLayoutObject(document.body, "2U");
			groupInfoLayout.cells("a").setText("菜单导航区");
			groupInfoLayout.cells("a").hideHeader();
			groupInfoLayout.cells("a").setWidth(230);
			groupInfoLayout.cells("a").attachObject("templet_left");

			groupInfoLayout.cells("b").setText("主显示区");
			groupInfoLayout.cells("b").hideHeader();
			groupInfoLayout.cells("b").attachObject("templet_main");

			//生成树对象
			this.loadTree();
		},

		//生成树对象
		loadTree : function(){
			groupInfoQTree = DhtmlxUtis.createTree(group_tree, this.tonclick);
		},

		//加载树  调用后台方法，获取数据
		getTreeData : function()
		{
			$.singleReq({
				data :
				{
					"reqUrl" : "privilegeinfo",
					"reqMethod" : "queryUserInfo"
				},
				success : function(ret)
				{
					if(ret){
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
							groupInfoQTree.loadJSONObject(ret.retObj);
						}else{
							UOMPComp.showFailedDialog(ret.resMsg,"");
						}
					}
				}
			});
		},

		//树点击事件
		tonclick : function(id) {
			var groupId = groupInfoQTree.getUserData(id, "groupId");
			var userType = groupInfoQTree.getUserData(id, "userType");
			if(userType != "1")
			{
				userType = "0";
			}
			$("#privilegeInfoMain").attr("src",GLOBAL_INFO.CONTEXTPATH+"/page/admin/user_privilege_edit.jsp?userId="+groupId+"&userType=" + userType);
		}
}