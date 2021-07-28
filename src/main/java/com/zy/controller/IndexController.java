package com.zy.controller;

import com.zy.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

	public static final String SESSION_LOGIN_USER = "session_login_user";

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView md = new ModelAndView("index");
		HttpSession session = request.getSession();
		List<Users> users = (List<Users>) session.getAttribute("userInfo");
		if(users != null && users.size() > 0){
			Users users1 = users.get(0);
			md.addObject("uid",users1.getUid());
		}
		return md;
	}

	@RequestMapping("/logout")
	public String lgout(HttpServletRequest request){

		//shiro 登出，这样就无法访问没有授权的借口
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		try {
			//设置 http的session失效
			request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		//并注销session,这样前端就不能登录获取相关信息
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
