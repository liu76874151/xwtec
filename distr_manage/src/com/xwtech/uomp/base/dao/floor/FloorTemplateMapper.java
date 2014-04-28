/**
 * Title: FloorTemplateMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-6 
 * @ time 下午2:59:19
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorTemplateBean;

/**
 * @author zhanglu
 *
 */
public interface FloorTemplateMapper {

	/**
	 * 根据条件查询楼层模版
	 * 创建日期：2013-11-6下午3:15:03
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorTemplateBean>
	 */
	List<FloorTemplateBean> queryFloorTemplateList(Map<String, String> paramMap);
	
	/**
	 * 查询楼层模版个数
	 * 创建日期：2013-11-6下午3:31:10
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryFloorTemplateCount(Map<String,String> paramMap);
	
	/**
	 * 根据主键查询模版
	 * 创建日期：2013-11-8下午3:32:13
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorTemplateBean
	 */
	FloorTemplateBean queryFloorTemplateByPk(Map<String,String> paramMap);
	
	/**
	 *  根据渠道查询所有可用模版
	 * 创建日期：2013-11-11上午11:11:57
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorTemplateBean
	 */
	List<FloorTemplateBean> queryFloorTemplateAll(Map<String,String> paramMap);
	
	/**
	 * 更新启用状态
	 * 创建日期：2013-11-7上午10:08:19
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateUseableState(Map<String,String> paramMap);
	
	/**
	 * 修改模版信息
	 * 创建日期：2013-11-8下午4:05:24
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateFloorTemplate(FloorTemplateBean floorTemplateBean);
	
	/**
	 * 删除模版信息
	 * 创建日期：2013-11-11上午10:37:12
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteFloorTemplate(FloorTemplateBean floorTemplateBean);
	
	/**
	 * 添加模版
	 * 创建日期：2013-11-8下午1:56:45
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorTemplate(FloorTemplateBean floorTemplateBean);
}
