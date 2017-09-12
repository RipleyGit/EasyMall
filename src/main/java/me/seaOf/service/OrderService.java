package me.seaOf.service;

import me.seaOf.anno.Tran;
import me.seaOf.bean.Order;
import me.seaOf.bean.OrderInfo;
import me.seaOf.bean.OrderItem;
import me.seaOf.exception.MsgException;

import java.util.List;

public interface OrderService extends Service {
    /**
     * 增加订单
     * @param order 订单信息
     * @param list	订单项信息
     * @throws MsgException
     */
    @Tran
    void addOrder(Order order, List<OrderItem> list) throws MsgException;

    /**
     * 根据用户id查询所有的订单信息
     * @param id
     * @return
     */
    List<OrderInfo> findOrderByUserId(int id);

}
