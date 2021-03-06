package com.liqian.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liqian.cms.dao.CommentDao;
import com.liqian.cms.domain.Comment;
import com.liqian.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao dao;

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		return dao.addComment(comment);
	}

	@Override
	public PageInfo selects(int id,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list=dao.selects(id);
		PageInfo info=new PageInfo(list);
		return info;
	}
}
