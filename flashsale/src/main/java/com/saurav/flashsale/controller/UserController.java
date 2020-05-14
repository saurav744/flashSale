package com.saurav.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.flashsale.entity.User;
import com.saurav.flashsale.entity.request_response.UserRequest;
import com.saurav.flashsale.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "User APIs")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Create a user")
	@PostMapping("/user")
	public ResponseEntity<User> createUser(
			@ApiParam(value = "user request", required = true) @RequestBody UserRequest userRequest) {
		
		User user = userService.add(userRequest);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);	
	}

}
