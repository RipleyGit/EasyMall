package me.seaOf.factory;

import me.seaOf.anno.Tran;
import me.seaOf.dao.Dao;
import me.seaOf.service.Service;
import me.seaOf.utils.TranManager;

import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * 通用的工厂类
 */
public class BasicFactory {
    private static BasicFactory factory = new BasicFactory();
    private static Properties prop = new Properties();

    public static BasicFactory getFactory() {
        return factory;
    }

    private BasicFactory() {
    }

    static {
        // 读取配置文件中配置的信息
        try {
            String confPath = BasicFactory.class.getClassLoader()
                    .getResource("conf.properties").getPath();
            prop.load(new FileInputStream(confPath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 创建各种实例
     */
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        try {
            String className = prop.getProperty(clazz.getSimpleName());
            Class clz = Class.forName(className);
            //判断是service层还是dao层，还是其他层
            if (Service.class.isAssignableFrom(clz)) {
                //创建委托类对象
                final T t = (T) clz.newInstance();
                //创建代理对象
                T proxy = (T) Proxy.newProxyInstance(
                        clz.getClassLoader(),
                        clz.getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                //定义返回值对象
                                Object result = null;
                                //判断方法是否使用事务的注解
                                if (method.isAnnotationPresent(Tran.class)) {
                                    //使用了需要添加事务的注解
                                    try {
                                        //开启事务
                                        TranManager.startTran();
                                        //执行委托类对象的方法
                                        result =  method.invoke(t,args);
                                        //提交事务
                                        TranManager.commitTran();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                        //回滚事务
                                        TranManager.rollbackTran();
                                        throw e.getCause();
                                    }finally {
                                        //释放事务
                                        TranManager.releseTran();
                                    }
                                }else{
                                    //没有使用 关闭数据库连接
                                    try {
                                        result = method.invoke(t,args);
                                    }catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                        throw e.getCause();
                                    } finally {
                                        TranManager.releseTran();
                                    }
                                }
                                return result;
                            }
                        });
                return  proxy;
            }else if(Dao.class.isAssignableFrom(clz)){
                return (T)clz.newInstance();
            }else{
                //既不是业务层也不是dao层
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw  new  RuntimeException(e);
        }
    }

    /*
     * 根据conf.properties配置文件中的配置信息 创建UserDao的实例 UserDao=cn.tedu.dao.UserDaoImpl
     */
//    public <T> T getInstance(Class<T> clazz) {
//        try {
//            // 1.读取配置文件中配置的信息(UserDao的实现类)
//            // cn.tedu.dao.UserDaoImpl
//            String className = prop.getProperty(clazz.getSimpleName());
//
//            // 2.根据类的全限定名称获得该类的Class对象
//            Class clz = Class.forName(className);
//
//            // 3.利用反射技术根据该类Class对象创建该类的实例
//            Object obj = clz.newInstance();
//            return (T)obj;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//    }

}
