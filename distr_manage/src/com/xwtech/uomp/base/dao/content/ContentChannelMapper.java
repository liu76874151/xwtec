package com.xwtech.uomp.base.dao.content;

import com.xwtech.uomp.base.pojo.content.ContentChannelBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
public interface ContentChannelMapper {
    List<ContentChannelBean> queryAllSubChannelList(Map<String, String> map);

    List<ContentChannelBean> queryAllChannelList(Map<String, String> map);
    
    List<ContentChannelBean> queryAllChannels(Map<String, String> map);
    
    List<ContentChannelBean> querySubchannel(String parentId);
    
    int queryAllChannelsCount(Map<String, String> map);
    
    int saveChannel(ContentChannelBean contentChannelBean);
    
    ContentChannelBean queryChannelByPkid(Map<String, String> map);
    
    int updateByPrimaryKey(ContentChannelBean contentChannelBean);
    
    List<Map<String, Object>> querryChannelByChannal(String channelNum);
}
