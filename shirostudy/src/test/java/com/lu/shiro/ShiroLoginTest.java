package com.lu.shiro;

import com.lu.shiro.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class ShiroLoginTest {
    @Before
    public void prepare() {
        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:JDBCRealm.ini");
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);
    }

    @Test
    public void loginFalseTest() {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = TokenUtil.createTokenByUsernameAndPassword("root", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
        }
        assert !subject.isAuthenticated();
        subject.logout();
    }

    @Test
    public void loginSuccessTest() {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken zhang = TokenUtil.createTokenByUsernameAndPassword("zhang", "123456");
        subject.login(zhang);
        assert subject.isAuthenticated();
        subject.logout();
    }

}
