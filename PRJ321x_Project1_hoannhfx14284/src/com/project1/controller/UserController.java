package com.project1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1.entity.User;
import com.project1.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	// need to inject user service
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String listUsers(HttpServletRequest request, ModelMap modelMap) {
		List<User> theUsers = userService.getUsers();
		PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(theUsers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(4);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "list-users";
	}
	
	/*
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		
		// get users from the service
		List<User> theUsers = userService.getUsers();
	
		// add the users to the model
		theModel.addAttribute("users", theUsers);
	
		return "list-users";
	}*/
	
	
	@GetMapping("/showFormForAddUser")
	public String showFormForAddUser(Model theModel) {
		
		// create model attribute to bind form data
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		
		return "user-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser) {
		
		// save the user using service
		userService.saveUser(theUser);
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
		
		// get the user from the service
		User theUser = userService.getUser(theId);
		
		// set user as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);
		
		// send over to form
		return "user-form";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int theId) {
		
		// delete the user
		userService.deleteUser(theId);
		
		return "redirect:/user/list";
	}
	
	/*
	@GetMapping("/searchUsers")
	public String searchUsers(@RequestParam("theSearchPhoneNumber") String theSearchPhoneNumber, 
						      Model theModel) {
		
		List<User> theUsers = userService.searchUsers(theSearchPhoneNumber);
		
		theModel.addAttribute("users", theUsers);
		
		return "list-users";
	}*/
	
	@GetMapping("/searchUsers")
	public String searchUsers(@RequestParam("theSearchPhoneNumber") String theSearchPhoneNumber, 
			HttpServletRequest request, ModelMap modelMap) {
		
		List<User> theUsers = userService.searchUsers(theSearchPhoneNumber);
		
		PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(theUsers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(4);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "list-users";
	}
	
	
	@GetMapping("/lockUser")
	public String lockUser(@RequestParam("userId") int theId) {
		
		userService.lockUser(theId);
		
		return "redirect:/user/list";
		
	}
	
	@GetMapping("/unlockUser")
	public String unlockUser(@RequestParam("userId") int theId) {
		
		userService.unlockUser(theId);
		
		return "redirect:/user/list";
		
	}
}
