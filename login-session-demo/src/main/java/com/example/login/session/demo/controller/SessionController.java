package com.example.login.session.demo.controller;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

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
import org.springframework.web.bind.support.SessionStatus;

import com.example.login.session.demo.constant.TodoConstants;
import com.example.login.session.demo.entity.Todo;
import com.example.login.session.demo.entity.User;
import com.example.login.session.demo.service.UserService;

@Controller
@SessionAttributes("userId")
public class SessionController {
	
	private static Logger LOGGER = Logger.getLogger("SessionController");

	@Autowired
    private UserService service;

    @RequestMapping(value= {"/", "/index"}, method = RequestMethod.GET)
    public String getWebAppLandingPage(ModelMap model){
    	// Check if already a session is established
    	try {
			if(null != model.get("userId")) {
			    model.put("firstName", service.getUserName((String)model.get("userId")));
			    model.put("newTodo", new Todo());
			    return "home";
			}
		} catch (NoSuchElementException e) {
			LOGGER.info("New Session Needed!");
		}
    	model.put("newUser", new User());
        return "index";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(value = "newUser") User newUser, BindingResult result, ModelMap model) {
    	if(!result.hasErrors()) {
    		if(null != newUser && !newUser.getUserId().isEmpty() && !newUser.getUserName().isEmpty() && !newUser.getPassword().isEmpty()) {
        		newUser = service.registerUser(newUser);
        		if(null != newUser) {
        			model.put("successMessage", TodoConstants.SUCCESS_MESSAGE_REGISTRATION_DONE + newUser.getUserId());
        			model.put("newUser", new User());
        		} else {
        			model.put("errorMessage", TodoConstants.ERROR_MESSAGE_USER_EXISTS_ALREADY);
        		}
                
        	} else {
        		model.put("errorMessage", TodoConstants.ERROR_MESSAGE_REGISTRATION_FIELDS_MENDATORY);
        	}
    	} else {
    		model.put("errorMessage", result.getAllErrors());
    		model.put("newUser", newUser);
    	}
        
        return "index";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String getHomePage(ModelMap model, @RequestParam String userId, @RequestParam String password){
 
    	if(userId.isEmpty() || password.isEmpty()) {
    		model.put("errorMessage", TodoConstants.ERROR_MESSAGE_LOGIN_FIELDS_MENDATORY);
        	model.put("newUser", new User());
            return "index";
    	}
        try {
        	boolean isValidUser = service.validateUser(userId, password);

            if (!isValidUser) {
                model.put("errorMessage", TodoConstants.ERROR_MESSAGE_LOGIN_INVALID_CREDENTIALS);
            	model.put("newUser", new User());
                return "index";
            }
		} catch (NoSuchElementException e) {
			model.put("errorMessage", TodoConstants.ERROR_MESSAGE_LOGIN_NO_SUCH_USER);
	    	model.put("newUser", new User());
            return "index";
		}

        model.put("userId", userId);
        model.put("firstName", service.getUserName(userId));
        model.put("newTodo", new Todo());

        return "home";
    }
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String endSession(ModelMap model, SessionStatus session) {
    	session.setComplete();
    	model.put("newUser", new User());
        return "index";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfileInfo(@SessionAttribute("userId") String userId, ModelMap model) {
        model.put("firstName", service.getUserName(userId));
        model.put("profile", "true");
    	return "home";
    }
}
