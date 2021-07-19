package com.intuit.craft.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	/*@Autowired
	UserRepository userRepository;
	
	@GetMapping("/endusers")
	public List<Enduser> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/endusers/{name}")
	public Enduser retrieveUser(@PathVariable String name) {
		return userRepository.findUserByNameNamedParams(name);
	}*/
	
	@GetMapping("/enduserspagination")
	public List<Enduser> retrieveAllUsersWithPagination(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam long upper)  {
		return userService.retrieveUsersIDLessThanWithPagination(upper, pageNo, pageSize);
	}
	
	@PostMapping("/endusers")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Enduser user){
		if(userService.createUser(user)) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(user.getId()).toUri();
				
				return ResponseEntity.created(location).build();
		}else
			return ResponseEntity.internalServerError().build();
	}
	
	@PutMapping("/endusers/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable long id,@Valid  @RequestBody Enduser user){
		user.setId(id);
		if(userService.updateUser(user)) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(user.getId()).toUri();
				
				return ResponseEntity.created(location).build();
		}else
			return ResponseEntity.internalServerError().build();
	}
	
	@DeleteMapping("/endusers/{id}")
	public void  deleteUser(@PathVariable long id) {
		if(!userService.deleteUser(id))
			throw new UserNotFoundException("USER0001", "id-"+ id);
	}
	
}
