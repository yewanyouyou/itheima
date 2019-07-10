package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String findAll() {
        Jedis jedis = JedisUtil.getJedis();
        String routes = jedis.get("routes");

        if (routes == null) {
            try {
                List<Category> list = categoryDao.fingAll();
                routes = mapper.writeValueAsString(list);
                jedis.set("routes",routes);
                return routes;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return routes;
    }

    @Override
    public String findRoutesByPage(String cid,String currentPage) {
        PageBean<Route> routesByPage = categoryDao.findRoutesByPage(cid, Integer.parseInt(currentPage));
        List<Route> routeList = routesByPage.getList();
        for (Route route : routeList) {
            route.setCategory(categoryDao.findCategory(cid));
            route.setSeller(categoryDao.findSeller(route.getSid()+""));
            route.setRouteImgList(categoryDao.findRouteImgList(route.getRid()+""));
        }
        routesByPage.setCurrentPage(Integer.parseInt(currentPage));

        String s = null;
        try {
            s = mapper.writeValueAsString(routesByPage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public int findRoutesByCid(String cid) {
        int count = categoryDao.findRoutesByCid(cid);
        double page = Math.ceil(1.0*count/10);
        return (int)page;
    }

}
