package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteRankDao;
import cn.itcast.travel.dao.impl.FavoriteRankDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteRankService;

import java.util.List;

public class FavoriteRankServiceImpl implements FavoriteRankService {
    FavoriteRankDao dao = new FavoriteRankDaoImpl();

    @Override
    public PageBean<Route> findRank(String currentPageStr, String rname, String minPriceStr, String maxPriceStr) {
        int currentPage = Integer.parseInt(currentPageStr);
        int minPrice = Integer.parseInt(minPriceStr);
        int maxPrice = Integer.parseInt(maxPriceStr);

        PageBean<Route> pageBean = new PageBean<>();

        int count = dao.totalCount(rname,minPrice,maxPrice);
        int totalPage = (int) Math.ceil(1.0*count/8);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalCount(count);
        pageBean.setCurrentPage(currentPage);

        List<Route> list = dao.findRank(currentPage, rname, minPrice, maxPrice);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public List<Route> findThree(String cid) {
        return dao.findRank(Integer.parseInt(cid));
    }
}
