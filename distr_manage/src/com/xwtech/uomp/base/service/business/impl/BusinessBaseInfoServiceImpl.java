package com.xwtech.uomp.base.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessBaseInfoMapper;
import com.xwtech.uomp.base.pojo.business.BusinessBaseInfoBean;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.CheckedTreeNode;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.business.IBusinessAreaDzService;
import com.xwtech.uomp.base.service.business.IBusinessBaseInfoService;
import com.xwtech.uomp.base.service.business.IBusinessBrandDzService;
import com.xwtech.uomp.base.service.business.IBusinessEffectWayDzService;
import com.xwtech.uomp.base.service.business.IBusinessExattrDzService;
import com.xwtech.uomp.base.service.business.IBusinessInfoService;
import com.xwtech.uomp.base.service.business.IBusinessTagDzService;
import com.xwtech.uomp.base.service.business.IBusinessTypeDzService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-21 下午01:43:20
 */
@Service("businessBaseInfoService")
public class BusinessBaseInfoServiceImpl implements IBusinessBaseInfoService {
    
    private final static Logger LOGGER = Logger.getLogger(BusinessBaseInfoServiceImpl.class);
    
    @Autowired
    BusinessBaseInfoMapper businessBaseInfoMapper;
    @Autowired
    IBusinessEffectWayDzService businessEffectWayDzService;
    @Autowired
    IBusinessAreaDzService businessAreaDzService;
    @Autowired
    IBusinessBrandDzService businessBrandDzService;
    @Autowired
    IBusinessExattrDzService businessExattrDzService;
    @Autowired
    IBusinessInfoService businessInfoService;
    @Autowired
    IBusinessTypeDzService businessTypeDzService;
    @Autowired
    IBusinessTagDzService businessTagDzService;

    public List<TreeNode> queryBusiBaseInfoTree() {
	List<TreeNode> treeNodeList = null;
	try {
	    List<Map<String, Object>> listResult = businessBaseInfoMapper.queryBusiBaseInfoTree();
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
	    LOGGER.error(e.getMessage());
	    treeNodeList = null;
	}

	return treeNodeList;
    }
    public Map<String, List<TreeNode>> queryBusiBaseInfoTreeExt() {
	Map<String, List<TreeNode>> sysTreeNodeMap = null;
        try {
            List<Map<String, Object>> listResult = businessBaseInfoMapper.queryBusiBaseInfoTree();
            if (listResult == null || listResult.size() <= 0) {
                return sysTreeNodeMap;
            }
            sysTreeNodeMap = new HashMap<String, List<TreeNode>>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
                TreeNode node = TreeNode.newInstance();
                if (dataMap != null) {
                    String subSystemNum = null;
                    for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
                        String key = (String) iter.next();
                        if ("busiBaseInfo".equals(key)) {
                            subSystemNum = (String) dataMap.get(key);
                            if (!sysTreeNodeMap.containsKey(subSystemNum)) {
                                sysTreeNodeMap.put(subSystemNum, new ArrayList<TreeNode>());
                            }

                        } else if ("id".equals(key)) {
                            node.setId((String) dataMap.get(key));
                        } else if ("text".equals(key)) {
                            node.setText((String) dataMap.get(key));
                        } else if ("pid".equals(key)) {
                            node.setPid((String) dataMap.get(key));
                        } else {
                            Map<String, Object> tmpMap = new HashMap<String, Object>();
                            tmpMap.put("name", key);
                            tmpMap.put("content", dataMap.get(key));
                            node.getUserdata().add(tmpMap);
                        }
                    }
                    ((List<TreeNode>) sysTreeNodeMap.get(subSystemNum)).add(node);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            sysTreeNodeMap = null;
        }

        return sysTreeNodeMap;
    }
    
    public List<TreeNode> queryTopBusiTree() {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = new ArrayList<Map<String,Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "busiBaseInfo");
            map.put("text", "业务信息");
            map.put("jbNum", "-1");
            map.put("mj", new BigDecimal(0));
            map.put("pid", "-1");
            listResult.add(map);
            
