package com.intuit.craft.demo.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.craft.demo.user.dao.impl.UserDAOService;
import com.intuit.craft.demo.user.dto.EnduserDTO;
import com.intuit.craft.demo.user.dto.mapper.EnduserMapperUtil;
import com.intuit.craft.demo.user.exception.FieldDetailWithFixer;
import com.intuit.craft.demo.user.exception.InvalidRequestException;

@Service
public class UserService {

	@Autowired
	private UserDAOService userDAOService;

	public List<EnduserDTO> retrieveUsersIDLessThanWithPagination(long upper, int pageNo, int pageSize)
    { 
		List<EnduserDTO> findUsersIDLessThan = userDAOService.findUsersIDLessThan(upper, pageNo, pageSize);         
        if(findUsersIDLessThan != null) {
            return findUsersIDLessThan;
        } else {
            return new ArrayList<EnduserDTO>();
        }
	}
	
	@Transactional
	public long createUser(final EnduserDTO user) {
		validateUser(user);
		return userDAOService.createUser(EnduserMapperUtil.toEntity(user));
	}
	
	private void validateUser(EnduserDTO user) {
		List<FieldDetailWithFixer> invalidFields = new ArrayList<FieldDetailWithFixer>();;
		FieldDetailWithFixer validFirstname = validStringField(user.getFirstName(), "Firstname", true, 2, 10);
		if(validFirstname != null)
			invalidFields.add(validFirstname);
		FieldDetailWithFixer validLastname = validStringField(user.getLastName(), "Lastname", true, 2, 10);
		if(validLastname != null)
			invalidFields.add(validLastname);
		if(!invalidFields.isEmpty()) {
			throw new InvalidRequestException("USER-0001", "Request is invalid.", invalidFields);
		}
	}

	private FieldDetailWithFixer validStringField(String value, String fieldName, boolean required, int min, int max) {
		if(isNull(value)) {
			if(required)
				return new FieldDetailWithFixer(fieldName, fieldName + " cannot be null.");
		}else {
			value = value.trim();
			if(min > 0 && value.length() < min)
				return new FieldDetailWithFixer(fieldName, fieldName + " should have at least " + min + " characters.");
			else if(max > 0 && value.length() > max)
				return new FieldDetailWithFixer(fieldName, fieldName + " should have at most " + max + " characters.");

		}
		return null;
	}

	private boolean isNull(String firstName) {
		return firstName ==null || firstName.trim().length() == 0;
	}

	@Transactional
	public boolean updateUser(final EnduserDTO user) {
		return userDAOService.updateUser(EnduserMapperUtil.toEntity(user));
	}
	
	@Transactional
	public boolean deleteUser(final long id) {
		return userDAOService.deleteUser(id);
	}
	
	
}
