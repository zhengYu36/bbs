package com.zy.servlet;


import com.zy.entity.Users;
import com.zy.service.impl.LoginServiceImpl;
import org.apache.shiro.SecurityUtils;
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
    protected String loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String username = request.getParameter("userName");
        String password = request.getParameter("pwd");
        Users users = new Users();
        users.setUname(username);
        users.setUpwd(password);

        boolean rememberMe = false;

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

        Subject subject = SecurityUtils.getSubject();

        subject.login(token);

        List<Users> list = loginService.login(users);
        if (list != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", list);
            int trank = Integer.parseInt(list.get(0).getUtype());
            if (trank == 0) {
                return "index";
                //response.getWriter().print(0);
            } else {
                return "admin/index";
                //response.getWriter().print(1);
            }
        } else {
            response.getWriter().print("no");
        }

        return "login";
    }


}