package com.xwtech.uomp.base.dao.adv;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.adv.AdvPositionBean;

public interface AdvPositionMapper {
	int deleteByPrimaryKey(String positionNum);

	int insert(AdvPositionBean record);

	int insertSelective(AdvPositionBean record);

	int queryAdvPositionCount(Map<String, String> paramMap);

	List<AdvPositionBean> queryAdvPosition(Map<String, String> paramMap);

	AdvPositionBean findOneAdvPositionBean(String pkid);

	int updateByPrimaryKeySelective(Map<String, String> paramMap);

	int updateByPrimaryKey(AdvPositionBean record);
}