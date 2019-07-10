package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        User user = favoriteDao.findOne(Integer.parseInt(rid),uid);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.addFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public void delFavorite(String rid, int uid) {
        favoriteDao.delFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public PageBean<Route> findFavorite(int currentPage,int uid,String rname) {
        PageBean<Route> pageBean = new PageBean<>();

        int count = favoriteDao.totalCount(uid,rname);
        int totalPage = (int) Math.ceil(1.0*count/12);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalCount(count);
        pageBean.setCurrentPage(currentPage);

        List<Route> routeList = favoriteDao.findRouteByUid(uid, rname, currentPage);
        pageBean.setList(routeList);

        return pageBean;
    }


}
