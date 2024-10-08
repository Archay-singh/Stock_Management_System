package com.traffsys.stock.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.stock.Service.UserService;
import com.traffsys.stock.dto.ApiResponse;
import com.traffsys.stock.entity.User;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService service;

	@Transactional
	@PostMapping("/createUser")
	public ApiResponse AddUser(@RequestBody User user) {
		return service.createUser(user);
	}

	@DeleteMapping("/{id}")
	public ApiResponse DeleteUser(@PathVariable Long id) {
		return service.deleteUser(id);
	}

	//@PostMapping("/login")
	//public ApiResponse login(@RequestParam String userName, @RequestParam String password, HttpServletResponse httpServletResponse) {
		//return service.authenticate(userName, password, httpServletResponse);

	//}
	
}
