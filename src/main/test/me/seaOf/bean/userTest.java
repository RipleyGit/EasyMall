package me.seaOf.bean;

import junit.framework.TestCase;
import org.junit.Test;


public class userTest extends TestCase {

    @Test
    public void testUser(){
        User user = new User();
        user.setId(1);
        user.setNickname("hello");
        user.setUsername("world");
        user.setEmail("1233");
        System.out.println(user.getId()+";"+user.getNickname()+";"+user.getUsername()+";"+user.getEmail());
    }

}