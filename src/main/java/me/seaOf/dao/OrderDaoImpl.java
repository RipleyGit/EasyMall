package me.seaOf.dao;

import me.seaOf.bean.Order;
import me.seaOf.bean.OrderItem;
import me.seaOf.utils.DbUtils.BeanListHandler;
import me.seaOf.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    public void addOrder(Order order) {
        String sql = "insert into orders values(?,?,?,?,null,?)";
        try {
            JDBCUtils.update(sql, order.getId(), order.getMoney(),
                    order.getReceiverinfo(), order.getPaystate(),
                    order.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into orderitem values(?,?,?)";
        try {
            JDBCUtils.update(sql, orderItem.getOrder_id(),
                    orderItem.getProduct_id(),
                    orderItem.getBuynum());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public List<Order> findOrderByUserId(int userId) {
        String sql = "select * from orders where user_id=?";
        try {
            return JDBCUtils.query(sql, new BeanListHandler<Order>(Order.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<OrderItem> findOrderItemByOrderId(String orderId) {
        try {
            String sql = "select * from orderitem where order_id=?";
            return JDBCUtils.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

