package com.xwtech.uomp.base.service.content.impl;

import com.xwtech.uomp.base.dao.content.ContentChannelMapper;
import com.xwtech.uomp.base.dao.content.IContentChannelDao;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.content.ContentChannelBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.content.IContentChannelService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: alabo Date: 13-10-14 Time: 下午7:10 To change
 * this template use File | Settings | File Templates.
 */
@Service("contentChannelService")
public class ContentChannelServiceImpl implements IContentChannelService {
	 protected static final Logger log = Logger.getLogger(ContentChannelServiceImpl.class);

	@Autowired
	ContentChannelMapper contentChannelMapper;
	@Autowired
	IContentChannelDao contentChannelDao ;

	/**
	 * 根据频道ID获得频道下所有可用的非链接类子频道的列表
	 * 
	 * @param chanId
	 * @return
	 */
	public List<ContentChannelBean> queryAllSubChannelList(String chanId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("chanId", chanId);
		return contentChannelMapper.queryAllSubChannelList(param);
	}

	/**
	 * 查询渠道下所有可用的非链接类频道信息
	 * 
	 * @param channelNum
	 * @return
	 */
	public List<ContentChannelBean> queryAllChannelList(String channelNum) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("channelNum", channelNum);
		return contentChannelMapper.queryAllChannelList(param);
	}

	/**
	 * 查询所有可用频道信息
	 */
	public Page queryAllChannels(Map<String, String> param) {
		List<ContentChannelBean> list = contentChannelMapper.queryAllChannels(param);
		int count=contentChannelMapper.queryAllChannelsCount(param);
		Page page = new Page();
		page.setTotalRecord(count);
		page.setRecords(list);
		return page;
	}

	public int saveChannel(ContentChannelBean contentChannelBean) {
		return contentChannelMapper.saveChannel(contentChannelBean);
		
	}

	public Page querySubchannel(String parentId) {
		List<ContentChannelBean> list = contentChannelMapper.querySubchannel(parentId);
		Page page = new Page();
		page.setRecords(list);
		return page;
	}

	public boolean sort(String[] ContentChannelPkids, String ContentChannelPkid) {
		return contentChannelDao.sort(ContentChannelPkids, ContentChannelPkid);
	}

	public ContentChannelBean queryChannelByPkid(String chanId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("chanId", chanId);
		return contentChannelMapper.queryChannelByPkid(param);
	}

	public int updateByPrimaryKey(ContentChannelBean contentChannelBean) {
		return contentChannelMapper.updateByPrimaryKey(contentChannelBean);
	}

	public List<TreeNode> querryChannelByChannal(String channelNum) {
		   List<TreeNode> treeNodeList = null;
		   try {
			   List<Map<String, Object>> listResult =contentChannelMapper.querryChannelByChannal(channelNum);

			   if (listResult == null || listResult.size() <= 0) {
	                return treeNodeList;
	            }
			   treeNodeList = new ArrayList<TreeNode>();
	            for (int i = 0; i < listResult.size(); i++) {
	                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
	                TreeNode node = DhtmlTreeUtil.getTreeNode(dataMap);
	                treeNodeList.add(node);
	            }
		   } catch (Exception e) {
			e.printStackTrace();
			 log.error(e.getMessage());
	         treeNodeList = null;
	        }
	        return treeNodeList;
	}

	

}
