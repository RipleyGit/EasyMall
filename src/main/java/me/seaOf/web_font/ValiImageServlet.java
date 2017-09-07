package me.seaOf.web_font;

import me.seaOf.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 负责生成验证码图片并响应给浏览器
 */
@WebServlet(name = "ValiImageServlet",urlPatterns = {"/servlet/ValiImageServlet"})
public class ValiImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制浏览器不要缓存验证图片
        response.setDateHeader("Expries",-1);
        response.setHeader("Cache-Control","no-cache");
        VerifyCode vc = new VerifyCode();
        //将图片保存到response缓冲区中, 再响应给浏览器
        vc.drawImage(response.getOutputStream());

        //获取图片上的验证码
        String code = vc.getCode();
        //将验证码文本保存到session中, 用于后期的校验
        request.getSession().setAttribute("code", code);
    }
}
