package com.xwtech.uomp.base.dao.floor;

import com.xwtech.uomp.base.pojo.floor.FloorFaBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-12
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public interface FloorFaMapper {
    List<FloorFaBean> queryFloorFaList(Map<String, String> map);
    
    /**
     * 保存楼层方案
     * 创建日期：2013-11-26上午9:39:22
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:void
     */
    void addFloorFa(FloorFaBean floorFaBean);
    
    /**
     * 查询可用楼层方案
     * 创建日期：2013-11-27下午2:37:29
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:FloorFaBean
     */
    FloorFaBean queryFloorFaByFloorId(String floorId);
    
    /**
     * 批量插入楼层镜像
     * 创建日期：2013-11-28下午2:10:34
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:void
     */
    void batchInsertFa(List<FloorFaBean> floorFaBeanList);
}
