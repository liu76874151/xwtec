package com.xwtech.uomp.base.dao.org;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.org.TargetGroupInfoBean;

/**
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-16 下午03:40:49
 */
public interface TargetGroupInfoMapper {
	List<TargetGroupInfoBean> queryTargetGroupInfoList(Map<String, String> param);
	
	List<TargetGroupInfoBean> selectTargetGroupFilterBySecondPkid(Map<String, String> param);
	
	int queryTargetGroupInfoCount(Map<String, String> param);
	
	void addGroupInfo(TargetGroupInfoBean targetGroupInfoBean);
	
	TargetGroupInfoBean queryGroupInfoById(Map<String, String> param);
	
	int queryPhoneNumber(Map<String, String> param);
	
	void addPhoneNumber(TargetGroupInfoBean targetGroupInfoBean);
	void deletePhoneNumber(String groupId);
	
	void updatePhoneCount(Map<String, String> param);
	
	void updateState(Map<String, String> param);
	
	void updateGroupInfo(TargetGroupInfoBean targetGroupInfoBean);
}