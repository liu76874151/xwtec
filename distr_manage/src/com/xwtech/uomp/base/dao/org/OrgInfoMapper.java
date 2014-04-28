package com.xwtech.uomp.base.dao.org;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.org.OrgInfoBean;

/**
 * @author zhangel
 */
public interface OrgInfoMapper {
	List<OrgInfoBean> queryOrgInfo(Map<String, Object> map);
	int queryOrgInfoCount(Map<String, Object> map);
	void addOrgInfo(OrgInfoBean orginfobean);
	OrgInfoBean queryOrgInfoByPk(Map<String ,String> map);
	void updateOrgInfo(OrgInfoBean orginfobean);
	void updateOrgInfoState(OrgInfoBean orginfobean);
}