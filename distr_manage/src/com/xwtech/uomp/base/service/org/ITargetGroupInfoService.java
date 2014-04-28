package com.xwtech.uomp.base.service.org;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.org.TargetGroupInfoBean;

/**
 * 目标组织
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-16 下午03:53:53
 */
public interface ITargetGroupInfoService {

	/**
	 * 目标组织查询
	 * @param param
	 * @return  
	 * @author zhangel
	 */
	List<TargetGroupInfoBean> querytargetGroupInfoList(Map<String, String> param);
	
	List<TargetGroupInfoBean> selectTargetGroupFilterBySecondPkid(Map<String, String> param);
	
	Page queryTargetByPage(Map<String, String> param);
	
	void addGroupInfo(TargetGroupInfoBean targetGroupInfoBean);
	
	TargetGroupInfoBean queryGroupInfoById(Map<String, String> param);
	
	int queryPhoneNumber(Map<String, String> param);
	
	void addPhoneNumber(TargetGroupInfoBean targetGroupInfoBean);
	void deletePhoneNumber(String groupId);
	
	void updateState(Map<String, String> param);
	
	void updateGroupInfo(TargetGroupInfoBean targetGroupInfoBean);
}
