package me.seaOf.web_font;

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

@WebServlet(name = "CallbackServlet",urlPatterns = {"/servlet/CallbackServlet"})
public class CallbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获得回调所有数据
        String p1_MerId = request.getParameter("p1_MerId");//商户在易宝支付系统的唯一身份标识.
        String r0_Cmd = request.getParameter("r0_Cmd");//固定值"Buy"
        String r1_Code = request.getParameter("r1_Code");//固定值“1”表示支付成功
        String r2_TrxId = request.getParameter("r2_TrxId");//易宝支付平台产生的交易流水号 每笔唯一
        String r3_Amt = request.getParameter("r3_Amt");//金额 精确到分
        String r4_Cur = request.getParameter("r4_Cur");//返回的交易币种
        String r5_Pid = request.getParameter("r5_Pid");//商品名称
        String r6_Order = request.getParameter("r6_Order");//商品号
        String r7_Uid = request.getParameter("r7_Uid");//商户的订单号
        String r8_MP = request.getParameter("r8_MP");//如果用户使用的易宝支付会员进行支付则返回该用户的易宝支付会员ID;反之为””.
        String r9_BType = request.getParameter("r9_BType");//交易结果返回类型，点对点通信：为“1”: 浏览器重定向;为“2”: 服务器点对点通讯.
        String rb_BankId = request.getParameter("rb_BankId");//返回用户所使用的支付通道编码.该返回参数不参与到hmac校验，范例中没有收录，可根据您的需要自行添加
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");//银行订单号
        String rp_PayDate = request.getParameter("rp_PayDate");//支付成功时间
        String rq_CardNo = request.getParameter("rq_CardNo");//神州行充值卡序列号
        String ru_Trxtime = request.getParameter("ru_Trxtime");//交易结果通知时间
        // 身份校验 --- 判断是不是支付公司通知你
        String hmac = request.getParameter("hmac");
        String keyValue = PropUtils.getPropery("keyValue");

        // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
        boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, PropUtils.getPropery("keyValue"));
        if (isValid) {
            // 响应数据有效
            if ("1".equals(r9_BType)) {
                // 浏览器重定向
                response.getWriter().write("<h1>付款成功！等待商城进一步操作！等待收货...</h1>");
                //正式发布前删除以下两条代码
                OrderService os = BasicFactory.getFactory().getInstance(OrderService.class);
                os.updatePayStateByOid(r6_Order,1);
            } else if ("2".equals(r9_BType)) {
                // 服务器点对点 --- 支付公司通知你
                // 修改订单状态 为已付款
                if ("1".equals(r1_Code)) {
                    OrderService os = BasicFactory.getFactory().getInstance(OrderService.class);
                    os.updatePayStateByOid(r6_Order,1);
                    // 回复支付公司
                    response.getWriter().write("success");
                }
            }
        } else {
            // 数据无效
            System.out.println("数据被篡改！");
        }
    }
}
