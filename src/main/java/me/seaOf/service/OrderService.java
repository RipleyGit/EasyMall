package me.seaOf.service;

import me.seaOf.anno.Tran;
import me.seaOf.bean.Order;
import me.seaOf.bean.OrderInfo;
import me.seaOf.bean.OrderItem;
import me.seaOf.bean.SaleInfo;
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

    /**
     * 根据订单id查询订单
     * @param oid
     * @return
     */
    Order findOrderById(String oid);

    /**
     * 根据订单号删除订单
     * @param oid 订单id
     * @return
     */
    @Tran
    void deleteOrderById(String oid) throws  MsgException;

    /**
     * 根据订单id查找
     * @param oid
     * @return
     */
    Order findOrderByOid(String oid);

    /**
     *根据订单号更新订单的支付状态
     * @param r6_order
     * @param i
     */
    void updatePayStateByOid(String r6_order, int state);

    /**
     * 查询所有销售榜单列表
     * @return  销售榜单列表集合
     */
    List<SaleInfo> finSaleInfos();
}
