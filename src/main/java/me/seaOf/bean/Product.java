package me.seaOf.bean;

import lombok.Data;
import me.seaOf.anno.Column;

import java.io.Serializable;
@Data
public class Product implements Serializable {
    @Column("商品ID")
    private String id;
    @Column("商品名称")
    private String name;
    @Column("商品价格")
    private double price;
    @Column("商品分类")
    private String category;
    @Column("图片路径")
    private String imgurl;
    @Column("库存数量")
    private int	pnum;
    @Column("商品描述")
    private String description;

    /* 重写hashcode方法 */
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
    /* 重写equals方法 */
    @Override
    public boolean equals(Object obj) {
        //1.如果obj为null, 直接返回false
        if(obj == null){
            return false;
        }
        //2.如果obj == this, 直接返回true
        if(obj == this){
            return true;
        }

        //3.obj指向的对象的类型不是Product类型
        if(!(obj instanceof Product)){
            return false;
        }

        //4.比较两个对象的id值是否相等
        Product other = (Product) obj;
        if(id != null && id.equals(other.getId())){
            return true;
        }
        return false;
    }
}
