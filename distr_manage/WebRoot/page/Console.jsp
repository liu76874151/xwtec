<%@page import="com.xwtech.uomp.base.pojo.sso.LoginRequestBean"%>
<%@page import="com.xwtech.uomp.base.util.SSOUtil"%>
<%@page import="com.xwtech.uomp.base.pojo.admin.UserInfoBean"%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@include file="/page/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.net.URLDecoder"%>
<%@include file="taglibs.jsp"%>
<html>
<head>

<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/console/style.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>

<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>

<script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
<script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
<script src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>

<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>

<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>

<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_terrace.css"></link>

<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxChart/codebase/dhtmlxchart.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">

<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>

<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxChart/codebase/dhtmlxchart_debug.js"></script>



<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>

<!-- highcharts -->
<script type="text/javascript"  src="${contextPath}/resource/scripts/highCharts/highcharts.js"></script>
<script type="text/javascript"  src="${contextPath}/resource/scripts/highCharts/json2.js"></script>
<%
	LoginRequestBean loginReqBean = (LoginRequestBean) request
			.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean,
			request).getUserInfoBean();
	String city = userInfoBean.getUserAreaCode();
%>
<script>
var dhxLayout;
var barChart1;
window.onload = function() {
	
	//var toolbar = new dhtmlXToolbarObject("toolbarObj");
	//toolbar.loadXML("${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxChart/codebase/toolbar.xml?etc=" + new Date().getTime(), updateList);
    //第一个chart
    loadQueryCountByMonth("","全省");
    //第二个chart
    queryCountByCity("");
    //第三个chart
    loadQueryMoneyByMonth("","全省");
    //第四个chart
    querySumMoneyByCity("");
    
    component.initCompGrid();
	component.queryAdv();
	
	component.initFirstMarketGrid();
	component.queryFirstMarket();
	
	component.initSecondMarketGrid();
	component.querySecondMarket();
	
	component.initBusiGrid();
	component.queryBusi();
	
	component.initReservationFirstGrid();
	component.queryReservationFirst();
	
	component.initReservationSecondGrid();
	component.queryReservationSecond();
	
	component.queryCustomMenu();
}
	
	/**
	*初始化面板
	**/
	function initPanel() {
	    dhxLayout = new dhtmlXLayoutObject("parentId", "4I");
	    dhxLayout.cells("a").setText("Main Page");
	    dhxLayout.cells("b").setText("Site Navigation");
	    dhxLayout.cells("c").setText("Support & Feedback");
	    dhxLayout.cells("d").setText("Comments");
	}
	/**
	*营销案订单月报表
	**/
	function loadQueryCountByMonth(city,cityName) {
		$.singleReq({
		         data: {
		             "reqUrl": "console",
		             "reqMethod": "queryOrderCountByMonth",
		             "city":city
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj; 
		                 drawColumnCount(cityName,result);
		             }
		             else {
		             }
		         }
		});
	}
	
	function drawColumnCount(cityName,result){
		var data = [];
		for(var i = 0; i < result.length; i++){
			data.push(result[i].countNum);
		}
		$('#chart1').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '营销案订单月报表'
            },
            subtitle: {
                text: '各地市按月订单数'
            },
            xAxis: {
                categories: [
                    '01',
                    '02',
                    '03',
                    '04',
                    '05',
                    '06',
                    '07',
                    '08',
                    '09',
                    '10',
                    '11',
                    '12'
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '订单数'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}月</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: cityName,
                data: data
    
            }],
            credits: {
             	text: '',
             	href: ''
            }
        });
	}
	
	var pieChart;
	function queryCountByCity(monthStr) {
		$.singleReq({
		         data: {
		             "reqUrl": "console",
		             "reqMethod": "queryOrderCountByCity",
		             "monthStr":monthStr
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		             	 var result = ret.retObj; 
		                 drawPieChart(result,monthStr);
		             }
		             else {
		             }
		         }
		});
	}
	
	function drawPieChart(result,monthStr){
		var xLableArray = new Array();
		var yValueArray = new Array();
		var data = [];
		if(monthStr == ""){
			monthStr = "全年";
		}else{
			monthStr += "月";
		}
		for(var i = 0; i < result.length; i++ ){
			data.push({
                "name": result[i].areaName,
             	"y": result[i].countNum
             });
		}
		//alert(JSON.stringify(drilldownSeries));
			$('#chart2').highcharts({                   //图表展示容器，与div的id保持一致
	        chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '营销案订单月报表'
            },
            subtitle:{
            	text:monthStr+'订单份额'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    
                    dataLabels: {
                        enabled: false,
                        color: '#000000',
                        //connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ Math.floor((parseFloat(this.percentage)) * 100) / 100 +' %';
                        }
                    },
                    
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '订单份额',
                data: data
            }],
             credits: {
             	text: '',
             	href: ''
            }
	    });
	}
	
	/**
	*订单销售额月报表
	**/
	var moneyBarChart
	function loadQueryMoneyByMonth(city,cityName) {
		$.singleReq({
		         data: {
		             "reqUrl": "console",
		             "reqMethod": "querySumMoneyByMonth",
		             "city":city
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj; 
		                 drawColumnMoney(cityName,result);
		             }
		             else {
		             }
		         }
		});
	}
	
	function drawColumnMoney(cityName,result){
		var data = [];
		for(var i = 0; i < result.length; i++){
			data.push(parseInt(result[i].sumMoney));
		}
		//alert(JSON.stringify(data));
		$('#moneyBarChart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '营销案订单销售额月报表'
            },
            subtitle: {
                text: '各地市按月订单额'
            },
            xAxis: {
                categories: [
                    '01',
                    '02',
                    '03',
                    '04',
                    '05',
                    '06',
                    '07',
                    '08',
                    '09',
                    '10',
                    '11',
                    '12'
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '订单额,元'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}月</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: cityName,
                data: data
            }],
            credits: {
             	text: '',
             	href: ''
            }
        });
	}
	
	function querySumMoneyByCity(monthStr) {
		$.singleReq({
		         data: {
		             "reqUrl": "console",
		             "reqMethod": "querySumMoneyByCity",
		             "monthStr":monthStr
		         },
		         success: function (ret) {
		             if (ret.retCode == 0) {
		                 var result = ret.retObj; 
		                 drawPieChartMoney(result,monthStr);
		             }
		             else {
		             }
		         }
		});
	}
	
	function drawPieChartMoney(result,monthStr){
		var xLableArray = new Array();
		var yValueArray = new Array();
		var data = [];
		if(monthStr == ""){
			monthStr = "全年";
		}else{
			monthStr += "月";
		}
		for(var i = 0; i < result.length; i++ ){
			data.push({
                "name": result[i].areaName,
             	"y": parseInt(result[i].sumMoney)
             });
		}
		//alert(JSON.stringify(data));
			$('#moneyChartByMonth').highcharts({                   //图表展示容器，与div的id保持一致
	         chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '营销案订单销售额月报表'
            },
            subtitle:{
            	text:monthStr + '订单额'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    
                    dataLabels: {
                        enabled: false,
                        color: '#000000',
                        //connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ Math.floor((parseFloat(this.percentage)) * 100) / 100 +' %';
                        }
                    },
                    
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '订单份额',
                data: data
            }],
             credits: {
             	text: '',
             	href: ''
            }
	    });
	}
	
	function updateList() {
	/**
		sel.options.length = 0;
		toolbar.forEachItem(function(itemId) {
			if (toolbar.getType(itemId) == "text") {
				sel.options
						.add(new Option(toolbar.getItemText(itemId), itemId));
			}
		});
		**/
	}
	var component = {
		frameTabbar:null,
		 initCompGrid: function () {
	        datagrid_comp = DhtmlxUtis.createGrid('gridbox_comp', {
	            "header": "advNum,advAreaNum,广告名称,审核状态",
	            "initWidth": "0,0,120,75",
	            "colAlign": "left,left,left,left",
	            "colTypes": "ro,ro,ro,ro",
	            "colSorting": "str,str,str,str",
	            "colKeys": ["advNum","advAreaNum",
	            {
	            	"key":"advName",
	            	"formatter":function(v){
	            		return v;
	            	}
	            },
	            "auditStateLink"]
	        });
	    },
	    queryAdv:function(){
	    	var data_comp =
	        {
	            "reqUrl": "console",
	            "reqMethod": "queryAdvInfoList",
	            "auditState" : '0',
	            "start" : 0,
							"end" : 5
	        };
	        datagrid_comp.setColumnHidden(0,true);
	        datagrid_comp.setColumnHidden(1,true);
	    	DhtmlxUtis.loadGirdData(datagrid_comp, data_comp, false);
	    },
		initFirstMarketGrid: function () {
	        datagrid_first = DhtmlxUtis.createGrid('gridbox_first', {
	            "header": "marketFirstPkid,一级营销案名称,审核状态",
	            "initWidth": "0,120,75",
	            "colAlign": "left,left,center",
	            "colTypes": "ro,ro,ro",
	            "colSorting": "str,str,str",
	            "colKeys": ["marketFirstPkid",
	            {
	            	"key":"viewName",
	            	"formatter":function(v){
	            			return v;
	            	}
	            },
	            <%if ("0".equals(city)) {// 0 为江苏省%>
		            "proOper"
	            <%} else {%>
	            		"localOper"
	            	<%}%>
	            ]
	        });
	    },
	    /**一级营销案审核列表**/
	    queryFirstMarket:function(){
	    	var data_market_first = 
	    	{
	    		"reqUrl": "console",
	            "reqMethod": "queryMarketFirstAuditList",
	            "state":"1",
	            "verifyState":"0",
	            "start" : 0,
				"end" : 5
	    	};
	    	datagrid_first.setColumnHidden(0,true);
	    	DhtmlxUtis.loadGirdData(datagrid_first, data_market_first, false);
	    },
	    /**二级营销案列表**/
	    initSecondMarketGrid: function () {
	        datagrid_second = DhtmlxUtis.createGrid('gridbox_second', {
	            "header": "marketSecondPkid,二级营销案名称,审核状态",
	            "initWidth": "0,120,75",
	            "colAlign": "left,left,center",
	            "colTypes": "ro,ro,ro",
	            "colSorting": "str,str,str",
	            "colKeys": ["marketSecondPkid",
	            "preview",
	            <%if ("0".equals(city)) {// 0 为江苏省%>
		            "proOper"
	            <%} else {%>
	            		"localOper"
	            	<%}%>
	            ]
	        });
	    },
	    /**二级营销案审核列表**/
	    querySecondMarket:function(){
	    	var data_market_second = 
	    	{
	    		"reqUrl": "console",
	            "reqMethod": "queryMarketSecondForVerify",
	            "state":"1",
	            "verifyState":"0",
	            "start" : 0,
				"end" : 5
	    	};
	    	datagrid_second.setColumnHidden(0,true);
	    	DhtmlxUtis.loadGirdData(datagrid_second, data_market_second, false);
	    },
	    /**业务审核列表**/
	    initBusiGrid: function () {
	        datagrid_busi = DhtmlxUtis.createGrid('gridbox_busi', {
	            "header": "业务名称,审核状态",
	            "initWidth": "120,75",
	            "colAlign": "left,center",
	            "colTypes": "ro,ro",
	            "colSorting": "str,str",
	            "colKeys": ["busiName",
	            <%if ("0".equals(city)) {// 0 为江苏省%>
		            "proVerifyState"
	            <%} else {%>
	            		"localVerifyState"
	            	<%}%>
	            ]
	        });
	    },
	    /**业务审核列表**/
	    queryBusi:function(){
	    	var data_busi = 
	    	{
	    		"reqUrl": "console",
	            "reqMethod": "selectBusiInfoListForVerify",
	            "state":"1",
	            "verifyState":"0",
	            "start" : 0,
				"end" : 5
	    	};
	    	DhtmlxUtis.loadGirdData(datagrid_busi, data_busi, false);
	    },
	    /**
	 * 一级省级审核
	 * @param pkid
	 * @param flag
	 */
	verifyMarketFirstPro : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("pro");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
                            "proVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 一级市级审核
	 * @param pkid
	 * @param flag
	 */
	verifyMarketFirstCity : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("local");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
                            "localVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 一级审核框
	 */
	openAuditPanel:function(level){
		var selectRowId = datagrid_first.getSelectedRowId();
        var pkid = datagrid_first.cells(selectRowId, 0).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("一级营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/verify/auditContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
	},
	/**
	 * 二级省级审核
	 */
	verifyMarketSecondPro : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanelSecond("pro");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketSecond",
                            "reqMethod": "updateMarketSecondForVerify",
                            "marketSecondPkid": pkid,
                            "proVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**二级地市审核**/
	verifyMarketSecondCity : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanelSecond("local");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "marketSecond",
                            "reqMethod": "updateMarketSecondForVerify",
                            "marketSecondPkid": pkid,
                            "localVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 二级审核框
	 */
	openAuditPanelSecond:function(level){
		var selectRowId = datagrid_second.getSelectedRowId();
        var pkid = datagrid_second.cells(selectRowId, 0).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("二级营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/market/marketSecond/verify/auditContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
	},
	/**广告审核**/
	updateAuditState:function(flag,advNum){
	var selectRowId = datagrid_comp.getSelectedRowId();
	var advNum = datagrid_comp.cells(selectRowId, 0).getValue();
    var advAreaNums = datagrid_comp.cells(selectRowId, 1).getValue();
    var text;
	if(flag==1){
		text="【系统提示】\n\n您确定审核通过该广告吗？点击“否”则返回！"
	}else{
		text="【系统提示】\n\n您确定审核不通过该广告吗？点击“否”则返回！"
	}
    UOMPComp.showConfirmDialog(text, {
        "yes": function () {
        $.singleReq({
            data: {
                "reqUrl": "advInfo",
                "reqMethod": "verifyAdvInfo",
                "advNum": advNum,
                "areaNums": advAreaNums,
                "auditState":flag
            },
            success: function (ret) {
                if (ret) {
                    if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                        var resMsg = ret.resMsg;
                        var retCode = ret.retCode;
                        UOMPComp.showSuccessDialog(resMsg, "");
                        if (GLOBAL_INFO.SYS_FAILED == retCode) {

                        } else if (GLOBAL_INFO.SYS_SUCCESS) {
                            window.location.reload();
                        }
                    } else {
                        if (ret.resMsg) {
                            UOMPComp.showFailedDialog("不是本地区广告或者"+ret.resMsg, "");
                        } else {
                            UOMPComp.showFailedDialog("系统异常", "");
                        }
                    }
                }
            }
        });
        },
        "no": function () {
        }
    });

},
/**业务审核**/
proVerifyBusiInfo: function(busiNum,flag,channelNum){
    	var verify='3';
		if(flag){
			verify='4';
		}
		if (busiNum) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                	if (!flag) {
						var size={"height":"300","width":"1000"};
						var url=GLOBAL_INFO.CONTEXTPATH + 
						"/page/business/businessVerify/businessVerify_description.jsp?verify="+verify+"&busiNum="+busiNum+"&flag="+flag+"&channelNum="+channelNum;
						top.UOMPDialog.showSubPage("业务审核理由",url,function(){
							window.location.reload();
						},size);
						return;
                	}
                	
                    $.singleReq({
                        data: {
                            "reqUrl": "businessInfoHandler",
                            "reqMethod": "verifyBusinessInfo",
                            "busiNum": busiNum,
                            "channelNum" : channelNum,
                            "state":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog("操作完成!", "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
    },
    /**一级预约营销案列表**/
     initReservationFirstGrid: function () {
	        datagrid_ReservationFirst = DhtmlxUtis.createGrid('gridbox_ReservationFirst', {
	            "header": "marketFirstPkid,一级预约营销案,审核状态",
	            "initWidth": "0,95,75",
	            "colAlign": "left,left,center",
	            "colTypes": "ro,ro,ro",
	            "colSorting": "str,str,str",
	            "colKeys": ["marketFirstPkid",
	            {
	            	"key":"viewName",
	            	"formatter":function(v){
	            			return v;
	            		}
	            },
	            <%if ("0".equals(city)) {// 0 为江苏省%>
		            "proOper"
	            <%} else {%>
	            		"localOper"
	            	<%}%>
	            ]
	        });
	    },
	    /**一级预约营销案审核列表**/
	    queryReservationFirst:function(){
	    	var data_market_ReservationFirst = 
	    	{
	    		"reqUrl": "console",
	            "reqMethod": "queryMarketFirstAuditListOnConsole",
	            "state":"1",
	            "verifyState":"0",
	            "start" : 0,
				"end" : 5
	    	};
	    	datagrid_ReservationFirst.setColumnHidden(0,true);
	    	DhtmlxUtis.loadGirdData(datagrid_ReservationFirst, data_market_ReservationFirst, false);
	    },
	    /**
	 * 一级预约营销案省级审核
	 * @param pkid
	 * @param flag
	 */
	verifyReservationMarketFirstPro : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openAuditPanel("pro");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
                            "proVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 一级预约营销案市级审核
	 * @param pkid
	 * @param flag
	 */
	verifyReservationMarketFirstCity : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openReservationAuditPanel("local");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationFirst",
                            "reqMethod": "updateAuditState",
                            "marketFirstPkid": pkid,
                            "localVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 一级预约审核框
	 */
	openReservationAuditPanel:function(level){
		var selectRowId = datagrid_ReservationFirst.getSelectedRowId();
        var pkid = datagrid_ReservationFirst.cells(selectRowId, 0).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("一级预约营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_first/verify/auditContent.jsp?level="+level+"&marketPkId="+pkid,null,size);
	},
	/**二级预约营销案列表**/
	initReservationSecondGrid: function () {
	        datagrid_ReservationSecond = DhtmlxUtis.createGrid('gridbox_ReservationSecond', {
	            "header": "marketSecondPkId,二级预约营销案,审核状态",
	            "initWidth": "0,95,75",
	            "colAlign": "left,left,center",
	            "colTypes": "ro,ro,ro",
	            "colSorting": "str,str,str",
	            "colKeys": ["marketSecondPkId",
	            {
	            	"key":"viewName",
	            	"formatter":function(v){
	            			return v;
	            	}
	            },
	            <%if ("0".equals(city)) {// 0 为江苏省%>
		            "proOper"
	            <%} else {%>
	            		"localOper"
	            	<%}%>
	            ]
	        });
	    },
	    /**二级预约营销案审核列表**/
	    queryReservationSecond:function(){
	    	var data_market_ReservationSecond = 
	    	{
	    		"reqUrl": "console",
	            "reqMethod": "queryMarketSecondForVerifyOnConsole",
	            "state":"1",
	            "verifyState":"0",
	            "start" : 0,
				"end" : 5
	    	};
	    	datagrid_ReservationSecond.setColumnHidden(0,true);
	    	DhtmlxUtis.loadGirdData(datagrid_ReservationSecond,  data_market_ReservationSecond, false);
	    },
	
	/**二级预约营销案地市审核**/
	verifyReservationMarketSecondCity : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openReservationSecondAuditPanel("local");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationMarketSecond",
                            "reqMethod": "updateMarketSecondForVerify",
                            "marketSecondPkId": pkid,
                            "localVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 省级审核
	 */
	verifyReservationMarketSecondPro : function(pkid,flag){
		var verify='2';
		if(flag){
			verify='1';
		}
		
		if(verify == '2')
		{
			component.openReservationSecondAuditPanel("pro");
			return;
		}
		if (pkid) {
            UOMPComp.showConfirmDialog("【系统提示】\n\n您确定进行该操作么吗？点击“否”则返回！", {
                "yes": function () {
                    $.singleReq({
                        data: {
                            "reqUrl": "reservationMarketSecond",
                            "reqMethod": "updateMarketSecondForVerify",
                            "marketSecondPkId": pkid,
                            "proVerifyState":verify
                        },
                        success: function (ret) {
                            if (ret) {
                                if (ret.retCode == GLOBAL_INFO.SYS_SUCCESS) {
                                    var resMsg = ret.resMsg;
                                    var retCode = ret.retCode;
                                    UOMPComp.showSuccessDialog(resMsg, "");
                                    if (GLOBAL_INFO.SYS_FAILED == retCode) {

                                    } else if (GLOBAL_INFO.SYS_SUCCESS) {
                                        window.location.reload();
                                    }
                                } else {
                                    if (ret.resMsg) {
                                        UOMPComp.showFailedDialog(ret.resMsg, "");
                                    } else {
                                        UOMPComp.showFailedDialog("系统异常", "");
                                    }
                                }
                            }
                        }
                    });
                },
                "no": function () {
                }
            });
        }
	},
	/**
	 * 审核框
	 */
	openReservationSecondAuditPanel:function(level){
		var selectRowId = datagrid.getSelectedRowId();
        var pkid = datagrid.cells(selectRowId, 1).getValue();
	    var size={"height":"60","width":"400"};
	    top.UOMPDialog.showSubPage("二级预约营销案-审核", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/verify/auditContent.jsp?marketPkId="+pkid,null,size);
	},
	/**
	*查询自定义菜单
	**/
	queryCustomMenu:function(){
		$.singleReq({
	        data: {
			    	"reqUrl": "console",
			        "reqMethod": "queryCustomMenuList",
	        },
        success: function (ret) {
            if (ret.retCode == 0) {
            	var result = ret.retObj; 
            	var html_str = "";
                $.each(result, function(i, item) {
                	var url = item.funcUri;
                	if(url.indexOf("/page/") >= 0){
                		url = url.substring(6,url.length);
                	}
                	html_str += '<a class="navigation" style="margin-right:10px;margin-top:5px"'+
							'href="javascript:parent.frameComponent.loadURL(\''+item.funcId+'\',\''+item.moudleSortName+'\',\''+url+'\')"> <img alt=""'+
							'src="${contextPath}/resource/img/manage.png"> '+item.moudleSortName+' </a>';
                });
                html_str += '<a class="navigation" style="margin-right:10px;margin-top:5px"'+
							'href="javascript:parent.frameComponent.loadURL(\'MOD_ZDXCDADD\',\'定义快速菜单\',\'admin/custom_menu_add.jsp?subSysNum=00&jbNum=01&jb=1\')"> <img alt=""'+
							'src="${contextPath}/resource/img/add.gif"> 添加 </a>';
                $("#toolbarObj").html(html_str);
			}
        }
    });
	},
	openWin:function(pkid){
		var size={"height":"60","width":"800"};
	    top.UOMPDialog.showSubPage("一级预约营销案-查看", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_first/reservationFirst_edit.jsp?pkid="+pkid,null,size);
	},
	openWin2:function(pkid){
		var size={"height":"60","width":"800"};
	    top.UOMPDialog.showSubPage("二级预约营销案-查看", GLOBAL_INFO.CONTEXTPATH + "/page/reservation_market/reservation_second/edit.jsp?marketSecondPkid="+pkid,null,size);
	},
	openWinAdv:function(pkid){
		var size={"height":"60","width":"800"};
	    top.UOMPDialog.showSubPage("广告-查看", GLOBAL_INFO.CONTEXTPATH + "/page/adv/adv_view.jsp?advNum="+pkid,null,size);
	},
	previewMarketSecond:function(pkid,tchannal){
		var size={"height":"60","width":"800"};
	    top.UOMPDialog.showSubPage("二级营销案-查看", GLOBAL_INFO.CONTEXTPATH + "/page/market/marketSecond/marketSecond_preview.jsp?marketSecondPkid="+pkid+"&tchannal="+tchannal,null,size);
	},
	openMarketFirst:function(pkid){
		var size={"height":"60","width":"800"};
	    top.UOMPDialog.showSubPage("一级营销案-查看", GLOBAL_INFO.CONTEXTPATH + "/page/market/marketFirst/view.jsp?pkid="+pkid,null,size);
	},
	openBusi:function(pkid){
		var size={"height":"60","width":"800"};
		top.UOMPDialog.showSubPage("业务-查看", GLOBAL_INFO.CONTEXTPATH + "/page/business/businessinfo/viewInConsole.jsp?&jbNum=004&funcId=QDCXECJY",null,size);
	}
 }
</script>
</head>
<body style="overflow-y: scroll;">
	<table>
		<tr>
			<td valign="top" width="80%">
				<fieldset class="defaultFieldset" style="padding-top:1px;">
					<legend>快速开始</legend>
					<div id="toolbarObj">
					   <a class="navigation" style="margin-right:10px;margin-top:5px"
							href="javascript:loadQueryCountByMonth('14')"> <img alt=""
							src="${contextPath}/resource/img/manage.png"> 专区管理 </a>
					</div>
				</fieldset>
				<fieldset class="defaultFieldset" style="padding-top:1px">
					<legend>报表统计</legend>
					<table>
						<tr>
							<td>
								<div id="chart1"
									style="width:300px;height:290px;border:1px solid #A4BED4;"></div>
							</td>
							<td>
								<div id="myForm1" style="height:290px;">
									<table class="tb">
										<tr>
											<td colspan="3" align="center"><a class="navigation"
												style="width:85%;"
												href="javascript:loadQueryCountByMonth('','全省')">ALL</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('14','南京')">NJ</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('11','苏州')">SZ</a>
											</td>
											
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('19','无锡')">WX</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('12','海安')">HA</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('13','宿迁')">SQ</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('23','扬州')">YZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('15','连云港')">LYG</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('16','徐州')">XZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('18','镇江')">ZJ</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('17','常州')">CZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('20','南通')">NT</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('21','通州')">TZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryCountByMonth('22','盐城')">YC</a>
											</td>
											<td></td>
										</tr>
									</table>
								</div></td>
							<td>
								<div id="chart2"
									style="width:301px;height:290px;border:1px solid #A4BED4;"></div>
							</td>
							<td>
								<div id="myForm2" style="height:290px;">
									<table class="tb">
										<tr>
											<td colspan="3" align="center"><a class="navigation"
												style="width:85%;" href="javascript:queryCountByCity('')">ALL</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:queryCountByCity('01')">&nbsp;1&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:queryCountByCity('02')">&nbsp;2&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:queryCountByCity('03')">&nbsp;3&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:queryCountByCity('04')">&nbsp;4&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:queryCountByCity('05')">&nbsp;5&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:queryCountByCity('06')">&nbsp;6&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:queryCountByCity('07')">&nbsp;7&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:queryCountByCity('08')">&nbsp;8&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:queryCountByCity('09')">&nbsp;9&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:queryCountByCity('10')">10</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:queryCountByCity('11')">11</a>
											</td>
											<td><a class="navigation"
												href="javascript:queryCountByCity('12')">12</a>
											</td>
										</tr>

									</table>
								</div></td>
						</tr>
					</table>

					<table>
						<tr>
							<td>
								<div id="moneyBarChart"
									style="width:300px;height:290px;border:1px solid #A4BED4;"></div>
							</td>
							<td>
								<div id="myForm1" style="height:290px;">
									<table class="tb">
										<tr>
											<td colspan="3" align="center"><a class="navigation"
												style="width:80%;"
												href="javascript:loadQueryMoneyByMonth('','全省')">ALL</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('14','南京')">NJ</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('11','苏州')">SZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('19','无锡')">WX</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('12','海安')">HA</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('13','宿迁')">SQ</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('23','扬州')">YZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('15','连云港')">LYG</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('17','常州')">CZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('16','徐州')">XZ</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('18','镇江')">ZJ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('20','南通')">NT</a>
											</td>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('21','通州')">TZ</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:loadQueryMoneyByMonth('22','盐城')">YC</a>
											</td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</div></td>
							<td>
								<div id="moneyChartByMonth"
									style="width:301px;height:290px;border:1px solid #A4BED4;"></div>
							</td>
							<td>
								<div id="myForm2" style="height:290px;">
									<table class="tb">
										<tr>
											<td colspan="3" align="center"><a class="navigation"
												style="width:90%;" href="javascript:querySumMoneyByCity('')">ALL</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('01')">&nbsp;1&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('02')">&nbsp;2&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('03')">&nbsp;3&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('04')">&nbsp;4&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('05')">&nbsp;5&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('06')">&nbsp;6&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('07')">&nbsp;7&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('08')">&nbsp;8&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('09')">&nbsp;9&nbsp;</a>
											</td>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('10')">10</a>
											</td>
										</tr>
										<tr>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('11')">11</a>
											</td>
											<td><a class="navigation"
												href="javascript:querySumMoneyByCity('12')">12</a>
											</td>
										</tr>

									</table>
								</div></td>
						</tr>
					</table>
				</fieldset></td>
			<td valign="top">
				<fieldset class="defaultFieldset" style="padding:1px">
					<legend>代办事项</legend>
					<table style="width:98%">
						<tr>
							<td>
								<div style="text-align:right;margin-right:10px;width:90%"><a href="javascript:parent.frameComponent.loadURL('AUDIT_FIRST','一级营销案审核','market/marketFirst/verify/marketFirst_verify.jsp')">更多>></a></div>
								<div id="gridbox_first"
									style="height:125px;background-color:white;width: 220px;margin-right:2px"></div>

								<div style="text-align:right;margin-right:10px;width:90%"><a href="javascript:parent.frameComponent.loadURL('AUDIT_SECOND','二级营销案审核','market/marketSecond/verify/marketSecond_verify.jsp')">更多>></a></div>
								<div id="gridbox_second"
									style="height:125px;background-color:white;width: 220px;margin-right:2px"></div>

								<div style="text-align:right;margin-right:10px;width:90%"><a href="javascript:parent.frameComponent.loadURL('MOD_GGXXSH','广告审核','adv/advList_verify.jsp')">更多>></a></div>
								<div id="gridbox_comp"
									style="height:125px;background-color:white;width: 220px;margin-right:2px"></div>

								<div style="text-align:right;margin-right:10px;width:90%"><a href="javascript:parent.frameComponent.loadURL('businessVerify','业务审核','business/businessVerify/busiInfoVerify_query.jsp')">更多>></a></div>
								<div id="gridbox_busi"
									style="height:125px;background-color:white;width: 220px;margin-right:2px"></div>
									
								<div style="text-align:right;margin-right:10px;width:90%"><a href="javascript:parent.frameComponent.loadURL(' MOD_RESERVATION_FIRST_AUDIT','一级预约营销案审核','reservation_market/reservation_first/verify/query.jsp')">更多>></a></div>
								<div id="gridbox_ReservationFirst"
									style="height:125px;background-color:white;width: 220px;margin-right:2px"></div>
								
								<div style="text-align:right;margin-right:10px;width:90%"><a href="javascript:parent.frameComponent.loadURL('MOD_RESERVATION_SECOND_AUDIT','二级预约营销案审核','reservation_market/reservation_second/verify/marketSecond_verify.jsp')">更多>></a></div>
								<div id="gridbox_ReservationSecond"
									style="height:125px;background-color:white;width: 220px;margin-right:2px"></div>
							</td>
						</tr>
					</table>

				</fieldset></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</table>
</body>
</html>
