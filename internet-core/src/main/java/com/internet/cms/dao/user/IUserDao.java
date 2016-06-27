package com.internet.cms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.internet.cms.model.User;
import com.internet.cms.model.UserRole;

public interface IUserDao {

	/// 根据用户名获取用户对象
	User loadByUsername(@Param("username") String username);

	/// 获取用户的所有角色
	List<UserRole> listUserRoles(int userId);

}
