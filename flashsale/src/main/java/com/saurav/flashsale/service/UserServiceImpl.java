package com.saurav.flashsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saurav.flashsale.entity.User;
import com.saurav.flashsale.entity.request_response.UserRequest;
import com.saurav.flashsale.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User add(UserRequest userRequest) {
		User user = new User();
		user.setAddress(userRequest.getAddress());
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setPhone(userRequest.getPhone());
		user = userRepository.save(user);
		return user;
	}
	

}
