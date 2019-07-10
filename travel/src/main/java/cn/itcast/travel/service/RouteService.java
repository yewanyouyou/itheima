package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {

    public PageBean<Route> findAll(String cid, int currentPage,String rname);

    public int findTotalCount(String cid,String rname);

    public Route findRouteByRid(String rid);

}
