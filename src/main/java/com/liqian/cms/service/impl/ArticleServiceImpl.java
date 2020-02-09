package com.liqian.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liqian.cms.dao.ArticleDao;
import com.liqian.cms.domain.Article;
import com.liqian.cms.domain.Category;
import com.liqian.cms.domain.Channel;
import com.liqian.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;

	@Override
	public PageInfo<Article> selectsByAdmin(Article article,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list=articleDao.selectsByAdmin(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public boolean update(Article article) {
		try {
			articleDao.update(article);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Article select(int id) {
		// TODO Auto-generated method stub
		return articleDao.select(id);
	}
	//查询所有的栏目
	@Override
	public List<Channel> selectsChannel() {
		// TODO Auto-generated method stub
		return articleDao.selectsCannel();
	}
	//根据栏目id查询此栏目下的所有分类
	@Override
	public List<Category> selectsCategory(int id) {
		// TODO Auto-generated method stub
		return articleDao.selectsCategory(id);
	}
	//添加
	@Override
	public void add(Article article) {
		articleDao.add(article);
		
	}
	
	
	
}
