/**
 * Title: IChannelService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-8 
 * @ time 下午5:23:09
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.channel;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.channel.ChannelBean;

/**
 * @author zhanglu
 *
 */
public interface IChannelService {

	Page queryChannel(Map<String, String> paramMap);
	
	void addChannel(ChannelBean channelBean);
	
	ChannelBean queryChannelByNum(Map<String, String> paramMap);
	
	void updateChannel(ChannelBean channelBean);
	
	void deleteChannel(List<String> channelNums);
}
