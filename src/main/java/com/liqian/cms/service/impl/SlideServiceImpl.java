package com.liqian.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqian.cms.dao.SlideDao;
import com.liqian.cms.domain.Slide;
import com.liqian.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService{

	@Autowired
	private SlideDao dao;

	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return dao.selects();
	}
	
	
}
