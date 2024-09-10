package com.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyController {
	
	public String sayHello()
	{
		return "index";
	}
}
