package me.seaOf.bean;

import lombok.Data;
import me.seaOf.anno.Column;

@Data
public class SaleInfo {
    @Column("商品ID")
    private String prod_id;
    @Column("商品名称")
    private String prod_name;
    @Column("商品数量")
    private int sale_num;
}
