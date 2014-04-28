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
				"reqUrl" : "funinfo",
				"reqMethod" : "queryFunInfoList"
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
	if(funId != null)
	{
	    dhxLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/fun_info_view.jsp?funId="+funId);
	}
	
};

//级别为1
var menu1

//级别为2
var menu2

//级别为3
var menu3
//树右键生成菜单
function treeOnRegihtClick(id){
	//右键选中树
	tree.focusItem(id);
	tree.selectItem(id,false);
	
	//若存在则清空已存在的树
	if(menu1){
		menu1.clearAll();
	}
	if(menu2){
		menu2.clearAll();
	}
	if(menu3){
		menu3.clearAll();
	}
	
	//根据树的级别来判断展现的右键菜单
	var jb = tree.getUserData(id, "jb");
	
	if(jb == '1'){
		menu1 = new dhtmlXMenuObject();
		menu1.renderAsContextMenu();
		menu1.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		menu1.addNewChild(menu1.topId, 0, "new", "新增模块", false, "add.gif");
		menu1.addNewSeparator("new");
		menu1.addNewChild(menu1.topId, 1, "edit", "修改本级", false, "modify.gif");
		
		var X=document.getElementById('mouseXPosition').value;
		var Y=document.getElementById('mouseYPosition').value;
		menu1.attachEvent("onClick", function(menuId, zoneId, casState) {
			funTreeRightOpt(id,menuId);
		});
		menu1.showContextMenu(X,Y);
	}else if(jb == '2'){
		menu2 = new dhtmlXMenuObject();
		menu2.renderAsContextMenu();
		menu2.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		
		menu2.addNewChild(menu2.topId, 0, "new", "新增功能", false, "add.gif");
		menu2.addNewSeparator("new");
		menu2.addNewChild(menu2.topId, 2, "edit", "修改本级", false, "modify.gif");
		menu2.addNewSeparator("edit");
		menu2.addNewChild(menu2.topId, 3, "delete", "删除本级", false, "delete.gif");
		
		var X=document.getElementById('mouseXPosition').value;
		var Y=document.getElementById('mouseYPosition').value;
		menu2.attachEvent("onClick", function(menuId, zoneId, casState) {
			funTreeRightOpt(id,menuId);
		});
		menu2.showContextMenu(X,Y);
	}
	else if(jb == '3'){
		menu3 = new dhtmlXMenuObject();
		menu3.renderAsContextMenu();
		menu3.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		
		menu3.addNewChild(menu3.topId, 0, "new", "新增功能", false, "add.gif");
		menu3.addNewSeparator("new");
		menu3.addNewChild(menu3.topId, 1, "delete", "删除本级", false, "delete.gif");
		menu3.addNewSeparator("delete");
		menu3.addNewChild(menu3.topId, 2, "edit", "修改本级", false, "modify.gif");
		
		var X=document.getElementById('mouseXPosition').value;
		var Y=document.getElementById('mouseYPosition').value;
		menu3.attachEvent("onClick", function(menuId, zoneId, casState) {
			funTreeRightOpt(id,menuId);
		});
		menu3.showContextMenu(X,Y);
	}
	else if(jb == '4'){
	    menu4 = new dhtmlXMenuObject();
		menu4.renderAsContextMenu();
		menu4.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		
		menu4.addNewChild(menu4.topId, 0, "new", "新增功能", false, "add.gif");
		menu4.addNewSeparator("new");
		menu4.addNewChild(menu4.topId, 1, "delete", "删除本级", false, "delete.gif");
		menu4.addNewSeparator("delete");
		menu4.addNewChild(menu4.topId, 2, "edit", "修改本级", false, "modify.gif");
		
		var X=document.getElementById('mouseXPosition').value;
		var Y=document.getElementById('mouseYPosition').value;
		menu4.attachEvent("onClick", function(menuId, zoneId, casState) {
			funTreeRightOpt(id,menuId);
		});
		menu4.showContextMenu(X,Y);
	}
	else if(jb == '5'){
	    menu5 = new dhtmlXMenuObject();
		menu5.renderAsContextMenu();
		menu5.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		
		menu5.addNewChild(menu5.topId, 1, "delete", "删除本级", false, "delete.gif");
		menu5.addNewSeparator("delete");
		menu5.addNewChild(menu5.topId, 2, "edit", "修改本级", false, "modify.gif");
		
		var X=document.getElementById('mouseXPosition').value;
		var Y=document.getElementById('mouseYPosition').value;
		menu5.attachEvent("onClick", function(menuId, zoneId, casState) {
			funTreeRightOpt(id,menuId);
		});
		menu5.showContextMenu(X,Y);
	}
	else if(jb == "" || jb == null){
	    menu5 = new dhtmlXMenuObject();
		menu5.renderAsContextMenu();
		menu5.setIconsPath(GLOBAL_INFO.CONTEXTPATH+"/resource/img/");
		
		menu5.addNewChild(menu5.topId, 0, "new", "新增功能", false, "add.gif");
		
		var X=document.getElementById('mouseXPosition').value;
		var Y=document.getElementById('mouseYPosition').value;
		menu5.attachEvent("onClick", function(menuId, zoneId, casState) {
			funTreeRightOpt(id,menuId);
		});
		menu5.showContextMenu(X,Y);
	}
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
	var subSysName = tree.getUserData(treeId, "subSysName");
	var jbNum = tree.getUserData(treeId, "jbNum");
	var funcId = tree.getUserData(treeId, "funcId");
	
	//新增
	if(menuId == 'new'){
	    dhxLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/fun_info_add.jsp?subSysName=" + subSysName + "&jbNum=" + jbNum);
	}
	
	//修改
	if(menuId == 'edit'){
	     dhxLayout.cells("b").attachURL(GLOBAL_INFO.CONTEXTPATH+"/page/admin/fun_info_mod.jsp?subSysName=" + subSysName + "&jbNum=" + jbNum + "&funcId=" + funcId);
	}
	
	//删除
	if(menuId == 'delete'){
		if(jbNum){
			UOMPComp.showConfirmDialog("【系统提示】\n\n您确定删除该级别吗？点击“取消”则返回！", {
				"yes" : function()
				{
					$.singleReq({
						data : 
						{
							"reqUrl"     : "funinfo",
							"reqMethod"  : "deleteFunInfo",
							"subSysName" : subSysName,
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
		}else{
			UOMPComp.showTipDialog("请选择行", ""); 
		}
	}
}

