package com.kna.appsec.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * The Class StringUtil.
 */
public final class StringUtils {

    public static final int LOGIN_CAPCHAR_MAX = 5;
    public static final String TEMPLATE_XNB = "XNB";
    public static final String TEMPLATE_VAT = "VAT";
    public static final String PRODUCT_INFOR_SUBTRACT_Z = "Z";
    public static final String APOLLO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DISPLAY_DATE = "HH:mm dd/MM/yyyy";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9\\._-]{1,64}@[A-Za-z0-9]{2,64}(\\.[A-Za-z0-9]{2,64})*(\\.[A-Za-z]{2,4})$";
    private static final String[] htmlChar = new String[]{"&", "<", ">", "'", "\""};
    private static final String[] htmlNameCode = new String[]{"&amp;", "&lt;", "&gt;", "&apos;", "&quot;"};
    /**
     * The Constants charset.
     */
    private static final String charset = "!0123456789abcdefghijklmnopqrstuvwxyz";
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean validateEmail(String emailAdress) {
        if (StringUtils.isNullOrEmpty(emailAdress))
            return false;
        return emailAdress.trim().matches(EMAIL_REGEX);
    }

    /**
     * Checks if is null or empty.
     *
     * @param s the s
     * @return true, if is null or empty
     */
    public static boolean isNullOrEmpty(final String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * Kiem tra chuoi input khac null & khac empty
     *
     * @param s
     * @return
     * @author suongltt1
     * @since 11/04/2016
     */
    public static boolean isNotNullAndNotEmpty(final String s) {
        return (s != null && s.trim().length() > 0);
    }

    /**
     * Checks if is null or empty.
     *
     * @param s the s
     * @return true, if is null or empty
     */
    public static boolean isNullOrEmptyNotTrim(final String s) {
        return (s == null || s.length() == 0);
    }

    /**
     * From null to emtpy string.
     *
     * @param a the a
     * @return the string
     */
    public static String fromNullToEmtpyString(String a) {
        if (a == null) {
            return "";
        } else {
            return a;
        }
    }

    public static String priceWithDecimal(Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal(Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public static String priceToString(Double price) {
        String toShow = priceWithoutDecimal(price);
        if (toShow.indexOf(".") > 0) {
            return priceWithDecimal(price);
        } else {
            return priceWithoutDecimal(price);
        }
    }

    public static Date stringToDate(String input, String format) throws Exception {
        try {
            String expectedPattern = "".equals(format) ? "dd/MM/yyyy" : format;
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

            return formatter.parse(input);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String dateToString(Date input, String format) {
        try {
            String expectedPattern = "".equals(format) ? "dd/MM/yyyy" : format;
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

            return formatter.format(input);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String displayDate(String strDate) {
        Date date = null;
        try {
            date = stringToDate(strDate, APOLLO_DATE_FORMAT);

            String ret = dateToString(date, DISPLAY_DATE);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String convertStringToHTMLCode(String strSource) {
        if (isNullOrEmpty(strSource)) {
            return "";
        }
        for (int i = 0; i < htmlChar.length; i++) {
            strSource = strSource.replaceAll(htmlChar[i], htmlNameCode[i]);
        }
        return strSource;
    }

    public static String replaceSeparatorChar(String fileName) {
        return fileName.replace('/', File.separatorChar);
    }

    public static String convertHTMLNewLine(String str) {
        String result;
        String regex = "(\r\n|\n)";
        String replacement = "&newline;";
        result = str.replaceAll(regex, replacement);
        return result;
    }

    public static String convertHTMLBreakLine(String str) {
        String result;
        String regex = "(\r\n|\n)";
        String replacement = "<br />";
        result = str.replaceAll(regex, replacement);
        return result;
    }

    public static String toStringNumberMMYYY(String str) {
        String strMM = str.split("/")[0];
        String strYYYY = str.split("/")[1];
        strMM = Integer.valueOf(strMM) < 10 ? "0" + strMM : strMM;
        return strMM + "/" + strYYYY;
    }

    /**
     * Convert chart
     *
     * @param money
     * @return 10000 -> 10,000
     * @author hunglm16
     * @since 03/09/2015
     */
    public static String convertMoney(BigDecimal money) {
        if (money == null) {
            return "0";
        }
        String result = "";
        String _money = money.toBigInteger() + "";
        int isDot = 1;
        for (int i = _money.length(); i > 0; i--) {
            char ch = _money.charAt(i - 1);
            if (isDot == 3 && i != 1) {
                if (_money.charAt(i - 2) == '-') {
                    result = ch + result;
                    isDot = 0;
                } else {
                    result = "," + ch + result;
                    isDot = 0;
                }
            } else {
                result = ch + result;
            }
            isDot++;
        }
        return result;
    }

    /**
     * Sinh chuoi ma hoa password (code mobile server)
     *
     * @author : BangHN
     * @params : mat khau. Salt: ten dang nhap da lower.
     * @since : 1.0
     */
    // Xu ly ma hoa SHA-256
    public static String generateHash(String input, String salt) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        // SHA or MD5
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String hash = "";
        if (null == salt || "".equals(salt)) {
            salt = "";
        }
        if (isNullOrEmpty(input)) {
            input = "";
        }

        input += salt;
        byte[] data = input.getBytes("US-ASCII");

        md.update(data);
        byte[] digest = md.digest();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(digest[i]);
            if (hex.length() == 1)
                hex = "0" + hex;
            hex = hex.substring(hex.length() - 2);
            hash += hex;
        }
        return hash;
    }

    /**
     * Gets the random string.
     *
     * @param length the length
     * @return the random string
     */
    public static String getRandomString(int length) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }

    /**
     * FLOAT lam tron so thap phan
     *
     * @param decimalPlace, value
     * @return float
     * @author hunglm16
     * @description Truyen vao mot bien Float roi lam tron thap phan theo tham so decimalPlace
     */
    public static Float precisionFloat(int decimalPlace, Float value) {
        ///Vi du
        //value = 29.294998;  decimalPlace = 2
        //return 29.30
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * BigDecimal Lam tron so thap phan
     *
     * @param decimalPlace, value
     * @return float
     * @author hunglm16
     * @description Truyen vao mot bien BigDecimal roi lam tron thap phan theo tham so decimalPlace
     */
    public static BigDecimal precisionBigDecimal(int decimalPlace, BigDecimal value) {
        /// Vi du
        // value= 10.57125; decimalPlace = 0
        //return 11
        return value.setScale(decimalPlace, RoundingMode.HALF_UP);
    }

    public static boolean isFloat(String number) {
        try {
            return !new Float(number).isNaN();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumberInt(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    /**
     * @param formatImage
     * @return Lay cac dinh dang duoi file support trong app-setting.xml; vd: Configuration.getVideoUploadFileSupport();
     */
    public static String getFormatSupport(String formatImage) {
        String value = formatImage;
        int begin = -1;
        int end = -1;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            String gt = Character.toString(value.charAt(i));
            if ("/".equals(gt)) {
                begin = i;
            }
            if ("|".equals(gt)) {
                end = i;
            }
            if (begin != -1 && end != -1) {
                String view = value.substring(begin + 1, end);
                str.append(" .").append(view.trim()).append(";");
                begin = -1;
                end = -1;
            }
        }
        if (begin != -1 && end == -1) {
            String view = value.substring(begin + 1, value.length());
            str.append(" .").append(view.trim());
        }
//		System.out.println("Ho tro upload images app-setting.xml:"+ str);
        return str.toString();
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static String doubleToString(Double number, String format) {
        if (number == null) return "";
        return String.format(format, number);
    }

}
