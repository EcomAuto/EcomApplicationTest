package assertions;

import org.testng.Assert;

public class AssertUtils {

    public static void assertEquals(String actual, String expected, String message) {
        System.out.println("Asserting Equals: Actual=[" + actual + "], Expected=[" + expected + "]");
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertTrue(boolean condition, String message) {
        System.out.println("Asserting True: " + message);
        Assert.assertTrue(condition, message);
    }

    public static void fail(String message) {
        System.out.println("FAIL: " + message);
        Assert.fail(message);
    }
}
