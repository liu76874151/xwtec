package com.xwtech.uomp.base.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessInfoMapper;
import com.xwtech.uomp.base.dao.business.IBusinessInfoDao;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.area.AreaDABean;
import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeDzBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.CheckedTreeNode;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.pojo.verify.AuditLogBean;
import com.xwtech.uomp.base.service.business.IBusinessAreaDzService;
import com.xwtech.uomp.base.service.business.IBusinessBrandDzService;
import com.xwtech.uomp.base.service.business.IBusinessInfoService;
import com.xwtech.uomp.base.service.business.IBusinessTypeDzService;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoAreaDzService;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoBrandDzService;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoService;
import com.xwtech.uomp.base.service.verify.IAuditLogService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-28 上午09:14:02
 */
@Service("businessInfoService")
public class BusinessInfoServiceImpl implements IBusinessInfoService {

    @Autowired
    BusinessInfoMapper businessInfoMapper;
    @Autowired
    IBusinessTypeDzService businessTypeDzService;
    @Autowired
    IRelationBusinessInfoService relationBusinessInfoService;
    @Autowired
    IRelationBusinessInfoAreaDzService relationBusinessInfoAreaDzService;
    @Autowired
    IRelationBusinessInfoBrandDzService relationBusinessInfoBrandDzService;
    @Autowired
    IAuditLogService auditLogService;
    @Autowired
    IBusinessAreaDzService businessAreaDzService;
    @Autowired
    IBusinessBrandDzService businessBrandDzService;
    @Autowired
    IBusinessInfoDao businessInfoDao;

    public void addBusinessBean(BusinessInfoBean businessBean) {
	// TODO Auto-generated method stub

    }

    public Page queryBusinessInfoList(Map<String, String> map) {
	List<BusinessInfoBean> list = businessInfoMapper.queryBusinessInfoList(map);
	int totalRecord = businessInfoMapper.queryBusinessInfoCount(map);

	Page page = new Page();
	page.setRecords(list);
	page.setTotalRecord(totalRecord);

	return page;
    }

    public void updateBusinessBean(BusinessInfoBean businessBean) {
	// TODO Auto-generated method stub

    }

    public BusinessInfoBean queryBusiInfoBynum(Map<String, String> param) {
	return businessInfoMapper.queryBusiInfoBynum(param);
    }

