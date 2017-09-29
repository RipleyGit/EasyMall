package me.seaOf.service;

import me.seaOf.bean.*;
import me.seaOf.dao.OrderDao;
import me.seaOf.dao.ProdDao;
import me.seaOf.exception.MsgException;
import me.seaOf.factory.BasicFactory;
import me.seaOf.utils.JDBCUtils;
import me.seaOf.utils.TranManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao order_dao = BasicFactory.getFactory().getInstance(OrderDao.class);
    private ProdDao prod_dao = BasicFactory.getFactory().getInstance(ProdDao.class);

    public void addOrder(Order order, List<OrderItem> list) throws MsgException {
        //向order表中添加一条记录
        order_dao.addOrder(order);
        //遍历list
        for (OrderItem orderItem : list) {
            //根据商品的id查询商品的信息，重载一个添加的方法
            Product prod = prod_dao.findProdById(orderItem.getProduct_id());
            //判断库存是否不足
            if (prod.getPnum()<orderItem.getBuynum()){
                throw new MsgException("商品库存不足："+prod.getId()+","+prod.getName()+","+prod.getPnum());
            }
            //库存充足，修改商品库存
            prod_dao.updatePnum(prod.getId(), (prod.getPnum()-orderItem.getBuynum()));
            //向orderitem表中添加一条数据
            order_dao.addOrderItem(orderItem);
        }
    }



    public List<OrderInfo> findOrderByUserId(int userId) {
        //1.通过Userid查询当前用户的所有订单信息
        List<Order> orderList = order_dao.findOrderByUserId(userId);

        if(orderList == null ){
            return null;
        }

        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        for (Order order : orderList) {
            //2.遍历每一个订单, 通过订单id查询当前订单中包含的所有订单项信息
            List<OrderItem> orderItemList = order_dao
                    .findOrderItemByOrderId(order.getId());

            //3.遍历每一个订单项, 通过订单项获取商品信息及商品的购买数量
            Map<Product, Integer> map = new HashMap<Product, Integer>();
            for(OrderItem orderItem : orderItemList){
                //3.1.获取商品id, 通过商品id查询商品信息, 返回Product对象
                Product prod = prod_dao.findProdById(orderItem.getProduct_id());
                //3.2.获取购买数量
                int buyNum = orderItem.getBuynum();
                //3.3.将商品信息和购买数量存入map中
                map.put(prod, buyNum);
            }

            //4.将订单信息和所有的订单项信息存入OrderInfo中
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrder(order);
            orderInfo.setMap(map);

            //5.将一个完整的订单信息存入List集合中
            orderInfoList.add(orderInfo);
        }
        return orderInfoList;
    }



    public void deleteOrderById(String oid) throws MsgException {
        //根据订单id查询订单详情
        Order order = order_dao.findOrderById(oid);
        //判断是否存在，不存在抛出异常
        if (order == null){
            throw new MsgException("当前订单不存在，已删除！");
        }
        //判断是否支付，已支付抛出异常
        if(order.getPaystate() != 0){
            throw new MsgException("只有未支付的订单才能删除！");
        }
        //根据订单id查询该订单所有的订单条目
        List<OrderItem> orderItem = order_dao.findOrderItemByOrderId(oid);
        if (orderItem != null) {
            for (OrderItem item : orderItem) {
                //遍历：修改对应商品的库存
                prod_dao.changePnum(item.getProduct_id(),item.getBuynum());
            }
        }
        //删除orderitem订单id所对应所有订单项
        order_dao.deleteOrderItemById(oid);
        //删除orders对应的一条订单数据
        order_dao.deleteOrderById(oid);
    }

    public Order findOrderByOid(String oid) {
        return order_dao.findOrderById(oid);
    }

    public void updatePayStateByOid(String r6_order, int state) {
        order_dao.updatePayStateByOid( r6_order, state);
    }

    public List<SaleInfo> finSaleInfos() {
        return order_dao.findSaleInfos();
    }


    public Order findOrderById(String oid) {
        return order_dao.findOrderById(oid);
    }
}
