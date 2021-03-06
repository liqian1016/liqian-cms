package com.liqian.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liqian.cms.domain.Article;
import com.liqian.cms.domain.Category;
import com.liqian.cms.domain.Channel;

public interface ArticleDao {
	
	public List<Article> selectsByAdmin(Article article);
	
	public int update(Article article);

	public Article select(int id);
	//查询所有的栏目
	public List<Channel> selectsCannel();
	//根据栏目id查询此栏目下的所有分类
	public List<Category> selectsCategory(int id);
	//添加
	public void add(Article article);
	//查询24小时内文章
	public List<Article> select24Article(@Param("createTime")String createTime);
	//查询热门文章
	public List getHotList();

}
