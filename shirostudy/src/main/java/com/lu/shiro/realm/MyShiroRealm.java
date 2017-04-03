package com.lu.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2017/4/3.
 */
public class MyShiroRealm implements Realm {
    public String getName() {
        return "MyShiroRealm";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken cast = cast(token);
        
        return null;
    }

    private UsernamePasswordToken cast(AuthenticationToken token) {
        return (UsernamePasswordToken) token;
    }
}
