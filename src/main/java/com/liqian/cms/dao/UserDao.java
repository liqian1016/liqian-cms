package com.liqian.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liqian.cms.domain.User;
import com.liqian.cms.vo.UserVO;

public interface UserDao {
	/**
	 * 
	 * @Title: getUserList 
	 * @Description: 查询用户列表
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	public List<User> getUserList(@Param("username")String username);
	
	public int updateLocked(User user);
	//查询单个对象
	public User getOne(@Param("id")int id);
	//登录去
	public User login(User user);
	//用户唯一  查询此用户名数据库中是否存在
	public int getCountByUsername(@Param("username")String username);
	//注册 添加
	public void addUser(UserVO user);

}
