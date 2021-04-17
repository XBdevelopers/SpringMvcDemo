package com.example.login.session.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.session.demo.entity.Todo;
import com.example.login.session.demo.repository.TodoRepository;

@Component
@Transactional
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private TodoRepository repository;
	
	@Override
	public List<Todo> getTodos(String userId) {
		Optional<List<Todo>> todos = repository.getallRecords(userId);
		if(todos.isPresent()) {
			return todos.get();
		}
		return null;
	}

	@Override
	public Todo insertUpdateTodo(Todo todo) {
		return repository.save(todo);
	}

	@Override
	public boolean updateTodoStatus(String userId, int todoId, boolean status) {
		try {
			repository.updateStatus(userId, todoId, status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
