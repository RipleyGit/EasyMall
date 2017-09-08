package me.seaOf.filter;

import me.seaOf.bean.User;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.UserService;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class AutoLoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 自动登陆的必要条件
		 * 1.用户必须是为登陆状态
		 * 2.用户必须选择啦30天自动登陆，即cookie中包含啦自动登陆cookie
		 * 3.自动登陆cookie中的用户名和密码必须要正确
		 * 4.无论是否自动登陆哦都必须要放行过滤器
		 */
		
		//1.判断用户是否自动登陆
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("user") == null){
			//表明用户为登陆状态
			//2.判断cookie中是否包含自动登陆的cookie
			Cookie[] cs = req.getCookies();
			if( cs != null){
				for (Cookie c : cs) {
					if("autologin".equals(c.getName())){
						//3.判断自动登陆cookie中的用户名与密码是或否正确
						String value = c.getValue();//"admin:123"
						String username = value.split(":")[0];
						//对用户名进行url解析
						username = URLDecoder.decode(username,"utf-8");
						String password = value.split(":")[1];
						UserService service = BasicFactory.getFactory().getInstance(UserService.class);
						User user = service.loginUser(username, password);
						if(user != null){//用户名和密码正确
							//帮用户做登陆操作
							req.getSession().setAttribute("user", user);
						}
					}
					
				}
			}
		}
		//4.无论是否自动登陆，都要放行过滤器执行后面的操作
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
