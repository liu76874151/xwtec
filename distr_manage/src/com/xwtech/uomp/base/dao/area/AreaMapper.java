package com.xwtech.uomp.base.dao.area;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.area.AreaBean;




public interface AreaMapper { 
	List<AreaBean> queryAreaList(Map<String, String> map);
	
	List<AreaBean> queryCityList();
	
   	AreaBean findOneAreaBean(String pkid);
   	
    int queryAreaListCount(Map<String, String> map);
    
    int isExistAreaJbNum(Map<String, String> map);
    
    void saveArea(AreaBean areaBean);
    
    void deleteArea(String pkid);
    
    void updateArea(AreaBean areaBean);

    /**
     * @author unique
     */
    List<AreaBean> findAreaList(Map<String, String> param);
    
}