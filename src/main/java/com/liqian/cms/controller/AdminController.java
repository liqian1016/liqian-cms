package com.liqian.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.Article;
import com.liqian.cms.domain.Channel;
import com.liqian.cms.domain.Slide;
import com.liqian.cms.domain.User;
import com.liqian.cms.service.ArticleService;
import com.liqian.cms.service.CategoryService;
import com.liqian.cms.service.ChannelService;
import com.liqian.cms.service.SlideService;

@Controller
public class AdminController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private CategoryService categoryService;
	
	//首页的入口
	@RequestMapping("index")
	public String index(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "5")Integer pageSize) {
		//查询所有的栏目
		List<Channel> channelList=channelService.selects();
		model.addAttribute("channelList", channelList);
		
		//如果article中的channelId有值  点击栏目  查询此栏目下的文章
		if(article.getChannelId()!=null) {
			//先查询此栏目下的分类
			List  cates=categoryService.selects(article.getChannelId());
			model.addAttribute("cates", cates);
			//查询此栏目或者此分类下的文章
			PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
			model.addAttribute("info", info);
			//栏目有值
		}else {
			//如果栏目id==null  第一次进入  显示  轮播图和热门文章
			//查询所有的广告   作为轮播图
			List<Slide> slideList = slideService.selects();
			model.addAttribute("slideList", slideList);
			//查询所有的热门文章
			article.setHot(1);
			PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
			model.addAttribute("articleList", info.getList());
		}
		model.addAttribute("article", article);

		
		//显示最新文章
		PageInfo<Article> info2 = articleService.selectsByAdmin(article, pageNum, 10);
		model.addAttribute("newArcitles", info2.getList());

		
		return "index/index";
	}
	
	
	//后台管理系统的入口
	@RequestMapping("admin")
	public String admin() {
		return "admin/index";
	}
	
	
	//个人中心的入口
	@RequestMapping("my")
	public String my(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize,Article article,HttpSession session) {
			//从session中获得登录的用户
			User user = (User) session.getAttribute("user");
			if(user!=null) {
				article.setUserId(user.getId());
			}
			
			System.out.println("article==="+article);
			//个人的文章  做完登录之后 再处理
			
			PageInfo<Article> info = articleService.selectsByAdmin(article,pageNum,pageSize);
			model.addAttribute("list", info.getList());
			model.addAttribute("article", new Article());
			model.addAttribute("info", info);
		
		
		return "my/index";
	}
}
