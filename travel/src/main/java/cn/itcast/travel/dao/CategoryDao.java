package cn.itcast.travel.dao;

import cn.itcast.travel.domain.*;

import java.util.List;

public interface CategoryDao {

    public List<Category> fingAll();

    public PageBean<Route> findRoutesByPage(String cid,int currentPage);

    public Category findCategory(String cid);

    public Seller findSeller(String sid);

    public List<RouteImg> findRouteImgList(String rid);

    public int findRoutesByCid(String cid);

}
