$(document).ready(function(){
    custemMenuComponent.iniyLayout();
   // dhxLayout.cells("a").progressOn();
    custemMenuComponent.iniyGrid();
	//初始化树
	custemMenuComponent.loadTree();
    //custemMenuComponent.getTreeData();
	//加载树  调用后台方法，获取数据
	
	doReset();
});

var treeData;
//树对象
var tree;
var dhxLayout;
var mygrid;
var hasSelect = [];
var custemMenuComponent = {
        iniyLayout : function(){  //初始化布局
		    dhxLayout = new dhtmlXLayoutObject(document.body, "3W");
		    dhxLayout.cells("a").attachObject("privilegeIdObj"); 
		    dhxLayout.cells("b").attachObject("czqyObj"); 
		    dhxLayout.cells("c").attachObject("sjlbObj");
		    dhxLayout.cells("a").setWidth('35%');
		    dhxLayout.cells("b").setWidth(85);
		    dhxLayout.cells("c").setWidth("*");
		    dhxLayout.cells("c").fixSize(true, true);
		    dhxLayout.cells("a").hideHeader();
		    dhxLayout.cells("b").hideHeader();
		    dhxLayout.cells("c").hideHeader();
        },
        iniyGrid : function(){  //初始化布局
			var allCheck = "<input type='checkbox' style='width: 15px; height: 15px;' id='allCheck'  onClick='custemMenuComponent.allCheck(this);'></>";
		    mygrid = DhtmlxUtis.createGrid('gridbox', {
				"header"     : allCheck +",,,子系统名称,功能名称",
				"initWidth"  : "50,0,0,100,*",
				"colAlign"   : "left,left,left,left",
				"colTypes"   : "ch,ro,ro,ro,ro",
				"colSorting" : "txt,str,str,str,str"
			});
			 mygrid.attachEvent("onCheckbox",custemMenuComponent.doOnCheck);
        },
        
        doOnCheck : function(rowId, cellInd, state) {
		    if(!state)
		     {   
				 $("#allCheck").attr("checked", false); 
				 return true;  
			 }
			return true;
		},
			// 全选、取消全选
		allCheck : function(obj)
		{
			if(obj.checked)
			{
		        	mygrid.checkAll(true);
			}
			else
			{
				mygrid.checkAll(false);
			}
		},
       
		//生成树对象
		loadTree : function(){
			tree = DhtmlxUtis.createTree(privilege_tree);
			this.getTreeData();	
			tree.enableCheckBoxes(1);
			tree.attachEvent("onCheck", onCheckMethod);	
			var parents =tree.getAllChecked();
			if(parents!=null && parents !=""){
			    parents = parents.split(',');
				for(var i = 0; i < parents.length; i++)
				{
					var itemId = parents[i];
				    tree.setCheck(itemId,0);
				}
			}
		},
		//加载树  调用后台方法，获取数据
		getTreeData : function()
		{
			$.singleReq({
				data :
				{
					"reqUrl" : "customMenu",
					"reqMethod" : "queryCustomMenuForUser"
				},
				success : function(ret)
				{
					if(ret)
					{   
						if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS)
						{   	
							treeData = ret.retObj;
							tree.loadJSONObject(ret.retObj);
						}
						else
						{
							UOMPComp.showFailedDialog(ret.resMsg, "");
						}
					}
				}
			});
		}
}

//展开所有子节点
function openAllTree()
{
	var parents = tree.getAllItemsWithKids();
	parents = parents.split(',');
	for(var i = 0; i < parents.length; i++)
	{
		var itemId = parents[i];
		tree.openAllItems(itemId);
	}
}

//收缩所有子节点
function closeAllTree()
{
	var parents = tree.getAllItemsWithKids();
	parents = parents.split(',');
	for(var i = 0; i < parents.length; i++)
	{
		var itemId = parents[i];
		tree.closeAllItems(itemId);
	}
}

//点击则触发选中事项
function onCheckMethod(id,state)
{
	if(tree.hasChildren(id) > 0)
	{
	   // alert(id);
		tree.setSubChecked(id,state);
	}
}

