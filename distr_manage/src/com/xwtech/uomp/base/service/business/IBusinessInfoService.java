package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-28 上午09:13:52
 */
public interface IBusinessInfoService {
	Page queryBusinessInfoList(Map<String, String> map);

	void updateBusinessBean(BusinessInfoBean businessBean);

	void addBusinessBean(BusinessInfoBean businessBean);

	BusinessInfoBean queryBusiInfoBynum(Map<String, String> param);

	List<TreeNode> queryBusiInfoTree();

	void batchInsert(List<BusinessInfoBean> businessInfoBeans);

	void updateBusinessBeans(List<BusinessInfoBean> businessInfoBeans);

	void deleteBusinessInfo(String busiNum);
	
	BusinessInfoBean querySingleBusiInfo(BusinessInfoBean businessInfoBean);

	Page selectBusiInfoListForVerify(Map<String, Object> param,UserInfoBean userInfoBean);

	void verifyBusinessInfo(Map<String, Object> param);

	Page selectBusiInfoListForTest(Map<String, Object> param, UserInfoBean userInfoBean);

	void testBusinessInfo(Map<String, Object> param);
	
	Page selectBusiInfoListForVerifyOnConsole(Map<String, Object> param,UserInfoBean userInfoBean);
	
	List<BusinessInfoBean> queryForSort(Map<String, Object> param);
	
	String queryJbNum(Map<String, Object> param);

	void sortBusinessInfo(List<BusinessInfoBean> list);
}
