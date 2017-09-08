package me.seaOf.web_font;

import me.seaOf.bean.User;
import me.seaOf.exception.MsgException;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.UserService;
import me.seaOf.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet",urlPatterns = {"/servlet/RegistServlet"})
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.校验验证码
        String valistr = request.getParameter("valistr");
        if (WebUtils.isNull(valistr)) {
            // 将提示消息存入request域中,通过转发将消息带到regist.jsp进行提示
            request.setAttribute("msg", "验证码不能为空!");
            request.getRequestDispatcher("/regist.jsp").forward(request,
                    response);
            return;
        }
        String code = (String) request.getSession().getAttribute("code");
        if (!valistr.equalsIgnoreCase(code)) {
            request.setAttribute("msg", "验证码不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,
                    response);
            return;
        }

        // 2.获取请求参数, 并将数据封装到JavaBean中
        User user = new User();
		/* 千万不要导错包!!! */
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            // 3.调用JavaBean中的checkData方法校验数据
            user.checkData();

            //对password md5加密后再存入数据库
            user.setPassword(WebUtils.md5(user.getPassword()));

            // 4.实现注册(将用户信息保存进数据库)
            UserService service = BasicFactory.getFactory().getInstance(
                    UserService.class);
            service.registUser(user);
        } catch (MsgException e) {
			/* 获取抛出异常信息, 存入request域, 并转发regist.jsp */
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,
                    response);
            return;
        }

        // 5.提示用户注册成功, 3秒之后跳转到首页!
        response.getWriter()
                .write("<h1 style='color:red;text-align:center'>恭喜您注册成功, 3秒之后将会跳转到首页...</h1>");
        response.setHeader("refresh", "3;url=" + request.getContextPath()
                + "/index.jsp");
    }
}
