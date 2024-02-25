package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.app.entity.User;
import com.app.repositories.UserRepositories;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class Mycontroller {
	
	@Autowired
	UserRepositories userRepositories;
	
	
	@GetMapping("/showUsers")
	public String showUsers(Model model) 
	{
		model.addAttribute("users",userRepositories.findAll());
		return "show_user";
	}
	
	@GetMapping("/userForm")
	public String showForm(Model m)
	{
		User user=new User();
		m.addAttribute("user",user);
		return "user-input";
	}
	
	
	@PostMapping("/saveData")
	public String saveData(@ModelAttribute User user)
	{
		
		userRepositories.save(user);
		return "User-sucess";
	}
	
	
	

}
