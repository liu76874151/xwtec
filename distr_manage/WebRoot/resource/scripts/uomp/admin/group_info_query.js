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
		//树右键菜单1
		treeRgigthMenuAdd : null,
		//树右键菜单2
		treeRgigthMenuAll : null,
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
			groupInfoQTree.setOnRightClickHandler(this.treeOnRegihtClick);//右键事件
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
							if(ret.retObj){
								groupInfoQTree.loadJSONObject(ret.retObj);
							}
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
			groupInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/group_info_view.jsp?groupId="+groupId);
		},

		//树右键生成菜单
		 treeOnRegihtClick : function(id){
			//右键选中树
			groupInfoQTree.focusItem(id);
			groupInfoQTree.selectItem(id,false);

			//若存在则清空已存在的树
			if(this.treeRgigthMenuAdd){
				this.treeRgigthMenuAdd.clearAll();
			}
			if(this.treeRgigthMenuAll){
				this.treeRgigthMenuAll.clearAll();
			}
			var mj = groupInfoQTree.getUserData(id, "mj");

			var X=document.getElementById('mouseXPosition').value;
			var Y=document.getElementById('mouseYPosition').value;

			this.treeRgigthMenuAdd = new dhtmlXMenuObject();
			this.treeRgigthMenuAdd.renderAsContextMenu();
			this.treeRgigthMenuAdd.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
			this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 0, "new", "新增", false, "add.gif");
			this.treeRgigthMenuAdd.addNewSeparator("new");
			this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 2, "edit", "修改", false, "modify.gif");
			this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 3, "delete", "删除", false, "delete.gif");
			this.treeRgigthMenuAdd.attachEvent("onClick", function(menuId, zoneId, casState) {
				groupInfoQComponent.templetTreeRightOpt(id,menuId);
			});
			this.treeRgigthMenuAdd.showContextMenu(X,Y);
		},

		//说明:获取鼠标位置
		 mousePosition : function(ev){
			if(ev.pageX || ev.pageY){
				return {x:ev.pageX, y:ev.pageY};
			}
			return {
				x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
				y:ev.clientY + document.body.scrollTop  - document.body.clientTop
			};
		},

		 mouseMove : function(ev){
		    ev = ev || window.event;
		    var mousePos = groupInfoQComponent.mousePosition(ev);
			$('#mouseXPosition').val(mousePos.x);
			$('#mouseYPosition').val(mousePos.y);
			//document.getElementById('mouseYPosition').value = mousePos.y;
		},

		//模板树右键操作
		 templetTreeRightOpt :function(treeId,menuId){
			var groupId = groupInfoQTree.getUserData(treeId, "groupId");
			var areaNum = groupInfoQTree.getUserData(treeId, "areaNum");
			var mj = groupInfoQTree.getUserData(treeId, "mj");

			//新增
			if(menuId == 'new'){
				groupInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/group_info_add.jsp?groupId="+groupId+"&areaNum="+areaNum);
			}

			//修改
			if(menuId == 'edit'){
				groupInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/group_info_edit.jsp?groupId="+groupId+"&areaNum="+areaNum);
			}
			//删除
			if(menuId == 'delete'){
				if(mj == "0"){
					UOMPComp.showTipDialog("不是末级不能删除！", "");
				}
				else{
					UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该信息吗？点击“取消”则返回！", {
						"yes" : function()
						{
						$.singleReq({
							data :
							{
								"reqUrl" : "groupinfo",
								"groupId" : groupId,
								"reqMethod" : "deleteGroupInfo"
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

				}
			}
		}
}

document.onmousemove = groupInfoQComponent.mouseMove;
