package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface IndexDao {

    public List<Route> findHot();

    public List<Route> findNew();
}
