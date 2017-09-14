package me.seaOf.utils;

import me.seaOf.factory.BasicFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class PropUtils {
    private static Properties prop = new Properties();

    static {
        // 读取配置文件中配置的信息
        try {
            String confPath = PropUtils.class.getClassLoader()
                    .getResource("merchantInfo.properties").getPath();
            prop.load(new FileInputStream(confPath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private PropUtils(){

    }
    public static String getPropery(String str){
        return prop.getProperty(str);
    }
}
