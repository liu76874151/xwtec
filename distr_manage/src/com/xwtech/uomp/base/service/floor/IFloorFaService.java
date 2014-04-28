package com.xwtech.uomp.base.service.floor;

import com.xwtech.uomp.base.pojo.floor.FloorFaBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-12
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
public interface IFloorFaService {
    List<FloorFaBean> queryFloorFaList(String channelNum);
    
    void addFloorFa(FloorFaBean floorFaBean);
    
    FloorFaBean queryFloorFaByFloorId(String floorId);
    
    void batchInsertFa(List<FloorFaBean> floorFaBeanList);
}
