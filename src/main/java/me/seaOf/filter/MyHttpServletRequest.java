package me.seaOf.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.Map;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
    private boolean isEncode = true;
    /* isEncode 是否进行手动编解码的标识,
      默认值是true, 表示还没有手动编解码 */
    private HttpServletRequest request;
    public MyHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
    @Override
    public String getParameter(String name) {
        return this.getParameterValues(name) == null? null : this.getParameterValues(name)[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return this.getParameterMap().get(name);
    }
    @Override
    public Map<String, String[]> getParameterMap() {
		/*
		该方法可以返回所有请求参数组成的map集合, 此时map中
		保存的数据是乱码(get提交), 我们可以遍历map中的每一个
		参数值, 手动编解码处理完后将正确的数据再次存回map中,
		最后返回一个没有乱码的map集合
		 */
        if("POST".equalsIgnoreCase(request.getMethod())){//是POST提交
            return request.getParameterMap();
        }else if("GET".equalsIgnoreCase(request.getMethod())){
            //遍历map, 挨个取出乱码, 手动编解码再次存回map并返回map
            Map<String, String[]> map = request.getParameterMap();
            if(isEncode){
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    //取出每一个键值对, 由于值是数组, 还需要遍历
                    String[] vs = entry.getValue();
                    for (int i = 0; i < vs.length; i++) {
                        try {
                            vs[i] = new String(vs[i].getBytes("iso8859-1"), "utf-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
                }
                isEncode = false;
            }

            return map;
        }else{//其他5种提交方式
            return request.getParameterMap();
        }
        //return request.getParameterMap();
    }
}
/*
 * 被装饰者: request对象 --> 所属的类: A
 * 被装饰者所属的类实现的接口: HttpServletRequest
 *
 * 装饰类: MyHttpServletRequest  要实现HttpServletRequest接口
 * 在这里让MyHttpServletRequest继承一个装饰类, 我们这个类也是一个
 * 装饰类, 只需要覆盖需要改造的父类中方法即可!!
 */
/*
 * 1.写一个装饰类, 装饰类要和被装饰者所属的类实现相同的接口
 * 	或者继承相同的父类
 * 2.装饰类要提供构造方法接受被装饰者并保存在类的内部
 * 3.对于想要改造的方法直接进行改造, 对于不想改造的方法, 调用
 * 	原有对象上的方法
 */


