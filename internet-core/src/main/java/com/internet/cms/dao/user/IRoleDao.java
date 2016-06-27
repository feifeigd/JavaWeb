package com.internet.cms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.internet.cms.model.Role;

public interface IRoleDao {

	public List<Role> listRole(@Param("ids") String ids);

}
