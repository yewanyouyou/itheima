package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.IndexDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IndexService;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class IndexDaoImpl implements IndexDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Route> findHot() {
        String sql = "select * from tab_route order by count desc limit 0,4";
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<>(Route.class));
        return list;
    }

    @Override
    public List<Route> findNew() {
        String sql = "select * from tab_route order by rdate desc limit 0,4";
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<>(Route.class));
        return list;
    }
}
