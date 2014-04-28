//弹出框组件
var UOMPDialog =
{
	dialogWinows    : null,
	subPageCallback : null,
	dialogCallback  : null,
	subPageWin		: null,
	
	initDialog : function()
	{
		if(this.dialogWinows == null)
		{
			this.dialogWinows = new dhtmlXWindows();
			this.dialogWinows.attachViewportTo("winVP");
	    	this.dialogWinows.vp.style.border = "#909090 1px solid";
	   	 	this.dialogWinows.setImagePath(GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
		}
	},

	/**
	 * 弹出子页面
	 */
	showSubPage: function(title, url, callback,size)
	{
		title=(title && title != "") ? title : "子页面";
		if(this.dialogWinows == null)
		{
			this.initDialog();
		}
		//var subPageWin = this.dialogWinows.window("subPageWin");
		var subPageWin = this.dialogWinows.window(title);
		if(subPageWin == null)
		{   
		    
			//subPageWin = this.dialogWinows.createWindow("subPageWin", 0, 0, 800, 500);
			subPageWin = this.dialogWinows.createWindow(title, 0, 0, 800, 500);
			
			subPageWin.setText(title);
	    	subPageWin.button("park").hide();
		    subPageWin.button("minmax1").hide();
		    subPageWin.button("minmax2").hide();
		    subPageWin.setModal(true);
		 
		    
		}
		subPageWin.attachURL(url);
		if(size)
		{
		  subPageWin.setDimension(size.width,size.hieght);
		}else
		{
		  subPageWin.setDimension(800,500);
		}
		   subPageWin.center();
		this.subPageCallback = function(obj)
		{
			callback && callback(obj);
			subPageWin.close();
		}
	},

	/**
	 * 弹出子页面
	 */
	showSubPage2 : function(title, url, callback,size)
	{
		if(this.dialogWinows == null)
		{
			this.initDialog();
		}
		this.subPageWin = this.dialogWinows.window("subPageWin");
		if(this.subPageWin == null)
		{   
		    
			this.subPageWin = this.dialogWinows.createWindow("subPageWin", 0, 0, 800, 500);
			
			this.subPageWin.setText((title && title != "") ? title : "子页面");
			this.subPageWin.button("park").hide();
			this.subPageWin.button("minmax1").hide();
			this.subPageWin.button("minmax2").hide();
			this.subPageWin.setModal(true);
		 
		    
		}
		this.subPageWin.attachURL(url);
		if(size)
		{
			this.subPageWin.setDimension(size.width,size.hieght);
		}else
		{
			this.subPageWin.setDimension(800,500);
		}
		this.subPageWin.center();
		this.subPageCallback = function(obj)
		{
			callback && callback(obj);
			this.subPageWin.close();
		}
	},
	
	
	nextSubPage:function(title, url,size)
	{
		this.subPageWin.attachURL(url);
		if(size)
		{
			this.subPageWin.setDimension(size.width,size.hieght);
		}else
		{
			this.subPageWin.setDimension(800,500);
		}
		this.subPageWin.center();
	},
	
	/**
	 * 关闭子页面
	 */
	hideSubPage : function()
	{
		var subPageWin = this.dialogWinows.window("subPageWin");
		if(subPageWin != null)
		{
			subPageWin.close();
		}
	},

	/**
	 * 关闭子页面
	 */
	hideAlert : function()
	{
		var alertWin = this.dialogWinows.window("alertWin");
		if(alertWin != null)
		{
			alertWin.close();
		}
	},

	/**
	 * 确定框回调
	 */
	dialog_Callback : function(type)
	{
		if(type == "yes")
		{
			this.dialogCallback && this.dialogCallback.yes && this.dialogCallback.yes();
		}
		else
		{
			this.dialogCallback && this.dialogCallback.no && this.dialogCallback.no();
		}
		var alertWin = this.dialogWinows.window("alertWin");
		if(alertWin != null)
		{
			alertWin.close();
		}
	},

	/**
	 * 信息弹出框
	 * msg 提示信息
	 * type 0：提示 1：成功 2：失败
	 */
	alert : function(msg, type, title, callback)
	{
		if(this.dialogWinows == null)
		{
			this.initDialog();
		}
		var alertWin = this.dialogWinows.window("alertWin");
		if(alertWin == null)
		{
			this.dialogCallback = callback;
			alertWin = this.dialogWinows.createWindow("alertWin", 0, 0, 300, 200);
			alertWin.setText((title && title != "") ? title : "提示框");
	    	alertWin.button("park").hide();
		    alertWin.button("minmax1").hide();
		    alertWin.button("minmax2").hide();
		    alertWin.setModal(true);
		    alertWin.center();
		    var popImg = "popbox-faceIco_alert.png";
		    if(type == "1")
		    {
		    	popImg = "popbox-faceIco_success.png";
		    }
		    else if(type == "2")
		    {
		    	popImg = "popbox-faceIco_error.png";
		    }
		    var alertHtml = "<div class='popboxInner'>";
    		alertHtml += "<div class='popboxInner-faceIco'><img src='" + GLOBAL_INFO.CONTEXTPATH + "/resource/img/" + popImg + "'></div>";
    		alertHtml += "<div class='popboxInner-text'>" + msg + "</div>";
    		alertHtml += "<div class='popboxInner-btnArea'><a href=\"javascript:top.UOMPDialog.dialog_Callback('yes');\" class='btn-style1'>确认</a></div>";
  			alertHtml += "</div>";
		    alertWin.attachHTMLString(alertHtml);
		}
	},

	/**
	* 二次确定框
	*/
	confirm : function(msg, callback,yes,no)
	{
		if(this.dialogWinows == null)
		{
			this.initDialog();
		}
		if(yes==null||yes==undefined){
			yes='确认'
		}
		if(no==null||no==undefined){
			no='取消'
		}
		var alertWin = this.dialogWinows.window("alertWin");
		if(alertWin == null)
		{
			this.dialogCallback = callback;
			alertWin = this.dialogWinows.createWindow("alertWin", 0, 0, 300, 200);
			alertWin.setText("确定操作");
	    	alertWin.button("park").hide();
		    alertWin.button("minmax1").hide();
		    alertWin.button("minmax2").hide();
		    alertWin.setModal(true);
		    alertWin.center();
		    var confirmHtml = "<div class='popboxInner'>";
    		confirmHtml += "<div class='popboxInner-faceIco'><img src='" + GLOBAL_INFO.CONTEXTPATH + "/resource/img/popbox-faceIco_message.png'></div>";
    		confirmHtml += "<div class='popboxInner-text'>" + msg + "</div>";
    		confirmHtml += "<div class='popboxInner-btnArea'><a href=\"javascript:top.UOMPDialog.dialog_Callback('yes');\" class='btn-style1'>"+yes+"</a><a href=\"javascript:top.UOMPDialog.dialog_Callback('no');\" class='btn-style2'>"+no+"</a></div>";
  			confirmHtml += "</div>";
		    alertWin.attachHTMLString(confirmHtml);
		}
	}
};