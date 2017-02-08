import org.home.IpAddresses;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Halim on 08.02.2017.
 */
public class IpAddressesTest {
    List<Integer> list;

    @Before
    public void set() {
        list = new ArrayList<Integer>();
    }
    @Test
    public void test() {
        Assert.fail("fail");
    }

    @Test
    public void testCheckAddress() {
        // boolean isCorrect = IpAddresses.checkAddress("192.3.3.4", list);
        // boolean isCorrect = IpAddresses.checkAddress("19sdf2.3.3.4", new ArrayList<Integer>());
        boolean isCorrect = IpAddresses.checkAddress(".3.3.4", new ArrayList<Integer>());
        Assert.assertTrue("", isCorrect);
    }

    @Test
    public void testDotsCount() {
        int count = IpAddresses.countDots("192.3/4..3.4");
        Assert.assertEquals(3, count);
    }

    @Test
    public void testCheckBit() {
        boolean isNumber = IpAddresses.checkBit("3e");
        Assert.assertTrue("", isNumber);
    }

}
