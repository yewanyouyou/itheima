package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.util.JDBCUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> fingAll() {
        String sql = "select * from tab_category order by cid asc";
        List<Category> list = null;
        try {
            list = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        } catch (Exception e) {
        }

        return list;
    }

    @Override
    public PageBean<Route> findRoutesByPage(String cid, int currentPage) {
        String sql = "select * from tab_route where cid = ? limit " + (currentPage - 1) * 10 + ",10";
        String sql2 = "select count(rid) from tab_route where cid = ?";
        List<Route> list = null;
        Integer totalCount = null;
        try {
            list = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid);
            totalCount = template.queryForObject(sql2, Integer.class, cid);
        } catch (Exception e) {
        }

        int totalPage = (int) Math.ceil(1.0 * totalCount / 10);

        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public Category findCategory(String cid) {
        String sql = "select * from tab_category where cid = ?";
        Category category = null;
        try {
            category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
        } catch (Exception e) {
        }
        return category;
    }

    @Override
    public Seller findSeller(String sid) {
        String sql = "select * from tab_seller where sid = ?";
        Seller seller = null;
        try {
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        } catch (Exception e) {
        }
        return seller;
    }

    @Override
    public List<RouteImg> findRouteImgList(String rid) {
        String sql = "select * from tab_route_img where rid = ?";
        List<RouteImg> routeImgsList = null;
        try {
            routeImgsList = template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        } catch (Exception e) {
        }
        return routeImgsList;
    }

    @Override
    public int findRoutesByCid(String cid) {
        String sql = "select count(rid) from tab_route where cid = ?";
        Integer count = template.queryForObject(sql, Integer.class, cid);
        return count;
    }

}
