$(document).ready(function(){
	//初始化布局
	userInfoQComponent.initLayout();

	//加载树  调用后台方法，获取数据
	userInfoQComponent.getTreeData();
});

var userInfoQComponent = {
		//树对象
		userInfoQTree : null,
		//布局对象
		userInfoLayout : null,
		//树右键菜单1
		treeRgigthMenuAdd : null,
		//树右键菜单2
		treeRgigthMenuAll : null,

		//初始化布局
		initLayout : function (){
			userInfoLayout = new dhtmlXLayoutObject(document.body, "2U");
			userInfoLayout.cells("a").setText("菜单导航区");
			userInfoLayout.cells("a").hideHeader();
			userInfoLayout.cells("a").setWidth(230);
			userInfoLayout.cells("a").attachObject("templet_left");

			userInfoLayout.cells("b").setText("主显示区");
			userInfoLayout.cells("b").hideHeader();
			userInfoLayout.cells("b").attachObject("templet_main");

			//生成树对象
			this.loadTree();
		},

		//生成树对象
		loadTree : function(){
			userInfoQTree = DhtmlxUtis.createTree(group_tree, this.tonclick);
		},

		//加载树  调用后台方法，获取数据
		getTreeData : function()
		{
			$.singleReq({
				data :
				{
					"reqUrl" : "groupinfo",
					"reqMethod" : "queryGroupInfo"
				},
				success : function(ret)
				{
					if(ret){
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
							userInfoQTree.loadJSONObject(ret.retObj);
						}else{
							UOMPComp.showFailedDialog(ret.resMsg,"");
						}
					}
				}
			});
		},

		//树点击事件
		 tonclick : function(id) {
			var groupId = userInfoQTree.getUserData(id, "groupId");
			var areaNum = userInfoQTree.getUserData(id, "areaNum");
			userInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/user_info_list.jsp?groupId="+groupId+"&areaNum="+areaNum);
			//$("#groupInfoMain").attr("src",GLOBAL_INFO.CONTEXTPATH+"/page/admin/user_info_list.jsp?groupId="+groupId+"&areaNum="+areaNum);
		}
}
