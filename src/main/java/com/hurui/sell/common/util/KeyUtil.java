package com.hurui.sell.common.util;

import java.util.Random;

/**
 * @title 主键生成工具类
 * @author hurui
 */
public class KeyUtil {

    /**
     * @title 生成唯一主键
     * 格式：时间加随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return  System.currentTimeMillis() + String.valueOf(number);
    }
}

