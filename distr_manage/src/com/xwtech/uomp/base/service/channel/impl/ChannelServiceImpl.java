/**
 * Title: ChannelServiceimpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-8 
 * @ time 下午5:21:22
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.channel.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.channel.ChannelMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.channel.ChannelBean;
import com.xwtech.uomp.base.pojo.market.ReservationMarketSecondBean;
import com.xwtech.uomp.base.service.channel.IChannelService;

/**
 * @author zhanglu
 *
 */
@Service("channelService")
public class ChannelServiceImpl implements IChannelService{

	@Autowired
	ChannelMapper channelMapper;
	
	public Page queryChannel(Map<String, String> paramMap){
		List<ChannelBean> list = channelMapper.queryChannel(paramMap);
        int count = channelMapper.queryChannelCount(paramMap);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
	}
	
	public void addChannel(ChannelBean channelBean){
		channelMapper.addChannel(channelBean);
	}
	
	public ChannelBean queryChannelByNum(Map<String, String> paramMap){
		return channelMapper.queryChannelByNum(paramMap);
	}
	
	public void updateChannel(ChannelBean channelBean){
		channelMapper.updateChannel(channelBean);
	}
	
	public void deleteChannel(List<String> channelNums){
		channelMapper.deleteChannel(channelNums);
	}
}
