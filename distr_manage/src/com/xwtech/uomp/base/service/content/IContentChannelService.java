package com.xwtech.uomp.base.service.content;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.content.ContentChannelBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */
public interface IContentChannelService {
    List<ContentChannelBean> queryAllSubChannelList(String chanId);

    List<ContentChannelBean> queryAllChannelList(String channelNum);
    
    Page queryAllChannels(Map<String, String> param);

    int  saveChannel(ContentChannelBean contentChannelBean);
    
    int  updateByPrimaryKey(ContentChannelBean contentChannelBean);
    
    Page querySubchannel(String parentId);
    
    boolean sort(String[] ContentChannelPkids, String ContentChannelPkid) ;
    
    ContentChannelBean queryChannelByPkid(String chanId);
    
    List<TreeNode> querryChannelByChannal(String channelNum);
}
