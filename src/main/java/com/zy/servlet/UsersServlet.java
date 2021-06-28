package com.zy.servlet;

import com.zy.entity.Users;
import com.zy.service.impl.UsersServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usersServlet")
public class UsersServlet {

    @Autowired
    UsersServiceImpl usersService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users users = new Users();
        users.setUname(request.getParameter("uname"));
        users.setUpwd(request.getParameter("upwd"));
        users.setUemail(request.getParameter("uinfo"));
        users.setUtype(request.getParameter("power"));
        usersService.addUser(users);
        response.sendRedirect(request.getContextPath() + "/admin/userManager.jsp");
    }

    /**
     * 删除用户
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("id"));
        usersService.deleteUser(uid);
        response.sendRedirect(request.getContextPath() + "/admin/userManager.jsp");
    }

    /**
     * 更新管理员信息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users users = new Users();
        users.setUid(Integer.parseInt(request.getParameter("u_id")));
        users.setUname(request.getParameter("u_username"));
        users.setUpwd(request.getParameter("u_pwd"));
        users.setUemail(request.getParameter("u_info"));
        users.setUtype(request.getParameter("power"));
        usersService.updateUser(users);
        response.sendRedirect(request.getContextPath() + "/admin/userManager.jsp");

    }

    /**
     * 所有用户
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/usersManager", method = RequestMethod.POST)
    public void usersManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Users> usersList = usersService.usersManager();
        if (usersList != null) {
            JSONArray jsonArray = JSONArray.fromObject(usersList);
            response.getWriter().print(jsonArray);
        }
    }

    @RequestMapping(value = "/selectUesrId", method = RequestMethod.GET)
    public void selectUesrId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Users> usersList = usersService.editUser(id);
        if (usersList != null) {
            JSONArray jsonArray = JSONArray.fromObject(usersList);
            response.getWriter().print(jsonArray);
        }
    }
}
