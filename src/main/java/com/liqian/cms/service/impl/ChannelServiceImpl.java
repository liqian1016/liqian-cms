package com.liqian.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqian.cms.dao.ChannelDao;
import com.liqian.cms.domain.Channel;
import com.liqian.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{

	@Autowired
	private ChannelDao dao;
	
	public List<Channel> selects(){
		return dao.selects();
	}
}
