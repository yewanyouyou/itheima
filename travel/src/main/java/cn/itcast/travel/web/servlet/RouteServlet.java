package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    RouteService routeService = new RouteServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cidStr = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String rnameStr = request.getParameter("rname");

        rnameStr = new String(rnameStr.getBytes("iso-8859-1"),"utf-8");

        if (currentPage == null) {
            currentPage = "1";
        }

        PageBean<Route> all = routeService.findAll(cidStr, Integer.parseInt(currentPage),rnameStr);

        mapper.writeValue(response.getWriter(),all);
    }

    public void getRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");

        Route route = routeService.findRouteByRid(rid);

        mapper.writeValue(response.getWriter(),route);
    }

    public void findRouteByRid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");

        Route route = routeService.findRouteByRid(rid);

        mapper.writeValue(response.getWriter(),route);
    }


}
