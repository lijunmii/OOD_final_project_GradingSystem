package backend;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

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

    public static Double mean(List<Double> list) {
        Double sum = 0.0;
        int size = list.size();
        for (Double e : list) {
            sum += e;
        }
        return sum / size;
    }

    public static Double median(List<Double> list) {
        Collections.sort(list);
        int size = list.size();
        if (size == 0) {
            return null;
        } else if (size % 2 == 0) {
            return (list.get(size / 2) + list.get(size / 2 - 1)) / 2;
        } else {
            return list.get(size / 2);
        }
    }

    public static Double standardDeviation(Double[] x) {
        int m = x.length;
        Double sum = 0.0;
        for (int i = 0; i < m; i++) {
            sum += x[i];
        }
        Double dAve = sum / m;
        Double dVar = 0.0;
        for (int i = 0; i < m; i++) {
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return Math.sqrt(dVar / m);
    }
}