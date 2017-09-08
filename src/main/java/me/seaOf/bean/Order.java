package me.seaOf.bean;

import lombok.Data;
import java.util.Date;

@Data
public class Order {
    private String id;		//--订单编号
    private double money;	//--订单金额
    private String receiverinfo;//--收货信息
    private int paystate;	//--支付状态, 0表未支付 1已支付
    private Date ordertime;	//--下单时间
    private int user_id;	//--用户id(所属人id)
}
