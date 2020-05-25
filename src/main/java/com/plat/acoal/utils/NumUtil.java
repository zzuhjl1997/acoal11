package com.plat.acoal.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class NumUtil {
    /**
     * 直接取小数点后两位，不进位
     *
     * @param d
     * @return
     */
    public static Double dianhou2(Double d) {
        if (!(d == 0.00)) {
            d = Double.valueOf(d.toString() + "001");
        }
        DecimalFormat df = new DecimalFormat("#.00");
        BigDecimal bg = new BigDecimal(d);
        Double j = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        String t = df.format(j);
        return Double.valueOf(t);
    }

    /**
     * 直接取小数点后N位，不进位
     *
     * @param d
     * @return
     */
    public static Double dianhoun(Double d, int n) {
        StringBuffer fm = new StringBuffer();
        fm.append("#.");
        for (int i = 0; i < n; i++) {
            fm.append("0");
        }
        DecimalFormat df = new DecimalFormat(fm.toString());
        BigDecimal bg = new BigDecimal(d);
        Double j = bg.setScale(n, BigDecimal.ROUND_DOWN).doubleValue();
        String t = df.format(j);
        return Double.valueOf(t);
    }
}

