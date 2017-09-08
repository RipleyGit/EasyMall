package me.seaOf.bean;

import lombok.Data;
import java.util.Map;

@Data
public class OrderInfo {
    private Order order;//订单信息
    private Map<Product, Integer> map;//该订单中的所有订单项信息
}
