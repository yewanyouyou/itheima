package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageBean<Route> findAll(String cid, int currentPage,String rname) {
        PageBean<Route> all = routeDao.findAll(cid, currentPage,rname);

        int totalCount = findTotalCount(cid,rname);
        int totalPage = (int) Math.ceil(1.0*totalCount/new PageBean<>().getPageSize());
        all.setTotalCount(totalCount);
        all.setTotalPage(totalPage);

        return all;
    }

    @Override
    public int findTotalCount(String cid,String rname) {
        int totalCount = routeDao.findTotalCount(cid,rname);
        return totalCount;
    }

    @Override
    public Route findRouteByRid(String rid) {
        Route route = routeDao.findRouteByRid(rid);
        Category category = routeDao.findCategory(route.getCid() + "");
        List<RouteImg> routeImgList = routeDao.findRouteImgList(rid);
        Seller seller = routeDao.findSeller(route.getSid() + "");

        route.setCategory(category);
        route.setRouteImgList(routeImgList);
        route.setSeller(seller);

        return route;
    }
}
