package me.seaOf.factory;

import java.io.FileInputStream;
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

    /*
     * 根据conf.properties配置文件中的配置信息 创建UserDao的实例 UserDao=cn.tedu.dao.UserDaoImpl
     */
    public <T> T getInstance(Class<T> clazz) {
        try {
            // 1.读取配置文件中配置的信息(UserDao的实现类)
            // cn.tedu.dao.UserDaoImpl
            String className = prop.getProperty(clazz.getSimpleName());

            // 2.根据类的全限定名称获得该类的Class对象
            Class clz = Class.forName(className);

            // 3.利用反射技术根据该类Class对象创建该类的实例
            Object obj = clz.newInstance();
            return (T)obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