//将选中的数据列表移到右边去
function getFunIdToRight()
{
	//var statDate = new Date().toString();
	//alert(statDate.toString());
	var datas = [];
	var parents = tree.getAllChecked();
	//alert(parents);
	//dhxLayout.progressOn();
	parents = parents.split(',');
	if(parents != null && parents !=""){   
	    var jsonArr = [];
	    var json = {}; 
	   	for(var i = 0; i < parents.length; i++)
		{   
		    var id = parents[i];
			var funName = tree.getItemText(parents[i]);
			var funcId = tree.getUserData(parents[i],"funcId");
			var subSysNum = tree.getUserData(parents[i],"subSysNum");
			var subSysName = tree.getUserData(parents[i],"subSysName");
			var funcType = tree.getUserData(parents[i],"funcType");
			//alert("funcType="+funcType );
			if (subSysNum != "" && funName != "" && funcType!="" &&funcType !=null){ 
			    var flag = true;
			     if(funcType==1){
				    var sf = subSysNum + '--' + funcId;
			        for(var j = 0; j <= hasSelect.length; j++){
				        if(sf == hasSelect[j]){
				             flag = false;
				             break;
				        }
					}
					if(flag){
						hasSelect.push(subSysNum + '--' + funcId);
						var data = [0, subSysNum, funcId, subSysName, funName];
						datas.push(data);
					} 
				}	
			 }
		}
		if(datas.length > 0)
		{
			mygrid.parse(datas, "jsarray");
		}
		for(var m = 0; m < parents.length; m++){
		     var id=parents[m];
		     tree.setSubChecked(id,0); //取消选择状态
		}
	}
}

//清除被选中的数据
function removeFunIdFromRight()
{
	var ids=mygrid.getCheckedRows(0).split(',');
	for(var m = 0; m < ids.length; m++){
	   	var sf =mygrid.cells(ids[m], 1).getValue()+"--"+mygrid.cells(ids[m], 2).getValue();
        for(var i = 0; i < hasSelect.length; i++){
            if(sf == hasSelect[i]){
                 hasSelect.splice(i,1);
            }
        }
	    mygrid.deleteRow(ids[m]);
	}
}

//点击全选按钮
function doAllCheck()
{
	var parents = tree.getAllItemsWithKids();
	parents = parents.split(',');
	for(var i = 0; i < parents.length; i++)
	{
		var itemId = parents[i];
		tree.setSubChecked(itemId,1);
	}
}

//点击清空重填按钮
function doReset()
{
	//同时得删除侧面的列表信息
	var parents = tree.getAllChecked();
	if(parents!=null && parents !=""){
	    parents = parents.split(',');
		for(var i = 0; i < parents.length; i++)
		{
			var itemId = parents[i];
			tree.setSubChecked(itemId,0);
		}
	}
	mygrid.clearAll();
	hasSelect=[];
}
//获取被选中的数据将其保存到里面去 
function getGridCheckedData(id,state)
{
	var checked = mygrid.getCheckedRows(0);	//获取所有被选中的信息			
	var jsonArr = [];
	if (checked)
	{  
		checked = checked.split(','); 
		for (var i = 0; i < checked.length; i++)
		{   
			var subSysNum = mygrid.cells(checked[i],1).getValue();//子系统编码
			var funcId = mygrid.cells(checked[i],2).getValue();//功能id
			var funName = mygrid.cells(checked[i],4).getValue();//商品编码
		 	var json = {}; 
			json.subSysNum =subSysNum;
			json.funcId = funcId;
			json.funName = funName;
		    jsonArr.push(json);							
		}							
		return jsonArr;
	} else {
		return "error";
	}
}
//点击提交按钮
function doSubmit()
{

        var  jsonArr =this.getGridCheckedData();
		if (jsonArr=="error"){
				UOMPComp.showFailedDialog("请选择具体菜单","");	
				return ;
		 }else{
		   var jbNum = $("#jbNum").val();
	       $.singleReq({
				data :
				{
					"reqUrl" 		: "customMenu",
					"reqMethod" 	: "addCustomMenu",
				    "jbNum" : jbNum,//父类级别编码
				    "type" : 1,//新增类型
					"jsonForm"		: JSON.encode(jsonArr)
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
						    window.parent.window.location.reload();
						}
					  }else{
						   UOMPComp.showFailedDialog(ret.resMsg,"");
					  }
				  }
				}
			});
		} 
}