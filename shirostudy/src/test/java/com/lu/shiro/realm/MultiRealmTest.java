package com.lu.shiro.realm;

import com.lu.shiro.AbstractShiroTest;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class MultiRealmTest extends AbstractShiroTest {

    @Override
    public String iniPath() {
        return "classpath:realm/MultiRealmConfig.ini";
    }

    @Test
    public void testLogin1() {
        Subject login = login("root", "root");
        PrincipalCollection principals = login.getPrincipals();
        //只有一个验证通过
        assert principals.asList().size() == 1;
        assert login.isAuthenticated();
    }

    @Test
    public void testLogin2() {
        Subject login = login("zhang", "123456");
        assert login.isAuthenticated();
    }

    @Test
    public void testLogin3() {
        Subject login = login("lu", "123456");
        assert login.isAuthenticated();
    }

    @Test(expected = AuthenticationException.class)
    public void loginFail() {
        Subject login = login("GG", "GG");
        assert !login.isAuthenticated();
    }

}
