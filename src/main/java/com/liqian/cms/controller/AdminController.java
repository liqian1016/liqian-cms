package com.liqian.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.utils.DateUtil;
import com.bw.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.Article;
import com.liqian.cms.domain.Channel;
import com.liqian.cms.domain.Slide;
import com.liqian.cms.domain.User;
import com.liqian.cms.service.ArticleService;
import com.liqian.cms.service.CategoryService;
import com.liqian.cms.service.ChannelService;
import com.liqian.cms.service.SlideService;
import com.liqian.cms.service.UserService;
import com.liqian.cms.utils.CMSJsonUtil;

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
	@Autowired
	private UserService userService;
	
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
		
		//查询24小时内文章  2020-2-13 9:37:40    2020-2-12 9:37:40
		//两种方式处理  1.sql:  now()-INTERVAL 24 hour
		//2.通过工具类(Java)代码获得24小时之前的时间
		long time=24*60*60*1000;
		String createTime = DateUtil.getIntervalDate(time);
		
		//查询24小时内文章  >=createTime
		List<Article> list=articleService.select24Article(createTime);
		model.addAttribute("list", list);
		
		
		//查询热门文章
		List hotList=articleService.getHotList();
		model.addAttribute("hotList", hotList);
		
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

	
	@RequestMapping("admin/login")
	public String toLogin() {
		return "admin/login";
	}

	//后台管理中心的登录
	@ResponseBody
	@RequestMapping("login") // 智能用于get请求
	public Object login(User user, HttpSession session) {
		CMSJsonUtil cju = new CMSJsonUtil();
		// 验证非空
		boolean uname = StringUtil.isNotEmpty(user.getUsername());
		boolean upwd = StringUtil.isNotEmpty(user.getPassword());
		// 如果为空
		if (!uname || !upwd) {
			cju.setMsg("用户名密码不能为空");
			return cju;
		}

		// 登录去
		User u = userService.login(user);
		// 用户名不存在
		if (u == null) {
			cju.setMsg("用户名不存在");
			return cju;
		}
		//验证是否是管理员
		if(!u.getRole().equals("1")) {
			cju.setMsg("请输入管理员帐户");
			return cju;
		}
		
		// 验证是否被禁用
		if (u.getLocked() == 1) {
			cju.setMsg("该用户被禁用,请练习管理员");
			return cju;
		}
		// 验证密码
		//把输入的密码  加密  过后 和数据库中的已有的加密的密码比对
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		
		if (!md5Password.equals(u.getPassword())) {
			cju.setMsg("密码错误");
			return cju;
		}

		// 登录成功  会把user对象存到session作用域
		session.setAttribute("user", u);
		// 登录成功跳转到 主页
		cju.setMsg("true");
		return cju;
	}
		
	
}
