package com.internet.cms.dao.user;

import org.apache.ibatis.annotations.Param;

import com.internet.cms.model.User;

public interface IUserDao {

	/// 根据用户名获取用户对象
	User loadByUsername(@Param("username") String username);

}
