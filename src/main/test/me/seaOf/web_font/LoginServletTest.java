package me.seaOf.web_font;


import com.sun.org.apache.xpath.internal.SourceTree;
import junit.framework.TestCase;
import me.seaOf.bean.User;
import me.seaOf.factory.BasicFactory;
import me.seaOf.service.UserService;
import me.seaOf.utils.WebUtils;
import org.junit.Test;

public class LoginServletTest extends TestCase {
    @Test
    public void testLogin(){
        UserService service = BasicFactory.getFactory().getInstance(
                UserService.class);
        String username = "r456";
        String password = WebUtils.md5("456");
        User user = service.loginUser(username, password);
        System.out.println(user.toString());
    }

}