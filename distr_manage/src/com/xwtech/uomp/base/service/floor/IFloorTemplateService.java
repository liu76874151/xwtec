/**
 * Title: IFloorTemplateService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-6 
 * @ time 下午3:25:43
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.floor.FloorTemplateBean;

/**
 * @author zhanglu
 *
 */
public interface IFloorTemplateService {

	/**
	 * 根据条件查询楼层模版
	 * 创建日期：2013-11-6下午3:26:31
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:Page
	 */
	Page queryFloorTemplateList(Map<String, String> paramMap);
	
	/**
	 * 根据渠道查询可用模版
	 * 创建日期：2013-11-11上午11:17:44
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorTemplateBean>
	 */
	List<FloorTemplateBean> queryFloorTemplateAll(Map<String,String> paramMap);
	
	/**
	 * 根据主键查询模版信息
	 * 创建日期：2013-11-8下午3:33:02
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorTemplateBean
	 */
	FloorTemplateBean queryFloorTemplateByPk(Map<String,String> paramMap);
	
	/**
	 * 更新启用状态
	 * 创建日期：2013-11-7上午10:09:06
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateUseableState(Map<String,String> paramMap);
	
	/**
	 * 修改模版信息
	 * 创建日期：2013-11-8下午4:05:57
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateFloorTemplate(FloorTemplateBean floorTemplateBean);
	
	/**
	 * 删除模版信息
	 * 创建日期：2013-11-11上午10:37:58
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteFloorTemplate(FloorTemplateBean floorTemplateBean);
	
	/**
	 * 添加模版
	 * 创建日期：2013-11-8下午1:57:19
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorTemplate(FloorTemplateBean floorTemplateBean);
	
}
