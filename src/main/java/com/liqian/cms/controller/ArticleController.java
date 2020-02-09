package com.liqian.cms.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.Article;
import com.liqian.cms.domain.Category;
import com.liqian.cms.domain.Channel;
import com.liqian.cms.domain.User;
import com.liqian.cms.service.ArticleService;

@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	//个人查看文章
	@RequestMapping("selectArticle")
	public String selectArticle(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize,Article article,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null!=user) {
			article.setUserId(user.getId());
		}
		
		//个人的文章  做完登录之后 再处理
		
		PageInfo<Article> info = service.selectsByAdmin(article,pageNum,pageSize);
		model.addAttribute("list", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("info", info);
		return "my/article";
	}
	
	

	// 管理员查询文章的方法
	@RequestMapping("selectsByAdmin")
	public String gerArticleList(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize,Article article) {
		
		
		PageInfo<Article> info = service.selectsByAdmin(article,pageNum,pageSize);
		model.addAttribute("list", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("info", info);
		return "admin/article";
	}

	@ResponseBody
	@RequestMapping("updateArcitle")
	public boolean update(Article article) {
		return service.update(article);
	}
	
	@ResponseBody
	@RequestMapping("select")
	public Object select(int id) {
		Article article=service.select(id);
		return article;
	}
	
	@RequestMapping("toAdd")
	public Object toAdd() {
		return "my/publish";
	}
	
	//查询所有的栏目
	@ResponseBody
	@RequestMapping("selectsChannel")
	public Object selectsChannel() {
		List<Channel> list=service.selectsChannel();
		return list;
	}
	//根据栏目id查询此栏目下的所有分类
	@ResponseBody
	@RequestMapping("selectsCategory")
	public Object selectsCategory(int id) {
		List<Category> list=service.selectsCategory(id);
		return list;
	}
	@ResponseBody
	@RequestMapping("add")
	public boolean add(Article article,@RequestParam("file")MultipartFile file,HttpSession session) {
		//从session中获得登录的用户信息
		User user = (User) session.getAttribute("user");
		if(null!=user) {
			article.setUserId(user.getId());
		}
		
		try {
			if(file.getSize()>0) {
				//上传图片
				String path="e:/pic/";
				//获得上传的文件的名称
				String oleFileName = file.getOriginalFilename();
				//获得后缀
				String hz = oleFileName.substring(oleFileName.lastIndexOf("."));
				//获得新的文件的名称
				String newFileName=UUID.randomUUID().toString()+hz;
				//创建上传的文件
				File fiel2=new File(path+newFileName);
				file.transferTo(fiel2);
				article.setPicture(newFileName);
			}
			
			
			//添加
			service.add(article);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
