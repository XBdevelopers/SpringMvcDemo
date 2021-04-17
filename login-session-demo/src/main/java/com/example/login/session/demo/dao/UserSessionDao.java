package com.example.login.session.demo.dao;

import org.springframework.stereotype.Component;

import com.example.login.session.demo.entity.User;

@Component
public interface UserSessionDao {

	public User getUser(String userId);
	
	public User insertUser(User user);
}
