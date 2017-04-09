package com.lu.shiro.spring;

import com.lu.shiro.configure.ApplicationConfigure;
import com.lu.shiro.mapper.UserMapper;
import com.lu.shiro.pojo.User;
import com.lu.shiro.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigure.class)
public class AbstractSpringTest {

    @Autowired
    private UserMapper userMapper;

    public Subject login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = TokenUtil.createTokenByUsernameAndPassword(username, password);
        subject.login(authenticationToken);
        return subject;
    }

}
