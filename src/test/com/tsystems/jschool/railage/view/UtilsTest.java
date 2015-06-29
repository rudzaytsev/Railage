package com.tsystems.jschool.railage.view;

import junit.framework.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void testIsEmployee() throws Exception {

    }

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
}