package me.seaOf.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 处理全站乱码的过滤器
 *	1.处理请求参数乱码(GET和POST)
 *  2.处理响应正文乱码
 */
public class EncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*
     * 处理所拦截到的请求的核心方法
     * (相当于Servlet中的service方法)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("EncodingFilter.doFilter()....");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

		/* POST:
		 * 在过滤器中处理请求参数乱码(只需要写一次,
		 *   所有被拦截到的资源的乱码问题都可以解决)
		 */
        request.setCharacterEncoding("utf-8");//针对POST提交

		/*
		 * 在过滤器中处理响应正文乱码(只需要写一次,
		 *   所有被拦截到的资源的乱码问题都可以解决)
		 */
        response.setContentType("text/html;charset=utf-8");

		/*
		 * FilterChain: 过滤器链
		 */
        HttpServletRequest myRequest = new MyHttpServletRequest(req);

        //处理完后一定要放行过滤器, 才可以接着访问下面的资源
        chain.doFilter(myRequest, response);
    }

    /* 在Filter实例销毁之前立即执行
     * 进行善后的处理
     */
    public void destroy() {

    }
}
