package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.DataBaseTimeMapper;
import com.xwtech.uomp.base.service.IDataBaseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yugonggan
 * @ClassName: DataBaseTimeServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date Mar 8, 2013 3:56:59 PM
 */
@Service("dataBseTimeService")
public class DataBaseTimeServiceImpl implements IDataBaseTimeService {

    @Autowired
    DataBaseTimeMapper dataBaseTimeMapper;

    public String queryDataBaseTime() {
        return dataBaseTimeMapper.queryDataBaseTime();
    }

}
