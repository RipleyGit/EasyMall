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
import java.util.List;

@WebServlet(name = "ProdListBySearchServlet",urlPatterns = {"/servlet/ProdListBySearchServlet"})
public class ProdListBySearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.获取搜索条件
        String search = request.getParameter("search");
        search = search == null ? "" : search;

        // 2.调用service层的方法, 根据条件查询指定的商品
        ProdService service = BasicFactory.getFactory().getInstance(
                ProdService.class);
        List<Product> list = service.findAllBySearch(search);

        // 3.将所有商品的集合存入request域, 并转发带给prod_list.jsp
        request.setAttribute("list", list);
        request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
