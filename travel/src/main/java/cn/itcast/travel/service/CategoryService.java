package cn.itcast.travel.service;

public interface CategoryService {

    public String findAll();

    public String findRoutesByPage(String cid,String currentPage);

    public int findRoutesByCid(String cid);

}
