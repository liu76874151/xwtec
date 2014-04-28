package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessBaseInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-21 下午01:42:20
 */
public interface IBusinessBaseInfoService {
    
	List<TreeNode> queryBusiBaseInfoTree();

	Map<String, List<TreeNode>> queryBusiBaseInfoTreeExt();
	
	List<TreeNode> queryTopBusiTree();

	void saveBusinessInfo(BusinessBaseInfoBean businessBaseInfoBean);
	
	int queryJbNumCount(BusinessBaseInfoBean businessBaseInfoBean);

	String queryNewJbNum(String jbNum);
	
	BusinessBaseInfoBean queryBusiInfoBynum(String pkid);

	void updateBusinessInfo(BusinessBaseInfoBean businessBaseInfoBean);

	void deleteBusinessBaseInfo(String busiNum);
}
