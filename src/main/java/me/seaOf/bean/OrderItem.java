package me.seaOf.bean;

import lombok.Data;

@Data
public class OrderItem {
    private String order_id;//--订单编号
    private String product_id;//--商品编号
    private int buynum;//--商品购买数量
}
