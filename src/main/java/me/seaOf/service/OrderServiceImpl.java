package me.seaOf.service;

import com.sun.beans.util.Cache;
import me.seaOf.bean.Order;
import me.seaOf.bean.OrderInfo;
import me.seaOf.bean.OrderItem;
import me.seaOf.bean.Product;
import me.seaOf.dao.OrderDao;
import me.seaOf.dao.ProdDao;
import me.seaOf.exception.MsgException;
import me.seaOf.factory.BasicFactory;
import me.seaOf.utils.TranManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao order_dao = BasicFactory.getFactory().getInstance(OrderDao.class);
    private ProdDao prod_dao = BasicFactory.getFactory().getInstance(ProdDao.class);
    public void addOrder(Order order, List<OrderItem> list) throws MsgException {
        try{
            //开始事务
            TranManager.startTran();
            //调用dao层的方法添加订单信息
            order_dao.addOrder(order);
            for(OrderItem orderItem : list) {
                //检查购买数量(orderItem.buyNum)是否小于等于库存数量(Product.pnum)
                //获取购买数量
                int buyNum = orderItem.getBuynum();
                //获取库存数量
                //>>获取商品id
                String pid = orderItem.getProduct_id();
                //>>查询商品信息
                Product prod = prod_dao.findProdById(pid);
                int pnum = prod.getPnum();
                if (buyNum > pnum) {//如果购买数量大于库存数量
                    throw new MsgException("库存数量不足, 订单添加失败!");
                }
                //调用dao层的方法添加订单项信息
                order_dao.addOrderItem(orderItem);
                //将购买数量从库存数量中扣除
                prod_dao.updatePnum(pid, prod.getPnum() - buyNum);
            }
            //提交事务
            TranManager.commitTran();
        }catch(MsgException me){
                //回滚事务
                TranManager.rollbackTran();
                throw me;
        }finally{
                //关闭数据库连接
                TranManager.releseTran();
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

}
