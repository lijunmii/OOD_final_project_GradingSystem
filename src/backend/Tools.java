package backend;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Tools {
    public static DecimalFormat df_3_1 = new DecimalFormat("000.0");
    public static DecimalFormat df_X_1 = new DecimalFormat("0.0");

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