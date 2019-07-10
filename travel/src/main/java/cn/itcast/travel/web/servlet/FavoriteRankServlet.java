package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteRankService;
import cn.itcast.travel.service.impl.FavoriteRankServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rank/*")
public class FavoriteRankServlet extends BaseServlet {
    FavoriteRankService service = new FavoriteRankServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    public void findRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String rname = request.getParameter("rname");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (minPrice == null || "".equals(minPrice)) {
            minPrice = "-1";
        }
        if (maxPrice == null || "".equals(maxPrice)) {
            maxPrice = "-1";
        }

        PageBean<Route> all = service.findRank( currentPage,rname,minPrice,maxPrice);

        mapper.writeValue(response.getWriter(),all);
    }

    public void findThree(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String cid = request.getParameter("cid");
        List<Route> three = service.findThree(cid);
        mapper.writeValue(response.getWriter(),three);
    }


}
