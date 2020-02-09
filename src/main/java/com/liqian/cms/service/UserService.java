package com.liqian.cms.service;

import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.User;
import com.liqian.cms.vo.UserVO;

public interface UserService {
	
	public PageInfo<User> getUserList(String username,Integer pageNum,Integer pageSize);
	
	public int updateLocked(User user);
	
	public User getOne(int id);
	//登录去
	public User login(User user);
	//用户唯一  查询此用户名数据库中是否存在
	public int getCountByUserName(String username);
	//注册 添加
	public void addUser(UserVO user);

}
