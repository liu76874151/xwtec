package com.xwtech.uomp.base.dao.area;

import java.util.List;


import com.xwtech.uomp.base.pojo.area.AreaDABean;



public interface AreaDAMapper {
	List<AreaDABean> queryAreaDAList(String bossCode);
	
	List<AreaDABean> queryAreaCityList(String bossCode);
	
    int insert(AreaDABean record);

    int insertSelective(AreaDABean record);
}