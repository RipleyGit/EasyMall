package me.seaOf.utils;

import junit.framework.TestCase;
import me.seaOf.bean.SaleInfo;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PoiUtilsTest extends TestCase {
    @Test
    public void testUtils(){
        List<SaleInfo> list = new ArrayList<SaleInfo>();
        SaleInfo sale = new SaleInfo();
        sale.setProd_id("123");
        sale.setProd_name("456");
        sale.setSale_num(123);
        list.add(sale);
    }
}