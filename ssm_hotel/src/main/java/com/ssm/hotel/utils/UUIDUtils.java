package com.ssm.hotel.utils;

import java.util.UUID;

/**
 * @author zystart
 * @create 2022-11-23 21:31
 */
public class UUIDUtils {
    public static String randomUUID(){
       return UUID.randomUUID().toString().replace("-","");
    }
}
