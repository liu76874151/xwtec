package com.xwtech.uomp.base.service.schedule;

import java.util.concurrent.ExecutorService;

/**
 * 
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-22 下午01:35:25
 */
public interface ISynchMarketService {

    /**
     * 同步所有营销案数据
     *   
     * @author unique
     */
    void synchMarketPlanInfo();

    void synchMarketPlanInfoByCity(String cityId);
    
    /**
     * 网厅营销案数据同步
     * @param service  
     * @author unique
     */
    void synchMarketPlanInfoW(ExecutorService service);
    
    /**
     * 掌厅营销案数据同步
     * @param service  
     * @author unique
     */
    void synchMarketPlanInfoWap(ExecutorService service);
    
    /**
     * 短厅营销案数据同步
     * @param service  
     * @author unique
     */
    void synchMarketPlanInfoSms(ExecutorService service);
}
