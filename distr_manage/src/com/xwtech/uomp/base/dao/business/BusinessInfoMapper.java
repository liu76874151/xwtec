package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-3 上午11:16:32
 */
public interface BusinessInfoMapper {
	List<BusinessInfoBean> queryBusinessInfoList(Map<String, String> map);
	int queryBusinessInfoCount(Map<String, String> map);
	void updateBusinessBean(BusinessInfoBean businessInfoBean);
	void addBusinessBean(BusinessInfoBean businessBean);
	BusinessInfoBean queryBusiInfoBynum(Map<String, String> param);
	List<Map<String, Object>> queryBusiInfoTree();
	void insertSelective(BusinessInfoBean businessInfoBean);
	void deleteBusinessInfo(String busiNum);
	BusinessInfoBean querySingleBusiInfo(BusinessInfoBean businessInfoBean);
	void deleteBusinessInfos(Map<String, Object> param);
	List<Map<String, String>> selectBusiInfoListForVerify(Map<String, Object> param);
	int selectBusiInfoListForVerifyCount(Map<String, Object> param);
	void verifyBusinessInfo(Map<String, Object> param);
	List<Map<String, String>> selectBusiInfoListForTest(Map<String, Object> param);
	int selectBusiInfoListForTestCount(Map<String, Object> param);
	void testBusinessInfo(Map<String, Object> param);
	
	List<BusinessInfoBean> queryForSort(Map<String, Object> param);
	String queryJbNum(Map<String, Object> param);
}