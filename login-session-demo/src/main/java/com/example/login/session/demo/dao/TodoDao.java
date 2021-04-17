package com.example.login.session.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.login.session.demo.entity.Todo;

@Component
public interface TodoDao {

	public List<Todo> getTodos(String userId);
	public Todo insertUpdateTodo(Todo todo);
	public boolean updateTodoStatus(String userId, int todoId, boolean status);
}
