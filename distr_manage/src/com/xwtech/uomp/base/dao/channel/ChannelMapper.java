/**
 * Title: ChannelMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-8 
 * @ time 下午5:13:01
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.channel;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.channel.ChannelBean;

/**
 * @author zhanglu
 *
 */
public interface ChannelMapper {

	List<ChannelBean> queryChannel(Map<String, String> paramMap);
	
	ChannelBean queryChannelByNum(Map<String, String> paramMap);
	
	int queryChannelCount(Map<String, String> paramMap);
	
	void addChannel(ChannelBean channelBean);
	
	void updateChannel(ChannelBean channelBean);
	
	void deleteChannel(List<String> channelNums);
	
}
