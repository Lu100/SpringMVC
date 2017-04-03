package com.lu.shiro.utils;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Administrator on 2017/4/3.
 */
public class TokenUtil {
    public static AuthenticationToken createTokenByUsernameAndPassword(String username, String password) {
        return new UsernamePasswordToken(username, password);
    }

}

