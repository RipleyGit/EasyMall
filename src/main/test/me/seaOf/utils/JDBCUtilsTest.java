package me.seaOf.utils;

import junit.framework.TestCase;
import me.seaOf.bean.User;
import me.seaOf.utils.DbUtils.BeanHandler;
import org.junit.Test;

import java.sql.SQLException;


public class JDBCUtilsTest extends TestCase {

    @Test
    public void testJdbc(){
        String sql = "select * from user where 1=1 and username = 'admin'";
        System.out.printf(sql);
        try {
            User user = JDBCUtils.query(sql,new BeanHandler<User>(User.class));
            System.out.println(user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}