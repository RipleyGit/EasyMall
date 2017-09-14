package me.seaOf.web_back;

import me.seaOf.bean.SaleInfo;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SaleListServlet",urlPatterns = {"/servlet/SaleListServlet"})
public class SaleListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建业务层对象
        OrderService os = BasicFactory.getFactory().getInstance(OrderService.class);

        //调用业务层方法查询全部销售榜单
        List<SaleInfo> list = os.finSaleInfos();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/backend/sale_list.jsp").forward(request,response);
    }
}
