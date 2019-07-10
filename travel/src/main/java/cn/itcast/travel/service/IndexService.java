package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface IndexService {

    public List<Route> findHot();

    public List<Route> findNew();
}
