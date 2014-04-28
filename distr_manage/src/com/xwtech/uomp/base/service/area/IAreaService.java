package com.xwtech.uomp.base.service.area;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.area.AreaBean;
import com.xwtech.uomp.base.pojo.area.AreaDABean;


/**
 * 
 * @author Administrator
 *
 */

public interface IAreaService {
	/**
	 * 查询地市信息
	 * @param paramMap
	 * @return
	 */
    Page queryAreaList(Map<String, String> paramMap);
    /**
     * 查询地市信息数量
     * @param param
     * @return
     */
    int queryAreaListCount(Map<String, String> param);
    /**
     * 根据pkid查询一条地市信息
     * @param pkid
     * @return
     */
    AreaBean findOneArea(String pkid);
    /**
     * 保存地市信息
     * @param areaBean
     */
    void saveArea(AreaBean areaBean);
    /**
     * 修改地市信息
     * @param areaBean
     */
    void updateArea(AreaBean areaBean);
    /**
     * 删除地市信息
     * @param id
     */
    void deleteArea(String id);
    
    Page queryAreaDAList(String bossCode);
    
    Page queryCityList();
    
    List<AreaDABean> queryAreaCityList(String bossCode);
    
    /**@param end
     * @return  List<AreaBean>
     * @author unique
     */
    List<AreaBean> findAreaList(Map<String, String> param);
    /**
     * 根据级别编码判断是否重复
     * @param map
     * @return
     */
    int isExistAreaJbNum(Map<String, String> map);
  
}
