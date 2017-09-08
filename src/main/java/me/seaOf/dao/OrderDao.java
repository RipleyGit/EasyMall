package me.seaOf.dao;

import me.seaOf.bean.Order;
import me.seaOf.bean.OrderItem;

import java.util.List;

public interface OrderDao {
    /**
     * 添加订单信息(orders表)
     * @param order 订单信息
     */
    void addOrder(Order order);

    /**
     * 添加订单项信息(orderitem)
     * @param orderItem
     */
    void addOrderItem(OrderItem orderItem);

    /**
     * 根据用户id查询当前用户的所有订单
     * @param userId 用户id
     * @return	List<Order>
     */
    List<Order> findOrderByUserId(int userId);

    /**
     * 根据订单id查询所有的订单项信息
     * @param id
     * @return
     */
    List<OrderItem> findOrderItemByOrderId(String id);
}