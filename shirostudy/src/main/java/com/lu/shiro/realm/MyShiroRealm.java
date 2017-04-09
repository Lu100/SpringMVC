package com.lu.shiro.realm;

import com.lu.shiro.mapper.UserMapper;
import com.lu.shiro.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyShiroRealm implements Realm {

    private final UserMapper userMapper;

    @Autowired
    public MyShiroRealm(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String getName() {
        return "MyShiroRealm";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken cast = cast(token);
        boolean exist = userMapper.checkUserExist(cast.getUsername());
        if (!exist) {
            throw new UnknownAccountException(String.format("[%s]用户不存在", cast.getUsername()));
        }
        User user = userMapper.selectByNameAndPassword(cast.getUsername(), String.valueOf(cast.getPassword()));
        if (user == null) {
            throw new IncorrectCredentialsException(String.format("[%s]密码错误", cast.getUsername()));
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }

    private UsernamePasswordToken cast(AuthenticationToken token) {
        return (UsernamePasswordToken) token;
    }
}
