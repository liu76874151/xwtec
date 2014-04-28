package com.xwtech.uomp.base.dao.adv;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.adv.AdvInfoBean;



/**
 *@ClassName:AdvInfoMapper.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-13 time：下午04:18:48
 *@version 1.0
 */
public interface AdvInfoMapper {
	/**
	 * 查询广告信息
	 * @param map
	 * @return
	 */
	List<AdvInfoBean> queryAdvInfoList(Map<String, String> map);
	List<AdvInfoBean> queryAdvInfoListOrderShowXh(Map<String, String> map);
	/**
	 * 查询广告信息数量 
	 * @param map
	 * @return
	 */
	 int queryAdvInfoCount(Map<String, String> map);
	 /**
	  * 查找一条记录
	  * @param map
	  * @return
	  */
	 AdvInfoBean findOneAdvInfoBean(Map<String, String> map);
	 /**
	  *保存
	  */
	 int saveAdvInfo(AdvInfoBean advInfoBean);
	/**
	 * 修改
	 */
	 int updateAdvInfo(AdvInfoBean advInfoBean);
	 int updateAdvInfoUserState(AdvInfoBean advInfoBean);
	 /**
	  * 删除
	  */
     int deleteAdvInfo(String advNum);
     /**
      * 审核广告信息
      * @param map
      * @return
      */
     int verifyAdvInfo(Map<String, String> map);
}