            treeNodeList = new ArrayList<TreeNode>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
                TreeNode node = DhtmlTreeUtil.getTreeNode(dataMap);
                treeNodeList.add(node);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            treeNodeList = null;
        }

        return treeNodeList;
    }
    
    /**
     * 业务新增
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveBusinessInfo(BusinessBaseInfoBean businessBaseInfoBean) {
	String jbNum = businessBaseInfoBean.getJbNum();
	if ("-1".equals(jbNum)) {
            jbNum = null;
        }
        businessBaseInfoBean.setJbNum(jbNum);
        
        int count = this.queryJbNumCount(businessBaseInfoBean);
        
        //级别编码
        String newJbNum = null;
        int jb = 1;
        if (null == jbNum) {
            if (count < 100) {
        	if ((count + 1)<10) {
        	    newJbNum = "00" + (count + 1);
		}else{
		    newJbNum = "0" + (count + 1);
		}
            } else {
                newJbNum = "" + (count + 1);
            }
        } else {
            newJbNum = this.queryNewJbNum(jbNum);
        }

        if (!(null == jbNum)) {
            jb = newJbNum.length() / 3;//当前级别
        }
	businessBaseInfoBean.setJbNum(newJbNum);
        businessBaseInfoBean.setJb(jb);
        
        businessBaseInfoMapper.insertSelective(businessBaseInfoBean);//共性信息保存base_info
        //开通生效方式
        if (businessBaseInfoBean.getBusinessEffectWayBeans()!=null) {
            businessEffectWayDzService.batchInsert(businessBaseInfoBean.getBusinessEffectWayBeans(),businessBaseInfoBean.getBusiNum());
	}
        //关闭生效方式
        if (businessBaseInfoBean.getBusinessNEffectWayBeans()!=null) {
            businessEffectWayDzService.batchInsert(businessBaseInfoBean.getBusinessNEffectWayBeans(), businessBaseInfoBean.getBusiNum());
	}
        //关联地市
        if (businessBaseInfoBean.getBusinessAreaDzBeans()!=null) {
            businessAreaDzService.batchInsert(businessBaseInfoBean.getBusinessAreaDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        //关联品牌
        if (businessBaseInfoBean.getBusinessBrandDzBeans()!=null) {
            businessBrandDzService.batchInsert(businessBaseInfoBean.getBusinessBrandDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        //扩展属性
        if (businessBaseInfoBean.getBusinessExattrDzBeans()!=null) {
            businessExattrDzService.batchInsert(businessBaseInfoBean.getBusinessExattrDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        //业务标签
        if (businessBaseInfoBean.getBusiTagDzBeans()!=null) {
            businessTagDzService.batchInsert(businessBaseInfoBean.getBusiTagDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        
        List<BusinessInfoBean> businessInfoBeans = businessBaseInfoBean.getBusinessInfoBeans();
        if (businessInfoBeans!=null && businessInfoBeans.size()>0) {//TODO
            for (BusinessInfoBean businessInfoBean : businessInfoBeans) {
                businessInfoBean.setBusiName(businessBaseInfoBean.getBusiName());
                businessInfoBean.setBusiNum(businessBaseInfoBean.getBusiNum());
                businessInfoBean.setJbNum(newJbNum);
                businessInfoBean.setJb(jb);
                businessInfoBean.setBusiNamePym2(businessBaseInfoBean.getBusiNamePym2());
                businessInfoBean.setState(businessBaseInfoBean.getState());
                businessInfoBean.setFee(businessBaseInfoBean.getFee());//资费
                /**********************字段非空 start**************************/
                businessInfoBean.setProcessType(businessBaseInfoBean.getProcessType());
                businessInfoBean.setFeeType(businessBaseInfoBean.getFeeType());
                businessInfoBean.setSuppPayWay(businessBaseInfoBean.getSuppPayWay());
                businessInfoBean.setDeductWay(businessBaseInfoBean.getDeductWay());
                /**********************字段非空 end**************************/
                businessInfoBean.setMj(businessBaseInfoBean.getMj());
                businessInfoBean.setFeeScoreM(businessBaseInfoBean.getFeeScoreM());//M值
                businessInfoBean.setFeeScoreQ(businessBaseInfoBean.getFeeScoreQ());//积分
                businessInfoBean.setBusiClientUrl(businessBaseInfoBean.getBusiClientUrl());//客户端下载地址
                businessInfoBean.setBusiAdvl(businessBaseInfoBean.getBusiAdvl());//业务宣传语
                businessInfoBean.setBusiFeature(businessBaseInfoBean.getBusiFeature());//特点
                businessInfoBean.setBusiPrivilege(businessBaseInfoBean.getBusiPrivilege());//优惠活动
                businessInfoBean.setPrompt(businessBaseInfoBean.getPrompt());//温馨提示
            }
            //业务个性信息持久化
            businessInfoService.batchInsert(businessInfoBeans);
	}
	
    }
    
    public int queryJbNumCount(BusinessBaseInfoBean businessBaseInfoBean) {
	return businessBaseInfoMapper.queryJbNumCount(businessBaseInfoBean);
    }
    
    public String queryNewJbNum(String jbNum) {
	return businessBaseInfoMapper.queryNewJbNum(jbNum);
    }
    
    public BusinessBaseInfoBean queryBusiInfoBynum(String pkid) {
	return businessBaseInfoMapper.queryBusiInfoBynum(pkid);
    }
    
    /**
     * 业务信息更新修改
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateBusinessInfo(BusinessBaseInfoBean businessBaseInfoBean) {
	
	businessBaseInfoMapper.updateByPrimaryKeySelective(businessBaseInfoBean);//共性信息修改base_info
	
	businessEffectWayDzService.deleteEffectWayByBusiNum(businessBaseInfoBean.getBusiNum());//生效方式删除
        //开通生效方式
        if (businessBaseInfoBean.getBusinessEffectWayBeans()!=null) {
            businessEffectWayDzService.batchInsert(businessBaseInfoBean.getBusinessEffectWayBeans(),businessBaseInfoBean.getBusiNum());
	}
        //关闭生效方式
        if (businessBaseInfoBean.getBusinessNEffectWayBeans()!=null) {
            businessEffectWayDzService.batchInsert(businessBaseInfoBean.getBusinessNEffectWayBeans(), businessBaseInfoBean.getBusiNum());
	}
        //关联地市
        businessAreaDzService.deleteByBusiNum(businessBaseInfoBean.getBusiNum());//删除
        if (businessBaseInfoBean.getBusinessAreaDzBeans()!=null) {
            businessAreaDzService.batchInsert(businessBaseInfoBean.getBusinessAreaDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        //关联品牌
        businessBrandDzService.deleteByBusiNum(businessBaseInfoBean.getBusiNum());
        if (businessBaseInfoBean.getBusinessBrandDzBeans()!=null) {
            businessBrandDzService.batchInsert(businessBaseInfoBean.getBusinessBrandDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        //扩展属性
        businessExattrDzService.deleteByBusiNum(businessBaseInfoBean.getBusiNum());
        if (businessBaseInfoBean.getBusinessExattrDzBeans()!=null) {
            businessExattrDzService.batchInsert(businessBaseInfoBean.getBusinessExattrDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        
        //业务标签
        businessTagDzService.deleteByBusiNum(businessBaseInfoBean.getBusiNum());
        if (businessBaseInfoBean.getBusiTagDzBeans()!=null) {
            businessTagDzService.batchInsert(businessBaseInfoBean.getBusiTagDzBeans(), businessBaseInfoBean.getBusiNum());
	}
        
        List<BusinessInfoBean> businessInfoBeans = businessBaseInfoBean.getBusinessInfoBeans();
        if (businessInfoBeans!=null && businessInfoBeans.size()>0) {
            for (BusinessInfoBean businessInfoBean : businessInfoBeans) {
                businessInfoBean.setBusiName(businessBaseInfoBean.getBusiName());
                businessInfoBean.setBusiNum(businessBaseInfoBean.getBusiNum());
                businessInfoBean.setJbNum(businessBaseInfoBean.getJbNum());
                businessInfoBean.setJb(businessBaseInfoBean.getJb());
                businessInfoBean.setBusiNamePym2(businessBaseInfoBean.getBusiNamePym2());
                businessInfoBean.setFee(businessBaseInfoBean.getFee());//资费
//                businessInfoBean.setState(businessBaseInfoBean.getState());
                /**********************字段非空 start**************************/
                businessInfoBean.setProcessType(businessBaseInfoBean.getProcessType());
                businessInfoBean.setFeeType(businessBaseInfoBean.getFeeType());
                businessInfoBean.setSuppPayWay(businessBaseInfoBean.getSuppPayWay());
                businessInfoBean.setDeductWay(businessBaseInfoBean.getDeductWay());
                /**********************字段非空 end**************************/
                businessInfoBean.setMj(businessBaseInfoBean.getMj());
              //TODO
                businessInfoBean.setFeeScoreM(businessBaseInfoBean.getFeeScoreM());//M值
                businessInfoBean.setFeeScoreQ(businessBaseInfoBean.getFeeScoreQ());//积分
                businessInfoBean.setBusiClientUrl(businessBaseInfoBean.getBusiClientUrl());//客户端下载地址
                businessInfoBean.setBusiAdvl(businessBaseInfoBean.getBusiAdvl());//业务宣传语
                businessInfoBean.setBusiFeature(businessBaseInfoBean.getBusiFeature());//特点
                businessInfoBean.setBusiPrivilege(businessBaseInfoBean.getBusiPrivilege());//优惠活动
                businessInfoBean.setPrompt(businessBaseInfoBean.getPrompt());//温馨提示
            }
            //业务个性信息持久化
            businessInfoService.updateBusinessBeans(businessInfoBeans);
	}else{
	    //删除个性信息
	    businessInfoService.deleteBusinessInfo(businessBaseInfoBean.getBusiNum());
	}
    }
    /**
     * 删除共性业务
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteBusinessBaseInfo(String busiNum) {
	
	businessBaseInfoMapper.deleteBusinessBaseInfo(busiNum);
	
	businessEffectWayDzService.deleteEffectWayByBusiNum(busiNum);//生效方式删除
	businessAreaDzService.deleteByBusiNum(busiNum);//关联地市删除
	businessBrandDzService.deleteByBusiNum(busiNum); //关联品牌
	//扩展属性
        businessExattrDzService.deleteByBusiNum(busiNum);
        //个性信息删除f_state=1
        businessInfoService.deleteBusinessInfo(busiNum);
	
    }

}
