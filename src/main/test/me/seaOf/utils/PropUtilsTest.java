package me.seaOf.utils;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropUtilsTest extends TestCase {
    @Test
    public void testProp(){
        String keyValue = PropUtils.getPropery("keyValue");
        System.out.println(keyValue);
    }
}