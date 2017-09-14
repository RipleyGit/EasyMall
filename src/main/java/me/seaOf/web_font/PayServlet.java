package me.seaOf.web_font;

import me.seaOf.bean.Order;
import me.seaOf.exception.MsgException;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.OrderService;
import me.seaOf.utils.PaymentUtil;
import me.seaOf.utils.PropUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PayServlet",urlPatterns = {"/servlet/PayServlet"})
public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收订单id
        String oid = request.getParameter("orderid");
        //准备第三方支付平台准备需要的参数
        String p0_Cmd = "Buy";
        String p1_MerId = PropUtils.getPropery("p1_MerId");
        String p2_Order = oid;
        //测试时使用
        String p3_Amt = "0.01";//订单金额
        //正式发布时使用
        OrderService os = BasicFactory.getFactory().getInstance(OrderService.class);
        Order order = os.findOrderById(oid);

        //String p3_Amt = ""+order.getMoney();

        String p4_Cur = "CNY";//指定支付为人民币
        String p5_Pid = "";//商品名称
        String p6_Pcat="";//商品种类
        String  p7_Pdesc="";//商品描述
        String p8_Url= PropUtils.getPropery("responseURL");//商品接受支付成功数据的地址
        String p9_SAF = "";//送货地址
        String pa_MP = "";//商户扩展信息
        String pd_FrpId = request.getParameter("pd_FrpId");//支付通道编码
        String pr_NeedResponse = "1";//应答机制
        String hmac = PaymentUtil.buildHmac( p0_Cmd, p1_MerId,
                 p2_Order,  p3_Amt,  p4_Cur, p5_Pid,  p6_Pcat,
                 p7_Pdesc, p8_Url,  p9_SAF, pa_MP, pd_FrpId,
                 pr_NeedResponse, PropUtils.getPropery("keyValue")) ;//签名数据
        request.setAttribute("pd_FrpId", pd_FrpId);
        request.setAttribute("p0_Cmd", p0_Cmd);
        request.setAttribute("p1_MerId", p1_MerId);
        request.setAttribute("p2_Order", p2_Order);
        request.setAttribute("p3_Amt", p3_Amt);
        request.setAttribute("p4_Cur", p4_Cur);
        request.setAttribute("p5_Pid", p5_Pid);
        request.setAttribute("p6_Pcat", p6_Pcat);
        request.setAttribute("p7_Pdesc", p7_Pdesc);
        request.setAttribute("p8_Url", p8_Url);
        request.setAttribute("p9_SAF", p9_SAF);
        request.setAttribute("pa_MP", pa_MP);
        request.setAttribute("pr_NeedResponse", pr_NeedResponse);
        request.setAttribute("hmac", hmac);
        //转发请求
        request.getRequestDispatcher("/confirm.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
