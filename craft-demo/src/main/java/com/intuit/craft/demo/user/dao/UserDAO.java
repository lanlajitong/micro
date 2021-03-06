package com.intuit.craft.demo.user.dao;

import java.util.List;

import com.intuit.craft.demo.user.dto.EnduserDTO;
import com.intuit.craft.demo.user.entity.Enduser;

public interface UserDAO {

	public List<EnduserDTO> findUsersIDLessThan(long upper, int pageNo, int pageSize);
	public long createUser(Enduser user);
	public boolean updateUser(Enduser user);
	public boolean deleteUser(long id);
}
