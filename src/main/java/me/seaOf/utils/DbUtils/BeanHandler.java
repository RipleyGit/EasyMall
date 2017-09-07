package me.seaOf.utils.DbUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;

public class BeanHandler<T> implements ResultSetHandler<T> {
    private Class clazz;

    public BeanHandler(Class clazz){
        /**
         * 接受T类型的class对象，获得该类型的属性和方法
         */
        this.clazz = clazz;
    }

    /**
     * 将查询结果集中的第一行记录封装成一个bean对象
     * @param rs  数据库查询结果集
     * @return T 查询类型的封装bean
     * @throws Exception
     */
    @Override
    public T handle(ResultSet rs) throws Exception {
        if(rs.next()){
            //通过该类型的class对象，创建该类型的实例
            T t = (T) clazz.newInstance();
            //剖析得出该类型中包含哪些公共的方法和属性
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            //根据beanInfo类获取该数据类型中的属性描述器数组
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            //遍历属性描述器数组，获取每一组属性描述器，再活树包含的属性及相关get,set方法
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                Method method = pd.getWriteMethod();
                try {
                    method.invoke(t,rs.getObject(name));
                } catch (Exception e) {
                    continue;
                }
            }
            return t;
        }
        return null;
    }
}
