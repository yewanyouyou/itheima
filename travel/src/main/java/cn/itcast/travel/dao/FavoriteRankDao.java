package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteRankDao {

    public int totalCount(String rname,int mixPrice,int maxPrice);

    public List<Route> findRank(int currentPage, String rname, int minPrice, int maxPrice);

    public List<Route> findRank(int cid);
}
