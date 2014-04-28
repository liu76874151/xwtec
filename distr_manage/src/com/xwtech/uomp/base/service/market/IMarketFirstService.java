package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.MarketUnityRecodeBean;

public interface IMarketFirstService {
	/**
	 *查询一组统一营销案 
	 */
	Page queryUnityMarketFirstList(Map<String, String> map);
	/**
	 * 查询一级营销案信息
	 * @param param
	 * @return
	 */
    Page queryMarketFirstList(Map<String, Object> param);
    /**
     * 查询 一集营销案的名字和id按展示顺序
     * @param city
     * @return
     */
    
    Page queryViewNameOrderByMarketOrder(String city);
    Page queryViewNameOrderByZTMarketOrder(String city);
    /**
     * 查询一级营销案信息数量
     * @param param
     * @return
     */
    int queryMarketFirstListCount(Map<String, Object> param);
    /**
     * 保存一级营销案
     * @param marketFirstBean
     * @return
     */
    int saveMarketFirst(MarketFirstBean marketFirstBean);
    /**
     * 查询MarketFirst的Sequence 
     * @return
     */
    String queryMarketFirstSequence();
    /**
     * 更新营销案展示顺序(MARKET_ORDER)
     * @param marketFirstPkids
     * @param marketFirstPkid
     * @return
     */
    boolean sort(String[] marketFirstPkids,String marketFirstPkid);
    boolean sortZT(String[] marketFirstPkids,String marketFirstPkid);
    /**
     * 查询一级营销案下有效的二级营销案 
     * @param marketFirstPkid
     * @return
     */
    int queryMarketSencodCount(String marketFirstPkid);
    /**
     * 删除一条一级营销案
     * @param marketFirstPkids
     */
    void deleteMarketFirst(Map<String, Object> marketFirstPkids);
    /**
     * 根据pkid查询一条有效记录 
     * @param marketFirstPkid
     * @return
     */
    MarketFirstBean queryByPrimaryKey(String marketFirstPkid); 
	/**
	 * 修改一级营销案 
	 * @param marketFirstBean
	 * @return
	 */
    int updateByPrimaryKey(Map<String, String> map);
	/**
	 * 判断一级营销案名称是否存在 
	 * @param viewName
	 * @return
	 */
    int isExistViewName( Map<String, Object> param);
    /**
     * 保存统一营销案记录 
     * @param marketUnityRecodeBean
     */
    void saveUnityRecode(MarketUnityRecodeBean marketUnityRecodeBean);
    
    /**
     * 查询审核列表
     * 创建日期：2013-12-15下午8:08:41
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:List<MarketFirstBean>
     */
    Page queryMarketForAudit(Map<String, String> param);
    
    void updateAuditState(MarketFirstBean marketFirstBean);
    
    Page queryMarketForTest(Map<String, String> param);
    
    void updateTestState(MarketFirstBean marketFirstBean);
	
	/**
     * 修改一级营销案时将关联的二级营销案的渠道一起修改
     * @param map
     */
    void updateSecondMarketChannel(Map<String, String> map);
    
    int queryCountHaveSecond(Map<String, Object> param);
    
    List<MarketFirstBean> queryMarketAuditOnConsole(Map<String, String> param);
}
