$(document).ready(function(){
	//初始化布局
	initLayout();
	
	//加载树  调用后台方法，获取数据
	indexPage.getTreeData();
});

var dhxLayout;

var tree;
function loadTree(){
    tree = DhtmlxUtis.createTree("fun_tree",tonclick);
    tree.setOnRightClickHandler(treeOnRegihtClick);
}

var indexPage = 
{
	//加载树  调用后台方法，获取数据
	getTreeData : function()
	{
		$.singleReq({
			data : 
			{
				"reqUrl" : "businessInfoHandler",
				"reqMethod" : "queryBusiBaseInfoExt"
			},
			success : function(ret)
			{
			    if(ret.retCode == 0)
			    {
			        tree.loadJSONObject(ret.retObj,function(){
			        	tree.loadOpenStates();
			        });
			    }
				else
				{
				    alert(ret.resMsg);
				}
			    			   
			}
		});
	}
};

//初始化布局
function initLayout(){ 
	dhxLayout = new dhtmlXLayoutObject(document.body, "2U");
	dhxLayout.cells("a").setText("菜单导航区");
	dhxLayout.cells("a").hideHeader();
	dhxLayout.cells("a").setWidth(230);
	dhxLayout.cells("a").attachObject("templet_left");
	
	dhxLayout.cells("b").setText("主显示区");
	dhxLayout.cells("b").hideHeader();
	dhxLayout.cells("b").attachObject("templet_main");
	
	loadTree();
}



//刷新区域大小
function onPanelResize(){

    //依tabbar大小来自动调整页签大小。
	tabbar.enableAutoReSize(true);
	tabbar.setSize("450px","400px",true);
};

//树点击事件
function tonclick(id) {
	var funId = tree.getUserData(id, "funcId");
	var jbNum = tree.getUserData(id, "jbNum");
	if(funId != null)
	{
	    dhxLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/business/businessinfo/businessInfo_modify.jsp?&jbNum=" + jbNum + "&funcId=" + funId);
	}
	
};


var menu
//树右键生成菜单
function treeOnRegihtClick(id){
	//右键选中树
	tree.focusItem(id);
	tree.selectItem(id,false);
	var mj = tree.getUserData(id, "mj");
	var jbNum = tree.getUserData(id, "jbNum");
	
	menu = new dhtmlXMenuObject();
	menu.renderAsContextMenu();
	menu.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
//	if (jbNum!="-1") {
		if (mj=="0") {
			menu.addNewChild(menu.topId, 0, "new", "新增下级业务", false, "add.gif");
			menu.addNewSeparator("new");
		}
		menu.addNewChild(menu.topId, 2, "edit", "详情/修改", false, "modify.gif");
		menu.addNewSeparator("edit");
		menu.addNewChild(menu.topId, 3, "delete", "删除业务", false, "delete.gif");
//	}
	
	var X=document.getElementById('mouseXPosition').value;
	var Y=document.getElementById('mouseYPosition').value;
	menu.attachEvent("onClick", function(menuId, zoneId, casState) {
		funTreeRightOpt(id,menuId);
	});
	menu.showContextMenu(X,(Y-10));
	
}

//说明:获取鼠标位置
function mousePosition(ev){
	if(ev.pageX || ev.pageY){
		return {x:ev.pageX, y:ev.pageY};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y:ev.clientY + document.body.scrollTop  - document.body.clientTop
	};
}
document.onmousemove = mouseMove;
function mouseMove(ev){
    ev = ev || window.event;
    var mousePos = mousePosition(ev);
	document.getElementById('mouseXPosition').value = mousePos.x;
	document.getElementById('mouseYPosition').value = mousePos.y;
}



//模板树右键操作
function funTreeRightOpt(treeId,menuId){
	var title = tree.getItemText(treeId);
	var url = tree.getUserData(treeId, "url");
	var jb = tree.getUserData(treeId, "jb");
	var jbNum = tree.getUserData(treeId, "jbNum");
	var funcId = tree.getUserData(treeId, "funcId");
	//新增
	if(menuId == 'new'){
	    dhxLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/business/businessinfo/businessInfo_add.jsp?jbNum=" + jbNum);
	}
	
	//修改
	if(menuId == 'edit'){
	     dhxLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/business/businessinfo/businessInfo_modify.jsp?&jbNum=" + jbNum + "&funcId=" + funcId);
	}
	
	//删除
	if(menuId == 'delete'){
		if(jbNum){
			UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该业务吗？点击“否”则返回！", {
				"yes" : function()
				{
					$.singleReq({
						data : 
						{
							"reqUrl"     : "businessInfoHandler",
							"reqMethod"  : "deleteBusinessBaseInfo",
						    "jbNum"      : jbNum,
						    "funcId"     : funcId
						},
						success : function(ret)
						{
							if(ret){
								if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS){
									var resMsg = ret.resMsg;
									var retCode = ret.retCode;
									UOMPComp.showTipDialog("删除成功!", ""); 
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
	}
}

