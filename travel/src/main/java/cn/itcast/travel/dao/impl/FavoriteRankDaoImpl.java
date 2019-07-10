package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteRankDao;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRankDaoImpl implements FavoriteRankDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int totalCount(String rname, int minPrice, int maxPrice) {
        StringBuilder sql2 = new StringBuilder("select count(rid) from tab_route where 1=1");
        List<Object> arrlist = new ArrayList<>();
        if (rname!=null && !rname.equals("") && !rname.equals("null")) {
            sql2.append(" and rname like ?");
            arrlist.add("%"+rname+"%");
        }
        if (minPrice != -1) {
            sql2.append(" and price > ?");
            arrlist.add(minPrice);
        }
        if (maxPrice != -1) {
            sql2.append(" and price < ?");
            arrlist.add(maxPrice);
        }

        Integer count = 0;
        try {
            count = template.queryForObject(sql2.toString(), Integer.class, arrlist.toArray());
        } catch (Exception e) {}

        return count;
    }

    @Override
    public List<Route> findRank(int currentPage, String rname, int minPrice, int maxPrice) {
        StringBuilder sql = new StringBuilder("select * from tab_route where 1=1");
        List<Object> arrlist = new ArrayList<>();
        if (rname!=null && !rname.equals("") && !rname.equals("null")) {
            sql.append(" and rname like ?");
            arrlist.add("%"+rname+"%");
        }
        if (minPrice != -1) {
            sql.append(" and price > ?");
            arrlist.add(minPrice);
        }
        if (maxPrice != -1) {
            sql.append(" and price < ?");
            arrlist.add(maxPrice);
        }
        String limit =  " order by count desc limit "+(currentPage-1)*8+",8";
        sql.append(limit);

        List<Route> list = null;
        try {
            list = template.query(sql.toString(), new BeanPropertyRowMapper<>(Route.class), arrlist.toArray());
        } catch (Exception e) {}

        return list;
    }

    @Override
    public List<Route> findRank(int cid) {
        String sql = "select * from tab_route where cid=? order by count desc limit 0,3";
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid);
        return list;
    }
}
