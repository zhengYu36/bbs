package com.zy.controller;

import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import com.zy.entity.Users;
import com.zy.entity.vo.TieziVo;
import com.zy.service.impl.TieziServiceImpl;
import com.zy.untils.PageInfoUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tieziServlet")
public class TieziController {

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
    public ModelAndView fatie(HttpServletRequest request,HttpServletResponse response){

        ModelAndView md = new ModelAndView("index");
        String title = request.getParameter("title");
        String tcontent = request.getParameter("tcontent");
        int uid = 0;
        HttpSession session = request.getSession();
        List<Users> users = (List<Users>) session.getAttribute("userInfo");
        if(users != null && users.size() > 0){
            Users users1 = users.get(0);
            uid = users1.getUid();
            md.addObject("uid",uid);
        }
        Tiezi tiezi = new Tiezi();
        tiezi.setTitle(title);
        tiezi.setTcontent(tcontent);
        tiezi.setUid(uid);
        tiezi.setTdate(new Date());
        tiezi.setTnum1(0);
        tiezi.setTnum2(0);
        tiezi.setPid(0);
        tiezi.setStatus(0);
        tieziService.fatie(tiezi);

        //返回相关数据
        return md;
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

        //当前页数
        String currentPage = "1";
        if(request != null){
            currentPage = request.getParameter("currentPage");
            if(StringUtils.isEmpty(currentPage)){
                currentPage = "1";
            }
        }
        //每页设置为9条数据
        //分页数据
        PageInfoUtils pageInfo = new PageInfoUtils();
        pageInfo.setPageSize(9);
        TieziVo tieziList = tieziService.allTie(pageInfo,currentPage,null);
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
        
        //查询标题
        String title = request.getParameter("title");

        //分页数据
        PageInfoUtils pageInfo = new PageInfoUtils();
        //查询全部帖子信息(普通帖子分页，热帖不进行分页)
        TieziVo tieziList = null;
        if(StringUtils.isNotBlank(title)){
            tieziList = tieziService.allTie(pageInfo,currentPage,0,title);
        }else{
            tieziList = tieziService.allTie(pageInfo,currentPage,0);
        }
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

    //具体的帖子信息
    @RequestMapping(value = "/about/{id}")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable int id) throws IOException {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");

        modelAndView.addObject("id",id);

        HttpSession session = request.getSession();
        List<Users> users = (List<Users>) session.getAttribute("userInfo");
        if(users != null && users.size() > 0){
            Users users1 = users.get(0);
            modelAndView.addObject("uid",users1.getUid());

        }

        Tiezi tiezi = new Tiezi();
        tiezi.setTid(id);
        List<Tiezi> tieziList = tieziService.TieziSingleShow(tiezi);
        if (tieziList != null) {
            JSONArray jsonArray = JSONArray.fromObject(tieziList);
            modelAndView.addObject("data",jsonArray);

        }

        return modelAndView;
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
