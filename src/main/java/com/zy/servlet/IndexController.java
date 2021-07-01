package com.zy.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	public static final String SESSION_LOGIN_USER = "session_login_user";

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/logout")
	public String lgout(){


		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		//并注销session
		return "index";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping("/register")
	public String register(){
		return "register";
	}

	@RequestMapping("/contact")
	public String contact(){
		return "contact";
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
