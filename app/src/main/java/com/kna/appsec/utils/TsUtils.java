package com.kna.appsec.utils;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TsUtils {
    static DecimalFormat df;

    public static List safe(List other) {

        return other == null ? Collections.EMPTY_LIST : other;
    }

    public static boolean isNotNull(List other) {
        if (other != null && other.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isObjectNotNull(Object other) {
        if (other != null) {
            return true;
        }
        return false;
    }

    public static <T> T getData(Object src, Class<T> clazz, HashMap<String, Field> destFields) throws Exception {
        Field[] fields = src.getClass().getDeclaredFields();


        T dest = clazz.newInstance();
        Field fieldDest;
        if (destFields == null) { //== null nghia la chi dung cho noi tai trong ham
            destFields = new HashMap<>();
        }
        for (Field field : dest.getClass().getDeclaredFields()) {
            destFields.put(field.getName(), field);
        }

        for (Field field : fields) {
            int modifier = field.getModifiers();
            if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) {
                continue;
            }

            field.setAccessible(true);
            Object value = field.get(src);


            fieldDest = destFields.get(field.getName());
            if (fieldDest == null || fieldDest.getType() != field.getType()) {
                continue;
            }
            fieldDest.setAccessible(true);
            fieldDest.set(dest, value);


        }
        return dest;
    }

    public static double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }

    public static BigDecimal calculatePercentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct).divide(new BigDecimal(100));
    }

    public static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static DecimalFormat getFormatDecimal() {
        if (df == null) {
            df = new DecimalFormat("#.##");
        }
        df.setRoundingMode(RoundingMode.CEILING);
        return df;
    }


}
