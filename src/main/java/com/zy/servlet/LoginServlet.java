package com.zy.servlet;


import com.zy.entity.Users;
import com.zy.service.impl.LoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/loginServlet")
public class LoginServlet {

    @Autowired
    LoginServiceImpl loginService;

    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("pwd");
        Users users = new Users();
        users.setUname(userName);
        users.setUpwd(password);
        users.setUquestion("0");
        users.setUanswer("0");
        users.setUemail("");
        //默认为会员
        users.setUtype("0");
        loginService.register(users);
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    protected ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");

        String username = request.getParameter("userName");
        String password = request.getParameter("pwd");
        Users users = new Users();
        users.setUname(username);
        users.setUpwd(password);

        boolean rememberMe = false;

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

            Subject subject = SecurityUtils.getSubject();

            subject.login(token);

            List<Users> list = loginService.login(users);
            if (list != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", list);

                modelAndView.addObject("uid",list.get(0).getUid());
                int trank = Integer.parseInt(list.get(0).getUtype());
                if (trank == 0) {
                    modelAndView.setViewName("index");
                    //return "index";
                    //response.getWriter().print(0);
                } else {
                    modelAndView.setViewName("admin/index");
                    //return "admin/index";
                    //response.getWriter().print(1);
                }
            } else {
                response.getWriter().print("no");
            }
        }  catch (AuthenticationException ae) {
//			ae.printStackTrace();
            modelAndView.setViewName("login");
            modelAndView.addObject("msg", "账号或密码错误");
        } catch (Exception e) {
//			e.printStackTrace();
            modelAndView.addObject("msg", "登录异常");
        }

        return modelAndView;
    }


}
