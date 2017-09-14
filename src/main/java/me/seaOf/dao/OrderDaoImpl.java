package me.seaOf.dao;

import me.seaOf.bean.Order;
import me.seaOf.bean.OrderItem;
import me.seaOf.bean.SaleInfo;
import me.seaOf.utils.DbUtils.BeanHandler;
import me.seaOf.utils.DbUtils.BeanListHandler;
import me.seaOf.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public Order findOrderById(String oid) {
        String sql = "select * from orders where id = ?";
        try {
            return JDBCUtils.query(sql,new BeanHandler<Order>(Order.class),oid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteOrderById(String oid) {
        String sql = "delete from orders where id=?";
        try {
            JDBCUtils.update(sql, oid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderItemById(String oid) {
        String sql = "delete from orderitem where order_id=?";
        try {
            JDBCUtils.update(sql, oid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePayStateByOid(String r6_order, int state) {
        String sql = "update orders set paystate=? where id=?";
        try {
            JDBCUtils.update(sql,state,r6_order);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SaleInfo> findSaleInfos() {
        String sql = "SELECT pd.id prod_id,pd.name prod_name,SUM(oi.buynum) sale_num\n" +
                "FROM products pd,orderitem oi,orders od\n" +
                "WHERE pd.id = oi.product_id\n" +
                "AND oi.order_id=od.id\n" +
                "GROUP BY prod_id\n" +
                "ORDER BY sale_num\n";
        try {
            return JDBCUtils.query(sql,new BeanListHandler<SaleInfo>(SaleInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<SaleInfo>();
        }
    }

}

