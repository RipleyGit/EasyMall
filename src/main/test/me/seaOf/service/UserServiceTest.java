package me.seaOf.service;

import junit.framework.TestCase;
import me.seaOf.factory.BasicFactory;
import org.junit.Test;

public class UserServiceTest extends TestCase {
    @Test
    public void testService() throws Exception {
        UserService service = BasicFactory.getFactory().getInstance(
                UserService.class);
        boolean result = service.hasUser("admin");
        System.out.println(result);
    }

}