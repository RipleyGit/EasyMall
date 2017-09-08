package me.seaOf.web_font;

import me.seaOf.bean.Product;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 修改购物车中商品的购买数量
 */
@WebServlet(name = "AjaxUpdateBuyNumServlet",urlPatterns = {"/servlet/AjaxUpdateBuyNumServlet"})
public class AjaxUpdateBuyNumServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取商品id和购买数量
        String pid = request.getParameter("pid");
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));

        //2.调用service层的方法查询指定id的商品
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        Product prod = service.findProdById(pid);

        //3.将cartmap中该商品的购买数量修改为buyNum
        Map<Product, Integer> map = (Map<Product, Integer>)
                request.getSession().getAttribute("cartmap");
        map.put(prod, buyNum);

        //4.做出响应
        response.getWriter().write("修改成功!");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
