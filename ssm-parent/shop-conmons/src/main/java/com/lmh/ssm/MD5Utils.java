package com.lmh.ssm;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
    //干扰数据 盐 防破解
    private static final String SALT = "1231~!@##%$RT!@#@/.%#$@";
    //散列算法类型为MD5
    private static final String algorithmName = "md5";
    //hash的次数
    private static final int HASH_ITERATIONS = 1024;

    public static String encrypt(String pswd) {


        /**
         *
         *shiro里面的一个类
         *
         * 第一个形参是散列算法类型：MD5
         * 第二个形参：要加密的字符串
         * 第三个形参：盐值
         *  在新增用户的时候  对密码进行加密存储 uuid随机生成一个字符串当做盐值  把盐值存储到数据库。
         *
         *  第四个参数：加密多少次
         */
        String newPassword = new SimpleHash(algorithmName, pswd, ByteSource.Util.bytes(SALT),HASH_ITERATIONS).toString();
        return newPassword;
    }
    public static void main(String[] args) {
        System.out.println("加密后String"+MD5Utils.encrypt("aini564788"));
    }
}
