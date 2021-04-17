package com.example.login.session.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.login.session.demo.entity.Todo;

@Service
public interface TodoService {

	public List<Todo> retrieveTodos(String user);
	public Todo addUpdateTodo(Todo todo);
	public boolean updateTodoStatus(String userId, int todoId, boolean status);
}
