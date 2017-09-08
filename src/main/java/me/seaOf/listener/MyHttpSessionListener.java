package me.seaOf.listener;


import me.seaOf.bean.Product;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

/**
 * 在session对象创建之后立即创建carmap集合 存入session中
 */
public class MyHttpSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("cartmap",
                new HashMap<Product, Integer>());
    }
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
