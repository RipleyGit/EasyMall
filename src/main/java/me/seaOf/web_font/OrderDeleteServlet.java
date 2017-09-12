package me.seaOf.web_font;

import me.seaOf.exception.MsgException;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderDeleteServlet",urlPatterns = {"/servlet/OrderDeleteServlet"})
public class OrderDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收订单id
        String oid = req.getParameter("id");
        //调用订单查询的方法 创建业务层对象
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        //调用删除订单方法
        try {
            orderService.deleteOrderById(oid);
            //删除成功给出提示
            resp.getWriter().write("删除成功");
        } catch (MsgException e) {
            //删除失败给出提示
            resp.getWriter().write(e.getMessage());
        }
        //设置定时跳转
        resp.setHeader("refresh", "2;url="+
                request.getContextPath()+
                "/servlet/OrderListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
