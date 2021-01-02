package cn.huat.util;

/**
 * Description
 *
 * @author helaxest
 * @date 2020/12/03  8:09
 * @mail:
 * @since JDK 1.8
 */
public class StateUtil {
    public static String getStateValue(Integer send){
        String state="草拟中";
        if(send==2){
            state="审核中";
        }
        if(send==3){
            state="复核中";
        }
        if(send==4){
            state="签发中";
        }
        if(send==5){
            state="分发";
        }
        if(send==6){
            state="分发完毕";
        }
        return state;
    }
}
