package me.seaOf.dao;

import junit.framework.TestCase;
import me.seaOf.bean.Order;
import me.seaOf.factory.BasicFactory;
import me.seaOf.utils.DbUtils.BeanHandler;
import me.seaOf.utils.JDBCUtils;
import org.junit.Test;

import java.sql.SQLException;

public class OrderDaoImplTest extends TestCase {
    @Test
    public void testDao(){
        String sql = "select * from orders where id = ?";
        try {
           Order order = JDBCUtils.query(sql,new BeanHandler<Order>(Order.class),"39cad0ff-4d0e-41ca-ac7d-be18f644c89d");
            System.out.println(order.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}