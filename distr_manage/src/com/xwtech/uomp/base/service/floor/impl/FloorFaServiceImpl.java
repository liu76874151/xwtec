package com.xwtech.uomp.base.service.floor.impl;

import com.xwtech.uomp.base.dao.floor.FloorFaMapper;
import com.xwtech.uomp.base.pojo.floor.FloorFaBean;
import com.xwtech.uomp.base.service.floor.IFloorFaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-12
 * Time: 下午5:03
 * To change this template use File | Settings | File Templates.
 */
@Service("floorFaService")
public class FloorFaServiceImpl implements IFloorFaService {

    @Autowired
    FloorFaMapper floorFaMapper;

    /**
     * 根据渠道编号，查询该渠道下可用楼层货架方案中的楼层信息
     *
     * @param channelNum
     * @return
     */
    public List<FloorFaBean> queryFloorFaList(String channelNum) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("channelNum", channelNum);
        map.put("state", "0");//可用
        return floorFaMapper.queryFloorFaList(map);
    }
    
    /**
     * 保存楼层方案
     */
    public void addFloorFa(FloorFaBean floorFaBean){
    	floorFaMapper.addFloorFa(floorFaBean);
    }
    
    /**
     * 查询可用楼层方案信息
     */
    public FloorFaBean queryFloorFaByFloorId(String floorId){
    	return floorFaMapper.queryFloorFaByFloorId(floorId);
    }
    
    /**
     * 批量插入楼层镜像
     */
    public void batchInsertFa(List<FloorFaBean> floorFaBeanList){
    	floorFaMapper.batchInsertFa(floorFaBeanList);
    }
}
