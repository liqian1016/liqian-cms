package com.liqian.cms.service;

import java.util.List;

import com.liqian.cms.domain.Category;

public interface CategoryService {
	public List<Category> selects(int channelId);

}
