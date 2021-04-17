package com.example.login.session.demo.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.login.session.demo.dao.UserSessionDao;
import com.example.login.session.demo.entity.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserSessionDao userSessionDao;
	
	@Override
	public boolean validateUser(String userid, String password) throws NoSuchElementException {
		try {
			User user = userSessionDao.getUser(userid);
			return password.equalsIgnoreCase(user.getPassword());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("User Not Registerd!");
		}
	}

	@Override
	public String getUserName(String userid) {
		return userSessionDao.getUser(userid).getUserName();
	}

	@Override
	public User registerUser(User user) {
		try {
			userSessionDao.getUser(user.getUserId());
			return null;
		} catch (NoSuchElementException e) {
			return userSessionDao.insertUser(user);
		}
	}

}
