package com.internet.cms.service.user;

import java.util.List;

import com.internet.cms.model.User;
import com.internet.cms.model.UserRole;

public interface IUserService {

	/// 登陆
	User login(String username, String password);

	List<UserRole> listUserRoles(int id);

}
