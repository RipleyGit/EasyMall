package me.seaOf.dao;

import me.seaOf.bean.User;
import me.seaOf.utils.DbUtils.BeanHandler;
import me.seaOf.utils.JDBCUtils;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    public boolean findUserByUsername(String username) {
        try {
            User user = JDBCUtils.query("select * from user where username=?", new BeanHandler<User>(User.class), username);
            boolean result = user != null;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void addUser(User user) {
        try {
            JDBCUtils.update("insert into user values(null, ?,?,?,?)",
                    user.getUsername(),
                    user.getPassword(),
                    user.getNickname(),
                    user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            User user = JDBCUtils.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class), username, password);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
