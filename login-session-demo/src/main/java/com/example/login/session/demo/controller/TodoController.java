package com.example.login.session.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.login.session.demo.constant.TodoConstants;
import com.example.login.session.demo.entity.Todo;
import com.example.login.session.demo.service.TodoService;
import com.example.login.session.demo.service.UserService;

@Controller
@SessionAttributes("userId")
public class TodoController {

	@Autowired
    private TodoService service;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(@SessionAttribute("userId") String userId, ModelMap model) {
        model.put("firstName", userService.getUserName(userId));
        model.put("newTodo", new Todo());
		return "home";
	}
	
	@RequestMapping(value = "/show-todo", method = RequestMethod.GET)
	public String getTodoList(@SessionAttribute("userId") String userId, ModelMap model) {
		List<Todo> todoList = service.retrieveTodos(userId);
		
		if(todoList.isEmpty()) {
			model.addAttribute("warningMessage", TodoConstants.ERROR_MESSAGE_NO_TODO_AVAILABLE);
		} else {
			model.put("todos", todoList);
		}
        model.put("firstName", userService.getUserName(userId));
		model.put("newTodo", new Todo());
		return "home";
	}
	
    @RequestMapping(value = "/add-new-todo", method = RequestMethod.POST)
	public String createTodo(@SessionAttribute("userId") String userId, 
			@ModelAttribute(value = "newTodo") Todo newTodo,
			BindingResult result, ModelMap model) {
		newTodo.setUserId(userId);
		newTodo.setDone(false);
		newTodo = service.addUpdateTodo(newTodo);
		if(null != newTodo) {
			model.put("successMessage", TodoConstants.SUCCESS_MESSAGE_TODO_CREATED);
		} else {
			model.put("errorMessage", TodoConstants.ERROR_MESSAGE_TODO_ADD_FAILED);
		}
        model.put("firstName", userService.getUserName(userId));
		model.put("newTodo", new Todo());
		return "home";
	}
    
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@SessionAttribute("userId") String userId, 
    		ModelMap model, @RequestParam("updateTodoId") int todoId) {
    	boolean updatedTodo= service.updateTodoStatus(userId, todoId, true);
    	if(updatedTodo) {
    		model.addAttribute("updateSuccessMessage", TodoConstants.SUCCESS_MESSAGE_TODO_COMPLETED);
    	} else {
    		model.addAttribute("updateErrorMessage", TodoConstants.ERROR_MESSAGE_TODO_UPDATE_FAILED);
    	}
        model.put("firstName", userService.getUserName(userId));
		model.put("newTodo", new Todo());
    	return "home";
    }
}
