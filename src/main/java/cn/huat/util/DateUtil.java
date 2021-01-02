package cn.huat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description 将日期格式化为字符串
 *
 * @author helaxest
 * @date 2020/12/02  21:36
 * @mail:
 * @since JDK 1.8
 */
public class DateUtil {
    public static String getSDF(Date date) {
        String strDateFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String s = sdf.format(date);
        return s;
    }
    public static String getSDFM(Date date) {
        String strDateFormat = "yyMMddss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String s = sdf.format(date);
        return s;
    }
}
