package cn.itcast.travel.dao.impl;


import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public PageBean<Route> findAll(String cid, int currentPage,String rname) {
        PageBean<Route> pageBean = new PageBean<>();

        //String sql2 = "select * from tab_route where cid = ? limit "+(currentPage-1)*pageBean.getPageSize()+","+pageBean.getPageSize();
        StringBuilder sql2 = new StringBuilder("select * from tab_route where 1=1");
        List<Object> arrlist = new ArrayList<>();
        if (cid!=null && !cid.equals("") && !cid.equals("null")) {
            sql2.append(" and cid = ?");
            arrlist.add(cid);
        }
        if (rname!=null && !rname.equals("") && !rname.equals("null")) {
            sql2.append(" and rname like ?");
            arrlist.add("%"+rname+"%");
        }
        String limit =  " limit "+(currentPage-1)*pageBean.getPageSize()+","+pageBean.getPageSize();
        sql2.append(limit);

        List<Route> list = null;
        try {
            list = template.query(sql2.toString(), new BeanPropertyRowMapper<>(Route.class), arrlist.toArray());
        } catch (Exception e) {}

        pageBean.setCurrentPage(currentPage);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public Seller findSeller(String sid) {
        String sql = "select * from tab_seller where sid = ?";
        Seller seller = null;
        try {
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        } catch (Exception e) {}
        return seller;
    }

    @Override
    public List<RouteImg> findRouteImgList(String rid) {
        String sql = "select * from tab_route_img where rid = ?";
        List<RouteImg> routeImgsList = null;
        try {
            routeImgsList = template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        } catch (Exception e) {}
        return routeImgsList;
    }

    @Override
    public Category findCategory(String cid) {
        String sql = "select * from tab_category where cid = ?";
        Category category = null;
        try {
            category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
        } catch (Exception e) {}
        return category;
    }

    @Override
    public int findTotalCount(String cid,String rname) {
        StringBuilder sql = new StringBuilder("select count(rid) from tab_route where 1=1");
        List<Object> arrlist = new ArrayList<>();
        if (cid!=null && !cid.equals("") && !cid.equals("null")) {
            sql.append(" and cid = ?");
            arrlist.add(cid);
        }
        if (rname!=null && !rname.equals("") && !rname.equals("null")) {
            sql.append(" and rname like ?");
            arrlist.add("%"+rname+"%");
        }

        Integer totalCount = 0;
        try {
            totalCount = template.queryForObject(sql.toString(), Integer.class, arrlist.toArray());
        } catch (Exception e) {}


        return totalCount;
    }

    @Override
    public Route findRouteByRid(String rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        return route;
    }
}
