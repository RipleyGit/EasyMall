package me.seaOf.web_back;

import me.seaOf.bean.SaleInfo;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.OrderService;
import me.seaOf.utils.PoiUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DownSaleInfoServlet",urlPatterns = {"/servlet/DownSaleInfoServlet"})
public class DownSaleInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用业务层的方法从数据库中查询销售榜单
        OrderService orderService= BasicFactory.getFactory().
                getInstance(OrderService.class);
        List<SaleInfo> list = orderService.finSaleInfos();

        //String fname = request.getParameter("fname");
        String fname = "workbook.xlsx";
        //处理乱码问题
        fname = new String(fname.getBytes("iso8859-1"),"utf-8");
        //告知浏览器以附件下载的方式打开
        response.setContentType("text/html;charset=gbk");
        response.setHeader("Content-Disposition","attachment;filename="+fname);
        OutputStream out = response.getOutputStream();
        Workbook wb = PoiUtils.getWorkbook(list,SaleInfo.class);
        wb.write(out);
        out.close();
    }
}
