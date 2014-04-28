$(document).ready(function(){
	//初始化树
	privilegeInfoEComponent.loadTree();

	//加载树  调用后台方法，获取数据
	privilegeInfoEComponent.getTreeData();
});

var treeData;
//树对象
var tree;
var privilegeInfoEComponent = {

		//生成树对象
		loadTree : function(){
			tree = DhtmlxUtis.createTree(privilege_tree);
			tree.enableCheckBoxes(1);
			tree.attachEvent("onCheck", onCheckMethod);
		},

		//加载树  调用后台方法，获取数据
		getTreeData : function()
		{
			var userId = $("#userId").val();
			var userType = $("#userType").val();
			$.singleReq({
				data :
				{
					"reqUrl" : "privilegeinfo",
					"reqMethod" : "viewPrivilegeInfo",
					"userId" : userId,
					"userType" : userType
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

function onCheckMethod(id,state)
{
	if(tree.hasChildren(id) > 0)
	{
		tree.setSubChecked(id,state);
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
	$("#privilege_tree").html("");
	//初始化树
	privilegeInfoEComponent.loadTree();
	//加载树
	tree.loadJSONObject(treeData);
}
//点击提交按钮
function doSubmit()
{
	var busiTreeSelect = tree.getAllChecked();
	busiTreeSelect = busiTreeSelect.split(',');
	var busiTreeSelectNumList = new Array();
	var subSysNumList = new Array();
	if(busiTreeSelect != null && busiTreeSelect.length > 0){
		for(var i = 0; i < busiTreeSelect.length; i++){
			subSysNumList.push(tree.getUserData(busiTreeSelect[i], "subSysNum"));
			busiTreeSelectNumList.push(tree.getUserData(busiTreeSelect[i], "funcId"));
		}
	}
	subSysNumList = subSysNumList.toString();
	busiTreeSelectNumList = busiTreeSelectNumList.toString();
	var userId = $("#userId").val();
	var userType = $("#userType").val();
	$.singleReq({
		data :
		{
			"reqUrl" : "privilegeinfo",
			"reqMethod" : "modifyPrivilegeInfo",
			"userId" : userId,
			"userType" : userType,
			"selectedSubSysNum" : subSysNumList,
			"selectedPrivilege" : busiTreeSelectNumList
		},
		success : function(ret)
		{
			if(ret)
			{
				if(ret.retCode == GLOBAL_INFO.SYS_SUCCESS)
				{
					UOMPComp.showSuccessDialog(ret.resMsg, "");
				}
				else
				{
					UOMPComp.showFailedDialog(ret.resMsg, "");
				}
			}
		}
	});
}