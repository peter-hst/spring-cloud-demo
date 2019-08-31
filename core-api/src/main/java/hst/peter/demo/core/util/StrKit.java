package hst.peter.demo.core.util;

import java.util.UUID;

/**
 * @author peter.huang
 * @date 2019/8/31 15:47
 */
public abstract class StrKit {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
