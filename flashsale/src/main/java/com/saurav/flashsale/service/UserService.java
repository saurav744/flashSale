package com.saurav.flashsale.service;

import com.saurav.flashsale.entity.User;
import com.saurav.flashsale.entity.request_response.UserRequest;

public interface UserService {
	
	public User add(UserRequest userRequest);
	

}
