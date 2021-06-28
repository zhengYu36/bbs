package com.zy.servlet;

import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import com.zy.entity.Users;
import com.zy.entity.vo.TieziVo;
import com.zy.service.impl.TieziServiceImpl;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tieziServlet")
public class TieziServlet {

    @Autowired
    TieziServiceImpl tieziService;


    @RequestMapping(value = "/deleteTiezi",method = RequestMethod.POST)
    public void deleteTiezi(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tieziService.deleteTiezi(id);
        response.sendRedirect(request.getContextPath()+"/admin/tieziManager.jsp");
    }

    @RequestMapping(value = "/jiajing",method = RequestMethod.POST)
    public void jiajing(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tieziService.jiajing(id);
        response.sendRedirect(request.getContextPath()+"/admin/tieziManager.jsp");
    }

    /**
     * 发帖
     * @param request
     * @param response
     */
    @RequestMapping(value = "/fatie",method = RequestMethod.POST)
    public void fatie(HttpServletRequest request,HttpServletResponse response){
        String title = request.getParameter("title");
        String tcontent = request.getParameter("tcontent");
        int uid = 0;
        HttpSession session = request.getSession();
        List<Users> users = (List<Users>) session.getAttribute("userInfo");
        if(users != null && users.size() > 0){
            Users users1 = users.get(0);
            uid = users1.getUid();
        }

        Tiezi tiezi = new Tiezi();
        tiezi.setTitle(title);
        tiezi.setTcontent(tcontent);
        tiezi.setUid(uid);
        tieziService.fatie(tiezi);
    }

    //回帖
    @RequestMapping(value = "/huitie",method = RequestMethod.POST)
    public void huitie(HttpServletRequest request,HttpServletResponse response){
        String comment = request.getParameter("comment").trim();
        String pid = request.getParameter("pid");
        //获取用户id
        int uid = 0;
        HttpSession session = request.getSession();
        List<Users> users = (List<Users>) session.getAttribute("userInfo");
        if(users != null && users.size() > 0){
            Users users1 = users.get(0);
            uid = users1.getUid();
        }
        Replytiezi tiezi = new Replytiezi();
        tiezi.setTcontent(comment);
        tiezi.setTdate(new Date());
        tiezi.setPid(Integer.parseInt(pid));
        tiezi.setUid(uid);
        tieziService.huitie(tiezi);
    }

    @RequestMapping(value = "/tieziShowInit",method = RequestMethod.POST)
    public void tieziShowInit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Tiezi> tieziList = tieziService.tieziShow();
        if (tieziList != null) {
            JSONArray jsonArray = JSONArray.fromObject(tieziList);
            response.getWriter().print(jsonArray);
        }
    }

    /**
     * 显示普通帖子和热帖
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/tieziShow",method = RequestMethod.POST)
    public void tieziShow(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //当前页数
        String currentPage = "1";
        if(request != null){
             currentPage = request.getParameter("currentPage");
            if(StringUtils.isEmpty(currentPage)){
                currentPage = "1";
            }
        }

        TieziVo tieziList = tieziService.allTie(currentPage);
        if (tieziList != null) {
            JSONArray jsonArray = JSONArray.fromObject(tieziList);
            response.getWriter().print(jsonArray);
        }
    }

    //热帖
    @RequestMapping(value = "/hottie",method = RequestMethod.POST)
    public void hottie(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Tiezi> tieziList = tieziService.hottie();
        if (tieziList != null) {
            JSONArray jsonArray = JSONArray.fromObject(tieziList);
            response.getWriter().print(jsonArray);
        }
    }

    /**
     * 单个帖子
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/selectOne",method = RequestMethod.GET)
    public void selectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setHeader("Content-type","text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Tiezi tiezi = new Tiezi();
        tiezi.setTid(id);
        List<Tiezi> tieziList = tieziService.TieziSingleShow(tiezi);
        if (tieziList != null) {
            JSONArray jsonArray = JSONArray.fromObject(tieziList);
            response.getWriter().print(jsonArray);
        }

    }

}