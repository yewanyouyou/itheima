package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    public User findByUserName(String userName);

    public void save(User user);

    public User findUserByCode(String code);

    public void updateStatus(String code);

    public User findForLogin(User user);

}
