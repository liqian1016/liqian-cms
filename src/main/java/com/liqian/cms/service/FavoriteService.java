package com.liqian.cms.service;

import com.github.pagehelper.PageInfo;
import com.liqian.cms.domain.Favorite;
import com.liqian.cms.domain.User;
import com.liqian.cms.exception.CMSRuntimeException;

public interface FavoriteService {
	//执行收藏
	int addFavo (Favorite favo) throws CMSRuntimeException;
	//展示我的收藏夹
	PageInfo selects(int pageNum, int pageSize, User user);

}
