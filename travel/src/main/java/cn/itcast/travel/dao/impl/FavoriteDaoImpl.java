package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findOne(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), rid, uid);
        } catch (Exception e) {
        }

        return user;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql, rid, new Date(), uid);

        String sql2 = "update tab_route set count=count+1 where rid=?";
        template.update(sql2,rid);
    }

    @Override
    public void delFavorite(int rid, int uid) {
        String sql = "DELETE FROM tab_favorite where rid=? and uid=?";
        template.update(sql, rid, uid);

        String sql2 = "update tab_route set count=count-1 where rid=?";
        template.update(sql2,rid);
    }

    @Override
    public List<Route> findRouteByUid(int uid,String rname,int currentPage) {
        String sql = "select r.* from tab_route r join tab_favorite f on r.rid=f.rid where f.uid=?";
        List<Object> list = new ArrayList<>();
        list.add(uid);
        if (rname!=null && !rname.equals("") && !rname.equals("null")){
            sql += " and r.rname like ?";
            list.add("%"+rname+"%");
        }
        sql += " limit "+(currentPage-1)*12+",12";

        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), list.toArray());
        return routeList;

    }

    @Override
    public int totalCount(int uid,String rname) {
        String sql = "select count(r.rid) from tab_route r join tab_favorite f on r.rid=f.rid where f.uid=?";
        List<Object> list = new ArrayList<>();
        list.add(uid);
        if (rname!=null && !rname.equals("") && !rname.equals("null")){
            sql += " and r.rname like ?";
            list.add("%"+rname+"%");
        }

        Integer totalCount = 0;
        try {
            totalCount = template.queryForObject(sql, Integer.class,list.toArray());
        } catch (Exception e){}
        return totalCount;
    }

}
