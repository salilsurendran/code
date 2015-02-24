package com.salil.chessproblems.util;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by salilsurendran on 2/21/2015.
 */
public class UtilityTest {

    @Test
    public void testCheckInputs(){
        Assert.assertNotNull(Utility.checkInput(new String[]{"8", "1", "1"}));
        int[] arr = Utility.checkInput(new String[]{"800", "2", "8"});
        Assert.assertNotNull(arr);
        Assert.assertEquals(3, arr.length);
        Assert.assertEquals(800,arr[0]);
        Assert.assertEquals(2,arr[1]);
        Assert.assertEquals(8,arr[2]);
        Assert.assertNotNull(Utility.checkInput(new String[]{"10", "4", "2"}));
        Assert.assertNotNull(Utility.checkInput(new String[]{"8", "8", "4"}));
        Assert.assertNull(Utility.checkInput(new String[]{"-8", "1", "1"}));
        Assert.assertNull(Utility.checkInput(new String[]{"0", "2", "8"}));
        Assert.assertNull(Utility.checkInput(new String[]{"1A", "4", "2"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "-1", "4"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "A", "1"}));
        Assert.assertNull(Utility.checkInput(new String[]{"800", "?", "8"}));
        Assert.assertNull(Utility.checkInput(new String[]{"1", "1", "a"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "2", "10"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "20", "1"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "2", "9"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "2", "0"}));
        Assert.assertNull(Utility.checkInput(new String[]{"8", "2", "0", "3"}));
        Assert.assertNull(Utility.checkInput(null));
    }
}
