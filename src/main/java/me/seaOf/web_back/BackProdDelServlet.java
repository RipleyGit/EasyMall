package me.seaOf.web_back;

import me.seaOf.factory.BasicFactory;
import me.seaOf.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 处理删除商品请求
 */
@WebServlet(name = "BackProdDelServlet",urlPatterns = {"/servlet/BackProdDelServlet"})
public class BackProdDelServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取商品的id
        String pid = request.getParameter("pid");

        //2.调用service层的方法根据商品id删除指定的商品
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        boolean result = service.delProd(pid);

        //3.做出响应
        response.getWriter().write(result+"");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
