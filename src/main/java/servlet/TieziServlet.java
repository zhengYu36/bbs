package servlet;

import entity.Replytiezi;
import entity.Tiezi;
import entity.Users;
import entity.vo.TieziVo;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import service.TieziService;
import service.impl.TieziServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TieziServlet",urlPatterns = "/tieziServlet")
public class TieziServlet extends HttpServlet {
    TieziServiceImpl tieziService = new TieziServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if (method.equals("tieziShow")){
            tieziShow(request,response);
        }else if (method.equals("fatie")){
            fatie(request,response);
        }else if (method.equals("deleteTiezi")){
            deleteTiezi(request,response);
        }else if(method.equals("huitie")){
            huitie(request,response);
        }else if(method.equals("hottie")){
            hottie(request,response);
        }
    }

    public void deleteTiezi(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tieziService.deleteTiezi(id);
        response.sendRedirect(request.getContextPath()+"/admin/tieziManager.html");
    }

    public void jiajing(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tieziService.jiajing(id);
        response.sendRedirect(request.getContextPath()+"/admin/tieziManager.html");
    }

    /**
     * 发帖
     * @param request
     * @param response
     */
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

    /**
     * 显示普通帖子和热帖
     * @param request
     * @param response
     * @throws IOException
     */
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
