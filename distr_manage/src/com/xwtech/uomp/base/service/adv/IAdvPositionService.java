package com.xwtech.uomp.base.service.adv;

import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.adv.AdvPositionBean;

public interface IAdvPositionService {
	void saveAdvPosition(AdvPositionBean areaBean);
	int queryAdvPositionListCount(Map<String, String> param);
	Page queryAdvPositionList(Map<String, String> paramMap);
	void deleteAdvPosition(String key);
	AdvPositionBean findOneAdvPositionBean(String pkid);
	int updateByPrimaryKeySelective(Map<String, String> paramMap);
}
