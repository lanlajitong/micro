package com.intuit.craft.demo.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.intuit.craft.demo.feed.TestUtil;
import com.intuit.craft.demo.user.controller.UserController;
import com.intuit.craft.demo.user.dto.EnduserDTO;
import com.intuit.craft.demo.user.exception.CraftResponseEntityExceptionHandler;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CraftDemoApplicationControllerTests {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private CraftResponseEntityExceptionHandler exceptionHandler;
	
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserController userController;
	
	private MockMvc restMockMvc;
	
	@AfterEach
	@BeforeEach
	public void cleanupDB() {
		String SQL_DELETE_ALL = "delete from Enduser ";
		 jdbcTemplate.update(SQL_DELETE_ALL, new MapSqlParameterSource());
	}
	
	
	
	@BeforeAll 
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(userController)
            //.setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionHandler)
            .setMessageConverters(jacksonMessageConverter).build();
    }  
	


	 @Test
	 @DisplayName("testGetEnduserspagination")
	 void testGetEnduserspagination() throws IOException, Exception {
		 List<Long> creatdUsers = createUsers(10);
		 URI targetUrl= UriComponentsBuilder.fromUriString("/enduserspagination")                             
				    .queryParam("pageNo", 0)   
				    .queryParam("pageSize", 5)
				    .queryParam("upper", creatdUsers.get(creatdUsers.size()-1))
				    .build()                                                
				    .encode()                                               
				    .toUri();
		 
	  List forObject = this.restTemplate.getForObject(targetUrl, List.class);
	  assertEquals(5, forObject.size());
	 }
	 
	@Test
	 @DisplayName("/enduserspagination rest api test ")
	 void testPostEndusers() throws IOException, Exception {
		 /*
		 URI targetUrl= UriComponentsBuilder.fromUriString("/endusers")                             
				    .build()                                                
				    .encode()                                               
				    .toUri();
		 
	  EnduserDTO  userDto = new EnduserDTO();
	  userDto.setFirstName("Z");
	  userDto.setLastName("Chen");
	  userDto.setBirthday(new Date());
	  ResponseEntity<EnduserDTO> resObject = this.restTemplate.postForEntity(targetUrl, userDto , EnduserDTO.class) ;
	  assertEquals(HttpStatus.CREATED.value(), resObject.getStatusCode());
	 */
	
	  EnduserDTO  userDto = new EnduserDTO();
		userDto.setFirstName("a");
		  //userDto.setFirstName("Zacl-" + i );
		  userDto.setLastName("Chen-");
		  userDto.setBirthday(new Date());
	  
		MvcResult result = restMockMvc.perform(post("/endusers")
		            .contentType(TestUtil.APPLICATION_JSON_UTF8)
		            .content(TestUtil.convertObjectToJsonBytes(userDto)))
		            .andExpect(status().isBadRequest())
		            .andReturn();
		 String contentAsString = result.getResponse().getContentAsString();
		 JSONObject jsonObject= new JSONObject(contentAsString );
		 JSONArray jsonArray = jsonObject.getJSONArray("fields");
		 assertEquals(1, jsonArray.length());
		 assertEquals("Firstname", jsonArray.getJSONObject(0).get("fieldName"));
	}
	 
	/*// @Test
	 void deleteEnduser() {
		 URI targetUrl= UriComponentsBuilder.fromUriString("/endusers/{id}")
				 	.buildAndExpand(15)
				    //.build()                                                
				    .encode()                                               
				    .toUri();
		 
	  this.restTemplate.delete(targetUrl) ;
	  
	  //assertEquals(HttpStatus.CREATED, resObject.getStatusCode());

	 }*/
	 
	    @Test
	    @Transactional
	    public void deleteExistingEnduser() throws Exception {
	    	List<Long> createUsers = createUsers(10);
	    	assertEquals(10L, countAllUsers());
	    	
	        // Get the tweetAuthor
	        restMockMvc.perform(delete("/endusers/{id}", createUsers.get(0) )
	            .accept(TestUtil.APPLICATION_JSON_UTF8))
	            .andExpect(status().isOk());

	        assertEquals(9L, countAllUsers());
	    }
	    
	    @Test
	    @Transactional
	    public void deleteNotExistingTweetAuthor() throws Exception {
	    	List<Long> createUsers = createUsers(1);
	    	assertEquals(1L, countAllUsers());
	        restMockMvc.perform(delete("/endusers/{id}", 0 )
	            .accept(TestUtil.APPLICATION_JSON_UTF8))
	            .andExpect(status().isNotFound());
	        assertEquals(1L, countAllUsers());
	    }

	    private long countAllUsers() {
			String SQL_COUNT_ALL_USERS = "SELECT COUNT(ID) from Enduser ";
			 Long queryForObject = jdbcTemplate.queryForObject(SQL_COUNT_ALL_USERS, new MapSqlParameterSource(), Long.class) ;
			 return queryForObject;
	    }
	    private List<Long> createUsers(int numOfUsers) throws IOException, Exception {
	    	List<Long> users = new ArrayList<>();
	    	 /*URI targetUrl= UriComponentsBuilder.fromUriString("/endusers")                             
					    .build()                                                
					    .encode()                                               
					    .toUri();*/
	    	for(int i=0; i< numOfUsers; i++) {
	    		EnduserDTO  userDto = new EnduserDTO();
	    		//userDto.setFirstName("a");
	    		  userDto.setFirstName("Zacl-" + i );
	    		  userDto.setLastName("Chen-" + i);
	    		  userDto.setBirthday(new Date());
    		  
	    		MvcResult result = restMockMvc.perform(post("/endusers")
	    		            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	    		            .content(TestUtil.convertObjectToJsonBytes(userDto)))
	    		            .andExpect(status().isCreated())
	    		            .andExpect(jsonPath("$.firstName", is("Zacl-" + i)))
	    		            .andReturn();
	    		 String contentAsString = result.getResponse().getContentAsString();
	    		 JSONObject jsonObject= new JSONObject(contentAsString );
	    		 long id = jsonObject.getLong("id");
	    		 users.add(id);
	    	}
	    	return users;
	    }
}