    public List<TreeNode> queryBusiInfoTree() {
	List<TreeNode> treeNodeList = null;
	try {
	    List<Map<String, Object>> listResult = businessInfoMapper.queryBusiInfoTree();
	    if (listResult == null || listResult.size() <= 0) {
		return treeNodeList;
	    }
	    treeNodeList = new ArrayList<TreeNode>();
	    for (int i = 0; i < listResult.size(); i++) {
		Map<String, Object> dataMap = listResult.get(i);
		CheckedTreeNode node = DhtmlTreeUtil.getCheckedTreeNode(dataMap);
		treeNodeList.add(node);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    treeNodeList = null;
	}

	return treeNodeList;
    }

    @Transactional
    public void batchInsert(List<BusinessInfoBean> businessInfoBeans) {
	for (BusinessInfoBean businessInfoBean : businessInfoBeans) {
	    List<BusinessTypeDzBean> businessTypeDzBeans = businessInfoBean.getBusinessTypeDzBeans();
	    List<RelationBusinessInfoBean> relationBusinessInfoBeans = businessInfoBean.getRelationBusinessInfoBeans();
	    businessInfoMapper.insertSelective(businessInfoBean);
	    if (businessTypeDzBeans!=null && businessTypeDzBeans.size()>0) {
		businessTypeDzService.batchInsert(businessTypeDzBeans, businessInfoBean.getBusiNum());
	    }
	    if (relationBusinessInfoBeans!=null && relationBusinessInfoBeans.size()>0) {
		relationBusinessInfoService.batchInsert(relationBusinessInfoBeans,businessInfoBean.getBusiNum());
	    }
	}
    }

    /**
     * 个性信息更新
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateBusinessBeans(List<BusinessInfoBean> businessInfoBeans) {
	if (businessInfoBeans!=null && businessInfoBeans.size()>0) {
	    Map<String, Object> param = new HashMap<String, Object>();
	    String[] channelNums = new String[businessInfoBeans.size()];
	    int index = 0;
	    for (BusinessInfoBean businessInfoBean : businessInfoBeans) {
		if (index==0) {
		    param.put("busiNum", businessInfoBean.getBusiNum());
		}
		channelNums[index] = businessInfoBean.getChannelNum();
		List<BusinessTypeDzBean> businessTypeDzBeans = businessInfoBean.getBusinessTypeDzBeans();
		List<RelationBusinessInfoBean> relationBusinessInfoBeans = businessInfoBean.getRelationBusinessInfoBeans();
		
		if (null==this.querySingleBusiInfo(businessInfoBean)) {
		    businessInfoMapper.insertSelective(businessInfoBean);
		}else{
		    businessInfoMapper.updateBusinessBean(businessInfoBean);
		}
		
		businessTypeDzService.deleteBusiTypeDz(businessInfoBean);
		if (businessTypeDzBeans!=null && businessTypeDzBeans.size()>0) {
		    businessTypeDzService.batchInsert(businessTypeDzBeans, businessInfoBean.getBusiNum());
		}
		relationBusinessInfoService.deleteRelaBusiness(businessInfoBean);
		relationBusinessInfoAreaDzService.deleteRelaBusiAreaDz(businessInfoBean);
		relationBusinessInfoBrandDzService.deleteRelaBusiBrandDz(businessInfoBean);
		if (relationBusinessInfoBeans!=null && relationBusinessInfoBeans.size()>0) {
		    relationBusinessInfoService.batchUpdate(relationBusinessInfoBeans,businessInfoBean.getBusiNum());
		}
		index++;
	    }
	    param.put("channelNum", channelNums);
	    businessInfoMapper.deleteBusinessInfos(param);
	}
	
    }

    /**
     * 个性信息删除
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteBusinessInfo(String busiNum) {
	businessInfoMapper.deleteBusinessInfo(busiNum);
	businessTypeDzService.deleteByBusiNum(busiNum);//关联业务分类删除
	//关联业务删除
	relationBusinessInfoService.deleteByBusiNum(busiNum);
	//关联业务关联地市删除
	relationBusinessInfoAreaDzService.deleteByBusiNum(busiNum);
	//关联业务关联品牌删除
	relationBusinessInfoBrandDzService.deleteByBusiNum(busiNum);
    }

    public BusinessInfoBean querySingleBusiInfo(BusinessInfoBean businessInfoBean) {
	return businessInfoMapper.querySingleBusiInfo(businessInfoBean);
    }

    /**
     * 审核用查询
     */
    public Page selectBusiInfoListForVerify(Map<String, Object> param,UserInfoBean userInfoBean) {
	List<Map<String, String>> list = businessInfoMapper.selectBusiInfoListForVerify(param);
	int totalRecord = businessInfoMapper.selectBusiInfoListForVerifyCount(param);
	
	String userarea  = userInfoBean.getUserAreaCode();
	for (int i = 0; i < list.size(); i++) {
	    Map<String, String> bean = list.get(i);
	    if ("0".equals(userarea)) {
		StringBuilder proVerifyUrl= new StringBuilder();
		    if ("0".equals(bean.get("proVerifyState"))) {
			proVerifyUrl.append("待审核&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../../../resource/img/toggle_enabled.gif' height='10'></a>");
			proVerifyUrl.append("&nbsp;&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../../../resource/img/toggle_disabled.gif' height='10'></a>");
			bean.put("proVerifyState", proVerifyUrl.toString());
		    }
		    if ("1".equals(bean.get("proVerifyState"))) {
			proVerifyUrl.append("审核通过&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../../../resource/img/toggle_disabled.gif' height='10'></a>");
			bean.put("proVerifyState", proVerifyUrl.toString());
		    }
		    if ("2".equals(bean.get("proVerifyState"))) {
			proVerifyUrl.append("<a href=\"javascript:component.showDesc('"+bean.get("description")+"');\">审核不通过</a>&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../../../resource/img/toggle_enabled.gif' height='10'></a>");
			bean.put("proVerifyState", proVerifyUrl.toString());
		    }
		    StringBuilder localVerifyUrl= new StringBuilder();
		    if ("0".equals(bean.get("localVerifyState"))) {
			localVerifyUrl.append("待审核");
			bean.put("localVerifyState", localVerifyUrl.toString());
		    }
		    if ("1".equals(bean.get("localVerifyState"))) {
			localVerifyUrl.append("审核通过");
			bean.put("localVerifyState", localVerifyUrl.toString());
		    }
		    if ("2".equals(bean.get("localVerifyState"))) {
			localVerifyUrl.append("<a href=\"javascript:component.showDesc('"+bean.get("description")+"');\">审核不通过</a>");
			bean.put("localVerifyState", localVerifyUrl.toString());
		    }
		    
	    }else {
		StringBuilder proVerifyUrl= new StringBuilder();
		    if ("0".equals(bean.get("proVerifyState"))) {
			proVerifyUrl.append("待审核");
			bean.put("proVerifyState", proVerifyUrl.toString());
		    }
		    if ("1".equals(bean.get("proVerifyState").toString())) {
			proVerifyUrl.append("审核通过");
			bean.put("proVerifyState", proVerifyUrl.toString());
		    }
		    if ("2".equals(bean.get("proVerifyState").toString())) {
			proVerifyUrl.append("<a href=\"javascript:component.showDesc('"+bean.get("description")+"');\">审核不通过</a>");
			bean.put("proVerifyState", proVerifyUrl.toString());
		    }
		//localVerifyState
		    StringBuilder localVerifyUrl= new StringBuilder();
		    if ("0".equals(bean.get("localVerifyState"))) {
			localVerifyUrl.append("待审核&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../../../resource/img/toggle_enabled.gif' height='10'></a>");
			localVerifyUrl.append("&nbsp;&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../../../resource/img/toggle_disabled.gif' height='10'></a>");
			bean.put("localVerifyState", localVerifyUrl.toString());
		    }
		    if ("1".equals(bean.get("localVerifyState"))) {
			localVerifyUrl.append("审核通过&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../../../resource/img/toggle_disabled.gif' height='10'></a>");
			bean.put("localVerifyState", localVerifyUrl.toString());
		    }
		    if ("2".equals(bean.get("localVerifyState"))) {
			localVerifyUrl.append("<a href=\"javascript:component.showDesc('"+bean.get("description")+"');\">审核不通过</a>&nbsp;<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../../../resource/img/toggle_enabled.gif' height='10'></a>");
			bean.put("localVerifyState", localVerifyUrl.toString());
		    }
	    }
	    //busiTestState
	    if (null == bean.get("busiTestState")) {
		bean.put("busiTestState", "待测试");
	    }
	    if ("0".equals(bean.get("busiTestState"))) {
		bean.put("busiTestState", "测试通过");
	    }
	    if ("1".equals(bean.get("busiTestState"))) {
		bean.put("busiTestState", "测试不通过");
	    }
	    
	    //relaBusi
	    bean.put("relaBusi", "<a href=\"javascript:component.showRelaBusiness('"+bean.get("busiNum")+"','"+bean.get("channelNum")+"');\" >查&nbsp;看</a>");
	    
	    //关联业务类型
	    List<BusinessTypeBean> businessTypeBeans = businessTypeDzService.queryBusiTypeByBusiNum(bean);
	    StringBuilder busiTypeName = new StringBuilder();
	    int index=0;
	    if (null!=businessTypeBeans && businessTypeBeans.size()>0) {
		for (BusinessTypeBean businessTypeBean : businessTypeBeans) {
			index++;
			busiTypeName.append(businessTypeBean.getBusiTypeName());
			if (index!=businessTypeBeans.size()) {
			    busiTypeName.append("、");
			}
		  }
		bean.put("busiTypeName", busiTypeName.toString());
	    }
	    //关联地区
	   List<AreaDABean> areaDABeans = businessAreaDzService.queryAreaByBusiNum(bean);
	   StringBuilder areaName = new StringBuilder();
	   index=0;
	   if (null!=areaDABeans && areaDABeans.size()>0) {
		for (AreaDABean areaDABean : areaDABeans) {
			index++;
			areaName.append(areaDABean.getAreaName());
			if (index!=areaDABeans.size()) {
			    areaName.append("、");
			}
		  }
		bean.put("areaName", areaName.toString());
	    }
	   
	 //关联品牌
		List<BrandBean> brandBeans = businessBrandDzService.queryBrandByBusiNum(bean);
		StringBuilder brandName = new StringBuilder();
		index=0;
		if (null!=brandBeans && brandBeans.size()>0) {
		    for (BrandBean brandBean : brandBeans) {
			index++;
			brandName.append(brandBean.getBrandName());
			if (index!=brandBeans.size()) {
			    brandName.append("、");
			}
		    }
		    bean.put("brandName", brandName.toString());
		}
	}
	
	Page page = new Page();
	page.setRecords(list);
	page.setTotalRecord(totalRecord);

	return page;
    }
    
    public Page selectBusiInfoListForVerifyOnConsole(Map<String, Object> param,UserInfoBean userInfoBean) {
    	List<Map<String, String>> list = businessInfoMapper.selectBusiInfoListForVerify(param);
    	int totalRecord = businessInfoMapper.selectBusiInfoListForVerifyCount(param);
    	
    	String userarea  = userInfoBean.getUserAreaCode();
    	for (int i = 0; i < list.size(); i++) {
    	    Map<String, String> bean = list.get(i);
    	    if ("0".equals(userarea)) {
    		StringBuilder proVerifyUrl= new StringBuilder();
    		    if ("0".equals(bean.get("proVerifyState"))) {
    			proVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
    			proVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
    			bean.put("proVerifyState", proVerifyUrl.toString());
    		    }
    		    if ("1".equals(bean.get("proVerifyState"))) {
    			proVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
    			bean.put("proVerifyState", proVerifyUrl.toString());
    		    }
    		    if ("2".equals(bean.get("proVerifyState"))) {
    			proVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
    			bean.put("proVerifyState", proVerifyUrl.toString());
    		    }
    		    StringBuilder localVerifyUrl= new StringBuilder();
    		    if ("0".equals(bean.get("localVerifyState"))) {
    			localVerifyUrl.append("待审核");
    			bean.put("localVerifyState", localVerifyUrl.toString());
    		    }
    		    if ("1".equals(bean.get("localVerifyState"))) {
    			localVerifyUrl.append("审核通过");
    			bean.put("localVerifyState", localVerifyUrl.toString());
    		    }
    		    if ("2".equals(bean.get("localVerifyState"))) {
    			localVerifyUrl.append("审核不通过");
    			bean.put("localVerifyState", localVerifyUrl.toString());
    		    }
    		    
    	    }else {
    		StringBuilder proVerifyUrl= new StringBuilder();
    		    if ("0".equals(bean.get("proVerifyState"))) {
    			proVerifyUrl.append("待审核");
    			bean.put("proVerifyState", proVerifyUrl.toString());
    		    }
    		    if ("1".equals(bean.get("proVerifyState").toString())) {
    			proVerifyUrl.append("审核通过");
    			bean.put("proVerifyState", proVerifyUrl.toString());
    		    }
    		    if ("2".equals(bean.get("proVerifyState").toString())) {
    			proVerifyUrl.append("审核不通过");
    			bean.put("proVerifyState", proVerifyUrl.toString());
    		    }
    		//localVerifyState
    		    StringBuilder localVerifyUrl= new StringBuilder();
    		    if ("0".equals(bean.get("localVerifyState"))) {
    			localVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
    			localVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
    			bean.put("localVerifyState", localVerifyUrl.toString());
    		    }
    		    if ("1".equals(bean.get("localVerifyState"))) {
    			localVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
    			bean.put("localVerifyState", localVerifyUrl.toString());
    		    }
    		    if ("2".equals(bean.get("localVerifyState"))) {
    			localVerifyUrl.append("<a href=\"javascript:component.proVerifyBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
    			bean.put("localVerifyState", localVerifyUrl.toString());
    		    }
    	    }
    	    //busiTestState
    	    if (null == bean.get("busiTestState")) {
    		bean.put("busiTestState", "待测试");
    	    }
    	    if ("0".equals(bean.get("busiTestState"))) {
    		bean.put("busiTestState", "测试通过");
    	    }
    	    if ("1".equals(bean.get("busiTestState"))) {
    		bean.put("busiTestState", "测试不通过");
    	    }
    	    //查看url
    	    String busiName = bean.get("busiName");
    	    if(busiName.length() > 10){
    	    	busiName = busiName.substring(0,9)+"...";
    	    }
    	    busiName = "<a href='javascript:component.openBusi(\""+bean.get("busiNum")+"\")'>"+busiName+"</a>";
    	    bean.put("busiName", busiName);
    	    //relaBusi
    	    bean.put("relaBusi", "<a href=\"javascript:component.showRelaBusiness('"+bean.get("busiNum")+"','"+bean.get("channelNum")+"');\" >查&nbsp;看</a>");
    	    
    	    //关联业务类型
    	    List<BusinessTypeBean> businessTypeBeans = businessTypeDzService.queryBusiTypeByBusiNum(bean);
    	    StringBuilder busiTypeName = new StringBuilder();
    	    int index=0;
    	    if (null!=businessTypeBeans && businessTypeBeans.size()>0) {
    		for (BusinessTypeBean businessTypeBean : businessTypeBeans) {
    			index++;
    			busiTypeName.append(businessTypeBean.getBusiTypeName());
    			if (index!=businessTypeBeans.size()) {
    			    busiTypeName.append("、");
    			}
    		  }
    		bean.put("busiTypeName", busiTypeName.toString());
    	    }
    	    //关联地区
    	   List<AreaDABean> areaDABeans = businessAreaDzService.queryAreaByBusiNum(bean);
    	   StringBuilder areaName = new StringBuilder();
    	   index=0;
    	   if (null!=areaDABeans && areaDABeans.size()>0) {
    		for (AreaDABean areaDABean : areaDABeans) {
    			index++;
    			areaName.append(areaDABean.getAreaName());
    			if (index!=areaDABeans.size()) {
    			    areaName.append("、");
    			}
    		  }
    		bean.put("areaName", areaName.toString());
    	    }
    	}
    	
    	
    	Page page = new Page();
    	page.setRecords(list);
    	page.setTotalRecord(totalRecord);

    	return page;
        }

    /**
     * 业务审核
     * 业务状态（0-正常；1-已删除；2-未审核; 3-审核未通过;4,待测试,5,测试不通过）
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void verifyBusinessInfo(Map<String, Object> param) {
	
	UserInfoBean userInfoBean = (UserInfoBean) param.get("userInfoBean");
	String state = (String) param.get("state");
	
	String userarea = userInfoBean.getUserAreaCode();
	
	AuditLogBean auditLogBean = new AuditLogBean();
	auditLogBean.setBisuId((String) param.get("busiNum"));
	auditLogBean.setOperType("1");
	auditLogBean.setChannelNum((String) param.get("channelNum"));
	auditLogBean.setOperUser(userInfoBean.getLoginName());//审核用户
	
	if ("4".equals(state)) {//审核通过
	    auditLogBean.setAuditState("0");
	    
	}else {
	    auditLogBean.setDescription((String) param.get("description"));//不通过原因
	    auditLogBean.setAuditState("1");
	    
	}
	
	if ("0".equals(userarea)) {//省级用户
	    auditLogBean.setOperLevel("0");
	    
	    if ("1".equals(auditLogBean.getAuditState())) {//审核不通过
		param.put("proVerifyState", "2");
	    }
	    if ("0".equals(auditLogBean.getAuditState())) {
		param.put("proVerifyState", "1");
	    }
	    
	    businessInfoMapper.verifyBusinessInfo(param);
	    
	}else {//地市用户
	    auditLogBean.setOperLevel("1");
	    
	    if ("1".equals(auditLogBean.getAuditState())) {//地市审核不通过
		param.put("localVerifyState", "2");
	    }
	    if ("0".equals(auditLogBean.getAuditState())) {
		param.put("localVerifyState", "1");
		param.put("state", "2");
	    }
	    businessInfoMapper.verifyBusinessInfo(param);
	}
	auditLogService.insertSelective(auditLogBean);
	
    }

    /**
     * 业务测试用查询
     */
    @SuppressWarnings("unchecked")
    public Page selectBusiInfoListForTest(Map<String, Object> param, UserInfoBean userInfoBean) {
	List<Map<String, String>> list = businessInfoMapper.selectBusiInfoListForTest(param);
	int totalRecord = businessInfoMapper.selectBusiInfoListForTestCount(param);
	for (int i = 0; i < list.size(); i++) {
	    Map bean = list.get(i);
	    bean.put("proVerifyState", "审核通过");
	    bean.put("localVerifyState", "审核通过");
	    
	    //busiTestState
	    StringBuilder busiTestUrl = new StringBuilder();
	    if (null == bean.get("busiTestState")) {
		busiTestUrl.append("待测试&nbsp;<a href=\"javascript:component.testBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='测试通过' src='../../../resource/img/toggle_enabled.gif' height='10'></a>");
		busiTestUrl.append("&nbsp;&nbsp;<a href=\"javascript:component.testBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='测试不通过' src='../../../resource/img/toggle_disabled.gif' height='10'></a>");
		bean.put("busiTestState", busiTestUrl.toString());
	    }
	    if ("0".equals(bean.get("busiTestState"))) {
		busiTestUrl.append("测试通过&nbsp;<a href=\"javascript:component.testBusiInfo('"+bean.get("busiNum")+"',false,'"+bean.get("channelNum")+"');\" ><img title='测试不通过' src='../../../resource/img/toggle_disabled.gif' height='10'></a>");
		bean.put("busiTestState", busiTestUrl.toString());
	    }
	    if ("1".equals(bean.get("busiTestState"))) {
		busiTestUrl.append("<a href=\"javascript:component.showDesc('"+bean.get("description")+"');\">测试不通过</a>&nbsp;<a href=\"javascript:component.testBusiInfo('"+bean.get("busiNum")+"',true,'"+bean.get("channelNum")+"');\" ><img title='测试通过' src='../../../resource/img/toggle_enabled.gif' height='10'></a>");
		bean.put("busiTestState", busiTestUrl.toString());
	    }
	    bean.put("relaBusi", "<a href=\"javascript:component.showRelaBusiness('"+bean.get("busiNum")+"','"+bean.get("channelNum")+"');\" >查&nbsp;看</a>");
	  
	    //关联业务类型
	    List<BusinessTypeBean> businessTypeBeans = businessTypeDzService.queryBusiTypeByBusiNum(bean);
	    StringBuilder busiTypeName = new StringBuilder();
	    int index=0;
	    if (null!=businessTypeBeans && businessTypeBeans.size()>0) {
		for (BusinessTypeBean businessTypeBean : businessTypeBeans) {
			index++;
			busiTypeName.append(businessTypeBean.getBusiTypeName());
			if (index!=businessTypeBeans.size()) {
			    busiTypeName.append("、");
			}
		  }
		bean.put("busiTypeName", busiTypeName.toString());
	    }
	    //关联地区
	    List<AreaDABean> areaDABeans = businessAreaDzService.queryAreaByBusiNum(bean);
	    StringBuilder areaName = new StringBuilder();
	    index=0;
	    if (null!=areaDABeans && areaDABeans.size()>0) {
		for (AreaDABean areaDABean : areaDABeans) {
		    index++;
		    areaName.append(areaDABean.getAreaName());
		    if (index!=areaDABeans.size()) {
			areaName.append("、");
		    }
		}
		bean.put("areaName", areaName.toString());
	    }
	    
	  //关联品牌
	    	   List<BrandBean> brandBeans = businessBrandDzService.queryBrandByBusiNum(bean);
	    	   StringBuilder brandName = new StringBuilder();
	    	   index=0;
	    	   if (null!=brandBeans && brandBeans.size()>0) {
	    	       for (BrandBean brandBean : brandBeans) {
	    		   index++;
	    		   brandName.append(brandBean.getBrandName());
	    		   if (index!=brandBeans.size()) {
	    		       brandName.append("、");
	    		   }
	    	       }
	    	       bean.put("brandName", brandName.toString());
	    	   }
	}
	
	Page page = new Page();
	page.setRecords(list);
	page.setTotalRecord(totalRecord);

	return page;
    }

    /**
     * 业务测试
     * 业务状态（0-正常；1-已删除；2-未审核; 3-审核未通过;4,待测试,5,测试不通过）
     */
    @Transactional
    public void testBusinessInfo(Map<String, Object> param) {
	
	UserInfoBean userInfoBean = (UserInfoBean) param.get("userInfoBean");
	String state = (String) param.get("state");
	String userarea = userInfoBean.getUserAreaCode();
	
	AuditLogBean auditLogBean = new AuditLogBean();
	auditLogBean.setBisuId((String) param.get("busiNum"));
	auditLogBean.setOperType("2");
	auditLogBean.setChannelNum((String) param.get("channelNum"));
	auditLogBean.setOperUser(userInfoBean.getLoginName());//测试用户
	if ("0".equals(state)) {//通过
	    auditLogBean.setAuditState("0");
	}else {
	    auditLogBean.setDescription((String) param.get("description"));//不通过原因
	    auditLogBean.setAuditState("1");
	}
	auditLogBean.setOperLevel("2");//未区分
	
	auditLogService.insertSelective(auditLogBean);
	businessInfoMapper.testBusinessInfo(param);
    }

    /**
     * 排序用查询
     */
    public List<BusinessInfoBean> queryForSort(Map<String, Object> param) {

	return businessInfoMapper.queryForSort(param);
    }

    public String queryJbNum(Map<String, Object> param) {
	
	return businessInfoMapper.queryJbNum(param);
    }

    /**
     * 业务排序
     */
    public void sortBusinessInfo(List<BusinessInfoBean> list) {
	
	businessInfoDao.sortBusinessInfo(list);
	//业务共性信息排序 
//	businessInfoDao.sortBaseBusinessInfo(list);
    }
    
}
