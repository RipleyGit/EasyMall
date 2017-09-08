package me.seaOf.dao;

import junit.framework.TestCase;
import me.seaOf.bean.User;
import me.seaOf.utils.DbUtils.BeanHandler;
import me.seaOf.utils.JDBCUtils;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoImplTest extends TestCase {
    @Test
    public void testUser() {
        try {
            User user = JDBCUtils.query("select * from user where username=?", new BeanHandler<User>(User.class), "admin");
            System.out.println(user.toString());
            boolean result = user != null;
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}