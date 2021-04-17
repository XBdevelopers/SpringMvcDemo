package com.example.login.session.demo.dao;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.login.session.demo.entity.User;
import com.example.login.session.demo.repository.UserRepository;

@Component
public class UserSessionDaoImpl implements UserSessionDao {

	@Autowired
	private UserRepository repository;
	
	@Override
	public User getUser(String userId) throws NoSuchElementException {
		Optional<User> fetchedUser = repository.findById(userId);
		
		return fetchedUser.get();
	}

	@Override
	public User insertUser(User user) {
		return repository.save(user);
	}

}
