$(document).ready(function(){
	frameComponent.init();
	var tree;
});

var frameComponent =
{
	frameDhxLayout : null,
	frameMenuTree  : null,
	frameTabbar    : null,
	dhxTabbar	   : null,
	dhxAccord      : null,
	userInfo 	   : null,
	loadFun		   : false,
	loadCustomFun  : false,
    
	//初始化
	init : function()
	{
		frameDhxLayout = new dhtmlXLayoutObject(document.body, "2U");
		frameDhxLayout.attachHeader("fra_Top");
		frameDhxLayout.cells("a").setText("系统导航栏");
		frameDhxLayout.cells("a").hideHeader();
		frameDhxLayout.cells("a").setWidth(230);
		frameDhxLayout.cells("a").attachObject("men_tabbar");
		frameDhxLayout.cells("b").setText("主显示区");
		frameDhxLayout.cells("b").hideHeader();
		frameDhxLayout.cells("b").attachObject("frm_main");
		//frameDhxLayout.setSkin('dhx_blue');
		frameDhxLayout.attachEvent("onPanelResizeFinish", this.doOnPanelResize);
	    this.initMenuTabbar();
		//加载树
		this.initWelcomePage();
	},

	initWelcomePage : function()
	{
		//初始化Tab栏
		frameTabbar = DhtmlxUtis.createTabbar("main_tabbar");
		//创建欢迎页
		frameTabbar.enableTabCloseButton(false);
		frameTabbar.addTab("welcome", "首页", "50px");
//		frameTabbar.setContentHref("welcome", GLOBAL_INFO.CONTEXTPATH + "/resource/img/welcome.gif");
		frameTabbar.setContentHref("welcome", GLOBAL_INFO.CONTEXTPATH + "/page/Console.jsp");
		frameTabbar.setTabActive("welcome");
		frameTabbar.enableTabCloseButton(true);
		frameTabbar.enableAutoReSize(true, true);
		frameDhxLayout.attachEvent("onResizeFinish", this.onPanelResize);
	},

	//刷新区域大小
	onPanelResize : function()
	{
		frameTabbar.setSize("","",true);
		
		var atab = dhxTabbar.getActiveTab();
		if(atab=="sysMenu"){
			dhxTabbar.setTabActive("cusMenu");
			dhxTabbar.setTabActive("sysMenu");
		}else{
			dhxTabbar.setTabActive("sysMenu");
			dhxTabbar.setTabActive("cusMenu");
		}
	},
	//刷新区域大小
	doOnPanelResize : function(ids){
		var atab = dhxTabbar.getActiveTab();
		if(atab=="sysMenu"){
			dhxTabbar.setTabActive("cusMenu");
			dhxTabbar.setTabActive("sysMenu");
		}else{
			dhxTabbar.setTabActive("sysMenu");
			dhxTabbar.setTabActive("cusMenu");
		}
	},
	//主菜单双击事件
	tonclick : function(id)
	{
		var title = frameMenuTree.getItemText(id);
		var url = frameMenuTree.getUserData(id, "url");
		if(!frameTabbar.cells(id)){
			frameTabbar.addTab(id, title, "100px");
			frameTabbar.setContentHref(id, url);
		}
		frameTabbar.setTabActive(id);
		//frameTabbar.forceLoad(id,url);
	},
	
	//初始化菜单TAB条
	initMenuTabbar : function(){
		dhxTabbar = new dhtmlXTabBar("men_tabbar", "top");
		dhxTabbar.setSkin('dhx_skyblue');
		dhxTabbar.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
		dhxTabbar.addTab("sysMenu", "系统菜单", "80px");
		//dhxTabbar.addTab("cusMenu", "快捷操作", "80px");
		dhxTabbar.setContent("sysMenu", "men_1");
		//dhxTabbar.setContent("cusMenu", "men_2");
		dhxTabbar.setCustomStyle("sysMenu", "#036", "#036", "font-family:'微软雅黑',Arial; font-size:13px;");
		//dhxTabbar.setCustomStyle("cusMenu", "#036", "#036", "font-family:'微软雅黑',Arial; font-size:13px;");
		dhxTabbar.enableAutoReSize(true);
		dhxTabbar.setTabActive("sysMenu");
		dhxTabbar.attachEvent("onSelect",this.changeMenu);
		this.initAccordBar();
	},
	
	//初始化Accord条
	initAccordBar : function(){
		$.singleReq({
			data : 
			{
				"reqUrl" : "menu",
				"reqMethod" : "getMenuInfo"
			},
			success : function(ret)
			{
				if("0" == ret.retCode){
					var menu_list = ret.retObj.list;
					userInfo = ret.retObj.user;
					if(userInfo){
						$('#userName').text(userInfo.userName);
					}
					if(menu_list && menu_list.length > 0){
						dhxAccord = dhxTabbar.cells("sysMenu").attachAccordion('dhx_blue');
						frameComponent.creatMenu(menu_list);
						frameComponent.loadFun = true;
					}
				}else{
					top.UOMPDialog.alert(ret.resMsg,0,"");
				}
			}
		});
	},
	
	//创建菜单
	creatMenu : function(msg){
		$.each(msg,function(i,item){
			dhxAccord.addItem(item.sysNum,item.sysTitle);
			dhxAccord.closeItem(item.sysNum);
			frameComponent.getMenuInfo(item.sysNum,item.treeNode);
		});
	},
	
	//加载菜单树
	getMenuInfo : function(sysNum,treeData){
	 	var tree = dhxAccord.cells(sysNum).attachTree();
	 	this.loadMenuTree(tree, treeData);
	},
	
	//加载主菜单
	loadMenuTree : function(treeObj,treeData)
	{
		frameMenuTree = DhtmlxUtis.createMenuTree(treeObj,frameTabbar,"0");
		frameMenuTree.loadJSONObject(treeData);
	},
	
	//退出登录
	logout : function(){
		$.singleReq({
			data : 
			{
				"reqUrl" : "login",
				"reqMethod" : "getLogout"
			},
			success : function(ret)
			{
				if("0" == ret.retCode){
					top.UOMPDialog.alert(ret.resMsg,0,"",{"yes" : function(){
							top.location.href = GLOBAL_INFO.CONTEXTPATH + "/index.jsp";
						}
					});
				}
			}
		});
	},
	
 	modifyPasswd : function(){
	
		//if(!frameTabbar.cells("modifyPasswd")){
		//	 frameTabbar.addTab("modifyPasswd", "修改密码", "100px");
		//     frameTabbar.setContentHref("modifyPasswd", GLOBAL_INFO.CONTEXTPATH + "/page/admin/user_password_modify.jsp");
		//}
	   
		//frameTabbar.setTabActive("modifyPasswd");
		
		top.UOMPDialog.showSubPage("修改密码",GLOBAL_INFO.CONTEXTPATH + "/page/admin/user_password_modify.jsp" ,function(){
				           
					});
	
	},
	
	initCustomFun : function(){
		$.singleReq({
			data : 
			{
				"reqUrl" : "menu",
				"reqMethod" : "getCustomFunInfo"
			},
			success : function(ret)
			{
				if("0" == ret.retCode){
					var menu_list = ret.retObj.list;
					userInfo = ret.retObj.user;
					if(userInfo){
						$('#userName').text(userInfo.userName);
					}
					if(menu_list && menu_list.length > 0){
						dhxAccord = dhxTabbar.cells("cusMenu").attachAccordion('dhx_blue');
						frameComponent.creatMenu(menu_list);
						frameComponent.loadCustomFun = true;
					}
				}else{
					top.UOMPDialog.alert(ret.resMsg,0,"");
				}
			}
		});
	},
	//获取自定义菜单选项
	initCustomFunByUse : function(){
		$.singleReq({
			data : 
			{
				"reqUrl" : "menu",
				"reqMethod" : "getCustomFunInfo"
			},
			success : function(ret)
			{
				if("0" == ret.retCode){
					var root = ret.retObj.root;
					userInfo = ret.retObj.user;
					if(userInfo){
						$('#userName').text(userInfo.userName);
					}
					if(root && root!=null){
						tree.loadJSONObject(root);
					    frameComponent.loadCustomFun = true;
					}
				}else{
					top.UOMPDialog.alert(ret.resMsg,0,"");
				}
			}
		});
	},
	//生成树对象
	loadTree : function(){
		tree = DhtmlxUtis.createTree(custem_tree_frame,this.tonclick_custom);
		frameComponent.initCustomFunByUse();
	},
	
	//树点击事件
	tonclick_custom : function(id) {
			var url = tree.getUserData(id, "url");
			var text = tree.getItemText(id)
			if(!frameTabbar.cells(id)){
				if(url !=null && url !="" ){
				    frameTabbar.addTab(id, text, "100px");
					frameTabbar.setContentHref(id, url);
					frameTabbar.setTabActive(id);
				}
			}
			frameTabbar.setTabActive(id);
	},
	changeMenu : function(id,last_id){
		if("sysMenu" ==id  && !frameComponent.loadFun){
			frameComponent.initAccordBar();
		}
		else if("cusMenu" == id && !frameComponent.loadCustomFun){
	        //调用加载树
			frameComponent.loadTree();
		}
		return true;
	},
	loadURL:function (id,title,url) {
		if(!frameTabbar.cells(id)){
			frameTabbar.addTab(id, title, "100px");
			frameTabbar.setContentHref(id, url);
		}
		frameTabbar.setTabActive(id);
        }
};