package com.liqian.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.Article;
import com.liqian.cms.domain.Category;
import com.liqian.cms.domain.Channel;

public interface ArticleService {
	public PageInfo<Article> selectsByAdmin(Article article, Integer pageNum, Integer pageSize);

	public boolean update(Article article);

	public Article select(int id);
	//查询所有的栏目
	public List<Channel> selectsChannel();
	//根据栏目id查询此栏目下的所有分类
	public List<Category> selectsCategory(int id);
	//添加
	public void add(Article article);
	//查询24小时内文章  >=createTime
	public List<Article> select24Article(String createTime);
	//查询热门文章
	public List getHotList();


}
