package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {
    FavoriteService favoriteService = new FavoriteServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String rid = request.getParameter("rid");

        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }

        boolean b = favoriteService.isFavorite(rid, uid);
        mapper.writeValue(response.getWriter(),b);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String rid = request.getParameter("rid");
        String temp = request.getParameter("temp");

        int uid;
        if (user == null) {
            mapper.writeValue(response.getWriter(),false);
        } else {
            uid = user.getUid();
            if ("false".equals(temp)) {
                favoriteService.addFavorite(rid, uid);
            } else {
                favoriteService.delFavorite(rid,uid);
            }
            mapper.writeValue(response.getWriter(), true);
        }
    }

    public void findFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");

        String currentPage = request.getParameter("currentPage");
        String rname = request.getParameter("rname");
        //rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        if (currentPage == null) {
            currentPage = "1";
        }

        PageBean<Route> all = favoriteService.findFavorite( Integer.parseInt(currentPage),user.getUid(),rname);

        mapper.writeValue(response.getWriter(),all);
    }
}
