package com.intuit.craft.demo.user;

import java.util.List;

public interface UserDAO {

	public List<Enduser> findUsersIDLessThan(long upper, int pageNo, int pageSize);
	public boolean createUser(Enduser user);
	public boolean updateUser(Enduser user);
	public boolean deleteUser(long id);
}
