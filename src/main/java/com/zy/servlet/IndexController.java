package com.zy.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}


	@RequestMapping("/adminMain")
	public String adminMain(){
		return "admin/main";
	}


	@RequestMapping("/userManager")
	public String userManager(){
		return "admin/userManager";
	}


	@RequestMapping("/tieziManager")
	public String tieziManager(){
		return "admin/tieziManager";
	}
}
