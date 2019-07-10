package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String list = categoryService.findAll();
        response.getWriter().write(list);
    }

    public void findRoutesByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String totalPage = categoryService.findRoutesByCid(cid) + "";
        if (currentPage == null) {
            currentPage = "1";
        } else if (totalPage != null) {
            if (Integer.parseInt(currentPage) < 1) {
                currentPage = "1";
            } else if (Integer.parseInt(currentPage) > Integer.parseInt(totalPage)) {
                currentPage = totalPage;
            }
        }
        String pageBean = categoryService.findRoutesByPage(cid,currentPage);
        response.getWriter().write(pageBean);
    }

}
