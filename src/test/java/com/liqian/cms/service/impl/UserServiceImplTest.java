package com.liqian.cms.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.User;
import com.liqian.cms.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class UserServiceImplTest {
	
	@Autowired
	private UserService service;

	@Test
	public void testGetUserList() {
		PageInfo<User> userList = service.getUserList(null, 1, 3);
		List<User> list = userList.getList();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	
	@Test
	public void testUpdateLocked() {
		User user=new User();
		user.setId(1);
		user.setLocked(0);
		service.updateLocked(user);
	}
	
	@Test
	public void testGetOne() {
		
		User user = service.getOne(1);
		System.out.println(user);
	}
}
