package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteRankService {

    public PageBean<Route> findRank(String currentPage,String rname,String minPrice,String maxPrice);

    public List<Route> findThree(String cid);
}
