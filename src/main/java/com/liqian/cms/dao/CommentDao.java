package com.liqian.cms.dao;

import java.util.List;

import com.liqian.cms.domain.Comment;

public interface CommentDao {
	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	public int addComment(Comment comment);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据文章id查询文章的评论
	 * @param id  文章的编号
	 * @return
	 * @return: List<Comment>
	 */
	public List<Comment> selects(int id);

}
