package com.internet.cms.service.user;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.internet.cms.dao.user.IUserDao;
import com.internet.cms.model.CmsException;
import com.internet.cms.model.User;
import com.internet.cms.model.UserRole;

@Service
public class UserService implements IUserService {

	@Inject
	private IUserDao userDao;

	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.loadByUsername(username);
		if(user == null)throw new CmsException("用户名或者密码不正确");
		return user;
	}

	public List<UserRole> listUserRoles(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
