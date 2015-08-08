package com.tsystems.jschool.railage;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by rudolph on 08.08.15.
 */
public class BCryptTest {

    @Test
    public void testBCrypt() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        //System.out.println(encoder.encode("admin"));
        //System.out.println(encoder.encode("client"));
        Assert.assertTrue(encoder.matches("batman", encoder.encode("batman")));
    }
}
