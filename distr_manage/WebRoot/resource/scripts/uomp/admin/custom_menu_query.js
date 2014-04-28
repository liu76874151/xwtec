$(document).ready(function(){
	//初始化布局
	infoComponent.initLayout();
	//加载树  调用后台方法，获取数据
	infoComponent.getTreeData();
});

var infoComponent = {
		//树对象
		custemInfoTree : null,
		//布局对象
		custemInfoLayout : null,
		//树右键菜单1
		treeRgigthMenuAdd : null,
		//树右键菜单2
		treeRgigthMenuAll : null,
		//初始化布局
		initLayout : function (){ 
			custemInfoLayout = new dhtmlXLayoutObject(document.body, "2U");
			custemInfoLayout.cells("a").setText("菜单导航区");
			custemInfoLayout.cells("a").hideHeader();
			custemInfoLayout.cells("a").setWidth(230);
			custemInfoLayout.cells("a").attachObject("templet_left");
			custemInfoLayout.cells("b").setText("主显示区");
			custemInfoLayout.cells("b").hideHeader();
			custemInfoLayout.cells("b").attachObject("templet_main");
			//生成树对象
			this.loadTree();
		},
		
		//生成树对象
		loadTree : function(){
			custemInfoTree = DhtmlxUtis.createTree(custom_tree, this.tonclick);
			custemInfoTree.setOnRightClickHandler(this.treeOnRegihtClick);//右键事件
		},
		
		//加载树  调用后台方法，获取数据
		getTreeData : function()
		{
			$.singleReq({
				data : 
				{
					"reqUrl" : "customMenu",
					"reqMethod" : "queryCustomMenuInfo"
				},
				success : function(ret)
				{
					if(ret){
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
							if(ret.retObj){
								custemInfoTree.loadJSONObject(ret.retObj);
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
		
		//树点击事件
		tonclick : function(id) {
			var funcId =  encodeURIComponent(custemInfoTree.getUserData(id, "funcId"));
			var jbNum = encodeURIComponent(custemInfoTree.getUserData(id, "jbNum"));
		    var subSysNum =  encodeURIComponent(custemInfoTree.getUserData(id, "subSysNum"));
			custemInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/customMenu_view.jsp?funcId="+funcId+"&jbNum="+jbNum+"&subSysNum="+subSysNum);
		},
		
		//树右键生成菜单
		treeOnRegihtClick : function(id){
			//右键选中树
			custemInfoTree.focusItem(id);
			custemInfoTree.selectItem(id,false);
			
			//若存在则清空已存在的树
			if(this.treeRgigthMenuAdd){
				this.treeRgigthMenuAdd.clearAll();
			}
			if(this.treeRgigthMenuAll){
				this.treeRgigthMenuAll.clearAll();
			}
			var mj = custemInfoTree.getUserData(id, "mj");
			
			var X=document.getElementById('mouseXPosition').value;
			var Y=document.getElementById('mouseYPosition').value;
			
			this.treeRgigthMenuAdd = new dhtmlXMenuObject();
			this.treeRgigthMenuAdd.renderAsContextMenu();
			this.treeRgigthMenuAdd.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		    var jb = custemInfoTree.getUserData(id, "jb");
			if(id == "01")
			{
				this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 0, "new", "新增", false, "add.gif");
			}else
			{   if(jb <2){
			        this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 0, "new", "新增", false, "add.gif");
					this.treeRgigthMenuAdd.addNewSeparator("new");
					this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 2, "edit", "修改", false, "modify.gif");
					this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 3, "delete", "删除", false, "delete.gif");
			    }else if(jb=2){
					this.treeRgigthMenuAdd.addNewChild(this.treeRgigthMenuAdd.topId, 3, "delete", "删除", false, "delete.gif");
			    }
			}
		

			this.treeRgigthMenuAdd.attachEvent("onClick", function(menuId, zoneId, casState) {
				infoComponent.templetTreeRightOpt(id,menuId);
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
		    var mousePos = infoComponent.mousePosition(ev);
			$('#mouseXPosition').val(mousePos.x);
			$('#mouseYPosition').val(mousePos.y);
		},
		
		//模板树右键操作
		 templetTreeRightOpt :function(treeId,menuId){
		    var funcId = custemInfoTree.getUserData(treeId, "funcId");
			var jbNum = custemInfoTree.getUserData(treeId, "jbNum");
			var subSysNum = custemInfoTree.getUserData(treeId, "subSysNum");
			var jb = custemInfoTree.getUserData(treeId, "jb");
			var mj = custemInfoTree.getUserData(treeId, "mj");
			
			funcId = encodeURIComponent(encodeURIComponent(funcId));
			jbNum = encodeURIComponent(encodeURIComponent(jbNum));
			subSysNum = encodeURIComponent(encodeURIComponent(subSysNum));
			jb = encodeURIComponent(encodeURIComponent(jb));
			if(jbNum == null || jbNum == "" || jbNum == "undefined" || subSysNum ==null || subSysNum== "undefined" ){
				jbNum = "ROOT";
			}
			//新增
			if(menuId == 'new'){
			     if(treeId=='01' && treeId !=null ){
			        custemInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/custom_menu_title_add.jsp?funcId=" + funcId + "&jbNum=" + jbNum+"&jb="+jb);
			     }else{
			        custemInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/custom_menu_add.jsp?subSysNum=" + subSysNum + "&jbNum=" + jbNum+"&jb="+jb);
			     }
			}
			//修改
			if(menuId == 'edit'){
				custemInfoLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/customMenu_edit.jsp?subSysNum=" + subSysNum + "&jbNum=" + jbNum + "&funcId=" + funcId);
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
							    "reqUrl"     : "customMenu",
								"reqMethod"  : "deleteCustomMenu",
								"subSysNum"  : subSysNum,
								"jbNum"      : jbNum,
								"funcId"     : funcId
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
}
document.onmousemove = infoComponent.mouseMove;
