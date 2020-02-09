package com.liqian.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqian.cms.dao.CategoryDao;
import com.liqian.cms.domain.Category;
import com.liqian.cms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao dao;

	@Override
	public List<Category> selects(int channelId) {
		// TODO Auto-generated method stub
		return dao.selects(channelId);
	}
	
}
