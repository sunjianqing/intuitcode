package com.jianqing.intuit.test;

import com.jianqing.intuit.AndriodView;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jianqing_sun on 12/12/17.
 */
public class ViewTests {

    @Test
    public void testAndroid() {
        AndriodView view  = new AndriodView();
        String testStr = "hello intuit";
        String resp = view.display(testStr);
        String expected = "Android Response\n" +
                "hello intuit\n" +
                "----------------------";
        Assert.assertEquals("Android view response should be matched.", resp, expected );
    }

}
