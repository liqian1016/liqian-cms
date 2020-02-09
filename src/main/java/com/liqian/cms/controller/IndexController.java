package com.liqian.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liqian.cms.domain.Article;
import com.liqian.cms.service.ArticleService;

@RequestMapping("indexs")
@Controller
public class IndexController {
	
	@Autowired
	private ArticleService service;

	
	@RequestMapping("select")
	public String select(Model model,Article article) {
		//通过文章id查询文章详情
		Article article1=service.select(article.getId());
		model.addAttribute("article", article1);
		return "index/article";
	}
}
