package com.tsystems.jschool.railage.view;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testIsEmployee() throws Exception {

    }

    @Test
    public void testExtractTrainId() throws Exception {
        String queryString = "?trainId=1";
        Integer result  = Utils.extractTrainId(queryString);
        Assert.assertEquals(1,result.intValue());
    }
}