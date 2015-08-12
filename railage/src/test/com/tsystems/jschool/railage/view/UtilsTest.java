package com.tsystems.jschool.railage.view;

import junit.framework.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsTest {


    @Test
    public void testExtractId() throws Exception {
        String uri = "/trains/1";
        Integer result  = Utils.extractId(uri);
        Assert.assertEquals(1,result.intValue());

        String uri2 = "/trains/id/111";
        Integer result2  = Utils.extractId(uri2);
        Assert.assertEquals(111,result2.intValue());

        String uri3 = "/stations/235";
        Integer result3  = Utils.extractId(uri3);
        Assert.assertEquals(235,result3.intValue());
    }

    @Test
    public void testExtractParamFromQueryStr() throws Exception {

        String queryString = "?par1=1&par2=aa&par3=23";
        String result = Utils.extractParamFromQueryStr(queryString,"par2");
        Assert.assertEquals("aa",result);
        result = Utils.extractParamFromQueryStr(queryString,"par3");
        Assert.assertEquals("23",result);
    }

    @Test
    public void testExtractNumberParam() throws Exception {

        String queryString = "?param1=1&param2=aa&param3=23";
        Integer result = Utils.extractNumberParam(queryString,"param3");
        Assert.assertEquals(23,result.intValue());
        result = Utils.extractNumberParam(queryString,"param2");
        Assert.assertNull(result);
    }

    @Test
    public void testDateTest() throws ParseException {

        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = dformat.parse("2015-01-01 10:30:00");

        java.sql.Date sqlDate = java.sql.Date.valueOf("2015-01-01");
        java.sql.Time sqlTime = java.sql.Time.valueOf("10:30:00");

        String sqlTimeStamp =  sqlDate.toString() + " " + sqlTime.toString();
        Date sqlDateVal = dformat.parse(sqlTimeStamp);

        Date d2 = dformat.parse("2015-01-01 10:20:00");

        Assert.assertEquals(d.getTime(),sqlDateVal.getTime());
        Assert.assertEquals(d2.getTime(),sqlDateVal.getTime() - 600000l);
    }
}