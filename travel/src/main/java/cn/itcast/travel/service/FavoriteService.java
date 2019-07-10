package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface FavoriteService {

    public boolean isFavorite(String rid,int uid);

    public void addFavorite(String rid,int uid);

    public void delFavorite(String rid,int uid);

    public PageBean<Route> findFavorite(int currentPage,int uid,String rname);
}
