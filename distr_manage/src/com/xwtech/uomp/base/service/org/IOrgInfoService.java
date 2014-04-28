package com.xwtech.uomp.base.service.org;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.org.OrgInfoBean;

/**
 * 营业厅
 * @author zhangel
 *
 */
public interface IOrgInfoService {
	List<OrgInfoBean> queryOrgInfo(Map<String, Object> map);
	Page queryOrgInfoByPage(Map<String, Object> map);
	void addOrgInfo(OrgInfoBean orginfobean);
	OrgInfoBean queryOrgInfoByPk(Map<String ,String> map);
	void updateOrgInfo(OrgInfoBean orginfobean);
	void updateOrgInfoState(OrgInfoBean orginfobean);
}
