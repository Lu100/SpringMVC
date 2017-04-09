package com.lu.shiro.realm;

import com.lu.shiro.AbstractShiroTest;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class SimpleRealmTest extends AbstractShiroTest {

    @Override
    public String iniPath() {
        return "classpath:realm/JDBCConfig.ini";
    }

    /**
     * 会出现验证失败的异常
     */
    @Test(expected = IncorrectCredentialsException.class)
    public void loginFalseTest() {
        Subject subject = login("root", "123456");
        assert !subject.isAuthenticated();
        subject.logout();
    }


    @Test
    public void loginSuccessTest() {
        Subject subject = login("zhang", "123456");
        assert subject.isAuthenticated();
        subject.logout();
    }

    public void test() {

    }

}
