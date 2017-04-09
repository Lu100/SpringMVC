package com.lu.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2017/4/3.
 */
public class MultiRealm1 implements Realm {

    public String getName() {
        return "MultiRealm1";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码
        if (!"root".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!"root".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    @PostConstruct
    public void appendToSecurityManager() {
        SecurityManager securityManager = SecurityUtils.getSecurityManager();
    }

//    private UsernamePasswordToken cast(AuthenticationToken token) {
//        return (UsernamePasswordToken) token;
//    }
}
