package com.liqian.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liqian.cms.domain.Category;

public interface CategoryDao {
	
	//查询本栏目下的所有的分类
	public List<Category> selects(@Param("channelId")int channelId);

}
