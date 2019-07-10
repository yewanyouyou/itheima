package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface FavoriteDao {

    public User findOne(int rid,int uid);

    public void addFavorite(int rid,int uid);

    public void delFavorite(int rid,int uid);

    public List<Route> findRouteByUid(int uid,String rname,int currentPage);

    public int totalCount(int uid,String rname);
}
