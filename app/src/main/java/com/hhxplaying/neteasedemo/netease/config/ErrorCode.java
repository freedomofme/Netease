package com.hhxplaying.neteasedemo.netease.config;

import java.util.HashMap;

public class ErrorCode {
    public static final String TAG = "ErrorCode";
    public static HashMap<Integer, String> errorCodeMap = new HashMap<>();
    static {
        errorCodeMap.put(0,  "服务器无响应");
        errorCodeMap.put(-7, "网络超时，请检查您的网络");
        errorCodeMap.put(-1, "网络错误，请检查您的网络");
        errorCodeMap.put(-2, "服务器错误");
        errorCodeMap.put(-3, "非法参数");
        errorCodeMap.put(-8, "非法解析错误");
        errorCodeMap.put(-4, "解析异常");
        errorCodeMap.put(-5, "解析数字异常");
        errorCodeMap.put(-6, "授权异常");
    }
}