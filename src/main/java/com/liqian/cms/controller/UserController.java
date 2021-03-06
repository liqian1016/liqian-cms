package com.liqian.cms.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.User;
import com.liqian.cms.service.UserService;
import com.liqian.cms.utils.CMSJsonUtil;
import com.liqian.cms.vo.UserVO;

@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	private UserService service;
	//注册
	@ResponseBody
	@PostMapping("reg") 
	public Object reg(UserVO user) {
		//验证
		CMSJsonUtil cju = new CMSJsonUtil();
		// 验证非空
		boolean uname = StringUtil.isNotEmpty(user.getUsername());
		boolean upwd = StringUtil.isNotEmpty(user.getPassword());
		boolean urpwd = StringUtil.isNotEmpty(user.getRePassword());
		// 如果为空
		if (!uname || !upwd||!urpwd) {
			cju.setMsg("用户名密码query密码不能为空");
			return cju;
		}
		
		//验证两次密码输入是否一致
		if(!user.getPassword().equals(user.getRePassword())) {
			cju.setMsg("两次密码输入不一致");
			return cju;
		}
		
		//用户唯一  查询此用户名数据库中是否存在
		int count=service.getCountByUserName(user.getUsername());
		if(count>0) {
			cju.setMsg("用户名存在,请重新输入");
			return cju;
		}
		
		//注册 添加
		//注册之前对密码进行md5加密  使用apach工具类进行加密
		String md5password = DigestUtils.md5Hex(user.getPassword());
		//吧把加密后的密码给user对象  覆盖之前的密码
		user.setPassword(md5password);
		
		
		service.addUser(user);
		cju.setMsg("true");
		
		return cju;
	}
	
	
	
	

	// 跳转到注册页面
	@GetMapping("reg") // 智能用于get请求
	public String toReg(HttpSession session) {
		return "index/reg";
	}

	// 注销
	@GetMapping("logout") // 智能用于get请求
	public String logout(HttpSession session) {
		// session.invalidate();//把session中所有的值都清空了
		session.removeAttribute("user");
		return "redirect:/index";
	}

	// 登录
	@ResponseBody
	@PostMapping("login") // 智能用于get请求
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
		User u = service.login(user);
		// 用户名不存在
		if (u == null) {
			cju.setMsg("用户名不存在");
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

	// 跳转到登录页面
	@GetMapping("login") // 智能用于get请求
	public String toLogin() {
		return "index/login";
	}

	@RequestMapping("selects") // get请求和post请求都可以
	public String getUserList(Model model, @RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize) {

		PageInfo<User> info = service.getUserList(username, pageNum, pageSize);
		model.addAttribute("list", info.getList());
		model.addAttribute("username", username);
		model.addAttribute("info", info);

		return "admin/user";

	}

	@ResponseBody
	@RequestMapping("update")
	public boolean updateLocked(User user) {
		int i = service.updateLocked(user);
		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}

}
