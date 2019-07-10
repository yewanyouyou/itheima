package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoIpml;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.Md5Util;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoIpml();

    @Override
    public boolean regist(User user) {
        if (userDao.findByUserName(user.getUsername()) != null) {
            return false;
        } else {
            String uuid = UuidUtil.getUuid();
            user.setStatus("N");
            user.setCode(uuid);

            String s = Md5Util.encodeByMd5(user.getPassword());
            user.setPassword(s);
            userDao.save(user);

            String context = "<a href='http://localhost:80/travel/user/activeUser?code="+uuid+"'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),context,"激活邮件");
            return true;
        }
    }

    @Override
    public boolean active(String code) {
        User userByCode = userDao.findUserByCode(code);
        if (userByCode != null) {
            userDao.updateStatus(code);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        String s = Md5Util.encodeByMd5(user.getPassword());
        user.setPassword(s);
        return userDao.findForLogin(user);
    }
}
