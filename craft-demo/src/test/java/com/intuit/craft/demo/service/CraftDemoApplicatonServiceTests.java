package com.intuit.craft.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.intuit.craft.demo.user.domain.UserService;
import com.intuit.craft.demo.user.dto.EnduserDTO;

@SpringBootTest
public class CraftDemoApplicatonServiceTests {

	@Autowired
	private UserService userService;
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	
	@AfterEach
	public void cleanupDB() {
		String SQL_DELETE_ALL = "delete from Enduser ";
		 jdbcTemplate.update(SQL_DELETE_ALL, new MapSqlParameterSource());
	}
	@Test
	@DisplayName("Subscription message service test ")
	void testSubscriptionMessage() {
		for(int i=0; i<30; i++) {
			EnduserDTO user = new EnduserDTO("Jack-" + i, "Li-" + i, new Date() );
			userService.createUser(user );
		}
		List<EnduserDTO> retrieveUsersIDLessThanWithPagination = userService.retrieveUsersIDLessThanWithPagination(10, 0, 5);
		//assertThat(retrieveUsersIDLessThanWithPagination.size()).isEqualTo(5);
		assertEquals(5, retrieveUsersIDLessThanWithPagination.size());
	}

}
