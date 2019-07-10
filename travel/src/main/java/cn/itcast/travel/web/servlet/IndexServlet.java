package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IndexService;
import cn.itcast.travel.service.impl.IndexServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index/*")
public class IndexServlet extends BaseServlet {
    IndexService indexService = new IndexServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    public void findRoutes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("type");
        List<Route> list = null;
        switch (num){
            case "1":
                list = indexService.findHot();
                break;
            case "2":
                list = indexService.findNew();
                break;
            default:
                    return;
        }
        mapper.writeValue(response.getWriter(),list);


    }

    /*public void findNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> list = indexService.findNew();
        mapper.writeValue(response.getWriter(),list);
    }*/

}
