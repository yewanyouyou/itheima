package cn.itcast.travel.dao;

import cn.itcast.travel.domain.*;

import java.util.List;

public interface RouteDao {

    public PageBean<Route> findAll(String cid,int currentPage,String rname);

    public Seller findSeller(String sid);

    public List<RouteImg> findRouteImgList(String rid);

    public Category findCategory(String cid);

    public int findTotalCount(String cid,String rname);

    public Route findRouteByRid(String rid);
}
