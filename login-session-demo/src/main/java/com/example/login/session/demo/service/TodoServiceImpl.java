package com.example.login.session.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.login.session.demo.dao.TodoDao;
import com.example.login.session.demo.entity.Todo;

@Component
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoDao todoDao;
	
	@Override
	public List<Todo> retrieveTodos(String userId) {
		return todoDao.getTodos(userId);
	}

	@Override
	public Todo addUpdateTodo(Todo todo) {
		return todoDao.insertUpdateTodo(todo);
	}

	@Override
	public boolean updateTodoStatus(String userId, int todoId, boolean status) {
		return todoDao.updateTodoStatus(userId, todoId, status);
	}

}
