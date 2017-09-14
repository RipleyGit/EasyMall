package me.seaOf.bean;

import lombok.Data;
import me.seaOf.anno.Column;

import java.util.Date;

@Data
public class Order {
    @Column("订单编号")
    private String id;		//--订单编号
    @Column("订单金额")
    private double money;	//--订单金额
    @Column("收货信息")
    private String receiverinfo;//--收货信息
    @Column("支付状态")
    private int paystate;	//--支付状态, 0表未支付 1已支付
    @Column("下单时间")
    private Date ordertime;	//--下单时间
    @Column("用户id")
    private int user_id;	//--用户id(所属人id)
}
