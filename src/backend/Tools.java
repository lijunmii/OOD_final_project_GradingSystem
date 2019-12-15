package backend;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Tools {
    public static DecimalFormat df = new DecimalFormat("0.0");

    public static boolean isNumeric(String str) {
        String testStr;
        try {
            testStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}