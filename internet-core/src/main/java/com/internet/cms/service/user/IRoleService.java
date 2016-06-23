package com.internet.cms.service.user;

import java.util.List;

import com.internet.cms.model.Role;
import com.internet.cms.model.UserRole;

public interface IRoleService {

	/**
	 * 根据用户角色对应关系获取登陆用户角色列表信息
	 * @param urs 当前登陆用户的用户角色列表信息
	 * @return
	 */
	List<Role> listRole(List<UserRole> urs);

}
