package com.intuit.craft.demo.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDAOService userDAOService;
	
	public List<Enduser> retrieveUsersIDLessThanWithPagination(long upper, int pageNo, int pageSize)
    { 
		List<Enduser> findUsersIDLessThan = userDAOService.findUsersIDLessThan(upper, pageNo, pageSize);         
        if(findUsersIDLessThan != null) {
            return findUsersIDLessThan;
        } else {
            return new ArrayList<Enduser>();
        }
	}
	
	public boolean createUser(final Enduser user) {
		return userDAOService.createUser(user);
	}
	
	public boolean updateUser(final Enduser user) {
		return userDAOService.updateUser(user);
	}
	
	public boolean deleteUser(final long id) {
		return userDAOService.deleteUser(id);
	}
	
	
}
