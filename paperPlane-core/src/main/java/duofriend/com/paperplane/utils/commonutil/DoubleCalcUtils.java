package duofriend.com.paperplane.utils.commonutil;

import java.math.BigDecimal;

/**
 * Description:
 * Created by jack-lin on 2017/12/5 0005.
 * Buddha bless, never BUG!
 */

public class DoubleCalcUtils {
    /**
     * double型的减法运算
     *
     * @param scaleLength 保留位数
     * @param d1
     * @param d2
     * @return
     */
    public static double subtract(int scaleLength, double d1, double d2) {

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));

        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        return bd1.subtract(bd2).setScale(scaleLength, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * double型的乘法运算
     *
     * @param scaleLength 保留位数
     * @param d1
     * @param d2
     * @return
     */
    public static double multiply(int scaleLength, double d1, double d2) {

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));

        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        return bd1.multiply(bd2).setScale(scaleLength, BigDecimal.ROUND_HALF_UP).doubleValue();
//       return bd1.multiply(bd2).doubleValue();
    }

    /**
     * double型的乘法运算
     *
     * @param scaleLength 保留位数
     * @param d1
     * @param d2
     * @return
     */
    public static double multiply(int scaleLength,int roundingMode,  double d1, double d2) {

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));

        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        return bd1.multiply(bd2).setScale(scaleLength, roundingMode).doubleValue();

    }

    /**
     * double型的加法运算
     *
     * @param scaleLength 保留位数
     * @param d1
     * @param d2
     * @return
     */
    public static double add(int scaleLength, double d1, double d2) {

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));

        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        return bd1.add(bd2).setScale(scaleLength, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * double型的除法运算
     *
     * @param scaleLength 保留位数
     * @param d1
     * @param d2
     * @return
     */
    public static double divide(int scaleLength, double d1, double d2) {

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));

        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        return bd1.divide(bd2,scaleLength,BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    /**
     * double型的除法运算
     *
     * @param scaleLength 保留位数
     * @param d1
     * @param d2
     * @return
     */
    public static double divide(int scaleLength,int roundingMode,  double d1, double d2) {

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));

        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        return bd1.divide(bd2,scaleLength,roundingMode).doubleValue();

    }

    public static double keepDecimalPoint(int scaleLength, double d1) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        return bd1.setScale(scaleLength, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static String doubleTrans(double d){
        if(Math.round(d)-d==0){
            return String.valueOf((long)d);
        }
        return String.valueOf(d);
    }
}
