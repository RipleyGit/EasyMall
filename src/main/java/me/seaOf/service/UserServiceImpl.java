package me.seaOf.service;

import me.seaOf.bean.User;
import me.seaOf.dao.UserDao;
import me.seaOf.exception.MsgException;
import me.seaOf.factory.BasicFactory;

public class UserServiceImpl implements UserService{
    private UserDao userDao = BasicFactory.getFactory().getInstance(UserDao.class);

    /**
     * 实现注册
     * @param user
     * @throws MsgException
     */
    public void registUser(User user) throws MsgException {
        //1.检查用户名是否存在
        boolean result = userDao.findUserByUsername(user.getUsername());
        if(result){//用户名已存在
            throw new MsgException("用户名已存在");
        }

        //2.实现注册(保存数据到数据库)
        userDao.addUser(user);
    }

    /**
     * 实现登陆
     * @param username 用户名
     * @param password 密码
     * @return User对象
     */
    public User loginUser(String username, String password) {
        return userDao.findUserByUsernameAndPassword(username, password);
    }

    /**
     * 根据用户名查询用户是否存在
     * @param username 用户名
     * @return true表示用户名已存在, false表示不存在
     */
    public boolean hasUser(String username) {
        return userDao.findUserByUsername(username);
    }

}

