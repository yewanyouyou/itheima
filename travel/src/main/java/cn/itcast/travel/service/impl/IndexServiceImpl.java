package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.IndexDao;
import cn.itcast.travel.dao.impl.IndexDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IndexService;

import java.util.List;

public class IndexServiceImpl implements IndexService {
    IndexDao indexDao = new IndexDaoImpl();

    @Override
    public List<Route> findHot() {
        return indexDao.findHot();
    }

    @Override
    public List<Route> findNew() {
        return indexDao.findNew();
    }
}
