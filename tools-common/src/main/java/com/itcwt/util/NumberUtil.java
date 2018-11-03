package com.itcwt.util;

import java.math.BigDecimal;

/**
 * @author cwt
 * @create by cwt on 2018-08-02 17:28
 */
public class NumberUtil {
    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static Number add(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return b1.add(b2);
    }

    /**
     * 提供精确的加法运算。
     *
     * @param values 被加数
     * @return 多个参数的和
     */
    public static Number add(Number... values) {
        BigDecimal base = new BigDecimal(0);
        for (Number value : values){
            base.add(new BigDecimal(value.toString()));
        }
        return base;
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static Number sub(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return b1.subtract(b2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static Number mul(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return b1.multiply(b2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param values 乘数
     * @return 多个参数的积
     */
    public static Number mul(Number... values) {
        BigDecimal base = new BigDecimal(1);
        for (Number value : values){
            base.multiply(new BigDecimal(value.toString()));
        }
        return base;
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static Number div(Number dividend, Number divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(dividend.toString());
        BigDecimal b2 = new BigDecimal(divisor.toString());
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后2位，进行四舍五入。
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return 两个参数的商
     */
    public static Number div(Number dividend, Number divisor) {
        BigDecimal b1 = new BigDecimal(dividend.toString());
        BigDecimal b2 = new BigDecimal(divisor.toString());
        return div(b1, b2, 2);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static Number round(Number value, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(value.toString());
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * string to double
     * @param value 需要转换的值
     * @param defaultValue 转换失败时返回此默认值
     * @return
     */
    public static Double parseStringToDouble(String value, Double defaultValue){
        Double parseValue = null;
        try {
            parseValue = Double.parseDouble(value);
        }catch (Exception e){
            parseValue = defaultValue;
        }
        return parseValue;
    }

    /**
     * string to integer
     * @param value 需要转换的值
     * @param defaultValue 转换失败时返回此默认值
     * @return
     */
    public static Integer parseStringToInteger(String value, Integer defaultValue){
        Integer parseValue = null;
        try {
            parseValue = Integer.parseInt(value);
        }catch (Exception e){
            parseValue = defaultValue;
        }
        return parseValue;
    }

    /**
     * string to float
     * @param value 需要转换的值
     * @param defaultValue 转换失败时返回此默认值
     * @return
     */
    public static Float parseStringToFloat(String value, Float defaultValue){
        Float parseValue = null;
        try {
            parseValue = Float.parseFloat(value);
        }catch (Exception e){
            parseValue = defaultValue;
        }
        return parseValue;
    }

}
