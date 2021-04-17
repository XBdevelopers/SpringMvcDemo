package com.example.login.session.demo.service;

import org.springframework.stereotype.Service;

import com.example.login.session.demo.entity.User;

@Service
public interface UserService {

	public boolean validateUser(String userid, String password);
	public String getUserName(String userid);
	public User registerUser(User user);
}
